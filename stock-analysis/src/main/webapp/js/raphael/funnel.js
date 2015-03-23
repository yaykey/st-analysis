var Funnel = {
	nodes:null,//初始化节点参数
	canvas:null,//画布
	defaults:null,//默认参数
	initialize:function(){//默认参数
		this.defaults = new Object();
		this.defaults.width = 600;//画布宽度
		this.defaults.height = 400;//画布高度
		this.defaults.chartDiv = "fnnelchart";//画布div
		this.defaults.top = $("#"+this.defaults.chartDiv).scrollTop();
		this.defaults.color = ["#ee0f10","#eeed40","#48ee26","#3e43ee","#ee2c86","#147064","#450b4d","#424d1f","#8ccd15"];
	},
	/**
	 * 初始化
	 */
	init:function(dataArr){
		this.initialize();
		this.createCanvas();
		this.setSource(dataArr);
	},
	/**
	 * 创建画布
	 */
	createCanvas: function () {
		this.canvas = new Raphael(this.defaults.chartDiv, this.defaults.width, this.defaults.height);
	},
	/**
	 * 初始化数据
	 * lifeArr1 当月的数据
	 * lifeArr2 下个月的数据
	 */
	setSource: function (dataArr) {
		if(dataArr==null){
			return ;
		}
		this.nodes = [];
		var i,j;
		for (i=dataArr.length-1;i>0;--i){
			for(j=0;j<i;++j){
				if(dataArr[j].num > dataArr[j+1].num){
					var a = dataArr[j];
					dataArr[j] = dataArr[j+1];
					dataArr[j+1] = a;
				}	
			}
		} 
		
		for(i=0;i<dataArr.length;i++){
			this.nodes[i] = {
					id:"node_"+i,
					index:i,
					title:dataArr[i].title,
					num:dataArr[i].num
				}
		}
		
	},
	createNode:function(n){
		var that = this;
		var l=30,g=10;
		var a = (this.defaults.width-200)/2;
		var b = this.defaults.height-20;
		var b1 = b/this.nodes.length;
		var a1 = b1*(a/b);
		
		var x,y,x1,y1,x2,y2,x3,y3;
		var result;
        for(var i=0;i<this.nodes.length;i++){
        	x = i*a1+l;
    		y = i*b1+g;
    		x1 = (2*a-i*a1)+l;
    		y1 = y;
    		
    		x2 = x+a1;
    		y2 = y+b1;
    		
    		x3 = x1-a1;
    		y3 = y2;
    		result = "M"+x+","+y+"L"+x1+","+y1+"S"+x1+","+y1+"L"+x3+","+y3+"S"+x3+","+y3+"L"+x2+","+y2+"M"+x2+","+y2+"L"+x+","+y;
            this.canvas.path(result).attr({
            		'fill':that.defaults.color[i],
    				'stroke-width':1,
    				'stroke':that.defaults.color[i],
    				'stroke-opacity':0.8
    		 });
            /**
    		 * 创建标题矩形框
    		 */
    		this.canvas.rect(2*a-a/3, i*b1+b1/3,a,35,2).attr({
    			'fill': "#abc0cd",
    			'stroke': "#267fcd",
    			'stroke-width':1,
    			'opacity':0.5
    		});
    		this.canvas.text(2*a-a/4, i*b1+b1/2+5,this.nodes[i].title).attr({
    			'font': '16px Verdana',
    			'text-anchor': 'start',
    			'font-family':"宋体",
    			 fill:"#000"
    		});
//            result = "M"+(x1-15)+","+(y1+b1/2)+"L"+(this.defaults.width-120)+","+(y1+b1/2);
//            this.canvas.path(result).attr({
//    				'stroke-width':1,
//    				'stroke':"#000",
//    				"stroke-dasharray":"- ",
//    				'stroke-opacity':0.8
//    		 });
//            this.createArrow((x1-15), (y1+b1/2), (this.defaults.width-120), (y1+b1/2), 6);
//            this.canvas.text((this.defaults.width-110), (y1+b1/2),this.nodes[i].title).attr({
//    			'font': '16px Verdana',
//    			'text-anchor': 'start',
//    			'font-family':"宋体",
//    			 fill:"#000"
//    		});
            
            
        }
        
        x = a1;
        y = b1+g;
        
        x1 = 4.5*a1;
		y1 = 4.5*b1+g;
        this.getArr(x,y,x1,y1,10);
	},
	getArr:function(x1, y1, x2, y2, size){
		var angle = Raphael.angle(x1, y1, x2, y2);//得到两点之间的角度
		
		var result = "M"+x1+","+ y1+"L" + x2 + "," + y2;
        this.canvas.path(result).attr({
				'stroke-width':size/2,
				'stroke': "#abc0cd",
				'stroke-opacity':0.8
		});
        /**
		 * 创建标题文字
		 */
		var text = this.canvas.text(x2*0.6-30,y2*0.6,"向  下  包  含").attr({
			'font': '20px Verdana',
			'font-weight':600,
			'font-family':"宋体",
			'opacity':0.5,
			 fill:"#000"
		}).transform("r"+(180+angle));
		x2 +=3;
		y2 +=6;
		
        var a45 = Raphael.rad(angle - 45);//角度转换成弧度
        var a45m = Raphael.rad(angle + 45);
        var x2a = x2 + Math.cos(a45) * size;
        var y2a = y2 + Math.sin(a45) * size;
        var x2b = x2 + Math.cos(a45m) * size;
        var y2b = y2 + Math.sin(a45m) * size;
        
        var result = "M"+ x2a+","+y2a+ "L"+x2+","+ y2+"S"+x2+","+ y2+","+x2b+","+y2b+"M"+x2b+","+y2b+"L"+x2a+","+y2a;
        this.canvas.path(result).attr({
        		'fill': "#abc0cd",
				'stroke-width':2,
				'stroke': "#abc0cd",
				'stroke-opacity':0.8
		 });
       
	},
	createArrow:function(x1, y1, x2, y2, size){
		var angle = Raphael.angle(x1, y1, x2, y2);//得到两点之间的角度
		var a45 = Raphael.rad(angle - 45);//角度转换成弧度
        var a45m = Raphael.rad(angle + 45);
        var x2a = x2 + Math.cos(a45) * size;
        var y2a = y2 + Math.sin(a45) * size;
        var x2b = x2 + Math.cos(a45m) * size;
        var y2b = y2 + Math.sin(a45m) * size;
        
        var result = "M"+ x2a+","+y2a+ "L"+x2+","+ y2+"S"+x2+","+ y2+","+x2b+","+y2b+"M"+x2b+","+y2b+"L"+x2a+","+y2a;
        return this.canvas.path(result).attr({
        		'fill': "#000",
				'stroke-width':1,
				'stroke': "#000",
				'stroke-opacity':0.5
		 });
	},
	createFunnel:function(dataArr){
		this.init(dataArr);
		this.createNode();
	}
}