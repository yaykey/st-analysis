AmCharts.AmStockChart = AmCharts.Class({
	construct : function(a) {
		this.type = "stock";
		this.cname = "AmStockChart";
		this.version = "3.11.3";
		this.theme = a;
		this.createEvents("zoomed", "rollOverStockEvent", "rollOutStockEvent",
				"clickStockEvent", "panelRemoved", "dataUpdated", "init",
				"rendered", "drawn");
		this.colors = "#FF6600 #FCD202 #B0DE09 #0D8ECF #2A0CD0 #CD0D74 #CC0000 #00CC00 #0000CC #DDDDDD #999999 #333333 #990000"
				.split(" ");
		this.firstDayOfWeek = 1;
		this.glueToTheEnd = !1;
		this.dataSetCounter = -1;
		this.zoomOutOnDataSetChange = !1;
		this.panels = [];
		this.dataSets = [];
		this.chartCursors = [];
		this.comparedDataSets = [];
		this.categoryAxesSettings = new AmCharts.CategoryAxesSettings(a);
		this.valueAxesSettings = new AmCharts.ValueAxesSettings(a);
		this.panelsSettings = new AmCharts.PanelsSettings(a);
		this.chartScrollbarSettings = new AmCharts.ChartScrollbarSettings(a);
		this.chartCursorSettings = new AmCharts.ChartCursorSettings(a);
		this.stockEventsSettings = new AmCharts.StockEventsSettings(a);
		this.legendSettings = new AmCharts.LegendSettings(a);
		this.balloon = new AmCharts.AmBalloon(a);
		this.previousEndDate = new Date(0);
		this.previousStartDate = new Date(0);
		this.dataSetCount = this.graphCount = 0;
		this.chartCreated = !1;
		this.extendToFullPeriod = !0;
		AmCharts.applyTheme(this, a, this.cname)
	},
	write : function(a) {
		var b = this.theme;
		this.initHC || (AmCharts.callInitHandler(this), this.initHC = !0);
		AmCharts.applyLang(this.language, this);
		var c = this.exportConfig;
		c && AmCharts.AmExport && !this.AmExport
				&& (this.AmExport = new AmCharts.AmExport(this, c));
		this.amExport
				&& AmCharts.AmExport
				&& (this.AmExport = AmCharts.extend(this.amExport,
						new AmCharts.AmExport(this), !0));
		this.AmExport && this.AmExport.init();
		this.chartRendered = !1;
		a = "object" != typeof a ? document.getElementById(a) : a;
		this.zoomOutOnDataSetChange && (this.endDate = this.startDate = void 0);
		this.categoryAxesSettings = AmCharts.processObject(
				this.categoryAxesSettings, AmCharts.CategoryAxesSettings, b);
		this.valueAxesSettings = AmCharts.processObject(this.valueAxesSettings,
				AmCharts.ValueAxesSettings, b);
		this.chartCursorSettings = AmCharts.processObject(
				this.chartCursorSettings, AmCharts.ChartCursorSettings, b);
		this.chartScrollbarSettings = AmCharts
				.processObject(this.chartScrollbarSettings,
						AmCharts.ChartScrollbarSettings, b);
		this.legendSettings = AmCharts.processObject(this.legendSettings,
				AmCharts.LegendSettings, b);
		this.panelsSettings = AmCharts.processObject(this.panelsSettings,
				AmCharts.PanelsSettings, b);
		this.stockEventsSettings = AmCharts.processObject(
				this.stockEventsSettings, AmCharts.StockEventsSettings, b);
		this.dataSetSelector
				&& (this.dataSetSelector = AmCharts.processObject(
						this.dataSetSelector, AmCharts.DataSetSelector, b));
		this.periodSelector
				&& (this.periodSelector = AmCharts.processObject(
						this.periodSelector, AmCharts.PeriodSelector, b));
		a.innerHTML = "";
		this.div = a;
		this.measure();
		this.createLayout();
		this.updateDataSets();
		this.addDataSetSelector();
		this.addPeriodSelector();
		this.addPanels();
		this.updatePanels();
		this.addChartScrollbar();
		this.updateData();
		this.skipDefault || this.setDefaultPeriod()
	},
	setDefaultPeriod : function(a) {
		var b = this.periodSelector;
		b && (this.animationPlayed = !1, b.setDefaultPeriod(a))
	},
	validateSize : function() {
		var a, b = this.panels;
		this.measurePanels();
		for (a = 0; a < b.length; a++)
			panel = b[a], panel.invalidateSize()
	},
	updateDataSets : function() {
		var a = this.mainDataSet, b = this.dataSets, c;
		for (c = 0; c < b.length; c++) {
			var d = b[c], d = AmCharts.processObject(d, AmCharts.DataSet);
			b[c] = d;
			d.id || (this.dataSetCount++, d.id = "ds" + this.dataSetCount);
			void 0 === d.color
					&& (d.color = this.colors.length - 1 > c
							? this.colors[c]
							: AmCharts.randomColor())
		}
		!a && AmCharts.ifArray(b) && (this.mainDataSet = this.dataSets[0])
	},
	updateEvents : function(a) {
		AmCharts.ifArray(a.stockEvents)
				&& AmCharts.parseEvents(a, this.panels,
						this.stockEventsSettings, this.firstDayOfWeek, this,
						this.dataDateFormat)
	},
	getLastDate : function(a) {
		var b = this.dataDateFormat;
		a = a instanceof Date ? new Date(a.getFullYear(), a.getMonth(), a
						.getDate(), a.getHours(), a.getMinutes(), a
						.getSeconds(), a.getMilliseconds()) : b ? AmCharts
				.stringToDate(a, b) : new Date(a);
		return new Date(AmCharts.changeDate(a,
				this.categoryAxesSettings.minPeriod, 1, !0).getTime()
				- 1)
	},
	getFirstDate : function(a) {
		var b = this.dataDateFormat;
		a = a instanceof Date ? new Date(a.getFullYear(), a.getMonth(), a
						.getDate(), a.getHours(), a.getMinutes(), a
						.getSeconds(), a.getMilliseconds()) : b ? AmCharts
				.stringToDate(a, b) : new Date(a);
		return new Date(AmCharts.resetDateToMin(a,
				this.categoryAxesSettings.minPeriod, 1, this.firstDayOfWeek))
	},
	updateData : function() {
		var a = this.mainDataSet;
		if (a) {
			var b = this.categoryAxesSettings;
			-1 == AmCharts.getItemIndex(b.minPeriod, b.groupToPeriods)
					&& b.groupToPeriods.unshift(b.minPeriod);
			var c = a.dataProvider;
			if (AmCharts.ifArray(c)) {
				var d = a.categoryField;
				this.firstDate = this.getFirstDate(c[0][d]);
				this.lastDate = this.getLastDate(c[c.length - 1][d]);
				this.periodSelector
						&& this.periodSelector.setRanges(this.firstDate,
								this.lastDate);
				a.dataParsed
						|| (AmCharts.parseStockData(a, b.minPeriod,
								b.groupToPeriods, this.firstDayOfWeek,
								this.dataDateFormat), a.dataParsed = !0);
				this.updateComparingData();
				this.updateEvents(a)
			} else
				this.lastDate = this.firstDate = void 0;
			this.glueToTheEnd
					&& this.startDate
					&& this.endDate
					&& this.lastDate
					&& (AmCharts.getPeriodDuration(b.minPeriod), this.startDate = new Date(this.startDate
							.getTime()
							+ (this.lastDate.getTime() - this.endDate.getTime())), this.endDate = this.lastDate, this.updateScrollbar = !0);
			this.updatePanelsWithNewData()
		}
		a = {
			type : "dataUpdated",
			chart : this
		};
		this.fire(a.type, a)
	},
	updateComparingData : function() {
		var a = this.comparedDataSets, b = this.categoryAxesSettings, c;
		for (c = 0; c < a.length; c++) {
			var d = a[c];
			d.dataParsed
					|| (AmCharts.parseStockData(d, b.minPeriod,
							b.groupToPeriods, this.firstDayOfWeek,
							this.dataDateFormat), d.dataParsed = !0);
			this.updateEvents(d)
		}
	},
	createLayout : function() {
		var a = this.div, b, c, d = document.createElement("div");
		d.style.position = "relative";
		this.containerDiv = d;
		a.appendChild(d);
		if (a = this.periodSelector)
			b = a.position;
		if (a = this.dataSetSelector)
			c = a.position;
		if ("left" == b || "left" == c)
			a = document.createElement("div"), a.style.cssFloat = "left", a.style.styleFloat = "left", a.style.width = "0px", a.style.position = "absolute", d
					.appendChild(a), this.leftContainer = a;
		if ("right" == b || "right" == c)
			b = document.createElement("div"), b.style.cssFloat = "right", b.style.styleFloat = "right", b.style.width = "0px", d
					.appendChild(b), this.rightContainer = b;
		b = document.createElement("div");
		d.appendChild(b);
		this.centerContainer = b;
		d = document.createElement("div");
		b.appendChild(d);
		this.panelsContainer = d
	},
	addPanels : function() {
		this.measurePanels();
		for (var a = this.panels, b = 0; b < a.length; b++) {
			var c = a[b], c = AmCharts.processObject(c, AmCharts.StockPanel,
					this.theme);
			a[b] = c;
			this.addStockPanel(c, b)
		}
		this.panelsAdded = !0
	},
	measurePanels : function() {
		this.measure();
		var a = this.chartScrollbarSettings, b = this.divRealHeight, c = this.panelsSettings.panelSpacing;
		a.enabled && (b -= a.height);
		(a = this.periodSelector) && !a.vertical
				&& (a = a.offsetHeight, b -= a + c);
		(a = this.dataSetSelector) && !a.vertical
				&& (a = a.offsetHeight, b -= a + c);
		a = this.panels;
		this.panelsContainer.style.height = b + "px";
		this.chartCursors = [];
		var d = 0, e, k;
		for (e = 0; e < a.length; e++) {
			k = a[e];
			var f = k.percentHeight;
			isNaN(f) && (f = 100 / a.length, k.percentHeight = f);
			d += f
		}
		this.panelsHeight = Math.max(b - c * (a.length - 1), 0);
		for (e = 0; e < a.length; e++)
			k = a[e], k.percentHeight = k.percentHeight / d * 100, k.panelBox
					&& (k.panelBox.style.height = Math.round(k.percentHeight
							* this.panelsHeight / 100)
							+ "px")
	},
	addStockPanel : function(a, b) {
		var c = this.panelsSettings, d = document.createElement("div");
		d.className = "amChartsPanel";
		0 < b && !this.panels[b - 1].showCategoryAxis
				&& (d.style.marginTop = c.panelSpacing + "px");
		a.panelBox = d;
		a.stockChart = this;
		a.id || (a.id = "stockPanel" + b);
		a.pathToImages = this.pathToImages;
		d.style.height = Math.round(a.percentHeight * this.panelsHeight / 100)
				+ "px";
		d.style.width = "100%";
		this.panelsContainer.appendChild(d);
		0 < c.backgroundAlpha && (d.style.backgroundColor = c.backgroundColor);
		if (d = a.stockLegend)
			d.container = void 0, d.title = a.title, d.marginLeft = c.marginLeft, d.marginRight = c.marginRight, d.verticalGap = 3, d.position = "top", AmCharts
					.copyProperties(this.legendSettings, d), a.addLegend(d,
					d.divId);
		a.zoomOutText = "";
		this.addCursor(a)
	},
	enableCursors : function(a) {
		var b = this.chartCursors, c;
		for (c = 0; c < b.length; c++)
			b[c].enabled = a
	},
	updatePanels : function() {
		var a = this.panels, b;
		for (b = 0; b < a.length; b++)
			this.updatePanel(a[b]);
		this.mainDataSet && this.updateGraphs();
		this.currentPeriod = void 0
	},
	updatePanel : function(a) {
		a.seriesIdField = "amCategoryIdField";
		a.dataProvider = [];
		a.chartData = [];
		a.graphs = [];
		var b = a.categoryAxis, c = this.categoryAxesSettings;
		AmCharts.copyProperties(this.panelsSettings, a);
		AmCharts.copyProperties(c, b);
		b.parseDates = !0;
		a.zoomOutOnDataUpdate = !1;
		a.mouseWheelScrollEnabled = this.mouseWheelScrollEnabled;
		a.dataDateFormat = this.dataDateFormat;
		a.language = this.language;
		a.showCategoryAxis
				? "top" == b.position
						? a.marginTop = c.axisHeight
						: a.marginBottom = c.axisHeight
				: (a.categoryAxis.labelsEnabled = !1, a.chartCursor
						&& (a.chartCursor.categoryBalloonEnabled = !1));
		var c = a.valueAxes, d = c.length, e;
		0 === d && (e = new AmCharts.ValueAxis(this.theme), a.addValueAxis(e));
		b = new AmCharts.AmBalloon(this.theme);
		AmCharts.copyProperties(this.balloon, b);
		a.balloon = b;
		c = a.valueAxes;
		d = c.length;
		for (b = 0; b < d; b++)
			e = c[b], AmCharts.copyProperties(this.valueAxesSettings, e);
		a.listenersAdded = !1;
		a.write(a.panelBox)
	},
	zoom : function(a, b) {
		this.zoomChart(a, b)
	},
	zoomOut : function() {
		this.zoomChart(this.firstDate, this.lastDate)
	},
	updatePanelsWithNewData : function() {
		var a = this.mainDataSet, b = this.scrollbarChart;
		if (a) {
			var c = this.panels;
			this.currentPeriod = void 0;
			var d;
			for (d = 0; d < c.length; d++) {
				var e = c[d];
				e.categoryField = a.categoryField;
				0 === a.dataProvider.length && (e.dataProvider = []);
				e.scrollbarChart = b
			}
			b
					&& (c = this.categoryAxesSettings, d = c.minPeriod, b.categoryField = a.categoryField, 0 < a.dataProvider.length
							? (e = this.chartScrollbarSettings.usePeriod, b.dataProvider = e
									? a.agregatedDataProviders[e]
									: a.agregatedDataProviders[d])
							: b.dataProvider = [], e = b.categoryAxis, e.minPeriod = d, e.firstDayOfWeek = this.firstDayOfWeek, e.equalSpacing = c.equalSpacing, e.axisAlpha = 0, e.markPeriodChange = c.markPeriodChange, b.bbsetr = !0, b
							.validateData(), c = this.panelsSettings, b.maxSelectedTime = c.maxSelectedTime, b.minSelectedTime = c.minSelectedTime);
			0 < a.dataProvider.length
					&& this.zoomChart(this.startDate, this.endDate)
		}
		this.panelDataInvalidated = !1
	},
	addChartScrollbar : function() {
		var a = this.chartScrollbarSettings, b = this.scrollbarChart;
		b && (b.clear(), b.destroy());
		if (a.enabled) {
			var c = this.panelsSettings, d = this.categoryAxesSettings, b = new AmCharts.AmSerialChart(this.theme);
			b.language = this.language;
			b.pathToImages = this.pathToImages;
			b.autoMargins = !1;
			this.scrollbarChart = b;
			b.id = "scrollbarChart";
			b.scrollbarOnly = !0;
			b.zoomOutText = "";
			b.marginLeft = c.marginLeft;
			b.marginRight = c.marginRight;
			b.marginTop = 0;
			b.marginBottom = 0;
			var c = d.dateFormats, e = b.categoryAxis;
			e.boldPeriodBeginning = d.boldPeriodBeginning;
			c && (e.dateFormats = d.dateFormats);
			e.labelsEnabled = !1;
			e.parseDates = !0;
			d = a.graph;
			if (AmCharts.isString(d)) {
				c = this.panels;
				for (e = 0; e < c.length; e++) {
					var k = AmCharts.getObjById(c[e].stockGraphs, a.graph);
					k && (d = k)
				}
				a.graph = d
			}
			var f;
			d
					&& (f = new AmCharts.AmGraph(this.theme), f.valueField = d.valueField, f.periodValue = d.periodValue, f.type = d.type, f.connect = d.connect, f.minDistance = a.minDistance, b
							.addGraph(f));
			d = new AmCharts.ChartScrollbar(this.theme);
			b.addChartScrollbar(d);
			AmCharts.copyProperties(a, d);
			d.scrollbarHeight = a.height;
			d.graph = f;
			this.listenTo(d, "zoomed", this.handleScrollbarZoom);
			f = document.createElement("div");
			f.style.height = a.height + "px";
			d = this.periodSelectorContainer;
			c = this.periodSelector;
			e = this.centerContainer;
			"bottom" == a.position
					? c ? "bottom" == c.position ? e.insertBefore(f, d) : e
							.appendChild(f) : e.appendChild(f)
					: c ? "top" == c.position ? e
							.insertBefore(f, d.nextSibling) : e.insertBefore(f,
							e.firstChild) : e.insertBefore(f, e.firstChild);
			b.write(f)
		}
	},
	handleScrollbarZoom : function(a) {
		if (this.skipScrollbarEvent)
			this.skipScrollbarEvent = !1;
		else {
			var b = a.endDate, c = {};
			c.startDate = a.startDate;
			c.endDate = b;
			this.updateScrollbar = !1;
			this.handleZoom(c)
		}
	},
	addPeriodSelector : function() {
		var a = this.periodSelector;
		if (a) {
			var b = this.categoryAxesSettings.minPeriod;
			a.minDuration = AmCharts.getPeriodDuration(b);
			a.minPeriod = b;
			a.chart = this;
			var c = this.dataSetSelector, d, b = this.dssContainer;
			c && (d = c.position);
			var c = this.panelsSettings.panelSpacing, e = document
					.createElement("div");
			this.periodSelectorContainer = e;
			var k = this.leftContainer, f = this.rightContainer, h = this.centerContainer, g = this.panelsContainer, n = a.width
					+ 2 * c + "px";
			switch (a.position) {
				case "left" :
					k.style.width = a.width + "px";
					k.appendChild(e);
					h.style.paddingLeft = n;
					break;
				case "right" :
					h.style.marginRight = n;
					f.appendChild(e);
					f.style.width = a.width + "px";
					break;
				case "top" :
					g.style.clear = "both";
					h.insertBefore(e, g);
					e.style.paddingBottom = c + "px";
					e.style.overflow = "hidden";
					break;
				case "bottom" :
					e.style.marginTop = c + "px", "bottom" == d ? h
							.insertBefore(e, b) : h.appendChild(e)
			}
			this.listenTo(a, "changed", this.handlePeriodSelectorZoom);
			a.write(e)
		}
	},
	addDataSetSelector : function() {
		var a = this.dataSetSelector;
		if (a) {
			a.chart = this;
			a.dataProvider = this.dataSets;
			var b = a.position, c = this.panelsSettings.panelSpacing, d = document
					.createElement("div");
			this.dssContainer = d;
			var e = this.leftContainer, k = this.rightContainer, f = this.centerContainer, h = this.panelsContainer, c = a.width
					+ 2 * c + "px";
			switch (b) {
				case "left" :
					e.style.width = a.width + "px";
					e.appendChild(d);
					f.style.paddingLeft = c;
					break;
				case "right" :
					f.style.marginRight = c;
					k.appendChild(d);
					k.style.width = a.width + "px";
					break;
				case "top" :
					h.style.clear = "both";
					f.insertBefore(d, h);
					d.style.overflow = "hidden";
					break;
				case "bottom" :
					f.appendChild(d)
			}
			a.write(d)
		}
	},
	handlePeriodSelectorZoom : function(a) {
		var b = this.scrollbarChart;
		b && (b.updateScrollbar = !0);
		a.predefinedPeriod
				? (this.predefinedStart = a.startDate, this.predefinedEnd = a.endDate)
				: this.predefinedEnd = this.predefinedStart = null;
		this.zoomChart(a.startDate, a.endDate)
	},
	addCursor : function(a) {
		var b = this.chartCursorSettings;
		if (b.enabled) {
			var c = new AmCharts.ChartCursor(this.theme);
			AmCharts.copyProperties(b, c);
			a.chartCursor && AmCharts.copyProperties(a.chartCursor, c);
			a.removeChartCursor();
			a.addChartCursor(c);
			this.listenTo(c, "changed", this.handleCursorChange);
			this.listenTo(c, "onHideCursor", this.hideChartCursor);
			this.listenTo(c, "zoomed", this.handleCursorZoom);
			this.chartCursors.push(c)
		}
	},
	hideChartCursor : function() {
		var a = this.chartCursors, b;
		for (b = 0; b < a.length; b++) {
			var c = a[b];
			c.hideCursor(!1);
			(c = c.chart) && c.updateLegendValues()
		}
	},
	handleCursorZoom : function(a) {
		var b = this.scrollbarChart;
		b && (b.updateScrollbar = !0);
		var b = {}, c;
		if (this.categoryAxesSettings.equalSpacing) {
			var d = this.mainDataSet.categoryField, e = this.mainDataSet.agregatedDataProviders[this.currentPeriod];
			c = new Date(e[a.start][d]);
			a = new Date(e[a.end][d])
		} else
			c = new Date(a.start), a = new Date(a.end);
		b.startDate = c;
		b.endDate = a;
		this.handleZoom(b)
	},
	handleZoom : function(a) {
		this.zoomChart(a.startDate, a.endDate)
	},
	zoomChart : function(a, b) {
		var c = new Date(a), d = this, e = d.firstDate, k = d.lastDate, f = d.currentPeriod, h = d.categoryAxesSettings, g = h.minPeriod, n = d.panelsSettings, m = d.periodSelector, t = d.panels, r = d.comparedGraphs, v = d.scrollbarChart, w = d.firstDayOfWeek;
		if (e && k) {
			a || (a = e);
			b || (b = k);
			if (f) {
				var l = AmCharts.extractPeriod(f);
				a.getTime() == b.getTime()
						&& l != g
						&& (b = AmCharts.changeDate(b, l.period, l.count), b
								.setTime(b.getTime() - 1))
			}
			a.getTime() < e.getTime() && (a = e);
			a.getTime() > k.getTime() && (a = k);
			b.getTime() < e.getTime() && (b = e);
			b.getTime() > k.getTime() && (b = k);
			l = AmCharts.getItemIndex(g, h.groupToPeriods);
			h = f;
			f = d.choosePeriod(l, a, b);
			d.currentPeriod = f;
			var l = AmCharts.extractPeriod(f), y = AmCharts.getPeriodDuration(
					l.period, l.count);
			AmCharts.getPeriodDuration(g);
			1 > b.getTime() - a.getTime() && (a = new Date(b.getTime() - 1));
			g = AmCharts.newDate(a);
			d.extendToFullPeriod
					&& (g.getTime() - e.getTime() < .1 * y
							&& (g = AmCharts.resetDateToMin(a, l.period,
									l.count, w)), k.getTime() - b.getTime() < .1
							* y
							&& (b = AmCharts.resetDateToMin(k, l.period,
									l.count, w), b = AmCharts.changeDate(b,
									l.period, l.count, !0)));
			for (e = 0; e < t.length; e++)
				k = t[e], k.chartCursor && k.chartCursor.panning && (g = c);
			for (e = 0; e < t.length; e++) {
				k = t[e];
				if (f != h) {
					for (c = 0; c < r.length; c++)
						y = r[c].graph, y.dataProvider = y.dataSet.agregatedDataProviders[f];
					c = k.categoryAxis;
					c.firstDayOfWeek = w;
					c.minPeriod = f;
					k.dataProvider = d.mainDataSet.agregatedDataProviders[f];
					if (c = k.chartCursor)
						c.categoryBalloonDateFormat = d.chartCursorSettings
								.categoryBalloonDateFormat(l.period), k.showCategoryAxis
								|| (c.categoryBalloonEnabled = !1);
					k.startTime = g.getTime();
					k.endTime = b.getTime();
					k.validateData(!0)
				}
				c = !1;
				k.chartCursor && k.chartCursor.panning && (c = !0);
				c
						|| (k.startTime = void 0, k.endTime = void 0, k
								.zoomToDates(g, b));
				0 < n.startDuration && d.animationPlayed && !c
						? (k.startDuration = 0, k.animateAgain())
						: 0 < n.startDuration && !c && k.animateAgain()
			}
			d.animationPlayed = !0;
			AmCharts.extractPeriod(f);
			n = new Date(b);
			v
					&& d.updateScrollbar
					&& (v.zoomToDates(a, n), d.skipScrollbarEvent = !0, setTimeout(
							function() {
								d.resetSkip.call(d)
							}, 100));
			d.updateScrollbar = !0;
			d.startDate = a;
			d.endDate = b;
			m && m.zoom(a, b);
			if (a.getTime() != d.previousStartDate.getTime()
					|| b.getTime() != d.previousEndDate.getTime())
				m = {
					type : "zoomed"
				}, m.startDate = a, m.endDate = b, m.chart = d, m.period = f, d
						.fire(m.type, m), d.previousStartDate = new Date(a), d.previousEndDate = new Date(b)
		}
		d.eventsHidden && d.showHideEvents(!1);
		d.chartCreated || (f = "init", d.fire(f, {
					type : f,
					chart : d
				}));
		d.chartRendered || (f = "rendered", d.fire(f, {
					type : f,
					chart : d
				}), d.chartRendered = !0);
		f = "drawn";
		d.fire(f, {
					type : f,
					chart : d
				});
		d.chartCreated = !0;
		d.animationPlayed = !0
	},
	resetSkip : function() {
		this.skipScrollbarEvent = !1
	},
	updateGraphs : function() {
		this.getSelections();
		if (0 < this.dataSets.length) {
			var a = this.panels;
			this.comparedGraphs = [];
			var b;
			for (b = 0; b < a.length; b++) {
				var c = a[b], d = c.valueAxes, e;
				for (e = 0; e < d.length; e++) {
					var k = d[e];
					k.prevLog && (k.logarithmic = k.prevLog);
					k.recalculateToPercents = "always" == c.recalculateToPercents
							? !0
							: !1
				}
				d = this.mainDataSet;
				e = this.comparedDataSets;
				k = c.stockGraphs;
				c.graphs = [];
				var f;
				for (f = 0; f < k.length; f++) {
					var h = k[f], h = AmCharts.processObject(h,
							AmCharts.StockGraph, this.theme);
					k[f] = h;
					if (!h.title || h.resetTitleOnDataSetChange)
						h.title = d.title, h.resetTitleOnDataSetChange = !0;
					h.useDataSetColors
							&& (h.lineColor = d.color, h.fillColors = void 0, h.bulletColor = void 0);
					var g = !1, n = d.fieldMappings, m;
					for (m = 0; m < n.length; m++) {
						var t = n[m], r = h.valueField;
						r && t.toField == r && (g = !0);
						(r = h.openField) && t.toField == r && (g = !0);
						(r = h.closeField) && t.toField == r && (g = !0);
						(r = h.lowField) && t.toField == r && (g = !0)
					}
					c.addGraph(h);
					g || (h.visibleInLegend = !1);
					r = !1;
					"always" == c.recalculateToPercents && (r = !0);
					var v = c.stockLegend, w, l, y, z;
					v
							&& (v = AmCharts.processObject(v,
									AmCharts.StockLegend, this.theme), c.stockLegend = v, w = v.valueTextComparing, l = v.valueTextRegular, y = v.periodValueTextComparing, z = v.periodValueTextRegular);
					if (h.comparable) {
						var x = e.length;
						if (h.valueAxis) {
							0 < x
									&& h.valueAxis.logarithmic
									&& "never" != c.recalculateToPercents
									&& (h.valueAxis.logarithmic = !1, h.valueAxis.prevLog = !0);
							0 < x && "whenComparing" == c.recalculateToPercents
									&& (h.valueAxis.recalculateToPercents = !0);
							v && h.valueAxis
									&& !0 === h.valueAxis.recalculateToPercents
									&& (r = !0);
							var B;
							for (B = 0; B < x; B++) {
								var A = e[B], q = h.comparedGraphs[A.id];
								q
										|| (q = new AmCharts.AmGraph(this.theme), q.id = "comparedGraph"
												+ B + "_" + f + A.id);
								q.periodValue = h.periodValue;
								q.dataSet = A;
								q.behindColumns = h.behindColumns;
								h.comparedGraphs[A.id] = q;
								q.seriesIdField = "amCategoryIdField";
								q.connect = h.connect;
								var s = h.compareField;
								s || (s = h.valueField);
								g = !1;
								n = A.fieldMappings;
								m;
								for (m = 0; m < n.length; m++)
									t = n[m], t.toField == s && (g = !0);
								if (g) {
									q.valueField = s;
									q.title = A.title;
									q.lineColor = A.color;
									h.compareGraphType
											&& (q.type = h.compareGraphType);
									g = h.compareGraphLineThickness;
									isNaN(g) || (q.lineThickness = g);
									g = h.compareGraphDashLength;
									isNaN(g) || (q.dashLength = g);
									g = h.compareGraphLineAlpha;
									isNaN(g) || (q.lineAlpha = g);
									g = h.compareGraphCornerRadiusTop;
									isNaN(g) || (q.cornerRadiusTop = g);
									g = h.compareGraphCornerRadiusBottom;
									isNaN(g) || (q.cornerRadiusBottom = g);
									g = h.compareGraphBalloonColor;
									isNaN(g) || (q.balloonColor = g);
									g = h.compareGraphBulletColor;
									isNaN(g) || (q.bulletColor = g);
									if (g = h.compareGraphFillColors)
										q.fillColors = g;
									if (g = h.compareGraphNegativeFillColors)
										q.negativeFillColors = g;
									if (g = h.compareGraphFillAlphas)
										q.fillAlphas = g;
									if (g = h.compareGraphNegativeFillAlphas)
										q.negativeFillAlphas = g;
									if (g = h.compareGraphBullet)
										q.bullet = g;
									if (g = h.compareGraphNumberFormatter)
										q.numberFormatter = g;
									g = h.compareGraphPrecision;
									isNaN(g) || (q.precision = g);
									if (g = h.compareGraphBalloonText)
										q.balloonText = g;
									g = h.compareGraphBulletSize;
									isNaN(g) || (q.bulletSize = g);
									g = h.compareGraphBulletAlpha;
									isNaN(g) || (q.bulletAlpha = g);
									g = h.compareGraphBulletBorderAlpha;
									isNaN(g) || (q.bulletBorderAlpha = g);
									if (g = h.compareGraphBulletBorderColor)
										q.bulletBorderColor = g;
									g = h.compareGraphBulletBorderThickness;
									isNaN(g) || (q.bulletBorderThickness = g);
									q.visibleInLegend = h.compareGraphVisibleInLegend;
									q.balloonFunction = h.compareGraphBalloonFunction;
									q.hideBulletsCount = h.hideBulletsCount;
									q.valueAxis = h.valueAxis;
									v
											&& (r && w
													? (q.legendValueText = w, q.legendPeriodValueText = y)
													: (l
															&& (q.legendValueText = l), q.legendPeriodValueText = z));
									c.showComparedOnTop
											? c.graphs.push(q)
											: c.graphs.unshift(q);
									this.comparedGraphs.push({
												graph : q,
												dataSet : A
											})
								}
							}
						}
					}
					v
							&& (r && w
									? (h.legendValueText = w, h.legendPeriodValueText = y)
									: (l && (h.legendValueText = l), h.legendPeriodValueText = z))
				}
			}
		}
	},
	choosePeriod : function(a, b, c) {
		var d = this.categoryAxesSettings, e = d.groupToPeriods, k = e[a], e = e[a
				+ 1], f = AmCharts.extractPeriod(k), f = AmCharts
				.getPeriodDuration(f.period, f.count), h = b.getTime(), g = c
				.getTime(), d = d.maxSeries;
		return (g - h) / f > d && 0 < d && e
				? this.choosePeriod(a + 1, b, c)
				: k
	},
	handleCursorChange : function(a) {
		var b = a.target, c = a.position, d = a.zooming;
		a = a.index;
		var e = this.chartCursors, k;
		for (k = 0; k < e.length; k++) {
			var f = e[k];
			f != b
					&& c
					&& (f.isZooming(d), f.previousMousePosition = NaN, f.forceShow = !0, f.initialMouse = b.initialMouse, f.selectionPosX = b.selectionPosX, f
							.setPosition(c, !1, a))
		}
	},
	getSelections : function() {
		var a = [], b = this.dataSets, c;
		for (c = 0; c < b.length; c++) {
			var d = b[c];
			d.compared && a.push(d)
		}
		this.comparedDataSets = a;
		b = this.panels;
		for (c = 0; c < b.length; c++)
			d = b[c], "never" != d.recalculateToPercents && 0 < a.length ? d
					.hideDrawingIcons(!0) : d.drawingIconsEnabled
					&& d.hideDrawingIcons(!1)
	},
	addPanel : function(a) {
		this.panels.push(a);
		AmCharts.removeChart(a);
		AmCharts.addChart(a)
	},
	addPanelAt : function(a, b) {
		this.panels.splice(b, 0, a);
		AmCharts.removeChart(a);
		AmCharts.addChart(a)
	},
	removePanel : function(a) {
		var b = this.panels, c;
		for (c = b.length - 1; 0 <= c; c--)
			if (b[c] == a) {
				var d = {
					type : "panelRemoved",
					panel : a,
					chart : this
				};
				this.fire(d.type, d);
				b.splice(c, 1);
				a.destroy();
				a.clear()
			}
	},
	validateData : function() {
		this.resetDataParsed();
		this.updateDataSets();
		this.mainDataSet.compared = !1;
		this.updateGraphs();
		this.updateData();
		var a = this.dataSetSelector;
		a && a.write(a.div)
	},
	resetDataParsed : function() {
		var a = this.dataSets, b;
		for (b = 0; b < a.length; b++)
			a[b].dataParsed = !1
	},
	validateNow : function() {
		this.skipDefault = !0;
		this.chartRendered = !1;
		this.clear(!0);
		this.write(this.div)
	},
	hideStockEvents : function() {
		this.showHideEvents(!1);
		this.eventsHidden = !0
	},
	showStockEvents : function() {
		this.showHideEvents(!0);
		this.eventsHidden = !1
	},
	showHideEvents : function(a) {
		var b = this.panels, c;
		for (c = 0; c < b.length; c++) {
			var d = b[c].graphs, e;
			for (e = 0; e < d.length; e++) {
				var k = d[e];
				!0 === a ? k.showBullets() : k.hideBullets()
			}
		}
	},
	invalidateSize : function() {
		var a = this;
		clearTimeout(a.validateTO);
		var b = setTimeout(function() {
					a.validateNow()
				}, 5);
		a.validateTO = b
	},
	measure : function() {
		var a = this.div, b = a.offsetWidth, c = a.offsetHeight;
		a.clientHeight && (b = a.clientWidth, c = a.clientHeight);
		this.divRealWidth = b;
		this.divRealHeight = c
	},
	clear : function(a) {
		var b = this.panels, c;
		if (b)
			for (c = 0; c < b.length; c++) {
				var d = b[c];
				a || (d.cleanChart(), d.destroy());
				d.clear(a)
			}
		(b = this.scrollbarChart) && b.clear();
		if (b = this.div)
			b.innerHTML = "";
		a || (this.div = null, AmCharts.deleteObject(this))
	}
});
AmCharts.StockEvent = AmCharts.Class({
			construct : function() {
			}
		});
