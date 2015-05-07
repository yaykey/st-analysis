<%@ page language="java" pageEncoding="UTF-8"%>
<table border="1" cellpadding="1" cellspacing="1">
	<thead>
		<tr>
			<th>DATE</th>
			<th>open</th>
			<th>close</th>
			<th>high</th>
			<th>low</th>
			<th>HIGHTIMEID</th>
			<th>LOWTIMEID</th>
			<th>HIGHPER</th>
			<th>LOWPER</th>
			<th>OPENPER</th>
			<th>CLOSEPER</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${st.next5Day}" var="subst">
			<tr>
				<td>
					<div>
						<fmt:formatDate value="${subst.date}" pattern="yyyy-MM-dd" />
					</div>
				</td>
				<td>${subst.open}</td>
				<td>${subst.close}</td>
				<td>${subst.high}</td>
				<td>${subst.low}</td>
				<td>${subst.highTimeId}</td>
				<td>${subst.lowTimeId}</td>
				<td class="sub-high-per">${subst.highPer}</td>
				<td class="sub-low-per">${subst.lowPer}</td>
				<td>${subst.openPer}</td>
				<td>${subst.closePer}</td>
			</tr>
		</c:forEach>
	</tbody>
</table>