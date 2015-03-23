<%@ page language="java" pageEncoding="UTF-8" import="com.st.framework.utils.encrypt.impl.EncryptorBase64Impl"%>
<%@include file="/commons/taglibs.jsp"%>
<html>
	<head>
		<title>综合智能应用平台</title>
		<%@include file="/manager/commons/meta.jsp"%>
		<script type="text/javascript">
			$(document).ready(function(){
				$("#resource").bind('click',function(){
					$("#encryptform").attr("action","${baseManager}/encrypt/encrypt.${actionExt}");
					$("#encryptform").submit();
				});
			});
		</script>
		
	</head>
			<form id="encryptform" name="encryptform" action="#"  method="post">
			
			
			<table>
				<tr>
					<td>源文件：</td>
					<td><input type="text" id="resourceText" name="resourceText" value="${source}"><input type="button" value="加密" id="resource"></td>
				</tr>
				<tr>
					<td>加密后：</td><td><span id="encryptResult">${result}</span>
					</td>
				</tr>
			</table>
			
			</form>
		
</html>