AmCharts.DataSet = AmCharts.Class({
			construct : function() {
				this.cname = "DataSet";
				this.fieldMappings = [];
				this.dataProvider = [];
				this.agregatedDataProviders = [];
				this.stockEvents = [];
				this.compared = !1;
				this.showInCompare = this.showInSelect = !0
			}
		});
AmCharts.PeriodSelector = AmCharts.Class({
	construct : function(a) {
		this.cname = "PeriodSelector";
		this.theme = a;
		this.createEvents("changed");
		this.inputFieldsEnabled = !0;
		this.position = "bottom";
		this.width = 180;
		this.fromText = "From: ";
		this.toText = "to: ";
		this.periodsText = "Zoom: ";
		this.periods = [];
		this.inputFieldWidth = 100;
		this.dateFormat = "DD-MM-YYYY";
		this.hideOutOfScopePeriods = !0;
		AmCharts.applyTheme(this, a, this.cname)
	},
	zoom : function(a, b) {
		var c = this.chart;
		this.inputFieldsEnabled
				&& (this.startDateField.value = AmCharts.formatDate(a,
						this.dateFormat, c), this.endDateField.value = AmCharts
						.formatDate(b, this.dateFormat, c));
		this.markButtonAsSelected()
	},
	write : function(a) {
		var b = this;
		a.className = "amChartsPeriodSelector";
		var c = b.width, d = b.position;
		b.width = void 0;
		b.position = void 0;
		AmCharts.applyStyles(a.style, b);
		b.width = c;
		b.position = d;
		b.div = a;
		a.innerHTML = "";
		c = b.theme;
		d = b.position;
		d = "top" == d || "bottom" == d ? !1 : !0;
		b.vertical = d;
		var e = 0, k = 0;
		if (b.inputFieldsEnabled) {
			var f = document.createElement("div");
			a.appendChild(f);
			var h = document.createTextNode(AmCharts.lang.fromText
					|| b.fromText);
			f.appendChild(h);
			d
					? AmCharts.addBr(f)
					: (f.style.styleFloat = "left", f.style.display = "inline");
			var g = document.createElement("input");
			g.className = "amChartsInputField";
			c && AmCharts.applyStyles(g.style, c.PeriodInputField);
			g.style.textAlign = "center";
			g.onblur = function(a) {
				b.handleCalChange(a)
			};
			AmCharts.isNN && g.addEventListener("keypress", function(a) {
						b.handleCalendarChange.call(b, a)
					}, !0);
			AmCharts.isIE && g.attachEvent("onkeypress", function(a) {
						b.handleCalendarChange.call(b, a)
					});
			f.appendChild(g);
			b.startDateField = g;
			if (d)
				h = b.width - 6 + "px", AmCharts.addBr(f);
			else {
				var h = b.inputFieldWidth + "px", n = document
						.createTextNode(" ");
				f.appendChild(n)
			}
			g.style.width = h;
			g = document.createTextNode(AmCharts.lang.toText || b.toText);
			f.appendChild(g);
			d && AmCharts.addBr(f);
			g = document.createElement("input");
			g.className = "amChartsInputField";
			c && AmCharts.applyStyles(g.style, c.PeriodInputField);
			g.style.textAlign = "center";
			g.onblur = function() {
				b.handleCalChange()
			};
			AmCharts.isNN && g.addEventListener("keypress", function(a) {
						b.handleCalendarChange.call(b, a)
					}, !0);
			AmCharts.isIE && g.attachEvent("onkeypress", function(a) {
						b.handleCalendarChange.call(b, a)
					});
			f.appendChild(g);
			b.endDateField = g;
			d ? AmCharts.addBr(f) : e = g.offsetHeight + 2;
			h && (g.style.width = h)
		}
		f = b.periods;
		if (AmCharts.ifArray(f)) {
			h = document.createElement("div");
			d
					|| (h.style.cssFloat = "right", h.style.styleFloat = "right", h.style.display = "inline");
			a.appendChild(h);
			d && AmCharts.addBr(h);
			a = document.createTextNode(AmCharts.lang.periodsText
					|| b.periodsText);
			h.appendChild(a);
			b.periodContainer = h;
			var m;
			for (a = 0; a < f.length; a++)
				g = f[a], m = document.createElement("input"), m.type = "button", m.value = g.label, m.period = g.period, m.count = g.count, m.periodObj = g, m.className = "amChartsButton", c
						&& AmCharts.applyStyles(m.style, c.PeriodButton), d
						&& (m.style.width = b.width - 1 + "px"), m.style.boxSizing = "border-box", h
						.appendChild(m), b.addEventListeners(m), g.button = m;
			!d && m && (k = m.offsetHeight);
			b.offsetHeight = Math.max(e, k)
		}
	},
	addEventListeners : function(a) {
		var b = this;
		AmCharts.isNN && a.addEventListener("click", function(a) {
					b.handlePeriodChange.call(b, a)
				}, !0);
		AmCharts.isIE && a.attachEvent("onclick", function(a) {
					b.handlePeriodChange.call(b, a)
				})
	},
	getPeriodDates : function() {
		var a = this.periods, b;
		for (b = 0; b < a.length; b++)
			this.selectPeriodButton(a[b], !0)
	},
	handleCalendarChange : function(a) {
		13 == a.keyCode && this.handleCalChange(a)
	},
	handleCalChange : function(a) {
		var b = this.dateFormat, c = AmCharts.stringToDate(
				this.startDateField.value, b), b = this.chart
				.getLastDate(AmCharts.stringToDate(this.endDateField.value, b));
		try {
			this.startDateField.blur(), this.endDateField.blur()
		} catch (d) {
		}
		if (c && b) {
			var e = {
				type : "changed"
			};
			e.startDate = c;
			e.endDate = b;
			e.chart = this.chart;
			e.event = a;
			this.fire(e.type, e)
		}
	},
	handlePeriodChange : function(a) {
		this.selectPeriodButton(
				(a.srcElement ? a.srcElement : a.target).periodObj, !1, a)
	},
	setRanges : function(a, b) {
		this.firstDate = a;
		this.lastDate = b;
		this.getPeriodDates()
	},
	selectPeriodButton : function(a, b, c) {
		var d = a.button, e = d.count, k = d.period, f, h, g = this.firstDate, n = this.lastDate, m, t = this.theme;
		g
				&& n
				&& ("MAX" == k
						? (f = g, h = n)
						: "YTD" == k
								? (f = new Date, f.setMonth(0, 1), f.setHours(
										0, 0, 0, 0), 0 === e
										&& f.setDate(f.getDate() - 1), h = this.lastDate)
								: "YYYY" == k || "MM" == k
										? this.selectFromStart
												? (f = g, h = new Date(g), h
														.setMonth(h.getMonth()
																+ e))
												: (f = new Date(n), AmCharts
														.changeDate(f, k, e, !1), f
														.setDate(f.getDate()
																- 1), h = n)
										: (m = AmCharts.getPeriodDuration(k, e), this.selectFromStart
												? (f = g, h = new Date(g
														.getTime()
														+ m - 1))
												: (f = new Date(n.getTime() - m
														+ 1), h = n)), a.startTime = f
						.getTime(), this.hideOutOfScopePeriods
						&& (b && a.startTime < g.getTime()
								? d.style.display = "none"
								: d.style.display = "inline"), f.getTime() > n
						.getTime()
						&& (m = AmCharts.getPeriodDuration("DD", 1), f = new Date(n
								.getTime()
								- m)), f.getTime() < g.getTime() && (f = g), "YTD" == k
						&& (a.startTime = f.getTime()), a.endTime = h.getTime(), b
						|| (this.skipMark = !0, this.unselectButtons(), d.className = "amChartsButtonSelected", t
								&& AmCharts.applyStyles(d.style,
										t.PeriodButtonSelected), a = {
							type : "changed"
						}, a.startDate = f, a.endDate = h, a.predefinedPeriod = k, a.chart = this.chart, a.count = e, a.event = c, this
								.fire(a.type, a)))
	},
	markButtonAsSelected : function() {
		if (!this.skipMark) {
			var a = this.chart, b = this.periods, c = a.startDate.getTime(), a = a.endDate
					.getTime(), d = this.lastDate.getTime();
			a > d && (a = d);
			d = this.theme;
			this.unselectButtons();
			var e;
			for (e = b.length - 1; 0 <= e; e--) {
				var k = b[e], f = k.button;
				k.startTime
						&& k.endTime
						&& c == k.startTime
						&& a == k.endTime
						&& (this.unselectButtons(), f.className = "amChartsButtonSelected", d
								&& AmCharts.applyStyles(f.style,
										d.PeriodButtonSelected))
			}
		}
		this.skipMark = !1
	},
	unselectButtons : function() {
		var a = this.periods, b, c = this.theme;
		for (b = a.length - 1; 0 <= b; b--) {
			var d = a[b].button;
			d.className = "amChartsButton";
			c && AmCharts.applyStyles(d.style, c.PeriodButton)
		}
	},
	setDefaultPeriod : function() {
		var a = this.periods, b;
		for (b = 0; b < a.length; b++) {
			var c = a[b];
			c.selected && this.selectPeriodButton(c)
		}
	}
});
AmCharts.StockGraph = AmCharts.Class({
			inherits : AmCharts.AmGraph,
			construct : function(a) {
				AmCharts.StockGraph.base.construct.call(this, a);
				this.cname = "StockGraph";
				this.useDataSetColors = !0;
				this.periodValue = "Close";
				this.compareGraphType = "line";
				this.compareGraphVisibleInLegend = !0;
				this.comparable = this.resetTitleOnDataSetChange = !1;
				this.comparedGraphs = {};
				this.showEventsOnComparedGraphs = !1;
				AmCharts.applyTheme(this, a, this.cname)
			}
		});
