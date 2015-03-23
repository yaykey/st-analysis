//**********************rapheal柱图插件************************
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
color       线颜色
*/
//**********************rapheal图标联动插件*************************
(function($) {
	jQuery.fn.extend({
		bartwochart: function(titleArr,dataArr,opts) {
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
				color:["#446abe","#5283ef","#77d3fe","#d3fe77","#feffd4","#ecd4ff","#c89cfe","#c259ff","#f959ff","#fca1ff","#ffcff2"],
				callback: function() { return false; }
			}, opts || {});
			var _self = this;
			var _this = $(this);
			var nodes_1 = [];//处理后向上显示数据
			var nodes_2 = [];//处理后向下显示数据
			var cond = {
				zWidth:0,//线图宽度
				zHeight:0,//线图高度
				zTop:0,//离顶部距离
				zEHeight:35,//线柱图说明高度
				zSpacing:10,//柱形间间距
				zMaxHeight:0,//线柱图上面最高的高度
				iHeight:25,//说明行间距
				iWidth:25,//说明列间距
				iHnum:0,//说明列数
				iWnum:0,//说明行数
				cLen:7, //字符宽度
				cMaxLen:0,//标题最宽宽度
				ziHeight:0,//说明与线图行间距
				titleHeight:70,//标题高度
				tHeight:4/7,
				zvalue:0,//减去值
				lWidth:0//目标值和底线值文字宽度
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
				nodes_1 = [];
				nodes_2 = [];
				var i,j;
				var maxvalue_1 = 0;
				var minvalue_1 = null;
				var maxlength = 0;
				var le = 0;
				for(i=0;i<dataArr.length;i++){
					if(dataArr[i].align=="top"){
						nodes_1[nodes_1.length] = {
								id:"node_"+i,
								index:i,
								title:dataArr[i].title,//标题
								numArr:dataArr[i].numArr //值
						};
						if(dataArr[i].numArr!=null&&dataArr[i].numArr.length>0){
							for(j=0;j<dataArr[i].numArr.length;j++){
								if(maxvalue_1<dataArr[i].numArr[j])
									maxvalue_1 = dataArr[i].numArr[j];
								
								if(minvalue_1==null||minvalue_1>dataArr[i].numArr[j])
									minvalue_1 = dataArr[i].numArr[j];
								
							}
						}
						if(opts.maxValue!=null&&opts.maxValue>maxvalue_1){
							cond.zMaxHeight = opts.maxValue;
						}else{
							cond.zMaxHeight = maxvalue_1;
						}
					}else{
						nodes_2[nodes_2.length] = {
								id:"node_"+i,
								index:i,
								num:dataArr[i].num,
								title:dataArr[i].title,//标题
								numArr:dataArr[i].numArr //值
						};
					}
					le = charLen(dataArr[i].title);
					if(maxlength<le){
						maxlength = le;
					}
				}
				cond.cMaxLen = maxlength*cond.cLen;
				cond.zvalue = minvalue_1*0.9;
				calculation();
			};
			/**
			 * 计算参数值
			 */
			calculation = function(){
				var w1 = 0,w2=0;
				if(opts.maxValue!=null){
					if(opts.maxTitle==null||opts.maxTitle.length<1){
						opts.maxTitle = "目标:"+opts.maxValue+""+opts.unit;
					}
					w1 = charLen(opts.maxTitle)*cond.cLen+1;//计算目标宽度
				}
				if(opts.minValue!=null){
					if(opts.minTitle==null||opts.minTitle.length<1){
						opts.minTitle = "底线:"+opts.minValue+""+opts.unit;
					}
					w2 = charLen(opts.minTitle)*cond.cLen+1;//计算底线宽度
				}

				if(w1<w2)
					cond.lWidth += w2;
				else
					cond.lWidth += w1;
//				cond.zWidth = Math.round((opts.width-cond.lWidth-2*cond.zSpacing)/titleArr.length);//间隔宽度
				
				cond.zWidth = Math.round((opts.width-cond.lWidth-2*cond.zSpacing)/31);//间隔宽度
				
				cond.zTop = 10;//线图位置
				if(opts.title!=null&&opts.title.length>0){
					cond.zTop += cond.titleHeight;
				}
				cond.zHeight = opts.height - cond.iHeight-cond.ziHeight-cond.zTop-cond.zEHeight;//线柱图高度
				
			};
			eventNode = function (d) {
				d.mouseover(function () {
					var xx = this.data("tx");
					var yy = this.data("ty");
					var text = this.data("text")
					var le = charLen(text)*8;
					var rw = le+5;
					var rh=20;
					
					x = xx-le/2;y = yy;
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
			 * 创建线柱图
			 */
			createLineBar = function(){
				createCanvas();//创建画布
				setSource();//初始化数据
				creatTitle();
				createKLine();
				
				var h = 0,n = 0,m = 0,w=0,l=0,x=0,y=0,i,j;
				var a = (cond.zHeight*cond.tHeight)/(((cond.zMaxHeight-cond.zvalue)*5)/4);//线图的比例
				var b = (cond.zHeight*(1-cond.tHeight))/110;//线图的比例
				if(nodes_1.length>nodes_2.length){
					l = nodes_1.length;
				}else{
					l = nodes_2.length;
				}
				w = (cond.zWidth - cond.zSpacing)/l;
				var result = "";
				for(i=0;i<nodes_1.length;i++){
					for(j=0;j<nodes_1[i].numArr.length;j++){
						h = Math.round(a*(nodes_1[i].numArr[j]-cond.zvalue));//柱形高度
						y = (cond.zHeight*cond.tHeight)-h+cond.zTop;
						x = cond.lWidth+cond.zSpacing+j*cond.zWidth+i*w;
						var node = canvas.rect(x,y,w,h,0).attr({
			    			'fill': opts.color[n],
			    			'stroke':opts.color[n],
			    			'stroke-width':1,
			    			'opacity':0.8
			    		});
						node.data('text', nodes_1[i].numArr[j]+""+opts.unit);
						node.data('tx',x);
						node.data('ty',y-25);
						eventNode(node);
					}
					createInfo(nodes_1[i].title,opts.color[n],n);
					n++;
					
				}
				for(i=0;i<nodes_2.length;i++){
					for(j=0;j<nodes_2[i].numArr.length;j++){
						h = Math.round(b*(nodes_2[i].numArr[j]));//柱形高度
						y = (cond.zHeight*cond.tHeight)+cond.zTop;
						x = cond.lWidth+cond.zSpacing+j*cond.zWidth+i*w;
						var node = canvas.rect(x,y,w,h,0).attr({
			    			'fill': opts.color[n],
			    			'stroke':opts.color[n],
			    			'stroke-width':1,
			    			'opacity':0.8
			    		});
						node.data('text', fixedNum(nodes_2[i].numArr[j])+"%");
						node.data('tx',x);
						node.data('ty',y+h+10);
						node.data();
						eventNode(node);
					}
					if(i==0)
						createBottomLine(b,nodes_2[i].title,nodes_2[i].num,"#ffa19d",i);
					else
						createBottomLine(b,nodes_2[i].title,nodes_2[i].num,"#87d3ff",i);
					createInfo(nodes_2[i].title,opts.color[n],n);
					n++;
				}
				creatLineInfo();
			};
			createBottomLine = function(b,title,num,color,i){
				// w = cond.zWidth*(titleArr.length-1)+cond.zSpacing+25;//线的长度
				
				var w = cond.zWidth*30+cond.zSpacing+25;//线的长度
				var x1=0,x11=0,x22=0,x2=0,y1=0,y2=0;

				x1 = cond.lWidth;//计算位置
				x2 = x1+w;//计算位置
				y1 = (cond.zHeight*cond.tHeight)+cond.zTop+Math.round(b*num);//计算y轴
				y2 = y1;//计算y轴

				var result = "M"+x1+","+y1+"L"+x2+","+y1;
	            canvas.path(result).attr({
	    				'stroke-width':1,
	    				'stroke':color,
	    				"stroke-dasharray":"- ",
	    				'stroke-opacity':0.8
	    		});
	    		canvas.text(5,y1+(i*3),title+num+"%").attr({
	    			'font': '12px Verdana',
	    			'text-anchor': 'start',
	    			'font-family':"宋体",
	    			 fill:"#000"
	    		});
			};
			createKLine = function(){
				var x1,x2,y1,y2,size=6;
				x1 = cond.lWidth;
				y1 = cond.zHeight+cond.zTop;
				x2 = x1;
				y2 = cond.zTop;
				var result = "M"+x1+","+y1+ "L"+x2+","+ y2;
				canvas.path(result).attr({
					'stroke-width':2,
					'stroke': "#b0c1ed",
					'stroke-opacity':0.8
				});
				createArrow(x1,y1,x2,y2,size);

				x2 = opts.width-10;
				result = "M"+x1+","+y1+ "L"+x2+","+ y1;
				canvas.path(result).attr({
					'stroke-width':2,
					'stroke': "#b0c1ed",
					'stroke-opacity':0.8
				});
				createArrow(x1,y1,x2,y1,size);
				y1 = cond.zHeight*cond.tHeight+cond.zTop;;
				result = "M"+x1+","+y1+ "L"+x2+","+ y1;
				canvas.path(result).attr({
					'stroke-width':2,
					'stroke': "#b0c1ed",
					'stroke-opacity':0.8
				});
			};
			createArrow = function(x1, y1, x2, y2, size){
				var angle = Raphael.angle(x1, y1, x2, y2);//得到两点之间的角度
				var a45 = Raphael.rad(angle - 30);//角度转换成弧度
		        var a45m = Raphael.rad(angle + 30);
		        var x2a = x2 + Math.cos(a45) * size;
		        var y2a = y2 + Math.sin(a45) * size;
		        var x2b = x2 + Math.cos(a45m) * size;
		        var y2b = y2 + Math.sin(a45m) * size;
		        
		        var result = "M"+ x2a+","+y2a+ "L"+x2+","+ y2+"S"+x2+","+ y2+","+x2b+","+y2b+"M"+x2b+","+y2b+"L"+x2a+","+y2a;
				
		        canvas.path(result).attr({
		        		'fill': "#7396ff",
						'stroke-width':1,
						'stroke': "#b0c1ed",
						'stroke-opacity':1
				 });
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
			 * 创建线图信息
			 */
			creatLineInfo = function(){
				if(titleArr==null||titleArr.length<1)
					return ;
				var x = cond.lWidth;;
				var y = cond.zHeight+cond.zTop+10;
				for(var i=0;i<titleArr.length;i++){
					if(i>0)
						x += cond.zWidth;
					canvas.text(x+5,y,titleArr[i]).attr({
		    			'font': '14px Verdana',
		    			'text-anchor': 'start',
		    			'font-family':"宋体",
		    			 fill:"#000"
		    		});
				}
			};
			/**
			 * 创建说明
			 */
			createInfo = function(title,color,i){
				var x,y,rw=10,rh=10,x1,y1;
				x = cond.lWidth+i*(cond.cMaxLen+cond.iWidth)-20;
					
				y = cond.zHeight+cond.zEHeight+cond.zTop;
				x1 = x+rw+5;
				y1 = y+2;
				canvas.rect(x,y,rw,rh,0).attr({
	    			'fill': color,
	    			'stroke':color,
	    			'stroke-width':1
	    		});
				canvas.text(x1,y1,title).attr({
	    			'font': '12px Verdana',
	    			'text-anchor': 'start',
	    			 'fill':"#000"
	    		});
			};
			/**
			 * 创建目标值和底线值
			 */
			createLine = function(){
				if(opts.maxValue==null)
					return false;
				var a = cond.zHeight*cond.tHeight/(((cond.zMaxHeight-cond.zvalue)*5)/4);//线图的比例
				
				//var w = cond.zWidth*(titleArr.length-1)+cond.zSpacing+25;//线的长度
				
				var w = cond.zWidth*30+cond.zSpacing+25;//线的长度
				var x1=0,x11=0,x22=0,x2=0,y1=0,y2=0;

				x1 = cond.lWidth;//计算目标位置
				x2 = x1+w;//计算目标位置
				y1 = cond.zHeight*cond.tHeight-Math.round(a*(opts.maxValue-cond.zvalue))+cond.zTop;//计算目标y轴
				y2 = y1;//计算目标y轴

				var result = "M"+x1+","+y1+"L"+x2+","+y1;
	            canvas.path(result).attr({
	    				'stroke-width':1,
	    				'stroke':"#ffa19d",
	    				"stroke-dasharray":"- ",
	    				'stroke-opacity':0.8
	    		});
	    		canvas.text(5,y1-6,opts.maxTitle).attr({
	    			'font': '12px Verdana',
	    			'text-anchor': 'start',
	    			'font-family':"宋体",
	    			 fill:"#000"
	    		});
	            
	    		if(opts.minValue==null)
					return false;
	    		
	    		y2 = cond.zHeight*cond.tHeight-Math.round(a*(opts.minValue-cond.zvalue))+cond.zTop;//计算目标y轴
				result = "M"+x1+","+y2+"L"+x2+","+y2;
	            canvas.path(result).attr({
	    				'stroke-width':1,
	    				'stroke':"#87d3ff",
	    				"stroke-dasharray":"- ",
	    				'stroke-opacity':0.8
	    		 });
	            if(y2-y1<10)
					y2 +=5;
	    		canvas.text(5,y2,opts.minTitle).attr({
	    			'font': '12px Verdana',
	    			'text-anchor': 'start',
	    			'font-family':"宋体",
	    			 fill:"#000"
	    		});
			};
			create = function(){
				createLineBar();
				createLine();
			};
			create();
		}
	});
})(jQuery);