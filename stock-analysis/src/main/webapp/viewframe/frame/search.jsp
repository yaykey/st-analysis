<%@ page language="java" pageEncoding="UTF-8"%>
<style>
	
</style>
<div id="nav-search-bar" class="nav-search-bar">
	<table cellPadding="0" cellSpacing="0" border="0" width="100%">
		<tr>
			<td>
				<input class="nav-search-input nav-search-input-default" type="text"
		value="请输入指标/报表/目录名称">
			</td>
			<td>
				<button class="nav-search-del" title="删除" style="display: none;">&nbsp;</button>
			</td>
		</tr>
	</table>
	
	
	<div class="nav-search-content" style="display:none;">
		<ul></ul>
	</div>
</div>

<script type="text/javascript">

$(document).ready(function() {
	var searchBar = $("#nav-search-bar");
	var searchIpt = searchBar.find(".nav-search-input");
	var delBtn = searchBar.find(".nav-search-del");
	var ulNode = searchBar.find("ul");
	var searchContent = searchBar.find(".nav-search-content");
	
	var defVal = "请输入指标/报表/目录名称";
	var searchHandlerTimeId = -1;
	var delay = 100;
	var oldVal = "";
	var searchUrl = "/search/search.feinno";
	
	
	searchIpt.bind("focus", function () {
		var val = $(this).val();
		if (val == defVal) {
			$(this).val("");
		}
		
		searchHandlerTimeId = window.setInterval(searchHandler, delay);
		
	});
	
	searchIpt.bind("blur", function () {
		window.clearInterval(searchHandlerTimeId);
		
		var val = $(this).val();
		
		if (!val) {
			$(this).val(defVal);
			
			delHandler();
		}
		
	});
	
	delBtn.bind("click", function(){
		
		delHandler();
	});
	
	function delHandler () {
		searchContent.hide();
		delBtn.hide();
		searchIpt.addClass("nav-search-input-default");
		$(searchIpt).val(defVal);
		oldVal = "";
	}
	
	function searchHandler () {
		
		var newVal = $.trim($(searchIpt).val());
		
		//if (newVal && newVal != oldVal) {
		if (newVal && "" != $.trim(newVal)) {
			if (newVal != oldVal) {
				oldVal = newVal;
				jQuery.ajax({
					type : "POST",
					url : base + searchUrl,
					dataType : 'text',
					data : {
						"val" : newVal
						//,"nocache" : new Date().getTime()
					},
					success : function(response) {
						if (response) {
							var items = eval(response);
							loadSearchContent(items);
						}
					},
					error : function(XMLHttpRequest, textStatus, errorThrown) {
						
					}
				});
			}
		} else {
			ulNode.empty();
			ulNode.append("没数据!");
		}
		
	} 
	
	
	
	function loadSearchContent (items) {
		
		ulNode.empty();
		
		searchContent.show();
		delBtn.show();
		searchIpt.removeClass("nav-search-input-default");
		
		var str = "";
		if (items.length > 0) {
			for (var i=0; i<items.length; i++) {
				var item = items[i];
				var liNode = $("<li>");
				
				
				
				//$(liNode).text(item.fname);
				
				var linkNode = $("<a>");
				
				$(linkNode).attr("fid", item.fid);
				$(linkNode).attr("mid", item.mid);
				$(linkNode).attr("url", item.url);
				
				/* if (item.parentid) {
					$(linkNode).attr("parentid", item.parentid);
				} */
				
				
				$(linkNode).attr("href", "javascript:void(0);");
				$(linkNode).text(item.fname);
				
				$(linkNode).bind("click", function(){
					var fid = $(this).attr("fid");
					var mid = $(this).attr("mid");
					
					//var parentid = $(this).attr("parentid");
					
					loadNav(mid, fid);
					
					var url = $(this).attr("url");
					
					loadURLContent({
						'url' : url,
						'mid' : mid,
						'fid' : fid
						//,
						//'parentid' : parentid
					});
				});
				
				
				liNode.append(linkNode);
				ulNode.append(liNode);			
			}
		} else {
			ulNode.append("没数据!");
		}
		
		
	}
	
	var fNode,mNode;
	
	function loadNav (mid, fid) {
		
		var navContent = "";
		
		fNode = $("#" + fid + "_f_li");
		mNode = $("#" + mid + "_m_dl");
		loadURL = fNode.attr("url");
		var mName = $.trim(mNode.find(".nav-report-group-title").find("span").text());
		var fName = $.trim(fNode.find("a").text());
		
		
		navContent += '<span>' + mName + '</span> > ';
		
		var pid = fNode.attr("pid");
		var isLeaf = fNode.attr("leaf");
		
		if (isLeaf == 1 && pid != null && "null" != pid) {
			var pLiNode = $("#" + pid + "_f_li");
			var pNmae = pLiNode.find(".nav-title").text();
			navContent += '<a target="_self" href="' + base + '/main/change.feinno' + '?mid=' + mid + '&fid=' + pid + '">' + pNmae + '</a> > ';
		}
		
		//navContent += '<a target="_self" href="' + base + '/main/index.feinno' + '?mid=' + mid + '&fid=' + fid + '">' + fName + '</a>';
		navContent += '<span>' + fName + '</span>';
		
		$("#vf_nav").empty();
		$("#vf_nav").append(navContent);
	}
	
	function loadURLContent (opt) {
		$("#vf_content").empty();
		//$("#vf_content").append("数据加载中!");
		$("#vf_content").append(loadContent);
				
		if ($.trim(opt.url)) {
			jQuery.ajaxSetup({ cache: true });
			
			var data = {
					"fid" : opt.fid,
					"mid" : opt.mid	
			}
			
			if (opt.parentid) {
				data.parentid = opt.parentid
			}
			
			jQuery.ajax({
				type : "GET",
				url : base + opt.url,
				dataType : 'text',
				data : data,
				/*data : {
					"fid" : opt.fid,
					"mid" : opt.mid
					,
					"parentid" : parentid,
					"dimTypeId" : dimTypeId,
					"dimId" : dimId,
					"photoType" : photoType,
					"photoId" : photoId
					//,"nocache" : new Date().getTime()
				},*/
				success : function(response) {
					window.setTimeout(function(){
						$("#vf_content").empty();
						$("#vf_content").append(response);
					},1);
					
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					var content = "";
					if (XMLHttpRequest.status == "404") {
						content = "<div>地址错误!</div>";
					} else if (XMLHttpRequest.status == "500") {
						content = "<div>服务器错误!</div>";
					} else {
						//content = textStatus + ":" + errorThrown
						content = 
							"<div style='display:none;'>" +
								"XMLHttpRequest.status=" + XMLHttpRequest.status +
								";textStatus=" + textStatus +
								";errorThrown=" + errorThrown +
							"</div>";
					}
					
					$("#vf_content").empty();
					$("#vf_content").append(content);
				}
			});
		} else {
			$("#vf_content").empty();
			$("#vf_content").append("<div>地址为空!</div>");
		}
	
	}
	
});

</script>