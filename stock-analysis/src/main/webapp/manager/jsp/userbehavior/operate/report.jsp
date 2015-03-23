<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/taglibs.jsp"%>
<h2 class="tit_1" style="text-align:left;">报表分类</h2>
<ul class="v4_btn clearfix" id="report_static">
 <c:forEach items="${requestScope.reportClassList}" var="reportClass">
 <li><a href="javascript:void(-1);" id="classid_${reportClass.classId}"  classtype="${reportClass.classType}" classid="${reportClass.classId}" title="${reportClass.className}" class="a4 sty1" onclick="funOperateStype('${reportClass.classId}')">
 	<c:choose>  
    <c:when test="${fn:length(reportClass.className) > 8}">  
        <c:out value="${fn:substring(reportClass.className, 0, 8)}" />  
    </c:when>  
   <c:otherwise>  
      <c:out value="${reportClass.className}" />  
  </c:otherwise>  
</c:choose>  
 </a></li>
 </c:forEach>  
</ul>		
<script type="text/javascript">
searchRptType();
funOperateStype($("#report_static").find("li").find("a").attr("classid"));
</script>