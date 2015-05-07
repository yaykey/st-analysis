<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<tbody>
	<c:forEach items="${requestScope.list}" var="st">
		<tr class="${(st.highPer>5)?('high'):('')} ${(st.lowPer<-5)?('low'):('')}">
			<td class="tbody-stock">${st.stock}</td>
			<td class="tbody-date">
				<div>
					<fmt:formatDate value="${st.date}" pattern="yyyy-MM-dd" />
				</div>
			</td>
			<%-- <td class="tbody-datedec diff">${st.dateDec}</td>
			<td class="tbody-sumdate SUM-DATE">&nbsp;</td> --%>
			<td class="tbody-open">${st.open}</td>
			<td class="tbody-close">${st.close}</td>
			
			<td class="tbody-high">${st.high}</td>
			<td class="tbody-low">${st.low}</td>
			
			<td class="tbody-hightimeid">${st.highTimeId}</td>
			<td class="tbody-lowtimeid">${st.lowTimeId}</td>
			
			<td class="tbody-highper">${st.highPer}</td>
			<td class="tbody-lowper">${st.lowPer}</td>
			
			<td class="tbody-openper">${st.openPer}</td>

<c:choose>
	<c:when test="${st.closePer <=-9}">
		<td class="tbody-closeper close-per" style="color: #00ff00">${st.closePer}</td>
	</c:when>
	<c:when test="${st.closePer <=-8}">
		<td class="tbody-closeper close-per" style="color: #00ff20 ">${st.closePer}</td>
	</c:when>
	<c:when test="${st.closePer <=-7}">
		<td class="tbody-closeper close-per" style="color: #00ff40">${st.closePer}</td>
	</c:when>
	<c:when test="${st.closePer <=-6}">
		<td class="tbody-closeper close-per" style="color: #00ff60">${st.closePer}</td>
	</c:when>
	<c:when test="${st.closePer <=-5}">
		<td class="tbody-closeper close-per" style="color: #00ff80">${st.closePer}</td>
	</c:when>
	<c:when test="${st.closePer <=-4}">
		<td class="tbody-closeper close-per" style="color: #00ffa0">${st.closePer}</td>
	</c:when>
	<c:when test="${st.closePer <=-3}">
		<td class="tbody-closeper close-per" style="color: #00ffc0">${st.closePer}</td>
	</c:when>
	<c:when test="${st.closePer <=-2}">
		<td class="tbody-closeper close-per" style="color: #00ffff">${st.closePer}</td>
	</c:when>
	
	<c:otherwise>
		<td  class="tbody-closeper close-per">${st.closePer}</td>
	</c:otherwise>
</c:choose>
			
			<%-- <td class="tbody-ma5"><fmt:formatNumber value="${st.MA5}" pattern="#.#####" /></td>
			<td class="tbody-ma10"><fmt:formatNumber value="${st.MA10}" pattern="#.#####" /></td> --%>
			<td class="tbody-amplitude">${st.amplitude}</td>
		</tr>
		<tr>
			<td></td>
			<td colspan="12">
				<%@ include file="/analysis/wave/sub-next5.jsp"%>
			</td>
		</tr>
	</c:forEach>
</tbody>
