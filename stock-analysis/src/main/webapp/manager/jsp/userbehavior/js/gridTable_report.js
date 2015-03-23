var ReportDataGrid = {
		editIndex:null,/**编辑行id*/
		indexId:null,/**切换数据类型用到*/
		codeIndex:100,/**新增自动增长id*/
		defaults:null,/**默认参数*/
		nodes:[],/**初始化节点参数*/
		tableTile:"",/**表格标题*/
		selectDataType:"",/**数据类型选择项*/
		editflag:"",/**是否可编辑 0是 1否*/
		classtype:1,/**获取报表类型的类型(1 报表 0 虚拟报表)*/
		initialize:function(){/**默认参数*/
			this.defaults = new Object();
			this.defaults.width =  window.screen.width-205;/**表格宽度*/
			this.defaults.tableid = "tableid";/**编辑表单id*/
			this.defaults.ITEM_LEVEL_ONE = 2;/**一级功能点*/
			this.defaults.ITEM_LEVEL_TWO = 3;/**二级功能点*/
			this.defaults.GROUP = 4;/**分组*/
			this.defaults.ITEM_STATUS_ADD = 1;/**新增*/
			this.defaults.ITEM_STATUS_UPDATE = 2;/**修改*/
			this.defaults.ITEM_STATUS_DEL = 3;/**删除*/
			this.defaults.ITEM_STATUS_LOSE = 4;/**失效*/
			this.defaults.ITEM_STATUS_CONTINUE = 5;/**延续*/
			this.defaults.DRAG_NO = 0;/**不能移入*/
			this.defaults.DRAG_APPEND = 1;/**移入某一个节点里面*/
			this.defaults.DRAG_INSERT = 2;/**移到某一节点下面*/
			this.resetUI();
		},
		/**
		 * 初始化
		 * tableid 编辑表单ID
		 * dataArr 数据对象
		 * title  编辑表单显示名称
		 * dataTypeList 数据类型
		 * newflag  是否可编辑 0是 1否
		 */
		init: function(tableStr,dataArr,title,dataTypeList,newflag,classtype){
			this.editIndex = null;
			this.editflag = newflag;
			this.classtype = classtype;
			this.initialize();
			this.defaults.tableid = tableStr;
			this.setSource(dataArr);
			if(this.editflag==1){
				this.viewTreeTable();
			}else{
				this.createTreeTable();
			}
			this.tableTile = title;
			this.selectDataType = dataTypeList;
			return this;
		},
		setSource: function (dataArr) {/**初始化数据*/
			if (dataArr[0][0] === "Id") {
				dataArr = dataArr.slice(1);
			}			
			var treeArr = [];
			var childArr;
			var n = 0;
			for(var i=0;i<dataArr.length;i++){
				if(dataArr[i][1]==this.defaults.ITEM_LEVEL_ONE){
					treeArr[n] = {
							"id":dataArr[i][0], /**功能点或分组id*/
							"chktype":dataArr[i][1],/**功能点类型 2:一级功能点 3:二级功能点 4:分组  */
							"name":dataArr[i][2],/**功能点或分组名称*/
							"old_name":dataArr[i][2],/**历史功能点或分组名称(用于判断是否修改)*/
							"parentid":dataArr[i][0],/**父id*/
							"datatype":dataArr[i][4],/**数据类型*/
							"regionvalue":dataArr[i][5],/**区间值*/
							"statisid":dataArr[i][6],/**功能点id*/
							"flag":dataArr[i][7],/**是否核心功能点*/
							"old_flag":dataArr[i][7],/**历史核心功能点(用于判断是否修改)*/
							"status":dataArr[i][8],/**状态*/
							"old_status":dataArr[i][8],/**历史状态(用于回复无删除和失效)*/					
							"rangeclassid":dataArr[i][9],/**区间类型ID*/
							"rangeid":dataArr[i][10],/**区间ID*/
							"rangetype":dataArr[i][11],/**区间类型名称*/
							"valueMin":dataArr[i][12],/**下限值*/
							"valueMax":dataArr[i][13],/**上限值*/
							"old_valueMin":dataArr[i][12],/**备份下限值*/
							"old_valueMax":dataArr[i][13],/**备份上限值*/
							"selItemId":dataArr[i][14],/**单选或文字图片ID集合*/
							"selItemName":dataArr[i][15],/**单选或文字图片名称集合*/
							"iconCls":"icon-fun_1"

					};
					childArr = this.childNodes(dataArr,dataArr[i][0],this.defaults.GROUP);
					if(childArr!=null&&childArr.length>0){
						treeArr[n].children = childArr;
					}
					n++;
				}
			}

			this.nodes = treeArr;
		},
		childNodes: function (dataArr,id,level) {/**获取子节点*/
			var n=0;
			var treeArr = [];
			var childArr;
			var iconCls;
			
			for(var i=0;i<dataArr.length;i++){
				
				if(dataArr[i][3]!=null&&dataArr[i][3]==id&&dataArr[i][0]!=dataArr[i][3]){
					if(dataArr[i][1]==this.defaults.ITEM_LEVEL_ONE){
						iconCls = "icon-fun_1";
					}else if(dataArr[i][1]==this.defaults.GROUP){
						iconCls = "icon-group";
					}else if(dataArr[i][1]==this.defaults.ITEM_LEVEL_TWO){
						iconCls = "icon-fun_2";
					}

					treeArr[n]={
							"id":dataArr[i][0], /**功能点或分组id*/
							"chktype":dataArr[i][1],/**功能点类型 2:一级功能点 3:二级功能点 4:分组*/
							"name":dataArr[i][2],/**功能点或分组名称*/
							"old_name":dataArr[i][2],/**历史功能点或分组名称(用于判断是否修改)*/
							"parentid":dataArr[i][3],/**父id*/
							"datatype":dataArr[i][4],/**数据类型*/
							"regionvalue":dataArr[i][5],/**区间值*/
							"statisid":dataArr[i][6],/**功能点id*/
							"flag":dataArr[i][7],/**是否核心功能点*/
							"old_flag":dataArr[i][7],/**历史核心功能点(用于判断是否修改)*/
							"status":dataArr[i][8],/**状态*/
							"old_status":dataArr[i][8],/**历史状态(用于回复无删除和失效)*/
							"rangeclassid":dataArr[i][9],/**区间类型ID*/
							"rangeid":dataArr[i][10],/**区间ID*/
							"rangetype":dataArr[i][11],/**区间类型名称*/
							"valueMin":dataArr[i][12],/**下限值*/
							"valueMax":dataArr[i][13],/**上限值*/
							"old_valueMin":dataArr[i][12],/**备份下限值*/
							"old_valueMax":dataArr[i][13],/**备份上限值*/
							"selItemId":dataArr[i][14],/**单选或文字图片ID集合*/
							"selItemName":dataArr[i][15],/**单选或文字图片名称集合*/
							"iconCls":iconCls
					};
					childArr = this.childNodes(dataArr,dataArr[i][0],this.defaults.ITEM_LEVEL_TWO);
					if(childArr!=null&&childArr.length>0){
						treeArr[n].children = childArr;
					}
					n++;
				}
			}
			return treeArr;
		},
		viewTreeTable:function(){
			var that = this;
			$(function(){
				var dataTypeArr = that.selectDataType;
				$('#'+that.defaults.tableid).treegrid({
					title:that.tableTile,
					width:that.defaults.width,
					nowrap: true,
					rownumbers: true,
					idField:'id',
					treeField:'name',
					onClickCell:clickCell,
					hideColumn:[[
				         {title:'功能点分级',field:'chktype'},  
				         {title:'区间类型',field:'rangeclassid'},
				         {title:'区间值',field:'regionvalue'},
				         {title:'区间ID',field:'rangeid'},
				         {title:'文字或图片ID集合',field:'selItemId'},
				         {title:'文字或图片名称集合',field:'selItemName'},
				         {title:'历史状态',field:'old_status'}
					 ]],
			         columns:[[
			                {title:'名称',field:'name',width:(that.defaults.width*0.3).toFixed(0)-20,align:'left',halign:'center'},
		                   {field:'datatype',title:'数据类型',width:(that.defaults.width*0.1).toFixed(0),align:'center',
		                	   formatter:function(value){
		                		   for(var i=0; i<dataTypeArr.length; i++){
		                			   if (dataTypeArr[i].dataTypeId == value) {
		                				   return dataTypeArr[i].dataTypeName;
		                			   } 
		                		   }
		                		   return value;
		                	   }
		                    },
		                   {field:'rangetype',title:'划分区间段',width:(that.defaults.width*0.1).toFixed(0),align:'center'},
		                   {field:'valueMin',title:'下限值',width:(that.defaults.width*0.1).toFixed(0),align:'right',halign:'center'},
		                   {field:'valueMax',title:'上限值',width:(that.defaults.width*0.1).toFixed(0),align:'right',halign:'center'},
		                   {field:'statisid',title:'功能点ID',width:(that.defaults.width*0.1).toFixed(0),align:'center'},
		                   {field:'flag',title:'核心功能',width:(that.defaults.width*0.1).toFixed(0)-10,align:'center',formatter:function(value){
			                	   if(value=='1')
			                		   return "是";
			                	   else if(value=='0')
			                		   return "否";
			                	   else
			                		   return value;
		                   		}
		                   },
		                   {field:'status',title:'状态',width:(that.defaults.width*0.1).toFixed(0)-5,align:'center', formatter:function(value){
			                	   if(value==null||value.length<1)
			                		   return "";
			                	   else if(value==that.defaults.ITEM_STATUS_ADD)
			                		   return "<span style='color:#448e08'>新增</span>";
			                	   else if(value==that.defaults.ITEM_STATUS_UPDATE)
			                		   return "<span style='color:#007efe'>修改</span>";
			                	   else if(value==that.defaults.ITEM_STATUS_LOSE)
			                		   return "<span style='color:#8c8b8b'>失效</span>";
			                	   else if(value==that.defaults.ITEM_STATUS_DEL)
			                		   return "<span style='color:#eb0101'>删除</span>";
			                	   else if(value==that.defaults.ITEM_STATUS_CONTINUE)
			                		   return "<span style='color:#4a4b4b'>延续</span>";
			                	   return value;
			                   }
		                   }
			           ]],
                        onBeforeLoad:function(row,param){
                        	$(this).treegrid('loadData',that.nodes);
                        	 searchFun();//搜索用到
                        },
                        onLoadSuccess: function(row, param) {
                        	 $("#saveSubmit").css({"display":"none"});
                        	 
 				       }
				});
				function clickCell(index, field){
					if(field.chktype==that.defaults.GROUP)
						return true;
					if(index=='rangetype'){
						if(field.datatype==11){
							if(field.rangeclassid!=null&&field.rangeclassid.length>0){
								jQuery.blockUI({ message: $("#range_type"), css: {border: 'none'},overlayCSS: { opacity:'0.2' }});
								$("#add_range_input").css({"display":"none"});
								$("#saveRange").css({"display":"none"});
								$("#rangeStr").find("#rangedel").css({"display":"none"});
								viewRange(field.id,field.rangeclassid,field.rangeid,field.regionvalue);
							}
						//}else if(field.datatype==16||field.datatype==17){
						}else if(field.datatype==17){
							viewSelItem(field.id,field.datatype,field.selItemId,field.selItemName);
						}
					}
				}
			});
		},
		createTreeTable: function () {/**创建树表格*/
			var that = this;
			$(function(){
				var dataTypeArr = that.selectDataType;
				$('#'+that.defaults.tableid).treegrid({
					title:that.tableTile,
					selectOnCheck:false,
					cascadeCheck:true,
					deepCascadeCheck:true,
					width:that.defaults.width,
					nowrap: true,
					rownumbers: true,
					dnd:true,
					idField:'id',
					treeField:'name',
					onClickCell:clickCell,
					hideColumn:[[
				         {title:'功能点分级',field:'chktype'},  
				         {title:'区间类型',field:'rangeclassid'},
				         {title:'区间值',field:'regionvalue'},
				         {title:'区间ID',field:'rangeid'},
				         {title:'文字或图片ID集合',field:'selItemId'},
				         {title:'文字或图片名称集合',field:'selItemName'},
				         {title:'历史状态',field:'old_status'},
				         {title:'历史名称',field:'old_name'},
				         {title:'历史核心功能点',field:'old_flag'},
				         {title:'历史下限值',field:'old_valueMin'},
				         {title:'历史上限值',field:'old_valueMax'}
					 ]],
					 columns:[[
							{title:'选择',field:'id',checkbox:true,width:30},
							{title:'名称',field:'name',width:(that.defaults.width*0.3).toFixed(0),align:'left',halign:'center'},
		                    {field:'datatype',title:"&nbsp;&nbsp;数据类型<span id='datatype_help' type_help='' onclick='dataTypeHelp(this)' class='data_style'>&nbsp;&nbsp;&nbsp;</span>",
								width:(that.defaults.width*0.1).toFixed(0),align:'center',
		                	   formatter:function(value){
		                		   for(var i=0; i<dataTypeArr.length; i++){
		                			   if (dataTypeArr[i].dataTypeId == value) {
		                				   return dataTypeArr[i].dataTypeName;
		                			   } 
		                		   }
		                		   return value;
		                	   },
		                	   editor:{
		                		   type:'combobox',
		                		   options:{
		                			   valueField:'dataTypeId',
		                			   textField:'dataTypeName',
		                			   data:dataTypeArr,
		                			   onChange:function(dtValue){
		                				   var field = $('#'+that.defaults.tableid).treegrid('find',that.editIndex);
		                				   if(that.indexId == field.id||field.datatype.length<1){
		                					   if(null!=field&&field.datatype!=dtValue){
		                						   that.endEditing();
			                					   for(var i=0; i<dataTypeArr.length; i++){
			    		                			   if (dataTypeArr[i].dataTypeId == dtValue) {
			    		                				   field.valueMin = dataTypeArr[i].dataMin;
			    		                				   field.valueMax = dataTypeArr[i].dataMax;
			    		                				   break;
			    		                			   }
			    		                		   }
			                					   if(dtValue==11){
			                						   if(field.chktype!=that.defaults.ITEM_LEVEL_ONE){
			                							   $.messager.alert("提示","三级功能点不能添加区间功能点!","error"); 
			                								field.datatype = '';
			                						   }else{
			                							   field.datatype = 11;
			                							   rangeEdit(field);
			                						   }
			                					   //}else if(dtValue==16||dtValue==17){
			                					   }else if(dtValue==17){
			                						   field.datatype = dtValue;
		                							   selItemEdit(field);
			                					   }else{
			                						   field.rangeclassid =  "";
			                						   field.regionvalue = "";
			                						   field.rangetype = ""; 
			                					   }
			                					   $('#'+that.defaults.tableid).datagrid("refreshRow",field.id);
			                					   that.editFun("datatype",field);
			                				   }
		                				   }
		                				   that.indexId = field.id;
		                			   }
		                		   }
		                	   }
		                    },
		                   {field:'rangetype',title:'划分区间段',width:(that.defaults.width*0.1).toFixed(0)-10,align:'center'},
		                   {field:'valueMin',title:'下限值',width:(that.defaults.width*0.1).toFixed(0)-10,align:'right',halign:'center',
		                	   editor:{
		                		   type:'numberbox',
		                		   options:{
		                			   min:-99999999999,
		                			   max:99999999999
		                		   }
		                	   }
		                   },
		                   {field:'valueMax',title:'上限值',width:(that.defaults.width*0.1).toFixed(0)-10,align:'right',halign:'center',
		                	   editor:{
		                		   type:'numberbox',
		                		   options:{
		                			   min:-99999999999,
		                			   max:99999999999
		                		   }
		                	   }
		                   },
		                   {field:'statisid',title:'功能点ID',width:(that.defaults.width*0.1).toFixed(0)-10,align:'left',halign:'center',
		                	   editor:{
		                		   type:'numberbox',
		                		   options:{
		                			   min:0,
		                			   max:99999999999999
		                		   }
		                	   }
		                   },
		                   {field:'flag',title:'核心功能',width:(that.defaults.width*0.1).toFixed(0)-10,align:'center',formatter:function(value){
			                	   if(value=='1')
			                		   return "是";
			                	   else if(value=='0')
			                		   return "否";
			                	   else
			                		   return "";
		                   		},
			                   editor:{
			                	   type:'combobox',
			                	   options:{
			                		   valueField:'dataTypeId',
		                			   textField:'dataTypeName',
			                		   data:[{dataTypeId:"1",dataTypeName:"是"},
			                		   {dataTypeId:"0",dataTypeName:"否"}]
			                	   }
			                   }
		                   },
		                   {field:'status',title:'状态',width:(that.defaults.width*0.1).toFixed(0)-13,align:'center', formatter:function(value){
		                	   if(value==null||value.length<1)
		                		   return "";
		                	   else if(value==that.defaults.ITEM_STATUS_ADD)
		                		   return "<span style='color:#448e08'>新增</span>";
		                	   else if(value==that.defaults.ITEM_STATUS_UPDATE)
		                		   return "<span style='color:#007efe'>修改</span>";
		                	   else if(value==that.defaults.ITEM_STATUS_LOSE)
		                		   return "<span style='color:#8c8b8b'>失效</span>";
		                	   else if(value==that.defaults.ITEM_STATUS_DEL)
		                		   return "<span style='color:#eb0101'>删除</span>";
		                	   else if(value==that.defaults.ITEM_STATUS_CONTINUE)
		                		   return "<span style='color:#4a4b4b'>延续</span>";
			                	   return value;
			                   }
		                   }
			           ]],
	                   toolbar:that.classtype==1?[
	                            '-',{
	                               	id:'appendRecord',
	                               	text:'功能点',
	                               	iconCls:'icon-add',
	                               	handler:function(){
	                               		append(that.defaults.ITEM_LEVEL_ONE);
	                               	}
	                               },
	                               '-',{
	                               	id:'appendRecord',
	                               	text:'子功能点分组',
	                               	iconCls:'icon-add',
	                               	handler:function(){
	                               		append(that.defaults.GROUP);
	                               	}
	                               },
	                               '-',{
	                               	id:'appendRecord',
	                               	text:'子功能点',
	                               	iconCls:'icon-add',
	                               	handler:function(){
	                               		append(that.defaults.ITEM_LEVEL_TWO);
	                               	}
	                               },
	                               '-',{
	                               	id:'deleteRecord',
	                               	text:'删除',
	                               	iconCls:'icon-cancel',
	                               	handler:function(){
	                               		remove();
	                               	}
	                               },
	                               '-',{
	                               	id:'recoverRecord',
	                               	text:'取消删除',
	                               	iconCls:'icon-back',
	                               	handler:function(){
	                               		recover();
	                               	}
	                               },
	                               '-',{
	                               	id:'loseRecord',
	                               	text:'失效',
	                               	iconCls:'icon-tip',
	                               	handler:function(){
	                               		lose();
	                               	}
	                               },
	                               '-',{
	                               	id:'yesRecord',
	                               	text:'生效',
	                               	iconCls:'icon-undo',
	                               	handler:function(){
	                               		takeEffect();
	                               	}
	                               },
	                               '-',{
			                               	id:'moveup',
			                               	text:'上移',
			                               	iconCls:'icon-up',
			                               	handler:function(){
			                               		moveUp();
			                               	}
		                               },
	                               '-',{
		                               	id:'movedown',
		                               	text:'下移',
		                               	iconCls:'icon-down',
		                               	handler:function(){
		                               		moveDown();
		                               	}
	                               }
	                           ]:[
		                            '-',{
		                               	id:'appendRecord',
		                               	text:'功能点',
		                               	iconCls:'icon-add',
		                               	handler:function(){
		                               		append(that.defaults.ITEM_LEVEL_ONE);
		                               	}
		                               },
		                               '-',{
		                               	id:'deleteRecord',
		                               	text:'删除',
		                               	iconCls:'icon-cancel',
		                               	handler:function(){
		                               		remove();
		                               	}
		                               },
		                               '-',{
		                               	id:'recoverRecord',
		                               	text:'取消删除',
		                               	iconCls:'icon-back',
		                               	handler:function(){
		                               		recover();
		                               	}
		                               },
		                               '-',{
		                               	id:'loseRecord',
		                               	text:'失效',
		                               	iconCls:'icon-tip',
		                               	handler:function(){
		                               		lose();
		                               	}
		                               },
		                               '-',{
		                               	id:'yesRecord',
		                               	text:'生效',
		                               	iconCls:'icon-undo',
		                               	handler:function(){
		                               		takeEffect();
		                               	}
		                               },
		                               '-',{
			                               	id:'moveup',
			                               	text:'上移',
			                               	iconCls:'icon-undo',
			                               	handler:function(){
			                               		moveUp();
			                               	}
		                               },
	                               '-',{
		                               	id:'movedown',
		                               	text:'下移',
		                               	iconCls:'icon-undo',
		                               	handler:function(){
		                               		moveDown();
		                               	}
	                               }
		                           ],
                        onBeforeLoad:function(row,param){
                        	$(this).treegrid('loadData',that.nodes);
                        	searchFun();/**搜索用到*/
                        },
                        onContextMenu: function(e,row){
                        	$('#report_menu').empty();
                        	createMenu(row.chktype,row.status);
                        	 e.preventDefault();
                        	 that.endEditing();
						     $(this).treegrid('unselectAll');
						     $(this).treegrid('select', row.id);
						     $('#report_menu').menu('show', {
						          left: e.pageX,
						           top: e.pageY
						     });
				       },
				       onLoadSuccess: function(row, param) {
				    	   that.initCheckbox(this);
				    	   /**锁表用到*/
				    	   var time1 = setInterval(function(){
				    		   jQuery.get(base + '/manager/userbehavior/repEdit.'+actionExt+"?rptid="+$("#rptid").val(),function(result){
				    			   var obj =  eval("(" + result + ")");
				    			   if(obj.chk==1){
				    				   clearInterval(time1);
				    			   }
				    		   });
				    	   },30000);
				    	  
				    	   $("#saveSubmit").css({"display":"block"});
				       }
				});
				/**
				 * 鼠标单击编辑
				 */
				function clickCell(index, field){
					if(index=='rangetype'){
						if(field.datatype==11){
							if(field.chktype==that.defaults.ITEM_LEVEL_ONE)
								rangeEdit(field);
						//}else if(field.datatype==16||field.datatype==17){
						}else if(field.datatype==17){
							selItemEdit(field);
						}
						
					}
					if(field.status==that.defaults.ITEM_STATUS_DEL||field.status==that.defaults.ITEM_STATUS_LOSE){
						that.endEditing();
						return true;
					}
					if(that.editIndex!=null&&that.editIndex==field.id){
						return true;
					}
					that.editFun(index, field);
				}
				/**
				 * 区间编辑
				 */
				function rangeEdit(field){
					if(field.datatype==11){
						if(field.status == 1){
							$("#add_range_input").css({"display":""});
							$("#saveRange").css({"display":""});
							$("#rangeStr").find("#rangedel").css({"display":""});
						}else{
							$("#add_range_input").css({"display":"none"});
							$("#saveRange").css({"display":"none"});
							$("#rangeStr").find("#rangedel").css({"display":"none"});
						}
						viewRange(field.id,field.rangeclassid,field.rangeid,field.regionvalue);
					}
				}
				/**
				 * 单选和文字图片编辑
				 */
				function selItemEdit(field){
					//if(field.datatype==16||field.datatype==17){
					if(field.datatype==17){
						viewSelItem(field.id,field.datatype,field.selItemId,field.selItemName);
					}
				}
				/**
				 * 新增一行
				 * colType 2 新增一级功能点  3新增子功能点 4新增分组
				 */
				function append(colType){
					var iconCls,state,name,flag;
					var parentid,nodeid;
					/**
					 * 0 新增到本级里面 1新增到本级下面
					 */
					var chkflag = 0;

					that.codeIndex++;
					var node = $('#'+that.defaults.tableid).treegrid('getSelected');
					if(colType==that.defaults.ITEM_LEVEL_ONE){
						iconCls = "icon-fun_1";
						name = "请输入一级功能点";
						if(node!=null&&node.chktype==that.defaults.ITEM_LEVEL_ONE){
							nodeid = node.id;
							chkflag = 1;
						}else{
							var nodes = $('#'+that.defaults.tableid).treegrid('getRoots');
							if(nodes!=null&&nodes.length>0){
								node = nodes[nodes.length-1];
								if(node!=null&&node.chktype==that.defaults.ITEM_LEVEL_ONE){
									nodeid = node.id;
									chkflag = 1;
								}
							}
						}
						parentid = that.codeIndex;
						flag = "0";
					}else if(colType==that.defaults.GROUP){
						if(node==null||node.chktype==that.defaults.ITEM_LEVEL_TWO){
							$.messager.alert("提示","请点击一级功能点后，再新增分组","error");
							return false;
						}
						if(node.chktype==that.defaults.ITEM_LEVEL_ONE){
							parentid = node.id;
							var nodes = $('#'+that.defaults.tableid).treegrid('getChildren',parentid);
							if(nodes!=null&&nodes.length>0){
								for(var i=0;i<nodes.length;i++){
									if(nodes[i]!=null&&nodes[i].chktype==that.defaults.GROUP){
										node = nodes[i];
										chkflag = 1;
									}
								}
							}
						}else if(node.chktype==that.defaults.GROUP){
							parentid = node.parentid;
							chkflag = 1;
						}
						nodeid = node.id;
						name = "默认";
						iconCls = "icon-group";
						flag = "";
					}else if(colType==that.defaults.ITEM_LEVEL_TWO){
						if(node==null){
							$.messager.alert("提示","请点击功能点或分组后再新增子功能点","error");
							return false;
						}
						if(node.chktype==that.defaults.GROUP){
							parentid = node.id;	
							var nodes = $('#'+that.defaults.tableid).treegrid('getChildren',parentid);
							if(nodes!=null&&nodes.length>0){
								node = nodes[nodes.length-1];
								if(node!=null&&node.chktype==that.defaults.ITEM_LEVEL_TWO){
									chkflag = 1;
								}
							}
						}else if(node.chktype==that.defaults.ITEM_LEVEL_ONE){
							parentid = node.parentid;
							chkflag = 0;
						}else if(node.chktype==that.defaults.ITEM_LEVEL_TWO){
							parentid = node.parentid;
							chkflag = 1;
						}
						nodeid = node.id;
						name = "请输入二级功能点";
						iconCls = "icon-fun_2";
						flag = "0";
					}
					var data = [{
						"id":that.codeIndex, /**功能点或分组id*/
						"chktype":colType,/**功能点类型 2:一级功能点  3:二级功能点  4:分组*/
						"name":name,/**功能点或分组名称*/
						"old_name":name,/**历史功能点或分组名称(用于判断是否修改)*/
						"parentid":parentid,/**父id*/
						"datatype":'',/**数据类型*/
						"regionvalue":'',/**区间值*/
						"statisid":'',/**功能点id*/
						"flag":flag,/**是否核心功能点*/
						"old_flag":flag,/**历史核心功能点(用于判断是否修改)*/
						"status":that.defaults.ITEM_STATUS_ADD,/**状态*/
						"old_status":that.defaults.ITEM_STATUS_ADD,/**历史状态*/
						"rangeclassid":"",/**区间类型ID*/
						"rangeid":"",/**区间ID*/
						"rangetype":"",/**区间类型名称*/
						"valueMin":"",/**下限值*/
						"valueMax":"",/**上限值*/
						"old_valueMin":"",/**下限值*/
						"old_valueMax":"",/**上限值*/
						"iconCls":iconCls
					}];
					if(null!=chkflag&&chkflag==1){
						$('#'+that.defaults.tableid).treegrid('insert', {
							after:nodeid,
							data: data[0]
						});
					}else{
						
						$('#'+that.defaults.tableid).treegrid('append', {
							parent:nodeid,
							data: data
						});
						if(node!=null){
							$('#'+that.defaults.tableid).treegrid('expand', node.id);
						}
					}
					$('#'+that.defaults.tableid).treegrid('select', that.codeIndex);
					node = $('#'+that.defaults.tableid).treegrid('getSelected');
					clickCell("name",node);
				}
				/**
				 * 删除功能点或分组
				 */
				function remove(){
					var ids="";
					var nodeArr = [];
					var nodeArr1 = [];
					var i,j,k;
					var node;
					var info = "";
					var chk = false;
					$.messager.confirm("提示","如果有下级分组或功能点，将删除本级以及以下所有分组或子功能点，确定删除?",function(result){
						if(result){
							that.endEditing();
							$('#'+that.defaults.tableid).treegrid('getPanel').find("input[name='id']").each(function(){
								 if($(this).attr("checked")){
									 node = $('#'+that.defaults.tableid).treegrid('find',$(this).val());
									 if(node!=null){
										 nodeArr[nodeArr.length] = node;
									 }
								 }
							 });
							 nodeArr1 = getChildNode(nodeArr);
							 if(nodeArr1.length>0){
								 for(i=0;i<nodeArr1.length;i++){
									 if(nodeArr1[i].chktype==that.defaults.ITEM_LEVEL_TWO){
										 deleteNode(nodeArr1[i]);
									 }
								 }
								 for(i=0;i<nodeArr1.length;i++){
									 if(nodeArr1[i].chktype==that.defaults.GROUP){
										 deleteNode(nodeArr1[i]);
									 }
								 }
								 for(i=0;i<nodeArr1.length;i++){
									 if(nodeArr1[i].chktype==that.defaults.ITEM_LEVEL_ONE){
										 deleteNode(nodeArr1[i]);
									 }
								 }
							 }
						}
					});
				}
				/**
				 * 删除节点
				 */
				function deleteNode(node){
					if(node==null){
						return 1;
					}
					if(node.chktype==that.defaults.GROUP){
						if(node.status==that.defaults.ITEM_STATUS_ADD){
							$('#'+that.defaults.tableid).treegrid('remove', node.id);
							return 0;
						}else{
							node.status = that.defaults.ITEM_STATUS_DEL;
							$('#'+that.defaults.tableid).datagrid("refreshRow",node.id);
							return 1;
						}
					}else{
						if(node.old_status==that.defaults.ITEM_STATUS_ADD){
							$('#'+that.defaults.tableid).treegrid('remove', node.id);
							return 0;
						}else{
							node.status = that.defaults.ITEM_STATUS_DEL;
							$('#'+that.defaults.tableid).datagrid("refreshRow",node.id);
							return 1;
						}
					}
				}
				/**
				 * 获取下级功能点或分组，并且去重
				 */
				function getChildNode(nodeArr){
					var nodeArr1 = [];
					if(nodeArr.length>0){
						for(i=0;i<nodeArr.length;i++){
							nodeArr1[i] = nodeArr[i];
						}
						
						 var nodes_1;
						 for(i=0;i<nodeArr.length;i++){
							 if(nodeArr[i].chktype==that.defaults.ITEM_LEVEL_ONE){
								 nodes_1 = $('#'+that.defaults.tableid).treegrid('getChildren',nodeArr[i].id);
								 for(j=0;j<nodes_1.length;j++){
									 chk = false;
									 for(k=0;k<nodeArr1.length;k++){
										 if(nodeArr1[k].id==nodes_1[j].id){
											 chk = true;
											 break;
										 }
									 }
									 if(!chk)
										 nodeArr1[nodeArr1.length] = nodes_1[j];
								 }
							 }else if(nodeArr[i].chktype==that.defaults.GROUP){
								 nodes_1 = $('#'+that.defaults.tableid).treegrid('getChildren',nodeArr[i].id);
								 for(j=0;j<nodes_1.length;j++){
									 chk = false;
									 for(k=0;k<nodeArr1.length;k++){
										 if(nodeArr1[k].id==nodes_1[j].id){
											 chk = true;
											 break;
										 }
									 }
									 if(!chk)
										 nodeArr1[nodeArr1.length] = nodes_1[j];
								 }
							 }
						 }
					 }
					return nodeArr1;
				}
				/**
				 * 取消删除
				 */
				function recover(){
					$.messager.confirm("提示","如果有下级分组或功能点，将会级联本层级以下所有分组或功能点取消删除，确定取消删除?",function(result){
						if(result){
							var node;
							var nodeArr = [];
							var nodeArr1 = [];
							var info = "";
							$('#'+that.defaults.tableid).treegrid('getPanel').find("input[name='id']").each(function(){
								 if($(this).attr("checked")){
									 node = $('#'+that.defaults.tableid).treegrid('find',$(this).val());
									 if(node!=null){
										 if(node.chktype!=that.defaults.GROUP){
											 if(node.status==that.defaults.ITEM_STATUS_ADD){			
												info += "第"+$(".datagrid-body-inner").find("tr[node-id="+node.id+"]").find("div.datagrid-cell-rownumber").html();
												info += "行为新增状态,不能取消删除<br/>";
											}
										 }
										 nodeArr[nodeArr.length] = node;
									 }
								 }
							 });
							if(info.length>1){
								$.messager.alert("提示",info,"error");
								return ;
							}
							nodeArr1 = getChildNode(nodeArr);
							
							var parentNode,parentNode1;
							var chk = false;
							var k;
							for(var i=0;i<nodeArr1.length;i++){
								if(nodeArr1[i].chktype==that.defaults.ITEM_LEVEL_TWO){
									parentNode = $('#'+that.defaults.tableid).treegrid('getParent',nodeArr1[i].id);
									if(parentNode.status ==that.defaults.ITEM_STATUS_DEL){
										chk = false;
										 for(k=0;k<nodeArr1.length;k++){
											 if(nodeArr1[k].id==parentNode.id){
												 chk = true;
												 break;
											 }
										 }
										 if(!chk){
											 nodeArr1[nodeArr1.length] = parentNode;
										 }
									} 
									if(parentNode.chktype==that.defaults.GROUP){
										parentNode1 = $('#'+that.defaults.tableid).treegrid('getParent',parentNode.id);
										if(parentNode1.status ==that.defaults.ITEM_STATUS_DEL){
											chk = false;
											 for(k=0;k<nodeArr1.length;k++){
												 if(nodeArr1[k].id==parentNode.id){
													 chk = true;
													 break;
												 }
											 }
											 if(!chk){
												 nodeArr1[nodeArr1.length] = parentNode1;
											 }
										} 
									}
								 }else if(nodeArr1[i].chktype==that.defaults.GROUP){
										parentNode = $('#'+that.defaults.tableid).treegrid('getParent',nodeArr1[i].id);
										if(parentNode.status ==that.defaults.ITEM_STATUS_DEL){
											chk = false;
											 for(k=0;k<nodeArr1.length;k++){
												 if(nodeArr1[k].id==parentNode.id){
													 chk = true;
													 break;
												 }
											 }
											 if(!chk){
												 nodeArr1[nodeArr1.length] = parentNode;
											 }
										} 
								 }
							}
							for(var i=0;i<nodeArr1.length;i++){
								canselDeleteNode(nodeArr1[i]);
							}
							
						}
					});
				}
				/**
				 * 取消删除节点
				 */
				function canselDeleteNode(node){
					if(node==null){
						return 1;
					}
					if(node.old_status!=null&&node.old_status==that.defaults.ITEM_STATUS_DEL){
						node.status = that.defaults.ITEM_STATUS_UPDATE;
					}else{
						node.status = node.old_status;
					}
					$('#'+that.defaults.tableid).datagrid("refreshRow",node.id);
				}
				/**
				 * 失效按钮操作
				 * 只有状态为延续或修改的功能点才能置为失效
				 */
				function lose(){
					$.messager.confirm("提示","如果有下级分组或功能点，将会级联本层级以下所有分组或功能点失效,确定置为失效状态?",function(result){
						if(result){							
							var node;
							var nodeArr = [];
							var info = "";
							var i;
							$('#'+that.defaults.tableid).treegrid('getPanel').find("input[name='id']").each(function(){
								 if($(this).attr("checked")){
									 node = $('#'+that.defaults.tableid).treegrid('find',$(this).val());
									 if(node!=null){
										 if(node.chktype!=that.defaults.GROUP){
											 if(node.status==that.defaults.ITEM_STATUS_ADD){
												 info += "第"+$(".datagrid-body-inner").find("tr[node-id="+node.id+"]").find("div.datagrid-cell-rownumber").html();
												 info += "行为新增状态,不能置为失效状态<br/>";
											 }else if(node.status==that.defaults.ITEM_STATUS_DEL){
												 info += "第"+$(".datagrid-body-inner").find("tr[node-id="+node.id+"]").find("div.datagrid-cell-rownumber").html();
												 info += "行为删除状态,不能置为失效状态<br/>";
											 }
										 }
										 nodeArr[nodeArr.length] = node;
									 }
								 }
							 });
							if(info.length>1){
								$.messager.alert("提示",info,"error");
								return ;
							}
							if(nodeArr.length>0){
								if(nodeArr.length==1){
									if (nodeArr[0].chktype!=that.defaults.ITEM_LEVEL_TWO){
										 var nodes = $('#'+that.defaults.tableid).treegrid('getChildren',nodeArr[0].id);
										 if(nodes!=null&&nodes.length>0){
											 var nodeArr1 = [];
											 for(var i=0;i<nodes.length; i++){
												 if(nodes[i].chktype!=that.defaults.GROUP){
													 if(nodes[i].status==that.defaults.ITEM_STATUS_ADD){
														 info += "第"+$(".datagrid-body-inner").find("tr[node-id="+nodes[i].id+"]").find("div.datagrid-cell-rownumber").html();
														 info += "行为新增状态,不能置为失效状态<br/>";
													 }else if(nodes[i].status==that.defaults.ITEM_STATUS_DEL){
														 info += "第"+$(".datagrid-body-inner").find("tr[node-id="+nodes[i].id+"]").find("div.datagrid-cell-rownumber").html();
														 info += "行为删除状态,不能置为失效状态<br/>";
													 }else{
														 nodeArr1[nodeArr1.length] = nodes[i];
													 }
												 } 
											 }
										 }
										 if(info.length>1){
											$.messager.alert("提示",info,"error");
											return ;
										 }
										 that.endEditing();
										 if(nodeArr[0].chktype!=that.defaults.GROUP){
											 nodeArr[0].status = that.defaults.ITEM_STATUS_LOSE;
											 $('#'+that.defaults.tableid).datagrid("refreshRow",nodeArr[0].id);
										 }
										 for(i=0;i<nodeArr1.length;i++){
											if(nodeArr1[i].chktype!=that.defaults.GROUP){
												nodeArr1[i].status = that.defaults.ITEM_STATUS_LOSE;
												$('#'+that.defaults.tableid).datagrid("refreshRow",nodeArr1[i].id);
											}
										}
									}else{
										that.endEditing();
										nodeArr[0].status = that.defaults.ITEM_STATUS_LOSE;
										$('#'+that.defaults.tableid).datagrid("refreshRow",nodeArr[0].id);	
									}
								}else{
									that.endEditing();
									for(i=0;i<nodeArr.length;i++){
										if(nodeArr[i].chktype!=that.defaults.GROUP){
											nodeArr[i].status = that.defaults.ITEM_STATUS_LOSE;
											$('#'+that.defaults.tableid).datagrid("refreshRow",nodeArr[i].id);
										}
									}
								}
							}else{
								$.messager.alert("提示","请选择记录","error");
								return ;
							}
						}
					});
				}
				/**
				 * 生效
				 */
				function takeEffect(){
					$.messager.confirm("提示","如果有下级分组或功能点，将会级联本层级以下所有分组或功能点生效，确定置为生效?",function(result){
						if(result){
							var node;
							var nodeArr = [];
							var nodeArr1 = [];
							var info = "";
							$('#'+that.defaults.tableid).treegrid('getPanel').find("input[name='id']").each(function(){
								if($(this).attr("checked")){
									node = $('#'+that.defaults.tableid).treegrid('find',$(this).val());
									if(node!=null){
										if(node.chktype!=that.defaults.GROUP){
											if(node.status==that.defaults.ITEM_STATUS_ADD){
												info += "第"+$(".datagrid-body-inner").find("tr[node-id="+node.id+"]").find("div.datagrid-cell-rownumber").html();
												info += "行为新增状态,不能置为生效<br/>";
											}else if(node.status==that.defaults.ITEM_STATUS_DEL){
												info += "第"+$(".datagrid-body-inner").find("tr[node-id="+node.id+"]").find("div.datagrid-cell-rownumber").html();
												info += "行为删除状态,不能置为生效<br/>";
											}else if(node.status==that.defaults.ITEM_STATUS_UPDATE){
												info += "第"+$(".datagrid-body-inner").find("tr[node-id="+node.id+"]").find("div.datagrid-cell-rownumber").html();
												info += "行为修改状态,不能置为生效<br/>";
											}else if(node.status==that.defaults.ITEM_STATUS_CONTINUE){
												info += "第"+$(".datagrid-body-inner").find("tr[node-id="+node.id+"]").find("div.datagrid-cell-rownumber").html();
												info += "行为延续状态,不能置为生效<br/>";
											}
										}
										nodeArr[nodeArr.length] = node;
									}
								}
							});
							if(info.length>1){
								$.messager.alert("提示",info,"error");
								return ;
							}
							var parentNode;
							for(var i=0;i<nodeArr.length;i++){
								if(nodeArr[i].chktype!=that.defaults.GROUP){
									if(nodeArr[i].old_status==that.defaults.ITEM_STATUS_LOSE){
										nodeArr[i].status = that.defaults.ITEM_STATUS_UPDATE;
									}else{
										nodeArr[i].status = nodeArr[i].old_status;
									}
		
									$('#'+that.defaults.tableid).datagrid("refreshRow",nodeArr[i].id);
								}
								if (nodeArr[i].chktype==that.defaults.ITEM_LEVEL_TWO){
									parentNode = $('#'+that.defaults.tableid).treegrid('getParent',nodeArr[i].id);
									if (parentNode.chktype==that.defaults.GROUP){
										parentNode = $('#'+that.defaults.tableid).treegrid('getParent',parentNode.id);
									}
									if(parentNode.old_status==that.defaults.ITEM_STATUS_LOSE){
										parentNode.status = that.defaults.ITEM_STATUS_UPDATE;
									}else{
										parentNode.status = parentNode.old_status;
									}
									$('#'+that.defaults.tableid).datagrid("refreshRow",parentNode.id);
								}else{
									if (nodeArr[i].chktype==that.defaults.GROUP){
										parentNode = $('#'+that.defaults.tableid).treegrid('getParent',nodeArr[i].id);
										if(parentNode.old_status==that.defaults.ITEM_STATUS_LOSE){
											parentNode.status = that.defaults.ITEM_STATUS_UPDATE;
										}else{
											parentNode.status = parentNode.old_status;
										}
										$('#'+that.defaults.tableid).datagrid("refreshRow",parentNode.id);
									}
									var nodes = $('#'+that.defaults.tableid).treegrid('getChildren',nodeArr[i].id);
									if(nodes!=null&&nodes.length>0){
										 for(var i=0;i<nodes.length; i++){
											 if(nodes[i].chktype!=that.defaults.GROUP){
												 if(nodes[i].status==that.defaults.ITEM_STATUS_LOSE){
													 if(nodes[i].old_status==that.defaults.ITEM_STATUS_LOSE){
														 nodes[i].status = that.defaults.ITEM_STATUS_UPDATE;
													 }else{
														 nodes[i].status = nodes[i].old_status;
													 }
													
													$('#'+that.defaults.tableid).datagrid("refreshRow",nodes[i].id);
												 }
											 } 
										 }
									}
								}
							}
						}
					});
					
				}
				/**
				 * 获取下一个节点
				 */
				function getNextNode(start_node){
					var ptr = $(".datagrid-body-inner").find("tr[node-id="+start_node.id+"]").next();
					if(ptr==null){
						return null;
					}
					if(ptr.attr("node-id")==null){
						ptr = ptr.next();
					}
					
					if(ptr!=null&&ptr.attr("node-id")!=null){
						var end_node = $('#'+that.defaults.tableid).treegrid('find',ptr.attr("node-id"));
						if(end_node!=null&&start_node.chktype==end_node.chktype){
							return end_node;
						}
					}
				}
				/**
				 * 获取上一个节点
				 */
				function getBeforeNode(start_node){
					var ptr = $(".datagrid-body-inner").find("tr[node-id="+start_node.id+"]").prev();
					
					if(ptr==null){
						return null;
					}
					if(ptr.attr("node-id")==null){
						ptr = ptr.prev();
					}
					
					if(ptr!=null&&ptr.attr("node-id")!=null){
						var end_node = $('#'+that.defaults.tableid).treegrid('find',ptr.attr("node-id"));
						if(end_node!=null&&start_node.chktype==end_node.chktype){
							return end_node;
						}else{
							return null;
						}
					}else{
						return null;
					}
				}
				/**
				 * 上移
				 */
				function moveUp(){
					var start_node = $('#'+that.defaults.tableid).treegrid('getSelected');
					var end_node = getBeforeNode(start_node);
					if(end_node==null){
						var info = "";
						if(start_node.chktype!=that.defaults.GROUP)
							info = "功能点";
						else
							info = "分组";
						$.messager.alert("提示",info+"已经是第一个了，不能向上移动！","error"); 
						return false;
					}
					that.endEditing();
					if(start_node.chktype==end_node.chktype){
						that.beforeDragNode(start_node,end_node);
					}else{
						$.messager.alert("提示","必须在本级功能点向上移动！","error"); 
					}
				}
				/**
				 * 下移
				 */
				function moveDown(){
					var start_node = $('#'+that.defaults.tableid).treegrid('getSelected');
					var end_node = getNextNode(start_node);
					if(end_node==null){
						var info = "";
						if(start_node.chktype!=that.defaults.GROUP)
							info = "功能点";
						else
							info = "分组";
						$.messager.alert("提示",info+"已经在最低部了，不能向下移动！","error"); 
						return false;
					}
					that.endEditing();
					if(start_node.chktype==end_node.chktype){
						that.afterDragNode(start_node,end_node);
					}else{
						$.messager.alert("提示","必须在本级功能点向下移动！","error"); 
					}
				}
				/** 
				 * 创建反键按钮
				 * chktype 功能点类型 2:一级功能点  3:二级功能点  4:分组
				 * status 功能点状态 
				 */
				function createMenu(chktype,status){
					var menu = new Array();
					if(chktype==that.defaults.ITEM_LEVEL_ONE){
						menu[0] ={id:"m_001",text:"新增功能点",onclick:function(){
							append(that.defaults.ITEM_LEVEL_ONE);
						},iconCls:"icon-add"};
						if(that.classtype==1){
							menu[1] ={id:"m_001",text:"新增分组",onclick:function(){
								append(that.defaults.GROUP);
							},iconCls:"icon-add"};	
							menu[2] ={id:"m_002",text:"新增子功能点",onclick:function(){
								append(that.defaults.ITEM_LEVEL_TWO);
							},iconCls:"icon-add"};
						}
					}else if(chktype==that.defaults.ITEM_LEVEL_TWO){
						if(that.classtype==1){
							menu[0] ={id:"m_001",text:"新增子功能点",onclick:function(){
								append(that.defaults.ITEM_LEVEL_TWO);
							},iconCls:"icon-add"};
						}
					}else{
						if(that.classtype==1){
							menu[0] ={id:"m_001",text:"新增分组",onclick:function(){
								append(that.defaults.GROUP);
							},iconCls:"icon-add"};	
							menu[1] ={id:"m_002",text:"新增子功能点",onclick:function(){
								append(that.defaults.ITEM_LEVEL_TWO);
							},iconCls:"icon-add"};
						}
					}
					menu[menu.length] ={id:"m_003",text:"删除",onclick:function(){
						remove();
					},iconCls:"icon-cancel"};
					menu[menu.length] ={id:"m_005",text:"取消删除",onclick:function(){
						recover();
					},iconCls:"icon-back"};
					if(chktype!=that.defaults.GROUP&&(status==that.defaults.ITEM_STATUS_UPDATE||status==that.defaults.ITEM_STATUS_CONTINUE)){
						menu[menu.length] ={id:"m_004",text:"失效",onclick:function(){
							lose();
						},iconCls:"icon-tip"};
					}
					if(chktype!=that.defaults.GROUP&&status==that.defaults.ITEM_STATUS_LOSE){
						menu[menu.length] ={id:"m_006",text:"生效",onclick:function(){
							takeEffect();
						},iconCls:"icon-undo"};
					}
					if(chktype==that.defaults.GROUP){
						menu[menu.length] ={id:"m_004",text:"失效",onclick:function(){
							lose();
						},iconCls:"icon-tip"};
						menu[menu.length] ={id:"m_006",text:"生效",onclick:function(){
							takeEffect();
						},iconCls:"icon-undo"};
					}
					menu[menu.length] ={id:"m_007",text:"上移",onclick:function(){
						moveUp();
					},iconCls:"icon-up"};
					menu[menu.length] ={id:"m_008",text:"下移",onclick:function(){
						moveDown();
					},iconCls:"icon-down"};

					for(var i=0;i<menu.length;i++){
						$('#report_menu').menu('appendItem',menu[i]); 
					}
				}
			});
		},
		/**
		 * 单元格编辑结束
		 */
		endEditing:function(){
			if (this.editIndex == null){
				return true;
			}
			$('#'+this.defaults.tableid).treegrid('endEditCell', this.editIndex);
			this.cellCheckbox(this.editIndex);
			this.editIndex = null;
			return true;
		},
		/**
		 * 编辑功能点
		 */
		editFun:function(index, field){
			if (this.endEditing()){
				this.editIndex = field.id;
				field.index = index;
				$('#'+this.defaults.tableid).treegrid('editCell',{index:index,field:field});
			}
		},
		resetUI:function(){
			var that = this;
			$.extend($.fn.datagrid.methods, {
				editCell: function(jq,param){
					return jq.each(function(){
						var opts = $(this).treegrid('options');
						var fields = $(this).treegrid('getColumnFields',true).concat($(this).treegrid('getColumnFields'));

						for(var i=0; i<fields.length; i++){
							var col = $(this).treegrid('getColumnOption', fields[i]);
							col.editor1 = col.editor;
							if (fields[i] == "name"){
								var tr=opts.finder.getTr(this,param.field.id);
								var nt=$(tr).find(".tree-title");
								var tevalue = $(nt).text();
								var _dc=nt.outerWidth();
								_dc = _dc>90?_dc:90;
								_dc = _dc>120?120:_dc;

								nt.empty();
								var _dd=$("<input class=\"tree-grid-editor\" maxlength=\"32\">").appendTo(nt);
								$(_dd).css({"position":""});
								if(param.index=="name"){
									$(_dd).val("").focus().val(tevalue);
								}else{
									$(_dd).val(tevalue);
								}
								_dd.width(220);
								_dd.height(document.compatMode=="CSS1Compat"?(18-(_dd.outerHeight()-_dd.height())):18);
							}else{
								if(param.field.chktype!=that.defaults.GROUP){
									if(param.field.status!=that.defaults.ITEM_STATUS_ADD){
										if(fields[i]!='name'&&fields[i]!='flag'&&fields[i]!='valueMin'&&fields[i]!='valueMax'){
											col.editor = null;
										}
									}
								}else{
									col.editor = null;
								}
							}
						}
						$(this).treegrid('beginEdit',param.field.id);
						var tr;
						var fieldname;
						for(var i=0; i<fields.length; i++){
							var col = $(this).treegrid('getColumnOption', fields[i]);
							col.editor = col.editor1;
						}
					});
				},
				endEditCell: function(jq,param){
					return jq.each(function(){
						var opts = $(this).treegrid('options');
						var row=opts.finder.getRow(this,param);
						if(row!=null){
							var tr= opts.finder.getTr(this,param);
							var nt=$(tr).find(".tree-title");
							var _e2=$(tr).find("input.tree-grid-editor");
							var val=_e2.val();
							_e2.remove();
							nt.html(val);
							row['name'] = val;
							$('#'+that.defaults.tableid).treegrid('endEdit', row['id']);
							
							var field = $('#'+that.defaults.tableid).treegrid('find',row['id']);
							if(field.status==that.defaults.ITEM_STATUS_CONTINUE||field.status==that.defaults.ITEM_STATUS_UPDATE){
								if($.trim(field.name)==$.trim(field.old_name)&&$.trim(field.flag)==$.trim(field.old_flag)
										&&$.trim(field.valueMin)==$.trim(field.old_valueMin)&&$.trim(field.valueMax)==$.trim(field.old_valueMax)){
									if(field.status != field.old_status){
										field.status = field.old_status;
										$('#'+that.defaults.tableid).datagrid("refreshRow",field.id);
									}
								}else{
									if(field.status!=that.defaults.ITEM_STATUS_UPDATE){
										field.status = that.defaults.ITEM_STATUS_UPDATE;
										$('#'+that.defaults.tableid).datagrid("refreshRow",field.id);
									}
								}
							}
						}
					});
				}
			});
		},
		/**
		 * start_node移入到end_node
		 */
		appendDragNode:function (start_node,end_node){
			var that = this;
			start_node.parentid = end_node.id;
			$('#'+that.defaults.tableid).treegrid('remove', start_node.id);
				$('#'+that.defaults.tableid).treegrid('append', {
				parent: end_node.id,
				data: [start_node]
			});

			if(end_node!=null){
				$('#'+that.defaults.tableid).treegrid('expand', end_node.id);
			}
		},
		/**
		 * start_node插入到end_node后
		 */
		afterDragNode:function (start_node,end_node){
			if(start_node.chktype!=this.defaults.ITEM_LEVEL_ONE){
				start_node.parentid = end_node.parentid;
			}
			$('#'+this.defaults.tableid).treegrid('remove', start_node.id);
			$('#'+this.defaults.tableid).treegrid('insert', {
				after: end_node.id,
				data: start_node
			});	
			$('#'+this.defaults.tableid).treegrid('select', start_node.id);
		},
		/**
		 * start_node插入到end_node前面
		 */
		beforeDragNode:function (start_node,end_node){
			if(start_node.chktype!=this.defaults.ITEM_LEVEL_ONE){
				start_node.parentid = end_node.parentid;
			}
			$('#'+this.defaults.tableid).treegrid('remove', start_node.id);
			$('#'+this.defaults.tableid).treegrid('insert', {
				before: end_node.id,
				data: start_node
			});
			$('#'+this.defaults.tableid).treegrid('select', start_node.id);
		},
		/**
		 * 判断是否可以移动 分组和二级功能点不能移动到其他一级功能点下
		 * return  0 不能移动 1插入end_node下面  2 移动到end_node里面
		 */
		isNodeDrag:function (start_node,end_node){
			var that = this;
			if(start_node.chktype==that.defaults.ITEM_LEVEL_ONE){
				if(end_node.chktype!=that.defaults.ITEM_LEVEL_ONE){
					
					return that.defaults.DRAG_NO;
				}else{
					
					return that.defaults.DRAG_INSERT;
				}
			}else if(start_node.chktype==that.defaults.GROUP){
				if(end_node.chktype==that.defaults.ITEM_LEVEL_TWO){
					return that.defaults.DRAG_NO;
				}else if(end_node.chktype==that.defaults.ITEM_LEVEL_ONE){ 
					/**不能移动到其他一级节点下*/
					if(start_node.parentid!=end_node.id){
						return that.defaults.DRAG_NO;
					}else{
	  					return that.defaults.DRAG_APPEND;
	  				}
				}else if(end_node.chktype==that.defaults.GROUP){
					/**不能移动到其他一级节点分组下*/
					if(start_node.parentid!=end_node.parentid){
						return that.defaults.DRAG_NO;
					}else{
	  					return that.defaults.DRAG_INSERT;
	  				}
				}
			}else if(start_node.chktype==that.defaults.ITEM_LEVEL_TWO){
				if(end_node.chktype==that.defaults.ITEM_LEVEL_ONE){
					return that.defaults.DRAG_NO;
				}else if(end_node.chktype==that.defaults.GROUP){
					/**不能移动到其他一级节点分组下*/
					/**被移动节点一级功能点*/
					var start_node_1 = $('#'+that.defaults.tableid).treegrid('find',start_node.parentid);
					if(start_node_1.parentid!=end_node.parentid){
						return that.defaults.DRAG_NO;
					}else{
	  					return that.defaults.DRAG_APPEND;
	  				}
				}else{
					/**被移动节点一级功能点*/
					var start_node_1 = $('#'+that.defaults.tableid).treegrid('find',start_node.parentid);
					/**移动到的节点一级功能点*/
	  				var end_node_2 = $('#'+that.defaults.tableid).treegrid('find',end_node.parentid);
					if(start_node_1.parentid!=end_node_2.parentid){
						return that.defaults.DRAG_NO;
					}else{
	  					return that.defaults.DRAG_INSERT;
	  				}
				}
			}
		},
		/**
		 * 多选
		 */
		 initCheckbox:function(obj){
			var that = this;
			var target = $(obj);  
			var opts = $.data(obj,"treegrid").options;  
			var panel = $(obj).datagrid("getPanel");  
			var gridBody = panel.find("div.datagrid-body");  
			var idField = opts.idField;/**这里的idField其实就是API里方法的id参数  */

			gridBody.find("div.datagrid-cell-check input[type=checkbox]").unbind("click").click(function(e){
				/**单选不管  */
				if(opts.cascadeCheck||opts.deepCascadeCheck){  
					var id=$(this).val();  
					var status = false;  
					if($(this).attr("checked")) 
						status = true;  
					that.selectCheckbox(id,status);
				}  
				e.stopPropagation();/**停止事件传播  */
			});
		},
		/**
		 * 多选
		 */
		cellCheckbox:function(nodeid){
			var that = this;
			var target = $('#'+that.defaults.tableid).treegrid('getPanel').find('.datagrid-view2');  
			target.find('tr[node-id='+nodeid+']').find("input[type=checkbox]").unbind("click").click(function(e){
				var id=$(this).val();  
				var status = false;  
				if($(this).attr("checked")) 
					status = true;  
				that.selectCheckbox(id,status);
				e.stopPropagation();/**停止事件传播  */
			});
		},
		/**
		 * 级联选择
		 */
		selectCheckbox:function(id,status){
			var that = this;
			/**级联选择子节点*/
			/**深度级联时先展开节点  */
			if(status){
				$('#'+that.defaults.tableid).treegrid('expand',id);
			}
			
			/**根据ID获取下层孩子节点 */ 
			var children = $('#'+that.defaults.tableid).treegrid('getChildren',id);
			for(var i=0;i<children.length;i++){
				$('#'+that.defaults.tableid).treegrid('getPanel').find('.datagrid-view2').find('tr[node-id='+children[i].id+']').find("input[name='id']").attr("checked",status);
			}
			if(!status){
				var parentNode = $('#'+that.defaults.tableid).treegrid('getParent',id);
				if(parentNode!=null){
					$('#'+that.defaults.tableid).treegrid('getPanel').find('.datagrid-view2').find('tr[node-id='+parentNode.id+']').find("input[name='id']").attr("checked",status);
					parentNode = $('#'+that.defaults.tableid).treegrid('getParent',parentNode.id);
					if(parentNode!=null){
						$('#'+that.defaults.tableid).treegrid('getPanel').find('.datagrid-view2').find('tr[node-id='+parentNode.id+']').find("input[name='id']").attr("checked",status);
					}
				}
			}
		},
		/**
		 * 区间值设置
		 * @param rangeclassid
		 * @param regionvalue
		 * @param rangetype
		 */
		setRangeValue:function(rangeclassid,regionvalue,rangetype){
			this.endEditing();
			var field = $('#'+this.defaults.tableid).treegrid('getSelected');
			if(field!=null){
				field.rangeclassid =  rangeclassid;
				field.regionvalue = regionvalue;
				field.rangetype = rangetype;
				$('#'+this.defaults.tableid).datagrid("refreshRow",field.id);
				this.editFun("datatype",field);
			}
		},
		/**
		 * 单选和文字连接设置
		 * @param selItemId
		 * @param selItemName
		 */
		setSelItemValue:function(selItemId,selItemName){
			this.endEditing();
			var field = $('#'+this.defaults.tableid).treegrid('getSelected');
			if(field!=null){
				field.selItemId =  selItemId;
				field.selItemName = selItemName;
				if(field.datatype==16)
					field.rangetype = "文字链内容";
				else 
				if(field.datatype==17)
					field.rangetype = "单选设置内容";
				$('#'+this.defaults.tableid).datagrid("refreshRow",field.id);
				if(this.editflag!=1){
					this.editFun("datatype",field);
				}
				if(field.status!=this.defaults.ITEM_STATUS_ADD||this.editflag==1){
					saveSelItem(field.id,field.datatype,selItemId,selItemName);
				}
				
			}
		}

};