AmCharts.StockPanel = AmCharts.Class({
	inherits : AmCharts.AmSerialChart,
	construct : function(a) {
		AmCharts.StockPanel.base.construct.call(this, a);
		this.cname = "StockPanel";
		this.theme = a;
		this.showCategoryAxis = this.showComparedOnTop = !0;
		this.recalculateToPercents = "whenComparing";
		this.panelHeaderPaddingBottom = this.panelHeaderPaddingLeft = this.panelHeaderPaddingRight = this.panelHeaderPaddingTop = 0;
		this.trendLineAlpha = 1;
		this.trendLineColor = "#00CC00";
		this.trendLineColorHover = "#CC0000";
		this.trendLineThickness = 2;
		this.trendLineDashLength = 0;
		this.stockGraphs = [];
		this.drawingIconsEnabled = !1;
		this.iconSize = 18;
		this.autoMargins = this.allowTurningOff = this.eraseAll = this.erasingEnabled = this.drawingEnabled = !1;
		AmCharts.applyTheme(this, a, this.cname)
	},
	initChart : function(a) {
		AmCharts.StockPanel.base.initChart.call(this, a);
		this.drawingIconsEnabled && this.createDrawIcons();
		(a = this.chartCursor) && this.listenTo(a, "draw", this.handleDraw)
	},
	addStockGraph : function(a) {
		this.stockGraphs.push(a);
		return a
	},
	handleCursorZoom : function(a) {
		this.chartCursor && this.chartCursor.pan
				&& AmCharts.StockPanel.base.handleCursorZoom.call(this, a)
	},
	removeStockGraph : function(a) {
		var b = this.stockGraphs, c;
		for (c = b.length - 1; 0 <= c; c--)
			b[c] == a && b.splice(c, 1)
	},
	createDrawIcons : function() {
		var a = this, b = a.iconSize, c = a.container, d = a.pathToImages, e = a.realWidth
				- 2 * b - 1 - a.marginRight, k = AmCharts.rect(c, b, b, "#000",
				.005), f = AmCharts.rect(c, b, b, "#000", .005);
		f.translate(b + 1, 0);
		var h = c.image(d + "pencilIcon.gif", 0, 0, b, b);
		a.pencilButton = h;
		f.setAttr("cursor", "pointer");
		k.setAttr("cursor", "pointer");
		k.mouseup(function() {
					a.handlePencilClick()
				});
		var g = c.image(d + "pencilIconH.gif", 0, 0, b, b);
		a.pencilButtonPushed = g;
		a.drawingEnabled || g.hide();
		var n = c.image(d + "eraserIcon.gif", b + 1, 0, b, b);
		a.eraserButton = n;
		f.mouseup(function() {
					a.handleEraserClick()
				});
		k.touchend && (k.touchend(function() {
					a.handlePencilClick()
				}), f.touchend(function() {
					a.handleEraserClick()
				}));
		b = c.image(d + "eraserIconH.gif", b + 1, 0, b, b);
		a.eraserButtonPushed = b;
		a.erasingEnabled || b.hide();
		c = c.set([h, g, n, b, k, f]);
		c.translate(e, 1);
		this.hideIcons && c.hide()
	},
	handlePencilClick : function() {
		var a = !this.drawingEnabled;
		this.disableDrawing(!a);
		this.erasingEnabled = !1;
		var b = this.eraserButtonPushed;
		b && b.hide();
		b = this.pencilButtonPushed;
		a ? b && b.show() : (b && b.hide(), this.setMouseCursor("auto"))
	},
	disableDrawing : function(a) {
		this.drawingEnabled = !a;
		var b = this.chartCursor;
		this.stockChart.enableCursors(a);
		b && b.enableDrawing(!a)
	},
	handleEraserClick : function() {
		this.disableDrawing(!0);
		var a = this.pencilButtonPushed;
		a && a.hide();
		a = this.eraserButtonPushed;
		if (this.eraseAll) {
			var a = this.trendLines, b;
			for (b = a.length - 1; 0 <= b; b--) {
				var c = a[b];
				c.isProtected || this.removeTrendLine(c)
			}
			this.validateNow()
		} else
			(this.erasingEnabled = b = !this.erasingEnabled)
					? (a && a.show(), this
							.setTrendColorHover(this.trendLineColorHover), this
							.setMouseCursor("auto"))
					: (a && a.hide(), this.setTrendColorHover())
	},
	setTrendColorHover : function(a) {
		var b = this.trendLines, c;
		for (c = b.length - 1; 0 <= c; c--) {
			var d = b[c];
			d.isProtected || (d.rollOverColor = a)
		}
	},
	handleDraw : function(a) {
		var b = this.drawOnAxis;
		AmCharts.isString(b) && (b = this.getValueAxisById(b));
		b || (b = this.valueAxes[0]);
		this.drawOnAxis = b;
		var c = this.categoryAxis, d = a.initialX, e = a.finalX, k = a.initialY;
		a = a.finalY;
		var f = new AmCharts.TrendLine(this.theme);
		f.initialDate = c.coordinateToDate(d);
		f.finalDate = c.coordinateToDate(e);
		f.initialValue = b.coordinateToValue(k);
		f.finalValue = b.coordinateToValue(a);
		f.lineAlpha = this.trendLineAlpha;
		f.lineColor = this.trendLineColor;
		f.lineThickness = this.trendLineThickness;
		f.dashLength = this.trendLineDashLength;
		f.valueAxis = b;
		f.categoryAxis = c;
		this.addTrendLine(f);
		this.listenTo(f, "click", this.handleTrendClick);
		this.validateNow()
	},
	hideDrawingIcons : function(a) {
		(this.hideIcons = a) && this.disableDrawing(a)
	},
	handleTrendClick : function(a) {
		this.erasingEnabled
				&& (a = a.trendLine, this.eraseAll || a.isProtected
						|| this.removeTrendLine(a), this.validateNow())
	},
	handleWheelReal : function(a, b) {
		var c = this.scrollbarChart;
		if (!this.wheelBusy && c) {
			var d = 1;
			b && (d = -1);
			var c = c.chartScrollbar, e = this.categoryAxis.minDuration();
			0 > a
					? (d = this.startTime + d * e, e = this.endTime + 1 * e)
					: (d = this.startTime - d * e, e = this.endTime - 1 * e);
			d < this.firstTime && (d = this.firstTime);
			e > this.lastTime && (e = this.lastTime);
			d < e && c.timeZoom(d, e, !0)
		}
	}
});
AmCharts.CategoryAxesSettings = AmCharts.Class({
			construct : function(a) {
				this.cname = "CategoryAxesSettings";
				this.minPeriod = "DD";
				this.equalSpacing = !1;
				this.axisHeight = 28;
				this.tickLength = this.axisAlpha = 0;
				this.gridCount = 10;
				this.maxSeries = 150;
				this.groupToPeriods = "ss 10ss 30ss mm 10mm 30mm hh DD WW MM YYYY"
						.split(" ");
				this.markPeriodChange = this.autoGridCount = !0;
				AmCharts.applyTheme(this, a, this.cname)
			}
		});
