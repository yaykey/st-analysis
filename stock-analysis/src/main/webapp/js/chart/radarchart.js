//**********************rapheal雷达图插件************************
/**
作者：lilinbing(2013-12-17)
版本：1.0
说明：rapheal雷达图插件
参数设置：
width       容器的宽
height      容器的高
unit        单位
color       柱状颜色
*/
//**********************rapheal图标联动插件*************************
(function($) {
	jQuery.fn.extend({
		radarchart: function(dataArr,opts) {
			opts = jQuery.extend({
				width: 600,//画布宽度
				height:400,//画布高度
				title:null,//标题
				unit:"万",//单位
				color:["#918ec7","#bbbace","#e0c1b3","#ff859c","#ffb45e","#FFCC99","#FFCC33","#FFCC66","#FFCC66","#FFCC66","#FFCC66","#FFCC66"],
				callback: function() { return false; }
			}, opts || {});
			var _self = this;
			var _this = $(this);
			var nodes = [];//处理后的数据
			var cond = {
				zTop:20,//距离顶部距离
				zBottom:10,//距离底部距离
				maxValue:0,
				minValue:0,
				radarLv:15,//雷达图间隔
				cLen:7, //字符宽度
				cMaxLen:0,//标题最宽宽度
				titleHeight:70//标题高度
			};
			var canvas;
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
			 * 创建画布
			 */
			createCanvas = function () {
				canvas = new Raphael(_this.attr("id"),opts.width,opts.height);
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
				var maxlength = 0;
				var le = 0;
				for(i=0;i<dataArr.length;i++){
					if(cond.maxValue<dataArr[i].num)
						cond.maxValue = dataArr[i].num;
					if(cond.minValue>dataArr[i].num)
						cond.minValue = dataArr[i].num;
					nodes[i] = {
							id:"node_"+i,
							index:i,
							cx:0,
							cy:0,
							title:dataArr[i].title,//标题
							num:dataArr[i].num //值
					};
					le = charLen(dataArr[i].title);
					if(maxlength<le){
						maxlength = le;
					}
				}
				cond.cMaxLen = maxlength*cond.cLen;
			};
			/**
			 * 创建柱形
			 */
			createRadar = function(){
				createCanvas();//创建画布
				setSource();//初始化数据
				var x = Math.ceil(opts.width/2);
				var y = cond.zTop;
				var size = opts.height-cond.zBottom-cond.zTop;
				if(opts.title!=null){
					y += cond.titleHeight;
					size -= cond.titleHeight;
					creatTitle();
				}
				size = size/(2*Math.cos(Raphael.rad(36)));
				var m = size;
				var result = "";
				var x1=x,y1=y;
				var n = 0;
				while(size>1){
					result = "";
					for(var i=0;i<5;i++){
						if(n<1){
							creatText(i,x1,y1);
						}
						if(size/m<=nodes[i].num||(size/m<0.02&&nodes[i].num<0.01)){
							if(nodes[i].cx<1){
								nodes[i].cx = x1;
								nodes[i].cy = y1;
							}
						}
						result += "M"+x1+","+y1;
						var a45 = Raphael.rad((i+1)*72-36);//角度转换成弧度
						var x1 = x1+Math.cos(a45) * size;
				        var y1 = y1+Math.sin(a45) * size;
				        result += "L"+x1+","+y1;
					}
					var c = n;
					if(n>11)
						c=11;
					canvas.path(result).attr({
	    				'stroke-width':2,
	    				'stroke':opts.color[c],
	    				'stroke-opacity':0.8
					});
					size -= cond.radarLv;
					y1 += cond.radarLv*Math.cos(Raphael.rad(36));
					if(size<=cond.radarLv){
						cond.radarLv = cond.radarLv/2;
					}
					n++;
				}
				creatDot();
				
			};
			creatTitle = function(){
				if(opts.title==null||opts.title.length<1)
					return ;
				var le = charLen(opts.title)*8;
				var x = Math.ceil((opts.width-le)/2);
				var y = cond.zTop;
				canvas.text(x,y,opts.title).attr({
	    			'font': '18px Verdana',
	    			'text-anchor': 'start',
	    			'font-family':"宋体",
	    			 fill:"#000"
	    		});
			};
			creatText = function(n,x,y){
				var le = charLen(nodes[n].title)*cond.cLen;
				if(n==0){
					y -=10;
					x -= le/2;
				}else if(n==1){
					x += 5;
				}else if(n==2){
					x += 5;
					y +=5;
				}else if(n==3){
					x -= le+10;
					y +=5;
				}else if(n==4){
					x -= le+10;
				}
				if(x<0)
					x = 0;
				if(x>opts.width)
					x = opts.width-le;
				canvas.text(x,y,nodes[n].title).attr({
	    			'font': '14px Verdana',
	    			'text-anchor': 'start',
	    			'font-family':"宋体",
	    			 fill:"#000"
	    		});
			};
			creatDot = function(){
				for(var i=0;i<nodes.length;i++){
					canvas.circle(nodes[i].cx, nodes[i].cy, 4).attr({
						'stroke-width':1,
	    				'stroke':"#918ec7",
						 fill:"#ff859c"
					});
					
					var result = "";
					if(i+1<nodes.length){
						result = "M"+nodes[i].cx+","+ nodes[i].cy+"L"+nodes[i+1].cx+","+ nodes[i+1].cy;
					}else{
						result = "M"+nodes[i].cx+","+ nodes[i].cy+"L"+nodes[0].cx+","+ nodes[0].cy;
					}
					canvas.path(result).attr({
	    				'stroke-width':3,
	    				'stroke':"#918ec7",
	    				'stroke-opacity':1
					});
					if(i==2){
						//nodes[i].cx -= 40;
					}else if(i==3){
						nodes[i].cx -= 60;
					}else if(i==4){
						nodes[i].cx -= 60;
					}
					
					
					canvas.text(nodes[i].cx+10,nodes[i].cy,(nodes[i].num*100).toFixed(2)+"%").attr({
		    			'font': '14px Verdana',
		    			'text-anchor': 'start',
		    			'font-family':"宋体",
		    			 fill:"#000"
		    		});
				}
			};
			create = function(){
				createRadar();
			};
			create();
		}
	});
})(jQuery);