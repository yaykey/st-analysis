<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<html>
<head>
	
</head>
<body>
	<%@include file="/date/date.jsp" %>
	<div class="control-bar-wrapper filter-control-bar"
		id="ControlBarWrapper" style="height: auto;">
		<div class="bg-iframe" id="ControlBar" style="width: auto;">
			<div class="control-bar">
				<div class="r time-picker-bar">
					<!-- <a href="javascript:void(0)" class="time-picker combobox" id="timePicker"> -->
						<input type="text" id="date"  name="data"  class="inp_cala" readonly="readonly" 
                			style="width:135px; height: 22px; margin-left:5px; margin-right:5px;">
				</div>
				<div id="timeSpan" class="r time-span clearfix">
					<a href="javascript:void(0)" timetype="1" class="l first selected" id="day">日</a>
					<a href="javascript:void(0)" timetype="4" class="l " id="week">周</a>
					<a href="javascript:void(0)" timetype="2" class="l " id="month">月</a>
				</div>
				<div class="filter-text-wrapper" id="FilterBtn">
					<a layer="#FilterBox" title="点击收起高级筛选"
						class="filter-text toggleable selected">高级筛选</a>
				</div>
				<ul class="filter-box layer non-excludable" id="FilterBox">
					<li class="filter-lane first-lane" id="Channel"><div
							class="filter-tool-box">
							<div class="filter-tool-bar">
								<div class="filter-combox srhcom" id="Channel-Group-Combox">
									<a layer="#tangram-combobox--TANGRAM__u-List"
										id="tangram-combobox--TANGRAM__u" href="javascript:void(0)"
										class="combobox"><span class="text ellipsis" title="全部分组">全部分组</span><span
										class="arrow"></span>
									</a>
								</div>
								&nbsp;
								<div class="filter-combox"></div>
								<div class="filter-search">
									<span class="filter-search-icon"></span> <a
										style="display: none;" class="dialog-close"
										href="javascript:void(0)">×</a> <input type="text"
										value="搜索渠道" class="filter-search-input gray">
								</div>
								&nbsp;
							</div>
						</div> <a class="filter-more" href="javascript:void(0)">更多</a>
						<div class="group wrapped clearfix">
							<label class="label">渠道<b class="arrow-left"></b>
							</label>
							<div class="filter-expand filter-shrink">
								<div class="group-wrapper">
									<a href="javascript:void(0)" title="全部" data="0"
										class="selectable-item ellipsis"
										id="tangram-filterGroup--TANGRAM__s-0">全部</a><a
										href="javascript:void(0)" title="安机市场"
										data="8245937480008728677" class="selectable-item ellipsis"
										id="tangram-filterGroup--TANGRAM__s-8245937480008728677">安机市场</a><a
										href="javascript:void(0)" title="豌豆荚"
										data="4123379920845930593" class="selectable-item  ellipsis"
										id="tangram-filterGroup--TANGRAM__s-4123379920845930593">豌豆荚</a><a
										href="javascript:void(0)" title="机锋市场"
										data="395237942715141296" class="selectable-item selected ellipsis"
										id="tangram-filterGroup--TANGRAM__s-395237942715141296">机锋市场</a><a
										href="javascript:void(0)" title="安卓市场"
										data="3977017331002900577" class="selectable-item  ellipsis"
										id="tangram-filterGroup--TANGRAM__s-3977017331002900577">安卓市场</a><a
										href="javascript:void(0)" title="应用汇"
										data="8318272075369443188" class="selectable-item  ellipsis"
										id="tangram-filterGroup--TANGRAM__s-8318272075369443188">应用汇</a>
								</div>
							</div>
						</div>
					</li>
					<li class="filter-lane" id="Version"><div
							class="filter-tool-box" style="display: none;">
							<div class="filter-tool-bar">
								&nbsp;
								<div class="filter-combox"></div>
								<div class="filter-search">
									<span class="filter-search-icon"></span> <a
										style="display: none;" class="dialog-close"
										href="javascript:void(0)">×</a> <input type="text"
										value="搜索内容" class="filter-search-input gray">
								</div>
								&nbsp;
							</div>
						</div> <a class="filter-more" href="javascript:void(0)">更多</a>
						<div class="group wrapped clearfix">
							<label class="label">版本<b class="arrow-left"></b>
							</label>
							<div class="filter-expand filter-shrink">
								<div class="group-wrapper">
									<a href="javascript:void(0)" title="全部" data="0"
										class="selectable-item  ellipsis"
										id="tangram-filterGroup--TANGRAM__q-0">全部</a><a
										href="javascript:void(0)" title="2.0.1"
										data="3544383699023822848" class="selectable-item selected ellipsis"
										id="tangram-filterGroup--TANGRAM__q-3544383699023822848">2.0.1</a><a
										href="javascript:void(0)" title="1.1" data="13843061847097344"
										class="selectable-item  ellipsis"
										id="tangram-filterGroup--TANGRAM__q-13843061847097344">1.1</a><a
										href="javascript:void(0)" title="1.2" data="14124536823808000"
										class="selectable-item  ellipsis"
										id="tangram-filterGroup--TANGRAM__q-14124536823808000">1.2</a><a
										href="javascript:void(0)" title="2.0" data="13561591165353984"
										class="selectable-item  ellipsis"
										id="tangram-filterGroup--TANGRAM__q-13561591165353984">2.0</a><a
										href="javascript:void(0)" title="1.0" data="13561586870386688"
										class="selectable-item  ellipsis"
										id="tangram-filterGroup--TANGRAM__q-13561586870386688">1.0</a><a
										href="javascript:void(0)" title="1.4.1"
										data="3329336812521914417" class="selectable-item  ellipsis"
										id="tangram-filterGroup--TANGRAM__q-3329336812521914417">1.4.1</a><a
										href="javascript:void(0)" title="1.3" data="14406011800518656"
										class="selectable-item  ellipsis"
										id="tangram-filterGroup--TANGRAM__q-14406011800518656">1.3</a><a
										href="javascript:void(0)" title="1.4" data="14687486777229312"
										class="selectable-item  ellipsis"
										id="tangram-filterGroup--TANGRAM__q-14687486777229312">1.4</a>
								</div>
							</div>
						</div>
					</li>
				</ul>
				<div style="" class="filter-result-box" id="FilterResults">
                	<span class="filter-result-label">已筛选选项:</span>
                    <span class="filter-result" id="ChannelResult"><em class="result" title="机锋市场">机锋市场</em><em class="reset"></em></span>
                    <span class="filter-result" id="UserTypeResult"></span>
                    <span class="filter-result" id="VersionResult"><em class="result" title="2.0.1">2.0.1</em><em class="reset"></em></span>
                </div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
	
</script>
</html>