AmCharts.ChartCursorSettings = AmCharts.Class({
			construct : function(a) {
				this.cname = "ChartCursorSettings";
				this.enabled = !0;
				this.bulletsEnabled = this.valueBalloonsEnabled = !1;
				this.categoryBalloonDateFormats = [{
							period : "YYYY",
							format : "YYYY"
						}, {
							period : "MM",
							format : "MMM, YYYY"
						}, {
							period : "WW",
							format : "MMM DD, YYYY"
						}, {
							period : "DD",
							format : "MMM DD, YYYY"
						}, {
							period : "hh",
							format : "JJ:NN"
						}, {
							period : "mm",
							format : "JJ:NN"
						}, {
							period : "ss",
							format : "JJ:NN:SS"
						}, {
							period : "fff",
							format : "JJ:NN:SS"
						}];
				AmCharts.applyTheme(this, a, this.cname)
			},
			categoryBalloonDateFormat : function(a) {
				var b = this.categoryBalloonDateFormats, c, d;
				for (d = 0; d < b.length; d++)
					b[d].period == a && (c = b[d].format);
				return c
			}
		});
AmCharts.ChartScrollbarSettings = AmCharts.Class({
			construct : function(a) {
				this.cname = "ChartScrollbarSettings";
				this.height = 40;
				this.enabled = !0;
				this.color = "#FFFFFF";
				this.updateOnReleaseOnly = this.autoGridCount = !0;
				this.hideResizeGrips = !1;
				this.position = "bottom";
				this.minDistance = 1;
				AmCharts.applyTheme(this, a, this.cname)
			}
		});
