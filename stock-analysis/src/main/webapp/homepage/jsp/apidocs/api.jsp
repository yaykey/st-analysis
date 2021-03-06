<%@ page language="java" pageEncoding="UTF-8"%>
<style type="text/css">
.b1 {
	white-space-collapsing: preserve;
}

.b2 {
	margin: 1.0in 1.25in 1.0in 1.25in;
}

.p1 {
	text-align: justify;
	hyphenate: auto;
	font-family: 微软雅黑;
	font-size: 12pt;
}

.p2 {
	text-align: center;
	hyphenate: auto;
	font-family: 微软雅黑;
	font-size: 24pt;
}

.p3 {
	text-align: justify;
	hyphenate: auto;
	font-family: Times New Roman;
	font-size: 12pt;
}

.p4 {
	text-align: start;
	hyphenate: auto;
	font-family: 微软雅黑;
	font-size: 12pt;
}

.p5 {
	text-align: center;
	hyphenate: auto;
	font-family: 微软雅黑;
	font-size: 12pt;
}

.p6 {
	margin-top: 0.2361111in;
	margin-bottom: 0.22916667in;
	text-align: start;
	hyphenate: auto;
	keep-together .within-page: always;
	keep-with-next .within-page: always;
	font-family: 微软雅黑;
	font-size: 22pt;
}

.p7 {
	margin-left: 0.33333334in;
	text-align: start;
	hyphenate: auto;
	font-family: 微软雅黑;
	font-size: 12pt;
}

.p8 {
	margin-left: 0.6666667in;
	text-align: start;
	hyphenate: auto;
	font-family: 微软雅黑;
	font-size: 12pt;
}

.p9 {
	text-indent: -0.4027778in;
	margin-left: 0.4027778in;
	margin-top: 0.2361111in;
	margin-bottom: 0.22916667in;
	text-align: start;
	hyphenate: auto;
	keep-together .within-page: always;
	keep-with-next .within-page: always;
	font-family: 微软雅黑;
	font-size: 22pt;
}

.p10 {
	text-indent: -0.5in;
	margin-left: 0.5in;
	margin-top: 0.18055555in;
	margin-bottom: 0.18055555in;
	text-align: start;
	hyphenate: auto;
	keep-together .within-page: always;
	keep-with-next .within-page: always;
	font-family: 微软雅黑;
	font-size: 16pt;
}

.p11 {
	text-indent: 0.33333334in;
	text-align: justify;
	hyphenate: auto;
	font-family: 微软雅黑;
	font-size: 12pt;
}

.p12 {
	text-indent: 0.29166666in;
	margin-left: 0.8333333in;
	text-align: start;
	hyphenate: auto;
	font-family: 微软雅黑;
	font-size: 12pt;
}

.p13 {
	text-indent: 0.29166666in;
	margin-left: 0.25in;
	text-align: start;
	hyphenate: auto;
	font-family: 微软雅黑;
	font-size: 12pt;
}

.p14 {
	text-indent: -0.5in;
	margin-left: 0.5in;
	margin-top: 0.18055555in;
	margin-bottom: 0.18055555in;
	text-align: start;
	hyphenate: auto;
	keep-together .within-page: always;
	keep-with-next .within-page: always;
	font-family: 微软雅黑;
	font-size: 12pt;
}

.p15 {
	text-indent: 0.29166666in;
	margin-left: 0.25in;
	text-align: start;
	hyphenate: auto;
	font-family: Times New Roman;
	font-size: 12pt;
}

.p16 {
	margin-left: 0.25in;
	text-align: start;
	hyphenate: auto;
	font-family: Verdana;
	font-size: 10pt;
}

.p17 {
	text-align: start;
	border-bottom: thin solid black;
	border-left: thin solid black;
	border-right: thin solid black;
	border-top: thin solid black;
	hyphenate: auto;
	font-family: Courier New;
	font-size: 12pt;
}

.p18 {
	text-align: start;
	border-bottom: thin solid black;
	border-left: thin solid black;
	border-right: thin solid black;
	border-top: thin solid black;
	hyphenate: auto;
	font-family: Courier New;
	font-size: 9pt;
}

