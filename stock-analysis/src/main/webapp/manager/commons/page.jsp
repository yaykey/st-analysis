<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<tr>
<th colspan="13" align="right" style="text-align:right; padding-right:10px; background:none; 
	height:22px; color: #4F6B72; font-size: 12px;">
当前： 第${pageInfo.page}页  共${pageInfo.totalPage}页 | 
<img src="${baseManager}/images/page/navLeft.gif" style="cursor: pointer;" onclick="javascript:goPage('1');" width="14" height="16" alt="第一页"/>
<img src="${baseManager}/images/page/left.gif" style="cursor: pointer;" onclick="javascript:goPage('${pageInfo.previousPage}');" width="16" height="16" alt="上一页" />
<img src="${baseManager}/images/page/right.gif" style="cursor: pointer;" onclick="javascript:goPage('${pageInfo.nextPage}');" width="16" height="16"  alt="下一页"/>
<img src="${baseManager}/images/page/navRight.gif" style="cursor: pointer;" onclick="javascript:goPage('${pageInfo.totalPage}');" width="14" height="16"  alt="最后一页" />
<input type="text" id="toPage" name="toPage" size="3" style="width:20px;text-align: center;"/>
<input type="button" class="button" value="GO"  style="cursor: pointer;" onclick="javascript:goPage(document.getElementById('toPage').value);" 
	onmouseover="this.className__='buttonhover'" onmouseout="this.className__='button'"/>  
</th>
</tr>