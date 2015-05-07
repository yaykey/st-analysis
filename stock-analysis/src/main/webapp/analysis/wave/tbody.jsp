<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<tbody>
	<c:forEach items="${requestScope.list}" var="st">
		<tr class="${(st.highPer>5)?('high'):('')} ${(st.lowPer<-5)?('low'):('')}">
			<td>
				<div>
					<fmt:formatDate value="${st.date}" pattern="yyyy-MM-dd" />
				</div>
			</td>
			<td class="diff">${st.dateDec}</td>
			<td class="SUM-DATE"></td>
			<td>${st.open}</td>
			<td>${st.close}</td>
			<td>${st.high}</td>
			<td>${st.low}</td>
			<td>${st.highTimeId}</td>
			<td>${st.lowTimeId}</td>
			<td>${st.highPer}</td>
			<td>${st.lowPer}</td>
			<td><fmt:formatNumber value="${st.MA5}" pattern="#.#####" /></td>
			<td><fmt:formatNumber value="${st.MA10}" pattern="#.#####" /></td>
		</tr>
		<tr>
			<td></td>
			<td colspan="12">
				<%@ include file="/analysis/wave/sub-next5.jsp"%>
			</td>
		</tr>
	</c:forEach>
</tbody>