AmCharts.LegendSettings = AmCharts.Class({
			construct : function(a) {
				this.cname = "LegendSettings";
				this.marginBottom = this.marginTop = 0;
				this.usePositiveNegativeOnPercentsOnly = !0;
				this.positiveValueColor = "#00CC00";
				this.negativeValueColor = "#CC0000";
				this.autoMargins = this.equalWidths = this.textClickEnabled = !1;
				AmCharts.applyTheme(this, a, this.cname)
			}
		});
AmCharts.PanelsSettings = AmCharts.Class({
	construct : function(a) {
		this.cname = "PanelsSettings";
		this.marginBottom = this.marginTop = this.marginRight = this.marginLeft = 0;
		this.backgroundColor = "#FFFFFF";
		this.backgroundAlpha = 0;
		this.panelSpacing = 8;
		this.panEventsEnabled = !0;
		this.creditsPosition = "top-right";
		AmCharts.applyTheme(this, a, this.cname)
	}
});
AmCharts.StockEventsSettings = AmCharts.Class({
			construct : function(a) {
				this.cname = "StockEventsSettings";
				this.type = "sign";
				this.backgroundAlpha = 1;
				this.backgroundColor = "#DADADA";
				this.borderAlpha = 1;
				this.borderColor = "#888888";
				this.balloonColor = this.rollOverColor = "#CC0000";
				AmCharts.applyTheme(this, a, this.cname)
			}
		});
