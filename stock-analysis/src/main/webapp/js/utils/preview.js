//**********************图片上传预览插件************************
//作者：lilinbing(2013-09-13)
//版本：1.0
//说明：图片上传预览插件
//上传的时候可以生成固定宽高范围内的等比例缩放图
//参数设置：
//width                     存放图片固定大小容器的宽
//height                    存放图片固定大小容器的高
//imgDiv                    页面DIV的JQuery的id
//imgType                   数组后缀名
//**********************图片上传预览插件*************************
(function($) {
	jQuery.fn.extend({
		imagePreview: function(opts) {
			opts = jQuery.extend({
				width: 100,
				height: 100,
				imgDiv: "#previewImg",
				imgForm: "#imgForm",
				imgType: ["gif", "jpeg", "jpg", "bmp", "png"],
				callback: function() { return false; }
			}, opts || {});
			var _self = this;
			var _this = $(this);
			var imgDiv = $(opts.imgDiv);
			imgDiv.css({width:opts.width+"px",height:opts.height+"px"});

			showImage = function(){
				var infilename = _this.attr("name");
				_this.attr("name","fileImage");
				$(opts.imgForm).ajaxSubmit({
					url :base + '/manager/preview/previewImage.' + actionExt,
					dataType : "json",
					type:"POST",
					success: function (res) { 
						if(res.chk==0){
							imgDiv.attr({src:base + '/servlet/previewImg?filename='+res.filename});
							imgDiv.show();
						}else{
							$.messager.alert("提示", "请选择图片!", "error");
						}
						_this.attr("name",infilename);
					} 
				});
			};
			_this.change(function() {
				if (this.value) {
					if (!RegExp("\.(" + opts.imgType.join("|") + ")$", "i").test(this.value.toLowerCase())) {
						alert("图片类型必须是" + opts.imgType.join("，") + "中的一种");
						this.value = "";
						return false;
					}
					if (this.files && this.files.item(0)){
						var reader = new FileReader();
						reader.onload =function(evt){
							imgDiv.attr({src: evt.target.result});
						}
						reader.readAsDataURL(this.files.item(0));
						imgDiv.show();
					}else{
						var Sys = {};
					    var ua = window.navigator.userAgent.toLowerCase();
					    var s;
					    (s = ua.match(/msie ([\d.]+)/)) ? Sys.ie = s[1] :
					    (s = ua.match(/firefox\/([\d.]+)/)) ? Sys.firefox = s[1] :
					    (s = ua.match(/chrome\/([\d.]+)/)) ? Sys.chrome = s[1] :
					    (s = ua.match(/opera.([\d.]+)/)) ? Sys.opera = s[1] :
					    (s = ua.match(/version\/([\d.]+).*safari/)) ? Sys.safari = s[1] : 0; 
					    if(Sys.ie<="6.0"){
							var image = new Image();
							image.src = 'file:///' + this.value;
							imgDiv.attr('src', image.src);
							imgDiv.show();
						}else{
							showImage();
				    	}
					}
				}
			});
		}
	});
})(jQuery);