.p19 {
	margin-left: 0.25in;
	text-align: start;
	hyphenate: auto;
	font-family: Times New Roman;
	font-size: 12pt;
}

.p20 {
	text-align: start;
	border-bottom: thin solid black;
	border-left: thin solid black;
	border-right: thin solid black;
	border-top: thin solid black;
	hyphenate: auto;
	font-family: Courier New;
	font-size: 10pt;
}

.p21 {
	text-indent: 0.33333334in;
	text-align: start;
	hyphenate: auto;
	font-family: Times New Roman;
	font-size: 12pt;
}

.p22 {
	margin-left: 0.25in;
	text-align: start;
	hyphenate: auto;
	font-family: 微软雅黑;
	font-size: 12pt;
}

.p23 {
	text-indent: 0.4097222in;
	margin-left: 0.09861111in;
	text-align: start;
	hyphenate: auto;
	font-family: 微软雅黑;
	font-size: 10pt;
}

.s1 {
	font-weight: bold;
}

.s2 {
	font-style: italic;
	color: blue;
}

.s3 {
	font-family: Times New Roman;
}

.s4 {
	font-family: Calibri;
	font-size: 10pt;
}

.s5 {
	color: black;
}

.s6 {
	display: inline-block;
	text-indent: 0;
	min-width: 0.4861111in;
}

.s7 {
	display: inline-block;
	text-indent: 0;
	min-width: 0.3611111in;
}

.s8 {
	display: inline-block;
	text-indent: 0;
	min-width: 0.44444445in;
}

.s9 {
	color: red;
}

.s10 {
	color: #4f81bd;
}

.s11 {
	font-size: 9pt;
}

.s12 {
	font-size: 9pt;
	font-style: italic;
	color: gray;
}

.s13 {
	font-weight: bold;
	color: navy;
}

.s14 {
	font-weight: bold;
	color: blue;
}

.s15 {
	font-weight: bold;
	color: green;
}

.s16 {
	font-style: italic;
	color: gray;
}

.td1 {
	width: 0.65625in;
	padding-start: 0.075in;
	padding-end: 0.075in;
	border-bottom: thin solid black;
	border-left: thin solid black;
	border-right: thin solid black;
	border-top: thin solid black;
}

.td2 {
	width: 0.7222222in;
	padding-start: 0.075in;
	padding-end: 0.075in;
	border-bottom: thin solid black;
	border-left: thin solid black;
	border-right: thin solid black;
	border-top: thin solid black;
}

.td3 {
	width: 1.1055555in;
	padding-start: 0.075in;
	padding-end: 0.075in;
	border-bottom: thin solid black;
	border-left: thin solid black;
	border-right: thin solid black;
	border-top: thin solid black;
}

.td4 {
	width: 3.4972222in;
	padding-start: 0.075in;
	padding-end: 0.075in;
	border-bottom: thin solid black;
	border-left: thin solid black;
	border-right: thin solid black;
	border-top: thin solid black;
}

.r1 {
	height: 0.40625in;
	keep-together: always;
}

.r2 {
	keep-together: always;
}

