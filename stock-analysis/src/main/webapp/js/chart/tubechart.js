//**********************rapheal试管图插件************************

/**
作者：lilinbing(2013-12-17)
版本：1.0
说明：rapheal试管图插件
参数设置：
width       容器的宽
height      容器的高
maxValue    目标值
minValue    底线值
maxTitle    目标值显示名称
minTitle    底线值显示名称
unit        单位
color       柱状颜色
*/
//**********************rapheal图标联动插件*************************
(function($) {
	jQuery.fn.extend({
		tubechart: function(dataArr,opts) {
			opts = jQuery.extend({
				width: 600,//画布宽度
				height:400,//画布高度
				title:null,//标题
				maxValue:null,//目标值
				minValue:null,//底线值
				maxTitle:null,//目标值显示名称
				minTitle:null,//底线值显示名称
				colorNum:0,//颜色
				unit:"万",//单位
				color:[["#a65efe","#D9BAFF"],["#9d85ff","#D7CEFF"],["#d19fde","#F2BDFF"],["#dd9fd1","#ffbdf2"]],
				callback: function() { return false; }
			}, opts || {});
			var _self = this;
			var _this = $(this);
			var nodes = [];//处理后的数据
			var cond = {
				zTop:10,//距离顶部距离
				titleHeight:70,//标题离试管高度
				zEWdith:0,//显示值宽度
				zHeight:0,//试管高度
				zWidth:50,//试管高度
				cLen:7, //字符宽度
				percentage:0.1,//百分比
				lWidth:130//目标值和底线值左边所占宽度
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
			 * 计算参数值
			 */
			calculation = function(){
				if(dataArr.title!=null&&dataArr.title.length>0){
					cond.zHeight = opts.height - cond.titleHeight-cond.zTop-5;
				}else{
					cond.zHeight = opts.height-cond.zTop-5;
				}
				var title = dataArr.value+opts.unit;
				cond.zEWdith = charLen(title)+60;
				
				//计算百分比
				if(dataArr.value<=opts.maxValue){
					cond.percentage = cond.zHeight/(opts.maxValue/0.8);
				}else{
					cond.percentage = cond.zHeight/(dataArr.value/0.9);
				}
				 
			};
			/**
			 * 创建柱形
			 */
			createTube = function(){
				createCanvas();//创建画布
				calculation();//计算参数值
				creatTitle();
				var x,y;
				x = cond.zEWdith;
				y = opts.height - cond.zHeight-5;
				var s1 = dataArr.value+opts.unit;

				canvas.rect(x,y,cond.zWidth,cond.zHeight,Math.floor(cond.zWidth/2)).attr({
	    			'fill': "#faf3ee",
	    			'stroke': "#aaaaaa",
	    			'stroke-width':2,
	    			'opacity':1
	    		});
				canvas.text(5,y+Math.round(cond.zHeight/2),s1).attr({
	    			'font': '14px Verdana',
	    			'text-anchor': 'start',
	    			'font-family':"宋体",
	    			'font-weight':600,
	    			 fill:"#000"
	    		});
				createP();
			};
			/**
			 * 创建标题
			 */
			creatTitle = function(){
				if(dataArr.title==null||dataArr.title.length<1)
					return ;
				var le = charLen(dataArr.title)*10;
				var x = Math.ceil((opts.width-le)/2);
				var y = 10;
				canvas.text(x,y,dataArr.title).attr({
	    			'font': '18px Verdana',
	    			'text-anchor': 'start',
	    			'font-family':"宋体",
	    			 fill:"#000"
	    		});
			};
			createP = function(){
				if((cond.percentage*dataArr.value)/(cond.percentage*opts.maxValue)<0.05)
					return ;
				
				var h = Math.floor(cond.percentage*dataArr.value)-10;
				var y = opts.height-h-6;
				var x = cond.zEWdith+1;
				canvas.rect(x,y,cond.zWidth-2,h,Math.floor(cond.zWidth/2)).attr({
	    			'fill': opts.color[opts.colorNum][0],
	    			'stroke':opts.color[opts.colorNum][0],
	    			'stroke-width':0,
	    			'opacity':1
	    		});
				h = Math.floor(cond.zWidth/2);
				//y -=h-25;
				if(opts.height-y<40)
					h -=12;
				canvas.rect(x,y,cond.zWidth-2,h,0).attr({
	    			'fill': opts.color[opts.colorNum][0],
	    			'stroke': opts.color[opts.colorNum][0],
	    			'stroke-width':0,
	    			'opacity':1
	    		});
				
				var t = (cond.zWidth)/2;
				y -=1;
				canvas.ellipse(x+t,y+1,t,2).attr({
	    			'fill': opts.color[opts.colorNum][1],
	    			'stroke': opts.color[opts.colorNum][1],
	    			'stroke-width':0,
	    			'opacity':1
	    		});
			};
			/**
			 * 创建目标值和底线值
			 */
			createLine = function(){
				if(opts.maxValue==null)
					return false;
				var zh = 20;//边框高度
				var w = cond.zWidth+cond.lWidth;//线的长度
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
				
				var x1=0,x11=0,x2=0,x22=0,y1=0,y2=0;
				x1 = w-w1+cond.zEWdith;//计算目标位置
				x2 = w-w2+cond.zEWdith;//计算底线位置
				x11 = cond.zEWdith;
				x22 = x11+w;
				
				
				y1 = opts.height-Math.ceil(cond.percentage*opts.maxValue);//计算目标y轴
				y2 = opts.height-Math.ceil(cond.percentage*opts.minValue);//计算底线y轴
				
				
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
	            canvas.rect(x2+3,y2,w2,zh,r).attr({
	    			'fill': "#fff55a",
	    			'stroke': "#fff55a",
	    			'stroke-width':0,
	    			'opacity':1
	    		});
	    		canvas.text(x2+5,y2+8,s2).attr({
	    			'font': '12px Verdana',
	    			'text-anchor': 'start',
	    			'font-family':"宋体",
	    			'font-weight':600,
	    			 fill:"#000",
	    		    'opacity':0.5
	    		});
			};
			create = function(){
				createTube();
				createLine();
			};
			create();
		}
	});
})(jQuery);