AmCharts.ValueAxesSettings = AmCharts.Class({
			construct : function(a) {
				this.cname = "ValueAxesSettings";
				this.tickLength = 0;
				this.showFirstLabel = this.autoGridCount = this.inside = !0;
				this.showLastLabel = !1;
				this.axisAlpha = 0;
				AmCharts.applyTheme(this, a, this.cname)
			}
		});
AmCharts.getItemIndex = function(a, b) {
	var c = -1, d;
	for (d = 0; d < b.length; d++)
		a == b[d] && (c = d);
	return c
};
AmCharts.addBr = function(a) {
	a.appendChild(document.createElement("br"))
};
AmCharts.applyStyles = function(a, b) {
	if (b && a)
		for (var c in a) {
			var d = c, e = b[d];
			if (void 0 !== e)
				try {
					a[d] = e
				} catch (k) {
				}
		}
};
AmCharts.parseStockData = function(a, b, c, d, e) {
	(new Date).getTime();
	var k = {}, f = a.dataProvider, h = a.categoryField;
	if (h) {
		var g = AmCharts.getItemIndex(b, c), n = c.length, m, t = f.length, r, v = {};
		for (m = g; m < n; m++)
			r = c[m], k[r] = [];
		var w = {}, l = a.fieldMappings, y = l.length;
		for (m = 0; m < t; m++) {
			var z = f[m], x = z[h], x = x instanceof Date ? AmCharts.newDate(x,
					b) : e ? AmCharts.stringToDate(x, e) : new Date(x), B = x
					.getTime(), A = {};
			for (r = 0; r < y; r++)
				A[l[r].toField] = z[l[r].fromField];
			var q;
			for (q = g; q < n; q++) {
				r = c[q];
				var s = AmCharts.extractPeriod(r), C = s.period, E = s.count, u, p;
				if (q == g || B >= v[r] || !v[r]) {
					w[r] = {};
					w[r].amCategoryIdField = String(AmCharts.resetDateToMin(x,
							C, E, d).getTime());
					var D;
					for (D = 0; D < y; D++)
						s = l[D].toField, u = w[r], p = Number(A[s]), u[s
								+ "Count"] = 0, u[s + "Sum"] = 0, isNaN(p)
								|| (u[s + "Open"] = p, u[s + "Sum"] = p, u[s
										+ "High"] = p, u[s + "Low"] = p, u[s
										+ "Close"] = p, u[s + "Count"] = 1, u[s
										+ "Average"] = p);
					u.dataContext = z;
					k[r].push(w[r]);
					q > g
							&& (s = AmCharts.newDate(x, b), s = AmCharts
									.changeDate(s, C, E, !0), s = AmCharts
									.resetDateToMin(s, C, E, d), v[r] = s
									.getTime());
					if (q == g)
						for (var F in z)
							z.hasOwnProperty(F) && (w[r][F] = z[F]);
					w[r][h] = AmCharts.newDate(x, b)
				} else
					for (C = 0; C < y; C++)
						s = l[C].toField, u = w[r], m == t - 1
								&& (u[h] = AmCharts.newDate(x, b)), p = Number(A[s]), isNaN(p)
								|| (isNaN(u[s + "Low"]) && (u[s + "Low"] = p), p < u[s
										+ "Low"]
										&& (u[s + "Low"] = p), isNaN(u[s
										+ "High"])
										&& (u[s + "High"] = p), p > u[s
										+ "High"]
										&& (u[s + "High"] = p), u[s + "Close"] = p, E = AmCharts
										.getDecimals(u[s + "Sum"]), D = AmCharts
										.getDecimals(p), u[s + "Sum"] += p, u[s
										+ "Sum"] = AmCharts.roundTo(
										u[s + "Sum"], Math.max(E, D)), u[s
										+ "Count"]++, u[s + "Average"] = u[s
										+ "Sum"]
										/ u[s + "Count"])
			}
		}
	}
	a.agregatedDataProviders = k
};
AmCharts.parseEvents = function(a, b, c, d, e, k) {
	var f = a.stockEvents, h = a.agregatedDataProviders, g = b.length, n, m, t, r, v, w, l, y;
	for (n = 0; n < g; n++) {
		w = b[n];
		v = w.graphs;
		t = v.length;
		var z;
		for (m = 0; m < t; m++)
			r = v[m], r.customBulletField = "amCustomBullet" + r.id + "_"
					+ w.id, r.bulletConfigField = "amCustomBulletConfig" + r.id
					+ "_" + w.id;
		for (var x = 0; x < f.length; x++)
			if (l = f[x], z = l.graph, AmCharts.isString(z)
					&& (z = AmCharts.getObjById(v, z)))
				l.graph = z
	}
	for (var B in h)
		if (h.hasOwnProperty(B)) {
			z = h[B];
			var A = AmCharts.extractPeriod(B), q = z.length, s;
			for (s = 0; s < q; s++) {
				var C = z[s];
				n = C[a.categoryField];
				y = n instanceof Date;
				k && !y && (n = AmCharts.stringToDate(n, k));
				var E = n.getTime();
				v = A.period;
				var x = A.count, u;
				u = "fff" == v ? n.getTime() + 1 : AmCharts.resetDateToMin(
						AmCharts.changeDate(new Date(n), A.period, A.count), v,
						x, d).getTime();
				for (n = 0; n < g; n++)
					for (w = b[n], v = w.graphs, t = v.length, m = 0; m < t; m++) {
						r = v[m];
						var p = {};
						p.eventDispatcher = e;
						p.eventObjects = [];
						p.letters = [];
						p.descriptions = [];
						p.shapes = [];
						p.backgroundColors = [];
						p.backgroundAlphas = [];
						p.borderColors = [];
						p.borderAlphas = [];
						p.colors = [];
						p.rollOverColors = [];
						p.showOnAxis = [];
						p.values = [];
						p.showAts = [];
						for (x = 0; x < f.length; x++) {
							l = f[x];
							y = l.date instanceof Date;
							k
									&& !y
									&& (l.date = AmCharts.stringToDate(l.date,
											k));
							y = l.date.getTime();
							var D = !1;
							l.graph
									&& (l.graph.showEventsOnComparedGraphs
											&& l.graph.comparedGraphs[a.id]
											&& (D = !0), (r == l.graph || D)
											&& y >= E
											&& y < u
											&& (p.eventObjects.push(l), p.letters
													.push(l.text), p.descriptions
													.push(l.description), l.type
													? p.shapes.push(l.type)
													: p.shapes.push(c.type), void 0 !== l.backgroundColor
													? p.backgroundColors
															.push(l.backgroundColor)
													: p.backgroundColors
															.push(c.backgroundColor), isNaN(l.backgroundAlpha)
													? p.backgroundAlphas
															.push(c.backgroundAlpha)
													: p.backgroundAlphas
															.push(l.backgroundAlpha), isNaN(l.borderAlpha)
													? p.borderAlphas
															.push(c.borderAlpha)
													: p.borderAlphas
															.push(l.borderAlpha), void 0 !== l.borderColor
													? p.borderColors
															.push(l.borderColor)
													: p.borderColors
															.push(c.borderColor), void 0 !== l.rollOverColor
													? p.rollOverColors
															.push(l.rollOverColor)
													: p.rollOverColors
															.push(c.rollOverColor), void 0 !== l.showAt
													? p.showAts.push(l.showAt)
													: p.showAts.push(c.showAt), p.colors
													.push(l.color), p.values
													.push(l.value), !l.panel
													&& l.graph
													&& (l.panel = l.graph.chart), p.showOnAxis
													.push(l.showOnAxis), p.date = new Date(l.date)));
							0 < p.shapes.length
									&& (l = "amCustomBullet" + r.id + "_"
											+ w.id, y = "amCustomBulletConfig"
											+ r.id + "_" + w.id, C[l] = AmCharts.StackedBullet, C[y] = p)
						}
					}
			}
		}
};
AmCharts.StockLegend = AmCharts.Class({
			inherits : AmCharts.AmLegend,
			construct : function(a) {
				AmCharts.StockLegend.base.construct.call(this, a);
				this.cname = "StockLegend";
				this.valueTextComparing = "[[percents.value]]%";
				this.valueTextRegular = "[[value]]";
				AmCharts.applyTheme(this, a, this.cname)
			},
			drawLegend : function() {
				var a = this;
				AmCharts.StockLegend.base.drawLegend.call(a);
				var b = a.chart;
				if (b.allowTurningOff) {
					var c = a.container, d = c.image(b.pathToImages
									+ "xIcon.gif", b.realWidth - 17, 3, 17, 17), b = c
							.image(b.pathToImages + "xIconH.gif", b.realWidth
											- 17, 3, 17, 17);
					b.hide();
					a.xButtonHover = b;
					d.mouseup(function() {
								a.handleXClick()
							}).mouseover(function() {
								a.handleXOver()
							});
					b.mouseup(function() {
								a.handleXClick()
							}).mouseout(function() {
								a.handleXOut()
							})
				}
			},
			handleXOver : function() {
				this.xButtonHover.show()
			},
			handleXOut : function() {
				this.xButtonHover.hide()
			},
			handleXClick : function() {
				var a = this.chart, b = a.stockChart;
				b.removePanel(a);
				b.validateNow()
			}
		});
