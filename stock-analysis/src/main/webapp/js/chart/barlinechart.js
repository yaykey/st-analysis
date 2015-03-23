//**********************rapheal线图插件************************
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
		linechart: function(titleArr,dataArr,opts) {
			opts = jQuery.extend({
				width: 600,//画布宽度
				height:400,//画布高度
				title:null,//标题
				maxValue:null,//目标值
				minValue:null,//底线值
				maxTitle:null,//目标值显示名称
				minTitle:null,//底线值显示名称
				info:true,//是否显示信息说明
				align:"bottom",//说明显示位置
				unit:"万",//单位
				color:["#e68d90","#fef764","#73befc","#ee0f10","#ff7396","#FCA2FD","#450b4d","#8ccd15","#7396ff","#ee2c86","#c43df2"],
				callback: function() { return false; }
			}, opts || {});
			var _self = this;
			var _this = $(this);
			var nodes = [];//处理后的数据
			var cond = {
				zWidth:0,//线图宽度
				zHeight:0,//线图高度
				zTop:0,//离顶部距离
				zEHeight:35,//线图说明高度
				zMaxHeight:0,//线图最高的高度
				iHeight:25,//说明行间距
				iWidth:25,//说明列间距
				iHnum:0,//说明列数
				iWnum:0,//说明行数
				cLen:7, //字符宽度
				cMaxLen:0,//标题最宽宽度
				ziHeight:0,//说明与线图行间距
				titleHeight:70,//标题高度
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
			fixedNum = function(num,n){
				if((num+"").indexOf(".")<1)
					return num;
				n = n==null?2:n;
				if((num+"").substring((num+"").indexOf(".")).length>(n+1)){
					num = num.toFixed(n);
				}
				return num;
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
				var maxvalue = 0;
				var minvalue = null;
				var maxlength = 0;
				var le = 0;
				if(opts.unit=="%"){
					opts.unit +=" ";
					opts.maxValue = opts.maxValue*100;
					opts.maxValue = fixedNum(opts.maxValue);
					opts.minValue = opts.minValue*100;
					opts.minValue = fixedNum(opts.minValue);
					for(i=0;i<dataArr.length;i++){
						for(j=0;j<dataArr[i].numArr.length;j++){
							dataArr[i].numArr[j] = fixedNum(dataArr[i].numArr[j]*100);
						}
					}
				}
				
				for(i=0;i<dataArr.length;i++){
					nodes[i] = {
							id:"node_"+i,
							index:i,
							title:dataArr[i].title,//标题
							numArr:dataArr[i].numArr //值
					};
					if(dataArr[i].numArr!=null&&dataArr[i].numArr.length>0){
						for(j=0;j<dataArr[i].numArr.length;j++){
							if(maxvalue<dataArr[i].numArr[j])
								maxvalue = dataArr[i].numArr[j];
							
							if(minvalue==null||parseFloat(minvalue)>parseFloat(dataArr[i].numArr[j])){
								minvalue = dataArr[i].numArr[j];
							}
								
						}
					}
					le = charLen(dataArr[i].title);
					if(maxlength<le){
						maxlength = le;
					}
				}
				
				if(opts.maxValue!=null&&opts.maxValue>maxvalue){
					cond.zMaxHeight = opts.maxValue;
				}else{
					cond.zMaxHeight = maxvalue;
				}
				
//				cond.zvalue = minvalue*0.9;
				cond.cMaxLen = maxlength*cond.cLen;
				calculation();
			};
			/**
			 * 计算参数值
			 */
			calculation = function(){
				var w1 = 0,w2=0;
				if(opts.maxValue!=null){
					if(opts.maxTitle==null||opts.maxTitle.length<1){
						opts.maxTitle = "目标:"+fixedNum(opts.maxValue)+""+opts.unit;
					}
					w1 = charLen(opts.maxTitle)*cond.cLen+1;//计算目标宽度
				}
				if(opts.minValue!=null){
					if(opts.minTitle==null||opts.minTitle.length<1){
						opts.minTitle = "底线:"+fixedNum(opts.minValue)+""+opts.unit;
					}
					w2 = charLen(opts.minTitle)*cond.cLen+1;//计算底线宽度
				}

				if(w1<w2)
					cond.lWidth += w2;
				else
					cond.lWidth += w1;
				//cond.zWidth = Math.round((opts.width-cond.lWidth)/titleArr.length);//间隔宽度
				cond.zWidth = Math.round((opts.width-cond.lWidth)/31);//间隔宽度
				
				cond.iHnum = Math.floor((opts.width-cond.lWidth+20)/(cond.cMaxLen+cond.iWidth));//计算说明列个数
				cond.iWnum = Math.ceil(nodes.length/cond.iHnum);//计算说明行个数

				if(opts.align=="top"){
					cond.zTop = cond.iWnum*cond.iHeight+cond.ziHeight;//线图位置
					if(opts.title!=null&&opts.title.length>0){
						cond.zTop += cond.titleHeight;
					}
					cond.zHeight = opts.height - cond.zTop-cond.zEHeight;//线图高度
				}else{
					cond.zTop = 10;//线图位置
					if(opts.title!=null&&opts.title.length>0){
						cond.zTop += cond.titleHeight;
					}
					cond.zHeight = opts.height - (cond.iWnum*cond.iHeight)-cond.ziHeight-cond.zTop-cond.zEHeight;//线图高度
				}
				
				
			};
			eventNode = function (d,n) {
				d.mouseover(function () {
					this.attr({'r':5});
					var xx = this.attr("cx");
					var yy = this.attr("cy");
					var text = this.data("text")+""+opts.unit;
					var le = charLen(text)*8;
					var rw = le;
					var rh=20;
					
					x = xx-le/2;y = yy-30;
					var rnode = canvas.rect(x,y,rw,rh,3).attr({
		    			'fill': this.data("color"),
		    			'stroke':this.data("color"),
		    			'stroke-width':1
		    		});
					var x1 = x+1,y1=y+10;
					var tnode = canvas.text(x1,y1,text).attr({
		    			'font': '12px Verdana',
		    			'text-anchor': 'start',
		    			 'fill':"#000"
		    		});
					var x1=x+le/2,x2=x+le/2,y1=y+17,y2=y+22,size=9;
					var angle = Raphael.angle(x1, y1, x2, y2);//得到两点之间的角度
					var a45 = Raphael.rad(angle - 60);//角度转换成弧度
			        var a45m = Raphael.rad(angle + 60);
			        var x2a = x2 + Math.cos(a45) * size;
			        var y2a = y2 + Math.sin(a45) * size;
			        var x2b = x2 + Math.cos(a45m) * size;
			        var y2b = y2 + Math.sin(a45m) * size;
			        var result = "M"+ x2a+","+y2a+ "L"+x2+","+ y2+"S"+x2+","+ y2+","+x2b+","+y2b+"M"+x2b+","+y2b+"L"+x2a+","+y2a;

			        var snode = canvas.path(result).attr({
		        		'fill': this.data("color"),
						'stroke-width':1,
						'stroke':this.data("color"),
						'stroke-opacity':1
			        });
					
					this.data("rnode",rnode);
					this.data("tnode",tnode);
					this.data("snode",snode);
				}).mouseout(function () {
					this.attr({'r':3});
					this.data("rnode").remove();
					this.data("tnode").remove();
					this.data("snode").remove();
				});
			};
			/**
			 * 创建线图
			 */
			createLineBar = function(){
				createCanvas();//创建画布
				setSource();//初始化数据
				creatTitle();
				createKLine();
				var a = (cond.zHeight*1.0)/(((cond.zMaxHeight-cond.zvalue)*5)/4);//线图的比例

				var h = 0,n = 0,m = 0,t=0,w=0,l=0,x=0,y=0,i,j;
				var result = "";
				for(i=0;i<nodes.length;i++){
					x = cond.lWidth;
					for(j=0;j<nodes[i].numArr.length;j++){
						if(j>0){
							result = "M"+x+","+y;
							x += cond.zWidth;
						}
						h = (nodes[i].numArr[j]-cond.zvalue)*a;
						y = cond.zHeight-h+cond.zTop;
						var node = canvas.circle(x,y, 3).attr({
							'stroke-width':1,
		    				'stroke':opts.color[i],
							 fill:opts.color[i]
						});
						node.data('text', fixedNum(nodes[i].numArr[j]));
						node.data('color',opts.color[i]);
						eventNode(node);
						if(j>0){
							result += "L"+x+","+ y;
							canvas.path(result).attr({
								'stroke-width':2,
								'stroke': opts.color[i],
								'stroke-opacity':0.8
							});
						}
					}
					if(opts.info)
						createInfo(nodes[i].title,opts.color[i],i);
				}
				creatLineInfo();
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
						'stroke': "#7396ff",
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
				titleArr = [];
				for(var i=1;i<31;i++){
					if(i%5==0||i==1){
						titleArr[i-1] = i+"日";
					}else{
						titleArr[i-1] = "";
					}
				}
				var x = cond.lWidth;;
				var y = cond.zHeight+cond.zTop+15;
				for(var i=0;i<titleArr.length;i++){
					if(i>0)
						x += cond.zWidth;
					canvas.text(x-10,y,titleArr[i]).attr({
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
				if(opts.align=="bottom"){
					var a = Math.floor(i/cond.iHnum);
					var b = i%cond.iHnum;
					if(b>0)
						x = cond.lWidth+b*(cond.cMaxLen+cond.iWidth)-20;
					else
						x = cond.lWidth-20;
					y = cond.zHeight+cond.zEHeight+cond.zTop+a*cond.iHeight;
					x1 = x+rw+5;
					y1 = y+2;
				}else if(opts.align=="top"){
					var a = Math.floor(i/cond.iHnum);
					var b = i%cond.iHnum;
					if(b>0)
						x = cond.lWidth+b*(cond.cMaxLen+cond.iWidth)-20;
					else
						x = cond.lWidth-20;
					y = a*cond.iHeight+5;
					if(opts.title!=null&&opts.title.length>0)
						y+=cond.titleHeight;
					x1 = x+rw+5;
					y1 = y+2;
				}
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
				var a = cond.zHeight/(((cond.zMaxHeight-cond.zvalue)*5)/4);//线图的比例
				//var w = cond.zWidth*(titleArr.length-1)+10;//线的长度
				var w = (opts.width-cond.lWidth);//线的长度
				var x1=0,x11=0,x22=0,x2=0,y1=0,y2=0;

				x1 = cond.lWidth;//计算目标位置
				x2 = x1+w;//计算目标位置
				y1 = cond.zHeight-Math.round(a*(opts.maxValue-cond.zvalue))+cond.zTop;//计算目标y轴
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
	    		
	    		y2 = cond.zHeight-Math.round(a*(opts.minValue-cond.zvalue))+cond.zTop;//计算目标y轴
				
	    		
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