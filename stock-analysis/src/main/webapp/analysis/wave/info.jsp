<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<div class="info">
	<table border="1" cellpadding="1" cellspacing="1">
		<thead>
			<tr>
				<th>highPerAvg</th>
				<th>lowPerAvg</th>
				<th>openAvg</th>
				<th>closeAvg</th>
				<th>dateAvg</th>
				<th>size</th>
				<th>orthersize</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td><fmt:formatNumber value="${infobean.highPerAvg }"
						pattern="#.###" /></td>
				<td><fmt:formatNumber value="${infobean.lowPerAvg }"
						pattern="#.###" /></td>
				<td><fmt:formatNumber value="${infobean.openAvg }"
						pattern="#.###" /></td>
				<td><fmt:formatNumber value="${infobean.closeAvg }"
						pattern="#.###" /></td>
				<td><fmt:formatNumber value="${infobean.dateAvg }"
						pattern="#.###" /></td>
				<td>${infobean.size }</td>
				<td>${infobean.otherSize }</td>


			</tr>
		</tbody>
	</table>
	<table border="1" cellpadding="1" cellspacing="1">

		<tr>
			<th>open U</th>
			<th>open D</th>
			<th>close U</th>
			<th>close D</th>
			<th>high U</th>
			<th>high D</th>
			<th>low U</th>
			<th>low D</th>

		</tr>

		<tr>
			<td>${infobean.nextCountInfo.openPerUp}</td>
			<td>${infobean.nextCountInfo.openPerDown}</td>
			<td>${infobean.nextCountInfo.closePerUp}</td>
			<td>${infobean.nextCountInfo.closePerDown}</td>
			<td>${infobean.nextCountInfo.highPerUp}</td>
			<td>${infobean.nextCountInfo.highPerDown}</td>
			<td>${infobean.nextCountInfo.lowPerUp}</td>
			<td>${infobean.nextCountInfo.lowPerDown}</td>

		</tr>
		
		<tr>
			<th>highPer Avg</th>
			<th>highPer Avg U</th>
			<th>highPer Avg D</th>

			<th>lowPer Avg</th>
			<th>lowPer Avg U</th>
			<th>lowPer Avg D</th>

			<th>closePer Avg</th>
			<th>closePer Avg U</th>
			<th>closePer Avg D</th>
		</tr>

		<tr>
			

			<td><fmt:formatNumber
					value="${infobean.nextCountInfo.highPerAvg}" pattern="#.###" />
			</td>
			<td><fmt:formatNumber
					value="${infobean.nextCountInfo.highPerAvgUp}" pattern="#.###" />
			</td>
			<td><fmt:formatNumber
					value="${infobean.nextCountInfo.highPerAvgDown}" pattern="#.###" />
			</td>

			<td><fmt:formatNumber
					value="${infobean.nextCountInfo.lowPerAvg}" pattern="#.###" /></td>
			<td><fmt:formatNumber
					value="${infobean.nextCountInfo.lowPerAvgUp}" pattern="#.###" /></td>
			<td><fmt:formatNumber
					value="${infobean.nextCountInfo.lowPerAvgDown}" pattern="#.###" />
			</td>


			<td><fmt:formatNumber
					value="${infobean.nextCountInfo.closePerAvg}" pattern="##.###" />
			</td>
			<td><fmt:formatNumber
					value="${infobean.nextCountInfo.closePerAvgUp}" pattern="#.###" />
			</td>
			<td><fmt:formatNumber
					value="${infobean.nextCountInfo.closePerAvgDown}" pattern="#.###" />
			</td>
		</tr>

	</table>


</div>