.t1 {
	table-layout: fixed;
	border-collapse: collapse;
	border-spacing: 0;
}
</style>

	<p class="p1"></p>
	<p class="p1"></p>
	<p class="p2">
		<span class="s1">新媒传信移动应用数据统计平台</span>
	</p>
	<p class="p2">
		<span class="s1">开发指南</span>
	</p>
	<p class="p3"></p>
	<p class="p1">
		<span>本文档主要描述了移动应用数据统计平台的基本机制，以及指导开发者如何在应用中使用SDK。文档中提供了开发者需要做哪些准备、如何获取所需资源及如何使用SDK等的指引，同时也提供了相关的范例供开发者学习使用。</span>
	</p>
	<p class="p1"></p>
	<p class="p1"></p>
	<p class="p4">
		<span class="s1">版本历史</span>
	</p>
	<table class="t1">
		<tbody>
			<tr class="r1">
				<td class="td1">
					<p class="p5">
						<span class="s1">版本</span>
					</p></td>
				<td class="td2">
					<p class="p5">
						<span class="s1">修订人</span>
					</p></td>
				<td class="td3">
					<p class="p5">
						<span class="s1">修订日期</span>
					</p></td>
				<td class="td4">
					<p class="p5">
						<span class="s1">修订内容</span>
					</p></td>
			</tr>
			<tr class="r2">
				<td class="td1">
					<p class="p5">
						<span>V1.0</span>
					</p></td>
				<td class="td2">
					<p class="p5">
						<span>李树林</span>
					</p></td>
				<td class="td3">
					<p class="p5">
						<span>2014/10/30</span>
					</p></td>
				<td class="td4">
					<p class="p1">
						<span>初稿</span>
					</p></td>
			</tr>
		</tbody>
	</table>
	<p class="p4"></p>
	<br>
	<p class="p1"></p>
	<p class="p6">
		<a name="_Toc402516990"><span class="s1">版权说明</span>
		</a>
	</p>
	<p class="p4">
		<span class="s2"> </span><span>本文档最终解释权归&ldquo;新媒传信移动应用数据统计平台&rdquo;所有，本文档供各位应用开发者开发参考使用，其他非商业性或非盈利性用途，未经允许，不得将本文档摘编、转载及用于其他用途。</span>
	</p>
	<p class="p4"></p>
	<br>
	<p class="p1"></p>
	<p class="p4"></p>
	<p class="p6">
		<a name="_Toc402516991"><span class="s1">目录</span>
		</a>
	</p>
	<p class="p4">
		<span>TOC \* MERGEFORMAT版权说明</span><span class="s3"> </span><a
			href="#_Toc402516990"><span class="s3">2</span>
		</a>
	</p>
	<p class="p4">
		<span>目录</span><span class="s3"> </span><a href="#_Toc402516991"><span
			class="s3">3</span>
		</a>
	</p>
	<p class="p4">
		<span>1</span><span class="s4"> </span><span>概述</span><span class="s3">
		</span><a href="#_Toc402516992"><span class="s3">5</span>
		</a>
	</p>
	<p class="p7">
		<span>1.1</span><span class="s4"> </span><span>简介</span><span
			class="s3"> </span><a href="#_Toc402516993"><span class="s3">5</span>
		</a>
	</p>
	<p class="p7">
		<span>1.2</span><span class="s4"> </span><span>功能</span><span
			class="s3"> </span><a href="#_Toc402516994"><span class="s3">5</span>
		</a>
	</p>
	<p class="p7">
		<span>1.3</span><span class="s4"> </span><span>支持平台</span><span
			class="s3"> </span><a href="#_Toc402516995"><span class="s3">5</span>
		</a>
	</p>
	<p class="p4">
		<span>2</span><span class="s4"> </span><span>使用流程</span><span
			class="s3"> </span><a href="#_Toc402516996"><span class="s3">6</span>
		</a>
	</p>
	<p class="p7">
		<span>2.1</span><span class="s4"> </span><span>开发前准备</span><span
			class="s3"> </span><a href="#_Toc402516997"><span class="s3">6</span>
		</a>
	</p>
	<p class="p8">
		<span class="s5">2.1.1</span><span class="s4"> </span><span class="s5">申请APPID</span><span
			class="s3"> </span><a href="#_Toc402516998"><span class="s3">6</span>
		</a>
	</p>
	<p class="p8">
		<span class="s5">2.1.2</span><span class="s4"> </span><span class="s5">下载SDK开发包</span><span
			class="s3"> </span><a href="#_Toc402516999"><span class="s3">6</span>
		</a>
	</p>
	<p class="p4">
		<span>3</span><span class="s4"> </span><span>SDK集成（Android）</span><span
			class="s3"> </span><a href="#_Toc402517000"><span class="s3">6</span>
		</a>
	</p>
	<p class="p7">
		<span>3.1</span><span class="s4"> </span><span>导入SDK</span><span
			class="s3"> </span><a href="#_Toc402517001"><span class="s3">6</span>
		</a>
	</p>
	<p class="p7">
		<span>3.2</span><span class="s4"> </span><span>添加权限</span><span
			class="s3"> </span><a href="#_Toc402517002"><span class="s3">7</span>
		</a>
	</p>
	<p class="p7">
		<span>3.3</span><span class="s4"> </span><span>初始化SDK</span><span
			class="s3"> </span><a href="#_Toc402517003"><span class="s3">7</span>
		</a>
	</p>
	<p class="p7">
		<span>3.4</span><span class="s4"> </span><span>会话统计</span><span
			class="s3"> </span><a href="#_Toc402517004"><span class="s3">8</span>
		</a>
	</p>
	<p class="p7">
		<span>3.5</span><span class="s4"> </span><span>页面统计</span><span
			class="s3"> </span><a href="#_Toc402517005"><span class="s3">8</span>
		</a>
	</p>
	<p class="p7">
		<span>3.6</span><span class="s4"> </span><span>自定义事件统计</span><span
			class="s3"> </span><a href="#_Toc402517006"><span class="s3">9</span>
		</a>
	</p>
	<p class="p7">
		<span>3.7</span><span class="s4"> </span><span>错误捕获</span><span
			class="s3"> </span><a href="#_Toc402517007"><span class="s3">9</span>
		</a>
	</p>
	<p class="p7">
		<span>3.8</span><span class="s4"> </span><span>定制数据上传策略</span><span
			class="s3"> </span><a href="#_Toc402517008"><span class="s3">9</span>
		</a>
	</p>
	<p class="p4">
		<span>4</span><span class="s4"> </span><span>SDK集成（IOS）</span><span
			class="s3"> </span><a href="#_Toc402517009"><span class="s3">10</span>
		</a>
	</p>
	<p class="p4">
		<span>5</span><span class="s4"> </span><span>SDK集成（WindowPhone）</span><span
			class="s3"> </span><a href="#_Toc402517010"><span class="s3">10</span>
		</a>
	</p>
	<p class="p1"></p>
	<br>
	<p class="p1"></p>
	<p class="p4"></p>
	<p class="p9">
		<span class="s6">1​&nbsp;</span><a name="_Toc402516992"><span
			class="s1">概述</span>
		</a>
	</p>
	<p class="p10">
		<span class="s6">1.1​&nbsp;</span><a name="_Toc402516993"><span
			class="s1">简介</span>
		</a>
	</p>
	<p class="p11">
		<span class="s5">移动应用数据统计平台SDK，作为一个应用程序库，随应用程序一起发行，它提供了高效采集分析数据，并在客户端完成初步的数据清洗与封装，传输到server端的数据采用压缩的模式，尽量减少用户的网络数据传输。</span>
	</p>
	<p class="p11">
		<span class="s5">目前，SDK提供了三大平台（android、ios、wp）相关的sdk，并采用一致的调用方式，透明的集成到用户的移动应用中。</span>
	</p>
	<p class="p12">
		<span class="s7">1、​&nbsp;</span>
	</p>
	<p class="p11"></p>
	<p class="p10">
		<span class="s6">1.2​&nbsp;</span><a name="_Toc402516994"><span
			class="s1">功能</span>
		</a>
	</p>
	<p class="p11">
		<span class="s5">SDK的实现的主要功能是：</span>
	</p>
	<p class="p12">
		<span class="s7">1、​&nbsp;</span><span class="s5">采集统计用户使用APP的总体情况（应用的使用次数、频率等）</span>
	</p>
	<p class="p12">
		<span class="s7">2、​&nbsp;</span><span class="s5">采集统计用户APP使用的页面路径</span>
	</p>
	<p class="p11">
		<span class="s5">支持自定义事件，关注事件的发生、结束、及持续时间。</span>
	</p>
	<p class="p10">
		<span class="s6">1.3​&nbsp;</span><a name="_Toc402516995"><span
			class="s1">支持平台</span>
		</a>
	</p>
	<p class="p1">
		<span class="s5">目前，SDK支持以下移动应用平台</span>
	</p>
	<p class="p13">
		<span class="s8">1、​&nbsp;</span><span class="s5">Android</span>
	</p>
	<p class="p13">
		<span class="s8">2、​&nbsp;</span><span class="s5">IOS</span><span
			class="s9">（暂无）</span>
	</p>
	<p class="p13">
		<span class="s8">3、​&nbsp;</span><span class="s5">WindowsPhone</span><span
			class="s9">（暂无）</span>
	</p>
	<p class="p9">
		<span class="s6">2​&nbsp;</span><a name="_Toc402516996"><span
			class="s1">使用流程</span>
		</a>
	</p>
	<p class="p10">
		<span class="s6">2.1​&nbsp;</span><a name="_Toc306886973"><a
			name="_Toc402516997"><span class="s1">开发前准备</span>
		</a>
		</a>
	</p>
	<p class="p14">
		<span class="s6">2.1.1​&nbsp;</span><a name="_Toc402516998"><span
			class="s5">申请APPID</span>
		</a>
	</p>
	<p class="p1">
		<span class="s5">使用SDK的前提条件，开发者必须登录开发平台，注册应用，然后盛情APPKEY。</span>
	</p>
	<p class="p1">
		<span class="s5">申请APPKEY的步骤如下所示：</span>
	</p>
	<p class="p14">
		<span class="s6">2.1.2​&nbsp;</span><a name="_Toc402516999"><span
			class="s5">下载SDK开发包</span>
		</a>
	</p>
	<p class="p13">
		<span class="s8">1、​&nbsp;</span><span class="s5">android开发包</span>
	</p>
	<p class="p13">
		<span class="s8">2、​&nbsp;</span><span class="s5">ios开发包</span>
	</p>
	<p class="p13">
		<span class="s8">3、​&nbsp;</span><span class="s5">windowPhone开发包</span>
	</p>
	<p class="p9">
		<span class="s6">3​&nbsp;</span><a name="_Toc402517000"><span
			class="s1">SDK集成（Android）</span>
		</a>
	</p>
	<p class="p10">
		<span class="s6">3.1​&nbsp;</span><a name="_Toc402517001"><span
			class="s1">导入</span><span class="s1">SDK</span>
		</a>
	</p>
	<p class="p15">
		<span class="s8">1、​&nbsp;</span><span>首先将feinno-sdk-android.1.0.0.jar拷贝到工程lib目录</span>
	</p>
	<p class="p15">
		<span class="s8">2、​&nbsp;</span><span>在工程设置中，引用该jar包</span>
	</p>
	<p class="p15">
		<span class="s8">3、​&nbsp;</span><span>在需要集成SDK的类中，导入Session会话类</span>
	</p>
	<p class="p16">
		<span class="s10">import
			com.feinno.android.sdk.FeinnoSdkSession;</span>
	</p>
	<p class="p10">
		<span class="s6">3.2​&nbsp;</span><a name="_Toc402517002"><span
			class="s1">添加权限</span>
		</a>
	</p>
	<p class="p1">
		<span>如果需要正确使用SDK，需要在工程文件AndroidManifest.xml中添加一下权限：</span>
	</p>
	<p class="p3"></p>
	<p class="p17">
		<span> </span><span class="s11"> </span><span class="s12">&lt;!--
			访问网络状态 --&gt;</span>
	</p>
	<p class="p18">
		<span> &lt;</span><span class="s13">uses-permission</span><span>
		</span><span class="s14">android:name=</span><span class="s15">"android.permission.ACCESS_NETWORK_STATE"</span><span>/&gt;</span>
	</p>
	<p class="p18">
		<span> </span><span class="s16">&lt;!-- 访问WIFI状态 --&gt;</span>
	</p>
	<p class="p18">
		<span> &lt;</span><span class="s13">uses-permission</span><span>
		</span><span class="s14">android:name=</span><span class="s15">"android.permission.ACCESS_WIFI_STATE"</span><span>
			/&gt;</span>
	</p>
	<p class="p18">
		<span> </span><span class="s16">&lt;!-- 连接网络 --&gt;</span>
	</p>
	<p class="p18">
		<span> &lt;</span><span class="s13">uses-permission</span><span>
		</span><span class="s14">android:name=</span><span class="s15">"android.permission.INTERNET"</span><span>/&gt;</span>
	</p>
	<p class="p18">
		<span> </span><span class="s16">&lt;!-- 读取手机状态 --&gt;</span>
	</p>
	<p class="p18">
		<span> &lt;</span><span class="s13">uses-permission</span><span>
		</span><span class="s14">android:name=</span><span class="s15">"android.permission.READ_PHONE_STATE"</span><span>/&gt;</span>
	</p>
	<p class="p3"></p>
	<p class="p10">
		<span class="s6">3.3​&nbsp;</span><a name="_Toc402517003"><span
			class="s1">初始化</span><span class="s1">SDK</span>
		</a>
	</p>
	<p class="p1">
		<span>SDK统计用户数据时，需要用户提供注册APP时获取的APPKEY，以及该APP发行时的渠道信息。提供给SDK使用的APPKEY及渠道信息可以通过两种方式设置：</span>
	</p>
	<p class="p13">
		<span class="s8">1、​&nbsp;</span><span>在AndroidManifest.xml中设置：</span>
	</p>
	<p class="p18">
		<span> </span><span class="s16">&lt;!-- APPKEY 信息 --&gt;</span>
	</p>
	<p class="p18">
		<span> &lt;</span><span class="s13">meta-data</span><span> </span><span
			class="s14">android:name=</span><span class="s15">"FEINNO_APP_KEY"</span><span>
		</span><span class="s14">android:value=</span><span class="s15">"abcd4321"</span><span>/&gt;</span>
	</p>
	<p class="p18">
		<span> </span><span class="s16">&lt;!-- 渠道信息 --&gt;</span>
	</p>
	<p class="p18">
		<span> &lt;</span><span class="s13">meta-data</span><span> </span><span
			class="s14">android:name=</span><span class="s15">"FEINNO_APP_CHANNEL"</span><span>
		</span><span class="s14">android:value=</span><span class="s15">"FEINNO_DP"</span><span>/&gt;</span>
	</p>
	<p class="p19"></p>
	<p class="p13">
		<span class="s8">2、​&nbsp;</span><span>开发者也可以在会话初始化时设置。</span>
	</p>
	<p class="p18">
		<span>FeinnoSdkSession.init(</span><span class="s13">this</span><span>.getApplicationContext(),</span><span
			class="s15">"appkey"</span><span>,</span><span class="s15">"appChannel"</span><span>);</span>
	</p>
	<p class="p19"></p>
	<p class="p1">
		<span class="s9">注意：开发者必须在APP的第一个启动的Activity中的onCreate方法中初始化会话。</span>
	</p>
	<p class="p18">
		<span class="s16">//appkey 及 渠道信息在AndroidManifest.xml中配置</span>
	</p>
	<p class="p18">
		<span> FeinnoSdkSession.init(</span><span class="s13">this</span><span>.getApplicationContext());</span>
	</p>
	<p class="p18">
		<span> </span>
	</p>
	<p class="p18">
		<span> </span><span class="s16">//appkey 及 渠道信息在代码中设置</span><span>
			FeinnoSdkSession.init(</span><span class="s13">this</span><span>.getApplicationContext(),</span><span
			class="s15">"appkey"</span><span>,</span><span class="s15">"appChannel"</span><span>);</span>
	</p>
	<p class="p19"></p>
	<p class="p10">
		<span class="s6">3.4​&nbsp;</span><a name="_Toc402517004"><span
			class="s1">会话统计</span>
		</a>
	</p>
	<p class="p13">
		<span class="s8">1、​&nbsp;</span><span>在Activity的OnResume方法中，开始一个会话。</span>
	</p>
	<p class="p20">
		<span> @Override</span>
	</p>
	<p class="p20">
		<span> </span><span class="s13">protected</span><span> </span><span
			class="s13">void</span><span> onResume() {</span>
	</p>
	<p class="p20">
		<span> </span><span class="s13">super</span><span>.onResume();</span>
	</p>
	<p class="p20">
		<span> FeinnoSdkSession.open();</span>
	</p>
	<p class="p20">
		<span> </span><span class="s12">//其他代码</span>
	</p>
	<p class="p20">
		<span> }</span>
	</p>
	<p class="p19"></p>
	<p class="p13">
		<span class="s8">2、​&nbsp;</span><span>在Activity的OnPause方法中，结束一个会话。</span>
	</p>
	<p class="p21"></p>
	<p class="p20">
		<span> @Override</span>
	</p>
	<p class="p20">
		<span> </span><span class="s13">protected</span><span> </span><span
			class="s13">void</span><span> onPause() {</span>
	</p>
	<p class="p20">
		<span> </span><span class="s13">super</span><span>.onPause();</span>
	</p>
	<p class="p20">
		<span> FeinnoSdkSession.close();</span>
	</p>
	<p class="p20">
		<span> </span><span class="s12">//其他代码</span>
	</p>
	<p class="p20">
		<span> }</span>
	</p>
	<p class="p3"></p>
	<p class="p1">
		<span class="s9">注意：在复杂的Activity的继承体系中，会话开始及结束的控制，需要注意顺序。会话的开始一定要在结束之前调用。</span>
	</p>
	<p class="p10">
		<span class="s6">3.5​&nbsp;</span><a name="_Toc402517005"><span
			class="s1">页面统计</span>
		</a>
	</p>
	<p class="p13">
		<span class="s8">1、​&nbsp;</span><span>页面开始：</span>
	</p>
	<p class="p17">
		<span>FeinnoSdkSession.pageEnd(</span><span class="s15">"pageName"</span><span>);</span>
	</p>
	<p class="p3"></p>
	<p class="p13">
		<span class="s8">2、​&nbsp;</span><span>页面结束</span>
	</p>
	<p class="p17">
		<span>FeinnoSdkSession.pageStart(</span><span class="s15">"pageName"</span><span>);</span>
	</p>
	<p class="p3"></p>
	<p class="p1">
		<span class="s9">注意：页面开始及结束的调用必须在Session的有效期之内才会被记录。即在会话的Open及Close之间调用。</span>
	</p>
	<p class="p10">
		<span class="s6">3.6​&nbsp;</span><a name="_Toc402517006"><span
			class="s1">自定义事件统计</span>
		</a>
	</p>
	<p class="p3"></p>
	<p class="p13">
		<span class="s8">1、​&nbsp;</span><span>事件开始：</span>
	</p>
	<p class="p17">
		<span>FeinnoSdkSession.eventEnd(</span><span class="s15">"eventName"</span><span>);</span>
	</p>
	<p class="p3"></p>
	<p class="p13">
		<span class="s8">2、​&nbsp;</span><span>事件结束</span>
	</p>
	<p class="p17">
		<span>FeinnoSdkSession.eventStart(</span><span class="s15">"eventName"</span><span>);</span>
	</p>
	<p class="p3"></p>
	<p class="p1">
		<span class="s9">注意：页面开始及结束的调用必须在Session的有效期之内才会被记录。即在会话的Open及Close之间调用。</span>
	</p>
	<p class="p10">
		<span class="s6">3.7​&nbsp;</span><a name="_Toc402517007"><span
			class="s1">错误捕获</span>
		</a>
	</p>
	<p class="p3">
		<span class="s9">暂无</span>
	</p>
	<p class="p10">
		<span class="s6">3.8​&nbsp;</span><a name="_Toc402517008"><span
			class="s1">定制数据上传策略</span>
		</a>
	</p>
	<p class="p13">
		<span class="s8">1、​&nbsp;</span><span>固定上传</span>
	</p>
	<p class="p22">
		<span>开发者可以在APP开始或者结束的时候触发上传功能</span>
	</p>
	<p class="p20">
		<span>FeinnoSdkSession.upload();</span>
	</p>
	<p class="p1"></p>
	<p class="p13">
		<span class="s8">2、​&nbsp;</span><span>自定义上传策略</span>
	</p>
	<p class="p22">
		<span class="s9">暂未实现</span>
	</p>
	<p class="p9">
		<span class="s6">4​&nbsp;</span><a name="_Toc402517009"><span
			class="s1">SDK集成（IOS）</span>
		</a>
	</p>
	<p class="p1">
		<span>暂无</span>
	</p>
	<p class="p9">
		<span class="s6">5​&nbsp;</span><a name="_Toc402517010"><span
			class="s1">SDK集成（WindowPhone）</span>
		</a>
	</p>
	<p class="p1">
		<span>暂无</span>
	</p>
	<p class="p11">
		<span>。 </span>
	</p>
	<p class="p23"></p>

