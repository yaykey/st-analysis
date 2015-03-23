//**********************rapheal玫瑰图插件************************

/**
作者：lilinbing(2013-12-17)
版本：1.0
说明：rapheal玫瑰图插件
参数设置：
width       容器的宽
height      容器的高
maxValue    目标值
minValue    底线值
maxTitle    目标值显示名称
minTitle    底线值显示名称
color       柱状颜色
*/
//**********************rapheal图标联动插件*************************
(function($) {
	jQuery.fn.extend({
		circlechart: function(dataArr,opts) {
			opts = jQuery.extend({
				width: 600,//画布宽度
				height:400,//画布高度
				title:null,//标题
				maxValue:null,//目标值
				minValue:null,//底线值
				maxTitle:null,//目标值显示名称
				minTitle:null,//底线值显示名称
				unit:"万",//单位
				color:["#ffa19d","#87d3ff","#5283ef"],
				callback: function() { return false; }
			}, opts || {});
			var _self = this;
			var _this = $(this);
			var cond = {
				zTop:10,//距离顶部距离
				titleHeight:70,//标题高度
				zEWdith:0,//显示值宽度
				iHeight:25,//说明行间距
				mr:0,//目标值半径
				dr:0,//底线值半径
				zr:0,//值半径
				bmw:12,//目标值边宽度
				bdw:8,//底线值边宽度
				bzw:20,//值边宽度
				cLen:7, //字符宽度
				cx:0,
				cy:0,
				title:"",
				ptitle:"",
				percentage:0,//计算百分比
				lWidth:150//目标值和底线值左边所占宽度
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
			 * 创建画布
			 */
			createCanvas = function () {
				canvas = new Raphael(_this.attr("id"),opts.width,opts.height);
			};
			/**
			 * 计算参数值
			 */
			calculation = function(){
				if(opts.title!=null&&opts.title.length>0){
					cond.zHeight = opts.height - cond.titleHeight-cond.zTop-5;
				}else{
					cond.zHeight = opts.height-cond.zTop-5;
				}
				//计算百分比
				if(dataArr[0].value!=null&&dataArr[0].allValue!=null){
					cond.percentage = dataArr[0].value/dataArr[0].allValue;
					cond.percentage = cond.percentage.toFixed(4);
				}
				cond.title = dataArr[0].title+"数："+dataArr[0].value+opts.unit;
				cond.ptitle = dataArr[0].title+"\n占比："+(cond.percentage*100)+"%";
				cond.zEWdith = charLen(cond.title)*cond.cLen+60;
				if((opts.width - cond.zEWdith)<cond.zHeight){
				
					cond.mr = Math.floor((opts.width - cond.zEWdith)/2);
				}else{
					cond.mr = Math.floor(cond.zHeight/2);
				}
				
				cond.dr = cond.mr - cond.bmw;
				cond.zr = cond.dr - cond.bdw;
				
				
				 
			};
			/**
			 * 创建圆形
			 */
			createCircle = function(){
				createCanvas();//创建画布
				calculation();//计算参数值
				creatTitle();
				var x,y;
				r = cond.mr-(cond.bmw+cond.bdw)/2;
				y = opts.height - cond.zHeight+cond.mr;
				x = cond.mr;
				canvas.circle(x, y, r).attr({fill: "#fff", stroke: "#fcfcfc",'stroke-width':cond.bmw+cond.bdw})
				x = x+cond.bmw+3;
				y = y-cond.bmw-3;
				createSector(x,y,cond.mr-cond.bmw/2,360*opts.maxValue,cond.bmw,opts.color[0]);
				var max = opts.maxValue*100;
				var min = opts.minValue*100;

				createInfo("目标值："+fixedNum(max)+"%",opts.color[0],2);
				createSector(x,y,cond.mr-cond.bmw/2-cond.bdw-2,360*opts.minValue,cond.bdw,opts.color[1]);
				createInfo("底线值："+fixedNum(min)+"%",opts.color[1],3);
				
				createSector(x,y,cond.mr-(cond.bmw+cond.bdw+cond.bzw)/2-10,360*cond.percentage,cond.bzw,opts.color[2]);
				createInfo(cond.title,opts.color[2],1);
				
				canvas.text((x-r/2-10),y+10,cond.ptitle).attr({
	    			'font': '18px Verdana',
	    			'text-anchor': 'start',
	    			'font-family':"宋体",
	    			 fill:"#000"
	    		});
			};
			createSector = function(x,y,r,angle,width,color){
				var angleplus = angle,//360度乘以40%
		        startAngle = 90,
		        endAngle =startAngle+angleplus,
		        cx = y,
		        cy = x,
		        rad = Math.PI / 180,
		        x1 = cx + r * Math.cos(-startAngle * rad),
		        x2 = cx + r * Math.cos(-endAngle * rad),//转化为弧度，再然半径，再加圆心的x坐标，得出变化后x坐标
		        y1 = cy + r * Math.sin(-startAngle * rad),
		        y2 = cy + r * Math.sin(-endAngle * rad);
		        var path = ["M", x1, y1, "A", r, r, 0, +(endAngle - startAngle > 180), 0, x2, y2];
		        path = path.join(" ");
		        canvas.path(path).attr({
    				'stroke-width':width,
    				'stroke':color,
    				'stroke-opacity':1
    		    });
			}
			/**
			 * 创建标题
			 */
			creatTitle = function(){
				if(opts.title==null||opts.title.length<1)
					return ;
				var le = charLen(opts.title)*10;
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
				var h = (opts.height-cond.zTop-3*cond.iHeight)/2;
				x = opts.width-cond.zEWdith+40;
				y = i*cond.iHeight+cond.zTop+h;
				x1 = x+rw+5;
				y1 = y+3;
				canvas.rect(x,y,rw,rh,0).attr({
	    			'fill': color,
	    			'stroke':color,
	    			'stroke-width':1
	    		});
				canvas.text(x1,y1,title).attr({
	    			'font': '12px Verdana',
	    			'text-anchor': 'start',
	    			'font-family':"宋体",
	    			 'fill':"#000"
	    		});
			};
			create = function(){
				createCircle();
			};
			create();
		}
	});
})(jQuery);