AmCharts.DataSetSelector = AmCharts.Class({
	construct : function(a) {
		this.cname = "DataSetSelector";
		this.theme = a;
		this.createEvents("dataSetSelected", "dataSetCompared",
				"dataSetUncompared");
		this.position = "left";
		this.selectText = "Select:";
		this.comboBoxSelectText = "Select...";
		this.compareText = "Compare to:";
		this.width = 180;
		this.dataProvider = [];
		this.listHeight = 150;
		this.listCheckBoxSize = 14;
		this.rollOverBackgroundColor = "#b2e1ff";
		this.selectedBackgroundColor = "#7fceff";
		AmCharts.applyTheme(this, a, this.cname)
	},
	write : function(a) {
		var b = this, c, d = b.theme;
		a.className = "amChartsDataSetSelector";
		var e = b.width;
		c = b.position;
		b.width = void 0;
		b.position = void 0;
		AmCharts.applyStyles(a.style, b);
		b.div = a;
		b.width = e;
		b.position = c;
		a.innerHTML = "";
		var e = b.position, k;
		k = "top" == e || "bottom" == e ? !1 : !0;
		b.vertical = k;
		var f;
		k && (f = b.width + "px");
		var e = b.dataProvider, h, g;
		if (1 < b.countDataSets("showInSelect")) {
			c = document.createTextNode(AmCharts.lang.selectText
					|| b.selectText);
			a.appendChild(c);
			k && AmCharts.addBr(a);
			var n = document.createElement("select");
			f && (n.style.width = f);
			b.selectCB = n;
			d && AmCharts.applyStyles(n.style, d.DataSetSelect);
			a.appendChild(n);
			AmCharts.isNN && n.addEventListener("change", function(a) {
						b.handleDataSetChange.call(b, a)
					}, !0);
			AmCharts.isIE && n.attachEvent("onchange", function(a) {
						b.handleDataSetChange.call(b, a)
					});
			for (c = 0; c < e.length; c++)
				if (h = e[c], !0 === h.showInSelect) {
					g = document.createElement("option");
					g.text = h.title;
					g.value = c;
					h == b.chart.mainDataSet && (g.selected = !0);
					try {
						n.add(g, null)
					} catch (m) {
						n.add(g)
					}
				}
			b.offsetHeight = n.offsetHeight
		}
		if (0 < b.countDataSets("showInCompare") && 1 < e.length)
			if (k ? (AmCharts.addBr(a), AmCharts.addBr(a)) : (c = document
					.createTextNode(" "), a.appendChild(c)), c = document
					.createTextNode(AmCharts.lang.compareText || b.compareText), a
					.appendChild(c), g = b.listCheckBoxSize, k) {
				AmCharts.addBr(a);
				f = document.createElement("div");
				a.appendChild(f);
				f.className = "amChartsCompareList";
				d && AmCharts.applyStyles(f.style, d.DataSetCompareList);
				f.style.overflow = "auto";
				f.style.overflowX = "hidden";
				f.style.width = b.width - 2 + "px";
				f.style.maxHeight = b.listHeight + "px";
				for (c = 0; c < e.length; c++)
					h = e[c], !0 === h.showInCompare
							&& h != b.chart.mainDataSet
							&& (d = document.createElement("div"), d.style.padding = "4px", d.style.position = "relative", d.name = "amCBContainer", d.dataSet = h, d.style.height = g
									+ "px", h.compared
									&& (d.style.backgroundColor = b.selectedBackgroundColor), f
									.appendChild(d), k = document
									.createElement("div"), k.style.width = g
									+ "px", k.style.height = g + "px", k.style.position = "absolute", k.style.backgroundColor = h.color, d
									.appendChild(k), k = document
									.createElement("div"), k.style.width = "100%", k.style.position = "absolute", k.style.left = g
									+ 10 + "px", d.appendChild(k), h = document
									.createTextNode(h.title), k.style.whiteSpace = "nowrap", k.style.cursor = "default", k
									.appendChild(h), b.addEventListeners(d));
				AmCharts.addBr(a);
				AmCharts.addBr(a)
			} else {
				d = document.createElement("select");
				b.compareCB = d;
				f && (d.style.width = f);
				a.appendChild(d);
				AmCharts.isNN && d.addEventListener("change", function(a) {
							b.handleCBSelect.call(b, a)
						}, !0);
				AmCharts.isIE && d.attachEvent("onchange", function(a) {
							b.handleCBSelect.call(b, a)
						});
				g = document.createElement("option");
				g.text = AmCharts.lang.comboBoxSelectText
						|| b.comboBoxSelectText;
				try {
					d.add(g, null)
				} catch (t) {
					d.add(g)
				}
				for (c = 0; c < e.length; c++)
					if (h = e[c], !0 === h.showInCompare
							&& h != b.chart.mainDataSet) {
						g = document.createElement("option");
						g.text = h.title;
						g.value = c;
						h.compared && (g.selected = !0);
						try {
							d.add(g, null)
						} catch (r) {
							d.add(g)
						}
					}
				b.offsetHeight = d.offsetHeight
			}
	},
	addEventListeners : function(a) {
		var b = this;
		AmCharts.isNN && (a.addEventListener("mouseover", function(a) {
					b.handleRollOver.call(b, a)
				}, !0), a.addEventListener("mouseout", function(a) {
					b.handleRollOut.call(b, a)
				}, !0), a.addEventListener("click", function(a) {
					b.handleClick.call(b, a)
				}, !0));
		AmCharts.isIE && (a.attachEvent("onmouseout", function(a) {
					b.handleRollOut.call(b, a)
				}), a.attachEvent("onmouseover", function(a) {
					b.handleRollOver.call(b, a)
				}), a.attachEvent("onclick", function(a) {
					b.handleClick.call(b, a)
				}))
	},
	handleDataSetChange : function() {
		var a = this.selectCB, a = this.dataProvider[a.options[a.selectedIndex].value], b = this.chart;
		b.mainDataSet = a;
		b.zoomOutOnDataSetChange && (b.startDate = void 0, b.endDate = void 0);
		b.validateData();
		a = {
			type : "dataSetSelected",
			dataSet : a,
			chart : this.chart
		};
		this.fire(a.type, a)
	},
	handleRollOver : function(a) {
		a = this.getRealDiv(a);
		a.dataSet.compared
				|| (a.style.backgroundColor = this.rollOverBackgroundColor)
	},
	handleRollOut : function(a) {
		a = this.getRealDiv(a);
		a.dataSet.compared
				|| (a.style.removeProperty
						&& a.style.removeProperty("background-color"), a.style.removeAttribute
						&& a.style.removeAttribute("backgroundColor"))
	},
	handleCBSelect : function(a) {
		var b = this.compareCB, c = this.dataProvider, d, e;
		for (d = 0; d < c.length; d++)
			e = c[d], e.compared && (a = {
				type : "dataSetUncompared",
				dataSet : e
			}), e.compared = !1;
		c = b.selectedIndex;
		0 < c
				&& (e = this.dataProvider[b.options[c].value], e.compared
						|| (a = {
							type : "dataSetCompared",
							dataSet : e
						}), e.compared = !0);
		b = this.chart;
		b.validateData();
		a.chart = b;
		this.fire(a.type, a)
	},
	handleClick : function(a) {
		a = this.getRealDiv(a).dataSet;
		!0 === a.compared ? (a.compared = !1, a = {
			type : "dataSetUncompared",
			dataSet : a
		}) : (a.compared = !0, a = {
			type : "dataSetCompared",
			dataSet : a
		});
		var b = this.chart;
		b.validateData();
		a.chart = b;
		this.fire(a.type, a)
	},
	getRealDiv : function(a) {
		a || (a = window.event);
		a = a.currentTarget ? a.currentTarget : a.srcElement;
		"amCBContainer" == a.parentNode.name && (a = a.parentNode);
		return a
	},
	countDataSets : function(a) {
		var b = this.dataProvider, c = 0, d;
		for (d = 0; d < b.length; d++)
			!0 === b[d][a] && c++;
		return c
	}
});
AmCharts.StackedBullet = AmCharts.Class({
	construct : function() {
		this.fontSize = 11;
		this.stackDown = !1;
		this.mastHeight = 8;
		this.shapes = [];
		this.backgroundColors = [];
		this.backgroundAlphas = [];
		this.borderAlphas = [];
		this.borderColors = [];
		this.colors = [];
		this.rollOverColors = [];
		this.showOnAxiss = [];
		this.values = [];
		this.showAts = [];
		this.textColor = "#000000";
		this.nextY = 0;
		this.size = 16
	},
	parseConfig : function() {
		var a = this.bulletConfig;
		this.eventObjects = a.eventObjects;
		this.letters = a.letters;
		this.shapes = a.shapes;
		this.backgroundColors = a.backgroundColors;
		this.backgroundAlphas = a.backgroundAlphas;
		this.borderColors = a.borderColors;
		this.borderAlphas = a.borderAlphas;
		this.colors = a.colors;
		this.rollOverColors = a.rollOverColors;
		this.date = a.date;
		this.showOnAxiss = a.showOnAxis;
		this.axisCoordinate = a.minCoord;
		this.showAts = a.showAts;
		this.values = a.values
	},
	write : function(a) {
		this.parseConfig();
		this.container = a;
		this.bullets = [];
		if (this.graph) {
			var b = this.graph.fontSize;
			b && (this.fontSize = b)
		}
		b = this.letters.length;
		(this.mastHeight + 2 * (this.fontSize / 2 + 2)) * b > this.availableSpace
				&& (this.stackDown = !0);
		this.set = a.set();
		a = 0;
		var c;
		for (c = 0; c < b; c++)
			this.shape = this.shapes[c], this.backgroundColor = this.backgroundColors[c], this.backgroundAlpha = this.backgroundAlphas[c], this.borderAlpha = this.borderAlphas[c], this.borderColor = this.borderColors[c], this.rollOverColor = this.rollOverColors[c], this.showOnAxis = this.showOnAxiss[c], this.color = this.colors[c], this.value = this.values[c], this.showAt = this.showAts[c], this
					.addLetter(this.letters[c], a, c), this.showOnAxis || a++
	},
	addLetter : function(a, b, c) {
		var d = this.container;
		b = d.set();
		var e = -1, k = this.stackDown, f = this.graph.valueAxis;
		this.showOnAxis && (this.stackDown = f.reversed ? !0 : !1);
		this.stackDown && (e = 1);
		var h = 0, g = 0, n = 0, m, t = this.fontSize, r = this.mastHeight, v = this.shape, w = this.textColor;
		void 0 !== this.color && (w = this.color);
		void 0 === a && (a = "");
		a = AmCharts.fixBrakes(a);
		a = AmCharts.text(d, a, w, this.chart.fontFamily, this.fontSize);
		a.node.style.pointerEvents = "none";
		d = a.getBBox();
		this.labelWidth = w = d.width;
		this.labelHeight = d.height;
		var l, d = 0;
		switch (v) {
			case "sign" :
				l = this.drawSign(b);
				h = r + 4 + t / 2;
				d = r + t + 4;
				1 == e && (h -= 4);
				break;
			case "flag" :
				l = this.drawFlag(b);
				g = w / 2 + 3;
				h = r + 4 + t / 2;
				d = r + t + 4;
				1 == e && (h -= 4);
				break;
			case "pin" :
				l = this.drawPin(b);
				h = 6 + t / 2;
				d = t + 8;
				break;
			case "triangleUp" :
				l = this.drawTriangleUp(b);
				h = -t - 1;
				d = t + 4;
				e = -1;
				break;
			case "triangleDown" :
				l = this.drawTriangleDown(b);
				h = t + 1;
				d = t + 4;
				e = -1;
				break;
			case "triangleLeft" :
				l = this.drawTriangleLeft(b);
				g = t;
				d = t + 4;
				e = -1;
				break;
			case "triangleRight" :
				l = this.drawTriangleRight(b);
				g = -t;
				e = -1;
				d = t + 4;
				break;
			case "arrowUp" :
				l = this.drawArrowUp(b);
				a.hide();
				break;
			case "arrowDown" :
				l = this.drawArrowDown(b);
				a.hide();
				d = t + 4;
				break;
			case "text" :
				e = -1;
				l = this.drawTextBackground(b, a);
				h = this.labelHeight + 3;
				d = t + 10;
				break;
			case "round" :
				l = this.drawCircle(b)
		}
		this.bullets[c] = l;
		this.showOnAxis
				? (m = isNaN(this.nextAxisY) ? this.axisCoordinate : this.nextY, n = h
						* e, this.nextAxisY = m + e * d)
				: this.value
						? (m = this.value, f.recalculateToPercents
								&& (m = m / f.recBaseValue * 100 - 100), m = f
								.getCoordinate(m)
								- this.bulletY, n = h * e)
						: this.showAt
								? (l = this.graphDataItem.values, f.recalculateToPercents
										&& (l = this.graphDataItem.percents), l
										&& (m = f.getCoordinate(l[this.showAt])
												- this.bulletY, n = h * e))
								: (m = this.nextY, n = h * e);
		a.translate(g, n);
		b.push(a);
		b.translate(0, m);
		this.addEventListeners(b, c);
		this.nextY = m + e * d;
		this.stackDown = k
	},
	addEventListeners : function(a, b) {
		var c = this;
		a.click(function() {
					c.handleClick(b)
				}).mouseover(function() {
					c.handleMouseOver(b)
				}).touchend(function() {
					c.handleMouseOver(b, !0)
				}).mouseout(function() {
					c.handleMouseOut(b)
				})
	},
	drawPin : function(a) {
		var b = -1;
		this.stackDown && (b = 1);
		var c = this.fontSize + 4;
		return this.drawRealPolygon(a, [0, c / 2, c / 2, -c / 2, -c / 2, 0], [
						0, b * c / 4, b * (c + c / 4), b * (c + c / 4),
						b * c / 4, 0])
	},
	drawSign : function(a) {
		var b = -1;
		this.stackDown && (b = 1);
		var c = this.mastHeight * b, d = this.fontSize / 2 + 2, e = AmCharts
				.line(this.container, [0, 0], [0, c], this.borderColor,
						this.borderAlpha, 1), k = AmCharts.circle(
				this.container, d, this.backgroundColor, this.backgroundAlpha,
				1, this.borderColor, this.borderAlpha);
		k.translate(0, c + d * b);
		a.push(e);
		a.push(k);
		this.set.push(a);
		return k
	},
	drawFlag : function(a) {
		var b = -1;
		this.stackDown && (b = 1);
		var c = this.fontSize + 4, d = this.labelWidth + 6, e = this.mastHeight, b = 1 == b
				? b * e
				: b * e - c, e = AmCharts.line(this.container, [0, 0], [0, b],
				this.borderColor, this.borderAlpha, 1), c = AmCharts.polygon(
				this.container, [0, d, d, 0], [0, 0, c, c],
				this.backgroundColor, this.backgroundAlpha, 1,
				this.borderColor, this.borderAlpha);
		c.translate(0, b);
		a.push(e);
		a.push(c);
		this.set.push(a);
		return c
	},
	drawTriangleUp : function(a) {
		var b = this.fontSize + 7;
		return this.drawRealPolygon(a, [0, b / 2, -b / 2, 0], [0, b, b, 0])
	},
	drawArrowUp : function(a) {
		var b = this.size, c = b / 2, d = b / 4;
		return this.drawRealPolygon(a, [0, c, d, d, -d, -d, -c, 0], [0, c, c,
						b, b, c, c, 0])
	},
	drawArrowDown : function(a) {
		var b = this.size, c = b / 2, d = b / 4;
		return this.drawRealPolygon(a, [0, c, d, d, -d, -d, -c, 0], [0, -c, -c,
						-b, -b, -c, -c, 0])
	},
	drawTriangleDown : function(a) {
		var b = this.fontSize + 7;
		return this.drawRealPolygon(a, [0, b / 2, -b / 2, 0], [0, -b, -b, 0])
	},
	drawTriangleLeft : function(a) {
		var b = this.fontSize + 7;
		return this.drawRealPolygon(a, [0, b, b, 0], [0, -b / 2, b / 2])
	},
	drawTriangleRight : function(a) {
		var b = this.fontSize + 7;
		return this.drawRealPolygon(a, [0, -b, -b, 0], [0, -b / 2, b / 2, 0])
	},
	drawRealPolygon : function(a, b, c) {
		b = AmCharts.polygon(this.container, b, c, this.backgroundColor,
				this.backgroundAlpha, 1, this.borderColor, this.borderAlpha);
		a.push(b);
		this.set.push(a);
		return b
	},
	drawCircle : function(a) {
		shape = AmCharts.circle(this.container, this.fontSize / 2,
				this.backgroundColor, this.backgroundAlpha, 1,
				this.borderColor, this.borderAlpha);
		a.push(shape);
		this.set.push(a);
		return shape
	},
	drawTextBackground : function(a, b) {
		var c = b.getBBox(), d = -c.width / 2 - 5, e = c.width / 2 + 5, c = -c.height
				- 12;
		return this.drawRealPolygon(a, [d, -5, 0, 5, e, e, d, d], [-5, -5, 0,
						-5, -5, c, c, -5])
	},
	handleMouseOver : function(a, b) {
		b || this.bullets[a].attr({
					fill : this.rollOverColors[a]
				});
		var c = this.eventObjects[a], d = {
			type : "rollOverStockEvent",
			eventObject : c,
			graph : this.graph,
			date : this.date
		}, e = this.bulletConfig.eventDispatcher;
		d.chart = e;
		e.fire(d.type, d);
		c.url && this.bullets[a].setAttr("cursor", "pointer");
		this.chart.showBalloon(AmCharts.fixNewLines(c.description),
				e.stockEventsSettings.balloonColor, !0)
	},
	handleClick : function(a) {
		a = this.eventObjects[a];
		var b = {
			type : "clickStockEvent",
			eventObject : a,
			graph : this.graph,
			date : this.date
		}, c = this.bulletConfig.eventDispatcher;
		b.chart = c;
		c.fire(b.type, b);
		b = a.urlTarget;
		b || (b = c.stockEventsSettings.urlTarget);
		AmCharts.getURL(a.url, b)
	},
	handleMouseOut : function(a) {
		this.bullets[a].attr({
					fill : this.backgroundColors[a]
				});
		a = {
			type : "rollOutStockEvent",
			eventObject : this.eventObjects[a],
			graph : this.graph,
			date : this.date
		};
		var b = this.bulletConfig.eventDispatcher;
		a.chart = b;
		b.fire(a.type, a)
	}
});