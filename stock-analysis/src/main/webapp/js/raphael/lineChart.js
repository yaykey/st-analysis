//**********************rapheal图形插件************************
/**
作者：lilinbing(2014-08-06)
版本：1.0
说明：rapheal图形插件
 */

/**
 * 定义公用方法
 */
(function($) {
	$.feinno={
		/**
		 * Y轴刻度值
		 * max 传入参数的最大值
		 * min 传入参数的最小值
		 */
		scaleY:function(max,min){
			var l,d,d1,d2,d3,k,s,chk = false;
			d1 = this.decimalLen(max);
			d2 = this.decimalLen(min);
			d3 = d1>=d2?d1:d2;
			d = this.lenNum(null,d3);
			if(max<0&&min<0){
				chk = true;
				var a = max,b=min;
				max = -b;
				min = -a;
			}
			
			max *=d;
			min *=d;
			l = this.lenNum(Math.round(min));
			min = Math.floor(min/l)*l;

			l = this.lenNum(Math.round(max));
			max = (max/l).toFixed(2)*l;

			k = (max-min)/4;
			l = this.lenNum(Math.round(k));
			if(Math.round(k/l)>k/l){
				k = Math.ceil(k/l)*l/d;
			}else{
				k = (Math.floor(k/l)*l+0.5*l)/d;
			}

			if(max/min>5){
				s = chk?-max/d:0;
			}else{
				s = chk?-max/d:min/d;
			}
			max = max/d;
			min = min/d;
			var arr = [];
			for(var i=0;i<5;i++){
				arr[i] = this.toFixed(s+k*i,d3);
				if(chk){
					if(arr[i]>-min)
						break;
				}else{
					if(arr[i]>max)
						break;
				}
			}
			return arr
		},
		/**
		 * 根据长度，返回数值
		 */
		lenNum:function (num,l){
			l = l!=null?l:(((num<0?-num:num)+"").length-1);
			var b = 1;
			for(var i=0;i<l;i++){
				b*=10;
			}
			return b;
		},
		/**
		 * 字符串长度，汉字占2个字符
		 */
		charLen:function (s){
			var l = 0;
			var a = s.split("");
			for (var i=0;i<a.length;i++) {
				if (a[i].charCodeAt(0)<299) {
					l++;
				} else {
					l+=2;
				}
			}
			if(l<4)
				l = 4;
			return l;
		},
		/**
		 * 长数值逗号","隔开
		 * @param strNum
		 * @returns
		 */
		formatNum:function(strNum){
			if (strNum.length <= 3) {
				return strNum;
			}
			if (!/^(\+|-)?(\d+)(\.\d+)?$/.test(strNum)) {
				return strNum;
			}
			var a = RegExp.$1, b = RegExp.$2, c = RegExp.$3;
			var re = new RegExp();
			re.compile("(\\d)(\\d{3})(,|$)");
			while (re.test(b)) {
				b = b.replace(re, "$1,$2$3");
			}
			return a + "" + b + "" + c;
		},
		/**
		 * 保留小数位数
		 * @param num 数值
		 * @param l 位数
		 * @returns
		 */
		toFixed:function(num,l){
			if(num==num.toFixed(l)){
				return parseFloat(num);
			}else
				return parseFloat(num.toFixed(l));
		},
		/**
		 * 小数位数
		 */
		decimalLen:function(num){
			var str = num+"";
			if(str.indexOf("\.")!=-1){
				return str.substring(str.indexOf("\.")+1).length;
			}else{
				return 0;
			}
		},
		/**
		 * 获取箭头
		 * @param x1
		 * @param y1
		 * @param x2
		 * @param y2
		 * @param size
		 */
		createArrow:function(x1, y1, x2, y2, size){
			var angle = Raphael.angle(x1, y1, x2, y2);//得到两点之间的角度
			var a45 = Raphael.rad(angle - 30);//角度转换成弧度
			var a45m = Raphael.rad(angle + 30);
			var x2a = x2 + Math.cos(a45) * size;
			var y2a = y2 + Math.sin(a45) * size;
			var x2b = x2 + Math.cos(a45m) * size;
			var y2b = y2 + Math.sin(a45m) * size;

			var result = "M"+ x2a+","+y2a+ "L"+x2+","+ y2+"S"+x2+","+ y2+","+x2b+","+y2b+"M"+x2b+","+y2b+"L"+x2a+","+y2a;

			this.canvas.path(result).attr({
				'fill': this.opts.lineColor,
				'stroke-width':1,
				'stroke': this.opts.lineColor,
				'stroke-opacity':1
			});
		},
		/**
		 * 生成随机颜色代码
		 * @returns {String}
		 */
		randomColor:function(){
			return '#'+('00000'+(Math.random()*0x1000000<<0).toString(16)).slice(-6);
		}
	};
})(jQuery);
(function($) {
	var chartLine = {
		opts:{},
		cond:{
			zWidth:0,//线图宽度
			zHeight:0,//线图高度
			zTop:10,//离顶部距离
			zRight:10,//线图离右边距离
			zEHeight:35,//线图说明高度
			iLeft:100,//说明离左边距离
			iHeight:25,//说明行间距
			iWidth:55,//说明列间距
			iHnum:0,//说明列数
			iWnum:0,//说明行数
			rw:20,//说明图宽度
			rh:2,//说明图高度
			cLen:7, //字符宽度
			cMaxLen:0,//标题最宽宽度
			ziHeight:10,//说明与线图行间距
			titleHeight:70,//标题高度
			twidth:10,//时间拖动轴宽度
			tNodeWidth:0,//时间轴节点宽度
			lWidth:0,//y轴文字宽度
			lHeight:16,//x轴文字离线图距离
			maxLen:0,//显示数值节点个数
			timeHeight:40//时间拖动轴高度
		},
		id:"",
		dataArr:[],//展示数据集合
		titleArr:[],//节点集合
		nodes:[],//刻度线条集合
		lines:[],//刻度线条集合
		pathNode:[],//曲线集合
		oldNum:null,//经过上一个节点的位置
		canvas:null,//画布
		timeNode:null,//时间轴拖动开始拖动节点和结束拖动节点距离
		nodeSet:null,
		barchk:true,
		/**
		 * 创建画布
		 */
		createCanvas:function() {
			this.canvas = new Raphael(this.id,this.opts.width,this.opts.height);
			this.nodeSet = this.canvas.set();
		},
		/**
		 * 初始化数据
		 * @returns
		 */
		 setSource:function(){
			if(this.opts.data==null&&this.opts.data.length>0){
				return ;
			}
			var i,j,n;
			var maxvalue = 0;
			var minvalue = null;
			var maxlength = 0;
			var data = [];
			var le = 0;
			for(i=0;i<this.opts.data.length;i++){
				n = 0;
				this.dataArr[i] = new Object();
				this.dataArr[i].name = this.opts.data[i].name;
				data = [];
				for(j=0;j<this.opts.data[i].data.length;j++){
					if(maxvalue<this.opts.data[i].data[j].num)
						maxvalue = this.opts.data[i].data[j].num;

					if(minvalue==null||minvalue>this.opts.data[i].data[j].num)
						minvalue = this.opts.data[i].data[j].num;
					
					this.titleArr[j] = this.opts.data[i].data[j].text;
				}
				le = $.feinno.charLen(this.opts.data[i].name);
				if(maxlength<le){
					maxlength = le;//说明最大字符数
				}
				if(this.cond.maxLen==0||this.cond.maxLen<this.opts.data[i].data.length){
					this.cond.maxLen = this.opts.data[i].data.length;
				}
				if(i>19){
					this.opts.color[i]=$.feinno.randomColor();
				}
			}
			if(this.opts.timeView){
				this.cond.maxLen = this.opts.defNum;
			}else{
				this.dataArr = this.opts.data;
			}
			var w1,w2;
			if(this.opts.arrY.length<1)
				this.opts.arrY = $.feinno.scaleY(maxvalue,minvalue);
			if(this.opts.perc){
				w1 = $.feinno.charLen(this.opts.arrY[this.opts.arrY.length-1]*100+"%")*8;//计算y轴刻度最大值字符宽度
				w2 = $.feinno.charLen(this.opts.arrY[0]*100+"%")*8;//计算y轴刻度最小值字符宽度
			}else{
				w1 = $.feinno.charLen($.feinno.formatNum(this.opts.arrY[this.opts.arrY.length-1]))*7;//计算y轴刻度最大值字符宽度
				w2 = $.feinno.charLen($.feinno.formatNum(this.opts.arrY[0]))*7;//计算y轴刻度最小值字符宽度
			}
			
			this.cond.cMaxLen = maxlength*this.cond.cLen;//说明最大宽度

			if(w1<w2)
				this.cond.lWidth += w2;
			else
				this.cond.lWidth += w1;

			this.cond.iHnum = Math.floor((this.opts.width-this.cond.lWidth-this.cond.iLeft)/(this.cond.cMaxLen+this.cond.iWidth+this.cond.rw));//计算说明列个数
			this.cond.iWnum = Math.ceil(this.opts.data.length/this.cond.iHnum);//计算说明行个数

			if(this.opts.align=="top"){
				this.cond.zTop += this.cond.iWnum*this.cond.iHeight+this.cond.ziHeight;//线图位置

				if(this.opts.title!=null&&this.opts.title.length>0){
					this.cond.zTop += this.cond.titleHeight;
				}
				this.cond.zHeight = this.opts.height - this.cond.zTop-this.cond.zEHeight;//线图高度
				if(this.opts.timeView){
					this.cond.zHeight -= this.cond.timeHeight;
				}
			}else{
				if(this.opts.title!=null&&this.opts.title.length>0){
					this.cond.zTop += this.cond.titleHeight;
				}
				
				this.cond.zHeight = this.opts.height - (this.cond.iWnum*this.cond.iHeight)-this.cond.ziHeight-this.cond.zTop-this.cond.zEHeight;//线图高度
				this.cond.zEHeight +=this.cond.lHeight;
				if(this.opts.timeView){
					this.cond.zHeight -= this.cond.timeHeight;
					this.cond.zEHeight += this.cond.timeHeight;
				}
			}
			
			var len = $.feinno.charLen(this.titleArr[0])*this.cond.cLen;
			this.cond.zRight += len/2;
		},
		/**
		 * 计算参数值
		 */
		 calNodeWidth:function(){
			this.cond.zWidth = (this.opts.width-this.cond.lWidth-this.cond.zRight)/(this.cond.maxLen-1);//间隔宽度
		},
		/**
		 * 创建线图
		 */
		 createLineBar:function(){
			this.createCanvas();//创建画布
			this.setSource();//初始化数据
			this.creatTitle();
			this.createKLine();

			if(this.opts.timeView){
				this.createTKLine();
			}else{
				this.changeLineBar(true);
			}
		},
		/**
		 * 创建或是改变线图
		 * chk 
		 */
		changeLineBar:function(chk){
			if(!this.barchk&&!chk){
				return ;
			}
			
			this.barchk = false;
			this.calNodeWidth();
			
			var a = this.cond.zHeight/(this.opts.arrY[this.opts.arrY.length-1]-this.opts.arrY[0]);//线图的比例
			var h = 0,n = 0,m = 0,t=0,w=0,l=0,x=0,y=0,i,j;
			var node,line,line1;
			var result = "";
			var result1 = "";
			var arr = [];
			this.nodes = [];
			for(i=0;i<this.dataArr.length;i++){
				x = this.cond.lWidth;
				result = "";
				arr = [];
				for(j=0;j<this.dataArr[i].data.length;j++){
					h = (this.dataArr[i].data[j].num - this.opts.arrY[0])*a;
					y = this.cond.zHeight-h+this.cond.zTop;
					if(j>0){
						x += this.cond.zWidth;
						if(j>1)
							result += ",";
						result += [x,y];
					}else{
						result = "M"+[x,y]+" R";
					}
					if(chk){
						node = this.canvas.circle(x,y, 3).attr({
							'stroke-width':1,
							'stroke':this.opts.color[i],
							'fill':this.opts.color[i]
						}).hide();
						node.data('text', this.dataArr[i].data[j].text);
						node.data('num', this.dataArr[i].data[j].num);
						node.data('id', this.dataArr[i].id);
						node.data('name', this.dataArr[i].name);
						node.data('color',this.opts.color[i]);
						node.data('num',j);
						this.eventNode(node);
						arr[j] = node;
						this.nodeSet.push(node);
						if(i==0){
							w = 60;
							if(this.cond.zWidth<60){
								w = this.cond.zWidth;
							}
							y = this.cond.zHeight+this.cond.zTop;

							result1 = "M"+x+","+y+ "L"+x+","+ this.cond.zTop;
							line = this.canvas.path(result1).attr({
								'stroke-width':1,
								'stroke':this.opts.lineColor,
								'stroke-opacity':0.5
							}).hide();

							result1 = "M"+x+","+y+ "L"+x+","+ this.cond.zTop;
							line1 = this.canvas.path(result1).attr({
								'stroke-width':w,
								'stroke':"#fff",
								'stroke-opacity':0
							});

							line.data("num",j);
							line.data("x",x);
							line.data("y",y);
							line1.data("line",line);
							this.eventLine(line1);
							this.lines[j] = line1;
							this.nodeSet.push(line,line1);
						}
					}
				}
				this.nodes[i] = arr;
				if(this.pathNode[i]==null){
					this.pathNode[i] = this.canvas.path(result).attr({
						'stroke-width':2,
						'stroke': this.opts.color[i],
						'stroke-opacity':1
					});
					this.createInfo(this.dataArr[i].name,this.opts.color[i],i);
				}else{
					this.pathNode[i].attr({path:result});
				}
			}
			this.creatLineInfo();
			this.barchk = true;
		},
		/**
		 * 创建坐标轴
		 */
		 createKLine:function(){
			var x1,x2,y1,y2,size=6;
			x1 = this.cond.lWidth;
			y1 = this.cond.zHeight+this.cond.zTop;
			x2 = x1;
			y2 = this.cond.zTop;
			var result = "M"+x1+","+(y1+3)+ "L"+x2+","+ y2;
			this.canvas.path(result).attr({
				'stroke-width':2,
				'stroke':this.opts.lineColor,
				'stroke-opacity':0.5
			});

			x2 = this.opts.width-this.cond.zRight;
			x1 -=3;
			result = "M"+x1+","+y1+ "L"+x2+","+ y1;
			this.canvas.path(result).attr({
				'stroke-width':2,
				'stroke': this.opts.lineColor,
				'stroke-opacity':0.5
			});
			var a = this.cond.zHeight/(this.opts.arrY[this.opts.arrY.length-1]-this.opts.arrY[0]);//线图的比例
			var w,text,h;
			for(var i=0;i<this.opts.arrY.length;i++){
				h = (this.opts.arrY[i]-this.opts.arrY[0])*a;

				y1 = this.cond.zHeight-h+this.cond.zTop;
				if(this.opts.perc){
					text = this.opts.arrY[i]*100+"%";
				}else{
					text = $.feinno.formatNum(this.opts.arrY[i]);	
				}
				w = $.feinno.charLen(text)*6;
				if(i>0){
					result = "M"+x1+","+y1+ "L"+x2+","+ y1;
					this.canvas.path(result).attr({
						'stroke-width':1,
						'stroke': this.opts.lineColor,
						'stroke-opacity':0.3
					});
				}
				this.createText(x1-w-5,y1-2,text);
			}
		},
		/**
		 * 创建时间坐标轴
		 */
		 createTKLine:function(){
			var x,y,w,h,x1,y1,h1,y2,size=6;
			x = this.cond.lWidth;
			y = this.cond.zHeight+this.cond.zTop+this.cond.lHeight+15;
			w = this.opts.width-this.cond.zRight - this.cond.lWidth;
			h = this.cond.timeHeight;
			
			this.canvas.rect(x,y,w,h).attr({
				'stroke-width':1,
				'stroke':this.opts.lineColor,
				'stroke-opacity':0.5
			});
			var that = this;
			
			var a = this.cond.timeHeight/(this.opts.arrY[this.opts.arrY.length-1]-this.opts.arrY[0]);//线图的比例
			this.cond.tNodeWidth = (this.opts.width-this.cond.lWidth-this.cond.zRight)/(this.opts.data[0].data.length-1);//间隔宽度
			var result = "",startName="",endName = "";
			x1 = this.cond.lWidth;
			var n = Math.ceil(this.opts.data[0].data.length/50);
			var chk = false;
			if(this.opts.data[0].data.length>60){
				chk = true;
			}
			
			for(j=0;j<this.opts.data[0].data.length;j++){
				h1 = (this.opts.data[0].data[j].num - this.opts.arrY[0])*a;
				y1 = y-h1+this.cond.timeHeight;
				if(j>0){
					endName = this.opts.data[0].data[j].text;
					x1 += this.cond.tNodeWidth;
					if(j%n==0)
						result += "L"+[x1,y1];
					if(j==this.opts.data[0].data.length-1){
						result += "L"+[x1,y1];
					}
					if(chk&&endName!=null&&endName.length==10){
						if(endName.substring(0,4)!=this.opts.data[0].data[j-1].text.substring(0,4)){
							this.createText(x1,y+h-this.cond.timeHeight/5,endName.substring(0,4));
						}
					}
				}else{
					y2 = y1;
					result = "M"+[x1,y1];
					startName = this.opts.data[0].data[j].text;
				}
				
			}
			y1 = y+h;
			result += "L"+[x1,y1];
			result += "L"+[x,y1]+"z";

			this.canvas.path(result).attr({
				'fill':"#BFE0FD",
				'stroke-width':1,
				'stroke':"#BFE0FD",
				'stroke-opacity':0.3,
				'opacity':0.3
			});
			
			if(chk&&startName!=null&&startName.length==10){
				startName = startName.substring(0,7);
			}
			if(chk&&endName!=null&&endName.length==10){
				endName = endName.substring(0,7);
			}
			var y2 = y1-this.cond.timeHeight/5;
			var x3 = x1-$.feinno.charLen(endName)*6;
			var y3 = y2;

			this.createText(x,y2,startName);
			this.createText(x3,y3,endName);
			this.createTimeDrag(x1,y);
		},
		/**
		 * 创建时间坐标轴拖动条
		 */
		createTimeDrag:function(x,y){
			var h=this.cond.timeHeight;
			var endNode = this.canvas.rect(x,y,this.cond.twidth,h).attr({
				'fill':"#008ACD",
				'stroke-width':1,
				'stroke':"#008ACD",
				'stroke-opacity':0.8,
				'opacity':0.8,
				'cursor':"move"
			});
			endNode.data("maxX",x);
			endNode.data("minX",this.cond.lWidth);
			endNode.data("lx",x);
			endNode.data("ly",y);
			x -=this.cond.tNodeWidth*(this.opts.defNum-1);
			var startNode = this.canvas.rect(x,y,this.cond.twidth,h).attr({
				'fill':"#008ACD",
				'stroke-width':1,
				'stroke':"#008ACD",
				'stroke-opacity':0.8,
				'opacity':0.8,
				'cursor':"move"
			});
			
			startNode.data("maxX",endNode.data("maxX"));
			startNode.data("minX",this.cond.lWidth-this.cond.twidth);
			startNode.data("lx",x);
			startNode.data("ly",y);
			startNode.data("endNode",endNode);
			endNode.data("startNode",startNode);

			this.timeEvent(startNode);
			this.timeEvent(endNode);
			
			this.timeChange(startNode, endNode,true);
			
		},
		createText:function(x,y,text){
			return this.canvas.text(x,y,text).attr({
				'font': '12px Verdana',
				'text-anchor': 'start',
				'font-family':"宋体",
				'fill':"#000"
			});
		},
		/**
		 * 创建标题
		 */
		 creatTitle:function(){
			if(this.opts.title==null||this.opts.title.length<1)
				return ;
			var le = $.feinno.charLen(this.opts.title)*8;
			var x = Math.ceil((this.opts.width-le)/2);
			var y = 10;
			this.canvas.text(x,y,this.opts.title).attr({
				'font': '18px Verdana',
				'text-anchor': 'start',
				'font-family':"宋体",
				fill:this.opts.textColor
			});
		},
		/**
		 * 创建线图信息
		 */
		 creatLineInfo:function(){
			if(this.titleArr==null||this.titleArr.length<1)
				return ;
			var x = 0,x1 = 0;
			var y = this.cond.zHeight+this.cond.zTop+this.cond.lHeight;
			var y1 = this.cond.zTop;
			var len = $.feinno.charLen(this.titleArr[0])*this.cond.cLen;
			var n = Math.ceil((len+40)/this.cond.zWidth);

			var pathStr = "";
			var titlenode,lnode;
			for(var i=0;i<this.titleArr.length;i++){
				if(i%n==0){
					x = i*this.cond.zWidth+this.cond.lWidth;
					x1 = x-len/2-5;
					if(x1<1){
						x1 = 0;
					}
					titlenode = this.canvas.text(x1,y,this.titleArr[i]).attr({
						'font': '14px Verdana',
						'text-anchor': 'start',
						'font-family':"宋体",
						fill:"#000"
					});
					pathStr = "M"+x+","+y1+ "L"+x+","+(y-this.cond.lHeight/3*2);
					lnode = this.canvas.path(pathStr).attr({
						'stroke-width':1,
						'stroke':this.opts.lineColor,
						'stroke-opacity':0.3
					});
					
					this.nodeSet.push(titlenode);
					this.nodeSet.push(lnode);
				}
			}
		},
		/**
		 * 创建说明
		 */
		 createInfo:function(title,color,i){
			var x,y,x1,y1;
			if(this.opts.align=="bottom"){
				var a = Math.floor(i/this.cond.iHnum);
				var b = i%this.cond.iHnum;
				if(b>0)
					x = this.cond.iLeft+this.cond.lWidth+b*(this.cond.cMaxLen+this.cond.iWidth);
				else
					x = this.cond.iLeft+this.cond.lWidth;
				y = this.cond.zHeight+this.cond.zEHeight+this.cond.zTop+a*this.cond.iHeight;
				
				x1 = x+this.cond.rw+5;
				y1 = y;
			}else if(this.opts.align=="top"){
				var a = Math.floor(i/this.cond.iHnum);
				var b = i%this.cond.iHnum;
				if(b>0)
					x = this.cond.iLeft+this.cond.lWidth+b*(this.cond.cMaxLen+this.cond.iWidth);
				else
					x = this.cond.iLeft+this.cond.lWidth;
				y = a*this.cond.iHeight+10;

				if(this.opts.title!=null&&this.opts.title.length>0){
					y += this.cond.titleHeight;
				}

				x1 = x+this.cond.rw+10;
				y1 = y;
			}
			this.canvas.rect(x,y,this.cond.rw,this.cond.rh,0).attr({
				'fill': color,
				'stroke':color,
				'stroke-width':1
			});
			this.canvas.text(x1,y1,title).attr({
				'font': '12px Verdana',
				'text-anchor': 'start',
				'fill':"#000"
			});
		},
		/**
		 * 拖动时间轴，改变线图范围
		 */
		timeChange:function(startNode,endNode,chk){
			var sx = startNode.data("lx")+this.cond.twidth;
			var ex = endNode.data("lx");
			var y = startNode.data("ly");
			var w = ex-sx;
			if(this.timeNode!=null){
				this.timeNode.attr({x:sx,y:y,width:w});
			}else{
				this.timeNode = this.canvas.rect(sx,y,w,this.cond.timeHeight).attr({
					'fill':"#529AFD",
					'stroke-width':1,
					'stroke':"#529AFD",
					'stroke-opacity':0.1,
					'opacity':0.1
				});
			}
			var sNum = Math.ceil((sx-this.cond.lWidth-this.cond.twidth)/this.cond.tNodeWidth)-1;
			var eNume = Math.ceil((ex-this.cond.lWidth)/this.cond.tNodeWidth);
			sNum = sNum>=0?sNum:0;
			eNume = eNume>this.opts.data[0].data.length?this.opts.data[0].data.length:eNume;
			if(sNum+1>=eNume){
				return;
			}
			
			if(!chk){
				if((eNume-sNum)%(2*this.opts.data.length)!=0){//解决数据量大，拖动慢的问题
					return;
				}
			}
			var data = [];
			this.titleArr = [];
			for(i=0;i<this.opts.data.length;i++){
				n = 0;
				this.dataArr[i] = new Object();
				this.dataArr[i].name = this.opts.data[i].name;
				data = [];
				for(j=sNum;j<eNume;j++){
					this.titleArr[n] = this.opts.data[i].data[j].text;
					data[n] =  this.opts.data[i].data[j];
					n++;
				}
				this.dataArr[i].data = data;
			}
			
			this.nodeSet.forEach(function(node){
				node.remove();
			});
			this.nodeSet.clear();
			this.cond.maxLen =  this.dataArr[0].data.length;
			
			this.changeLineBar(chk);
		},
		/**
		 * 节点事件绑定
		 */
		 eventNode:function(d,n) {
			var that = this;
			d.mouseover(function (e) {
				var e = e||window.event;
				var num = this.data("num");
				that.mouseShowEvent(num,e.clientX,e.clientY);
			}).mouseout(function () {

			}).click(function(){
				var obj = {id:this.data("id"),name:this.data("name"),text:this.data("text"),num:this.data("num")};
				if(that.opts.click!=null){
					that.opts.click(obj);
				}
			}).dblclick(function(){
				var obj = {id:this.data("id"),name:this.data("name"),text:this.data("text"),num:this.data("num")};
				if(that.opts.dblclick!=null){
					that.opts.dblclick(obj);
				}
				
			});
		},
		/**
		 * 节点线条事件绑定
		 */
		 eventLine:function(d) {
			 var that = this;
			 d.mouseover(function (e) {
				var e = e||window.event;
				var num = this.data("line").data("num");
				that.mouseShowEvent(num,e.clientX,e.clientY);
			 }).mouseout(function () {
				var num = this.data("line").data("num");
				that.mouseHideEvent(num);
			 });
		},
		/**
		 * 拖动时间事件
		 * @param node
		 */
		timeEvent:function(node){
			var that = this;
			node.drag(function (dx, dy) {
				var mx = this.lx+dx;
				var minX = this.data("minX"),maxX = this.data("maxX")
				if(mx<minX||mx>maxX){
					return ;
				}
				var startNode = this.data("startNode");
				if(startNode!=null){
					if(mx<startNode.data("lx")+that.cond.twidth){
						return ;
					}
				}else{
					startNode = this;
				}
				var endNode = this.data("endNode");
				if(endNode!=null){
					if(mx>endNode.data("lx")-that.cond.twidth){
						return ;
					}
				}else{
					endNode = this;
				}
				this.attr({x:mx});
				this.data("lx",mx);
				that.timeChange(startNode, endNode,false);
			},function () {
				this.lx = this.data("lx");
			}, function () {
				var startNode = this.data("startNode");
				if(startNode==null){
					startNode = this;
				}
				var endNode = this.data("endNode");
				if(endNode==null){
					endNode = this;
				}
				that.timeChange(startNode, endNode,true);
				if(that.opts.drag!=null){
					var obj = {start:that.dataArr[0].data[0].text,end:that.dataArr[0].data[that.dataArr[0].data.length-1].text};
					that.opts.drag(obj);
				}
			});
		},
		/**
		 * 鼠标滑到节点，显示效果
		 */
		 mouseShowEvent:function(num,x,y){
			if(this.oldNum!=null&&this.oldNum!=num){//处理由于节点太多，线条来不及隐藏
				this.mouseHideEvent(this.oldNum);
			}
			var obj = $("#"+this.getInofId());
			var line = this.lines[num].data("line");

			if(x>this.opts.width/2){
				x = x-obj.width()-15;
			}else{
				x +=15;
			}
			y -= obj.height()/2;
			y += $(document).scrollTop();

			for(var i=0;i<this.nodes.length;i++){
				this.nodes[i][num].toFront().show();
			}
			var str = "",title="";
			if(this.opts.infoTitle!=null&&this.opts.infoTitle.length>0){
				title = this.opts.infoTitle;
				for(var i=0;i<this.dataArr.length;i++){
					if(this.opts.perc){
						str += "<div style=\"height:20px;\">"+this.dataArr[i].data[num].text+"："+($.feinno.toFixed(this.dataArr[i].data[num].num*100,2)+"%")+"</div>";
					}else{
						str += "<div style=\"height:20px;\">"+this.dataArr[i].data[num].text+"："+$.feinno.formatNum(this.dataArr[i].data[num].num)+"</div>";
					}
				}
			}else{
				for(var i=0;i<this.dataArr.length;i++){
					if(this.opts.perc){
						str += "<div style=\"height:20px;\">"+this.dataArr[i].name+"："+($.feinno.toFixed(this.dataArr[i].data[num].num*100,2)+"%")+"</div>";
					}else{
						str += "<div style=\"height:20px;\">"+this.dataArr[i].name+"："+$.feinno.formatNum(this.dataArr[i].data[num].num)+"</div>";
					}
					
					title = this.dataArr[i].data[num].text;
				}
			}
			obj.find("#info_title").html(title);
			obj.find("#info_content").html(str);
			obj.css({left:x,top:y});
			obj.show();
			line.show();
			this.oldNum = num;
		},
		/**
		 * 鼠标移出节点，显示效果
		 */
		 mouseHideEvent:function(num){
			var line = this.lines[num].data("line");
			for(var i=0;i<this.nodes.length;i++){
				if(this.nodes[i][num])
					this.nodes[i][num].hide();
			}
			$("#"+this.getInofId()).hide();
			if(line!=null)
				line.hide();
		},
		/**
		 * 创建信息显示框
		 */
		creatInfo:function(){
			var info = "<div id=\""+this.getInofId()+"\" style=\"background-color:#666666;position:absolute;alpha(opacity=80);-moz-opacity:0.8;-khtml-opacity: 0.8;opacity: 0.8;display:none;border:1px solid #ccc;border-radius:6px;\">";
			info += "<div style=\"padding-left:25px;padding-right:25px;padding-top:5px;padding-bottom:10px;text-align:center;color:#fff;\" id=\"info_title\"></div>";
			info += "<div style=\"padding-left:10px;padding-right:10px;color:#fff;\" id=\"info_content\"></div>";
			info += "</div>";
			$("#"+this.id).append(info);
		},
		/**
		 * 信息提示框ID
		 * @returns {String}
		 */
		getInofId:function(){
			return "lineChart_info_"+this.id;
		},
		create:function(){
			if(this.opts.width==null){
				this.opts.width = $("#"+this.id).width();
				if(this.opts.width==null||this.opts.width<0){
					this.opts.width = 1000;
				}
			}
			if(this.opts.height==null){
				this.opts.height = $("#"+this.id).height();
				if(this.opts.height==null||this.opts.height<0){
					this.opts.height = 400;
				}
			}
			this.createLineBar();
			this.creatInfo();
		}
	};
	var feinnoObj = {};
	$.fn.feinnoLine = function(opts){
		var id = $(this).attr("id");
		feinnoObj[id] = jQuery.extend(true,{},chartLine);
		feinnoObj[id].id = id;
		feinnoObj[id].opts = jQuery.extend(false,$.fn.feinnoLine.defaults,opts || {});//参数处理
		feinnoObj[id].create();
	};
	$.fn.feinnoLine.defaults={
		title:"",
		data:[],
		color:['#6495ed','#ff7f50','#87cefa','#da70d6',
		       '#32cd32',
		       '#ff69b4','#ba55d3','#cd5c5c','#ffa500','#40e0d0',
		       '#1e90ff','#ff6347','#7b68ee','#00fa9a','#ffd700',
		       '#6699FF','#ff6666','#3cb371','#b8860b','#30e0e0'],
       width:null,
       height:null,
       align:"top",
       textColor:"#AAAAAA",//文字颜色
       lineColor:"#AAAAAA",//坐标线条颜色
       unit:"",//点事数值单位
       timeView:false,//是否显示时间拖动轴
       arrY:[],//Y轴刻度值
       perc:false,//是否是百分比
       defNum:5,//默认显示节点个数
       infoTitle:"",
       perc:false,//是否是百分比
       click:null,//节点点击事件
       dblclick:null,//节点双击时间
       drag:null,//时间轴拖动触发事件
	   callback: function() {return false;}
	};
})(window.jQuery);
  