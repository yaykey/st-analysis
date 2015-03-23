//**********************rapheal柱形图插件************************
/**
作者：lilinbing(2013-12-17)
版本：1.0
说明：rapheal柱形图插件
参数设置：
width       容器的宽
height      容器的高
maxValue    目标值
minValue    底线值
maxTitle    目标值显示名称
minTitle    底线值显示名称
align       说明显示位置
unit        单位
color       柱状颜色
*/
//**********************rapheal图标联动插件*************************
(function($) {
	jQuery.fn.extend({
		barchart: function(dataArr,opts) {
			opts = jQuery.extend({
				width: 600,//画布宽度
				height:400,//画布高度
				title:null,//标题
				maxValue:null,//目标值
				minValue:null,//底线值
				maxTitle:null,//目标值显示名称
				minTitle:null,//底线值显示名称
				align:"bottom",//说明显示位置
				unit:"万",//单位
				color:["#446abe","#77d3fe","#d3fe77","#feffd4","#ecd4ff","#c89cfe","#c259ff","#f959ff","#fca1ff","#5283ef","#ffcff2"],
				callback: function() { return false; }
			}, opts || {});
			var _self = this;
			var _this = $(this);
			var nodes = [];//处理后的数据
			var cond = {
				zWidth:0,//柱形宽度
				zHeight:0,//柱形高度
				zTop:0,//柱形离顶部距离
				zLeft:0,//柱形左边距离
				zRight:0,//柱形右边距离
				zSpacing:5,//柱形间间距
				zEHeight:17,//柱形上面说明高度
				zMaxHeight:0,//柱形最高的高度
				redWidth:0,//红线宽度
				infoNum:0, //对比参数个数
				iHeight:21,//说明行间距
				iWidth:25,//说明列间距
				iHnum:0,//说明列数
				iWnum:0,//说明行数
				cLen:7, //字符宽度
				cMaxLen:0,//标题最宽宽度
				ziHeight:15,//说明与柱形图行间距
				titleHeight:70,//标题高度
				lWidth:150//目标值和底线值左边所占宽度
			};
			var canvas;
			/**
			 * 创建画布
			 */
			createCanvas = function () {
				canvas = new Raphael(_this.attr("id"),opts.width,opts.height);
			};
			/**
			 * 字符串长度，汉字占2个字符
			 */
			charLen = function (s){
				var l = 0;
				var a = s.split("");
				for (var i=0;i<a.length;i++) {
					if (a[i].charCodeAt(0)<299) {
						l++;
					} else {
						l+=2;
					}
				}
				return l;
			};
			/**
			 * 初始化数据
			 * @returns
			 */
			setSource = function(){
				if(dataArr==null){
					return ;
				}
				nodes = [];
				var i,j;
				var children = [];
				var maxvalue = 0;
				var maxlength = 0;
				var le = 0;
				for(i=0;i<dataArr.length;i++){
					children = [];
					if(maxvalue<dataArr[i].num)
						maxvalue = dataArr[i].num;
					nodes[i] = {
							id:"node_"+i,
							index:i,
							title:dataArr[i].title,//标题
							num:dataArr[i].num, //值
							isview:true//是否显示数字
					};
					if(dataArr[i].children!=null&&dataArr[i].children.length>0){
						for(j=0;j<dataArr[i].children.length;j++){
							children[j]={
								id:"node_"+i+"_"+j,
								index:j,
								title:dataArr[i].children[j].title,//标题
								num:dataArr[i].children[j].num, //值
								isview:true//是否显示数字
							};
							cond.infoNum +=1;
							le = charLen(dataArr[i].children[j].title);
						}
					}else{
						cond.infoNum +=1; 
						le = charLen(dataArr[i].title);
					}
					if(maxlength<le){
						maxlength = le;
					}
					if(children.length>0){
						nodes[i].children = children;
					}
				}
				if(opts.maxValue!=null&&opts.maxValue>maxvalue){
					cond.zMaxHeight = opts.maxValue;
				}else{
					cond.zMaxHeight = maxvalue;
				}
				cond.cMaxLen = maxlength*cond.cLen;
				calculation();
			};
			/**
			 * 计算参数值
			 */
			calculation = function(){
				if(opts.align=="bottom"||opts.align=="top"){
					cond.iHnum = Math.floor(opts.width/(cond.cMaxLen+cond.iWidth));//计算说明列个数
					cond.iWnum = Math.ceil(cond.infoNum/cond.iHnum);//计算说明行个数

					cond.zWidth = Math.round((opts.width-cond.lWidth-nodes.length*cond.zSpacing)/nodes.length);//柱形宽度
					
					if(opts.align=="top"){
						cond.zTop = cond.iWnum*cond.iHeight+cond.ziHeight+cond.zEHeight+4;//柱形位置
						if(opts.title!=null&&opts.title.length>0){
							cond.zTop += cond.titleHeight;
						}
						cond.zHeight = opts.height - cond.zTop;//柱形计算位置的高度
					}else{
						cond.zEHeight +=10;
						cond.zTop = cond.zEHeight+4;//柱形位置
						if(opts.title!=null&&opts.title.length>0){
							cond.zTop += cond.titleHeight;
						}
						cond.zHeight = opts.height - (cond.iWnum*cond.iHeight)-cond.ziHeight-cond.zTop;//柱形计算位置的高度
					}
				}else if(opts.align=="right"||opts.align=="left"){
					cond.zTop = cond.zEHeight+4;//柱形位置
					if(opts.title!=null&&opts.title.length>0){
						cond.zTop += cond.titleHeight;
					}
					cond.zTop += 10;
					var a = (opts.height-cond.zTop)/cond.zMaxHeight;//柱形的比例
					var b = opts.minValue*a;
					var c = opts.maxValue*a;
					cond.zHeight = opts.height-cond.zTop;//柱形计算位置的高度
					var h = cond.zHeight/2-cond.zEHeight;
					if(b>(h/2)||c<h){
						if(b<h){
							h = b;
						}
						cond.iWnum = Math.ceil(h/cond.iHeight);//计算说明行个数
						
						if(cond.infoNum>cond.iWnum){
							cond.iHnum = Math.ceil((cond.infoNum - cond.iWnum)/(Math.ceil(h/cond.iHeight)));//计算说明列个数
							//cond.iHnum +=1;
						}else{
							cond.iHnum = 1;
						}
						if((cond.cMaxLen+cond.iWidth+30)>cond.lWidth){
							cond.zWidth = (opts.width-(cond.iHnum*(cond.cMaxLen+cond.iWidth+30))-nodes.length*cond.zSpacing)/nodes.length;//柱形宽度
						}else
							cond.zWidth = (opts.width-cond.lWidth-nodes.length*cond.zSpacing)/nodes.length;//柱形宽度
					}else{
						h = 2*h-cond.zEHeight;
						cond.iWnum = 0;//计算说明行个数
						cond.iHnum = Math.ceil(cond.infoNum/(Math.ceil(h/cond.iHeight)));//计算说明列个数
						cond.zWidth = (opts.width-(cond.iHnum*(cond.cMaxLen+cond.iWidth+60))-cond.lWidth-nodes.length*cond.zSpacing)/nodes.length;//柱形宽度
					}
					
				}
			};
			
			eventNode = function (d) {
				d.mouseover(function () {
					var xx = this.data("tx");
					var yy = this.data("ty");
					var text = this.data("text")
					var le = charLen(text)*8;
					var rw = le;
					var rh=20;
					
					x = xx-le/2;y = yy;
					if(x<1)
						x = 1;
					var rnode = canvas.rect(x,y,rw,rh,3).attr({
		    			'fill': "#CCCCCC",
		    			'stroke':"#CCCCCC",
		    			'stroke-width':1
		    		});
					var x1 = x+1,y1=y+10;
					var tnode = canvas.text(x1,y1,text).attr({
		    			'font': '12px Verdana',
		    			'text-anchor': 'start',
		    			 'fill':"#000"
		    		});
					this.attr({'opacity':1});
					this.data("rnode",rnode);
					this.data("tnode",tnode);
				}).mouseout(function () {
					this.attr({'opacity':0.8});
					this.data("rnode").remove();
					this.data("tnode").remove();
				});
			};
			/**
			 * 创建柱形
			 */
			createBar = function(){
				createCanvas();//创建画布
				setSource();//初始化数据
				creatTitle();
				var a = (cond.zHeight*1.0)/cond.zMaxHeight;//柱形的比例
				var h = 0,n = 0,m = 0,t=0,w=0,l=0,x=0,y=0;
				var node;
				for(var i=0;i<nodes.length;i++){
					if(opts.align=="right"){
						x = opts.width-(i+1)*(cond.zWidth+cond.zSpacing);
					}else{
						x = (nodes.length-i-1)*(cond.zWidth+cond.zSpacing);
					}
					
					if(nodes[i].children!=null&&nodes[i].children.length>0){
						for(var j=0;j<nodes[i].children.length;j++){
							h = Math.round(a*nodes[i].children[j].num);//柱形高度
							m+=h;
							y = cond.zHeight-m+cond.zTop;
							node = canvas.rect(x,y,cond.zWidth,h,0).attr({
				    			'fill': opts.color[n],
				    			'stroke':opts.color[n],
				    			'stroke-width':1,
				    			'opacity':0.8
				    		});
							createInfo(nodes[i].children[j].title,opts.color[n],n);
							n++;
						}
					}else{
						h = Math.round(a*nodes[i].num);//柱形高度
						y = cond.zHeight-h+cond.zTop;
						if(h<1){
							h = 10;
						}
						node = canvas.rect(x,y,cond.zWidth,h,0).attr({
			    			'fill': opts.color[n],
			    			'stroke':opts.color[n],
			    			'stroke-width':1,
			    			'opacity':0.8
			    		});
						
						createInfo(nodes[i].title,opts.color[n],n);
						n++;
					}
					
					if(m<1){
						m = h;
					}
					t = nodes[i].num+opts.unit;
					w = charLen(t)*cond.cLen+10;
					l = Math.floor(cond.zWidth/2);//计算位置
					node.data('text', nodes[i].num+opts.unit);
					node.data('tx',x+l);
					node.data('ty',y-26);
					eventNode(node);
					
					m = 0;
				}
				
			};
			/**
			 * 创建标题
			 */
			creatTitle = function(){
				if(opts.title==null||opts.title.length<1)
					return ;
				var le = charLen(opts.title)*8;
				var x = Math.ceil((opts.width-le)/2);
				var y = 10;
				canvas.text(x,y,opts.title).attr({
	    			'font': '18px Verdana',
	    			'text-anchor': 'start',
	    			'font-family':"宋体",
	    			 fill:"#000"
	    		});
			};
			/**
			 * 创建说明
			 */
			createInfo = function(title,color,i){
				var x,y,rw=10,rh=10,x1,y1;
				if(opts.align=="bottom"){
					var a = Math.floor(i/cond.iWnum);
					var b = i%cond.iHnum;
					if(b>0)
						x = b*cond.cMaxLen+cond.iWidth+rw+2;
					else
						x = 0;
					y = cond.zHeight+cond.zEHeight+cond.ziHeight+4+a*cond.iHeight;
					if(opts.title!=null&&opts.title.length>0)
						y += cond.titleHeight;
					x1 = x+rw+5;
					y1 = y+3;
				}else if(opts.align=="top"){
					var a = Math.floor(i/cond.iHnum);
					var b = i%cond.iHnum;
					if(b>0)
						x = b*cond.cMaxLen+cond.iWidth+rw+2;
					else
						x = 0;
					y = a*cond.iHeight+2;
					if(opts.title!=null&&opts.title.length>0)
						y += cond.titleHeight;
					x1 = x+rw+5;
					y1 = y+3;
				}else if(opts.align=="left"){
					var n = Math.floor(cond.zHeight/(cond.iHeight+5));
					var h = cond.zHeight - (n*(cond.iHeight+5));
					if(cond.iWnum>0){
						if(i<cond.iWnum){
							x = nodes.length*(cond.zSpacing+cond.zWidth)+cond.iWidth;
							y = opts.height-((i+1)*cond.iHeight)+10;
						}else{
							x = nodes.length*(cond.zSpacing+cond.zWidth)+cond.iWidth + (Math.ceil((i-cond.iWnum+1)/n)*(cond.cMaxLen+cond.iWidth+rw+12));
							if(Math.ceil(cond.infoNum/cond.iWnum)==2){
								y = opts.height-((cond.infoNum-i+1)*cond.iHeight);
							}else{
								y = ((n-(i-cond.iWnum)-1)%n)*cond.iHeight+cond.zTop+h;
							}
						}

					}else{
						x = nodes.length*(cond.zSpacing+cond.zWidth)+cond.iWidth + (Math.ceil((i+1)/n)*(cond.cMaxLen+cond.iWidth+rw+12));
						y = (i%n)*cond.iHeight+cond.zTop+h;
						
					}
					x1 = x+rw+5;
					y1 = y+3;
				}else if(opts.align=="right"){
					var n = Math.floor(cond.zHeight/(cond.iHeight+5));
					var h = cond.zHeight - (n*(cond.iHeight+5));
					if(cond.iWnum>0){
						if(i<cond.iWnum){
							x = opts.width-(nodes.length*(cond.zSpacing+cond.zWidth)+cond.iWidth)-(cond.cMaxLen+cond.iWidth);
							y = opts.height-((cond.iWnum-i)*cond.iHeight);
						}else{
							x =opts.width-(nodes.length*(cond.zSpacing+cond.zWidth)+cond.iWidth + ((Math.ceil((i-cond.iWnum+1)/n)+1)*(cond.cMaxLen+cond.iWidth)));
							if(Math.ceil(cond.infoNum/cond.iWnum)==2){
								y = opts.height-((cond.infoNum-i+1)*cond.iHeight);
							}else{
								y = ((i-cond.iWnum)%n)*cond.iHeight+cond.zTop+h;
							}
						}
					}else{
						x =opts.width-(nodes.length*(cond.zSpacing+cond.zWidth)+cond.iWidth + ((Math.ceil((i-cond.iWnum+1)/n)+1)*(cond.cMaxLen+cond.iWidth+rw+12)));
						y = (i%n)*cond.iHeight+cond.zTop+h;
					}
					x1 = x+rw+5;
					y1 = y+3;
				}
				canvas.rect(x,y,rw,rh,0).attr({
	    			'fill': color,
	    			'stroke':color,
	    			'stroke-width':1,
	    			'opacity':0.8
	    		});
				canvas.text(x1,y1,title).attr({
	    			'font': '12px Verdana',
	    			'text-anchor': 'start',
	    			'font-family':"宋体",
	    			 'fill':"#000",
	    			 'opacity':0.8
	    		});
			};
			/**
			 * 创建目标值和底线值
			 */
			createLine = function(){
				if(opts.maxValue==null)
					return false;
				var zh = 20;//边框高度
				var a = cond.zHeight/cond.zMaxHeight;//计算百分比
				var w = cond.zWidth*nodes.length+cond.lWidth;//线的长度
				var r = 4;//圆角
				var s1 = "",s2="";
				if(opts.maxTitle!=null&&opts.maxTitle.length>0){
					s1 = opts.maxTitle;
				}else{
					s1 = "目标:"+opts.maxValue+""+opts.unit;
				}
				if(opts.minTitle!=null&&opts.minTitle.length>0){
					s2 = opts.minTitle;
				}else{
					s2 = "底线:"+opts.minValue+""+opts.unit;
				}
				w1 = charLen(s1)*cond.cLen+1;//计算目标宽度
				w2 = charLen(s2)*cond.cLen+1;//计算底线宽度
				if(w1>w2)
					w2 = w1;
				else
					w1 = w2;
				
				var x1=0,x11=0,x22=0,x2=0,y1=0,y2=0;
				
				if(opts.align=="right"){
					x11 = opts.width;
					x22 = opts.width - w;
					x1 = x11-w;
					x2 = x11-w;
				}else{
					x1 = w-w1;//计算目标位置
					x2 = w-w2;//计算底线位置
					x22 = w;
				}

				y1 = cond.zHeight-Math.round(a*opts.maxValue)+cond.zTop;//计算目标y轴
				y2 = cond.zHeight-Math.round(a*opts.minValue)+cond.zTop;//计算底线y轴

				var result = "M"+x11+","+y1+"L"+x22+","+y1;
	            canvas.path(result).attr({
	    				'stroke-width':1,
	    				'stroke':"#91f1f0",
	    				'stroke-opacity':1
	    		 });
	            canvas.rect(x1,y1-zh,w1,zh,r).attr({
	    			'fill': "#91f1f0",
	    			'stroke': "#91f1f0",
	    			'stroke-width':0,
	    			'opacity':1
	    		});
	    		canvas.text(x1+1,y1-12,s1).attr({
	    			'font': '12px Verdana',
	    			'text-anchor': 'start',
	    			'font-family':"宋体",
	    			'font-weight':600,
	    			 fill:"#000",
	    			 'opacity':0.5
	    		});
	            
	    		if(opts.minValue==null)
					return false;
	    		
				result = "M"+x11+","+y2+"L"+x22+","+y2;
	            canvas.path(result).attr({
	    				'stroke-width':1,
	    				'stroke':"#fff55a",
	    				'stroke-opacity':1
	    		 });
	            canvas.rect(x2,y2,w2,zh,r).attr({
	    			'fill': "#fff55a",
	    			'stroke': "#fff55a",
	    			'stroke-width':0,
	    			'opacity':1
	    		});
	    		canvas.text(x2+2,y2+8,s2).attr({
	    			'font': '12px Verdana',
	    			'text-anchor': 'start',
	    			'font-family':"宋体",
	    			'font-weight':600,
	    			 fill:"#000",
	    			 'opacity':0.5
	    		});
			};
			create = function(){
				createBar();
				createLine();
			};
			create();
		}
	});
})(jQuery);