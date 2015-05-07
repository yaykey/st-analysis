<%@ page language="java" pageEncoding="UTF-8"%>
<table class="sub-table" border="0" cellpadding="0" cellspacing="0">
	<thead>
		<tr>
			<th class="thead-date">date</th>
			<th class="thead-open">open</th>
			<th class="thead-close">close</th>
			<th class="thead-high">high</th>
			<th class="thead-low">low</th>
			<th class="thead-hightimeid">hightimeid</th>
			<th class="thead-lowtimeid">lowtimeid</th>
			<th class="thead-highper">highper</th>
			<th class="thead-lowper">lowper</th>
			<th class="thead-openper">openper</th>
			<th class="thead-closeper">closeper</th>
			<th class="thead-amplitude">amplitude</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${st.next5Day}" var="subst">
			<tr>
				<td class="tbody-date">
					<div>
						<a href='${base }/mycharts/charts2.feinno?
							stockCode=sz${subst.stock}
							&dateId=<fmt:formatDate value="${subst.date}" pattern="yyyyMMdd" />' 
								target="_blank">
						<fmt:formatDate value="${subst.date}" pattern="yyyy-MM-dd" />
						</a>
					</div>
				</td>
				<td class="tbody-open">${subst.open}</td>
				<td class="tbody-close">${subst.close}</td>
				<td class="tbody-high">${subst.high}</td>
				<td class="tbody-low">${subst.low}</td>
				<td class="tbody-hightimeid">${subst.highTimeId}</td>
				<td class="tbody-lowtimeid">${subst.lowTimeId}</td>
				<td class="tbody-highper sub-high-per">${subst.highPer}</td>
				<td class="tbody-lowper sub-low-per">${subst.lowPer}</td>
				<td class="tbody-openper tbody-date">${subst.openPer}</td>
				<td class="tbody-closeper tbody-date">${subst.closePer}</td>
				<td class="tbody-amplitude">${subst.amplitude}</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
