		var setting = {
			check: {
				enable: true
			},
			data: {
				simpleData: {
					enable: true
				}
			}
		};
		
		var zNodes;
/*
		var zNodes =[
			{ id:1, pId:0, name:"随意勾选 1", open:true,value:"dddddddddd1"},
			{ id:11, pId:1, name:"随意勾选 1-1", open:true,value:"dddddddddd2"},
			{ id:111, pId:11, name:"随意勾选 1-1-1",value:"dddddddddd3"},
			{ id:112, pId:11, name:"随意勾选 1-1-2",value:"dddddddddd4"},
			{ id:12, pId:1, name:"随意勾选 1-2", open:true},
			{ id:121, pId:12, name:"随意勾选 1-2-1"},
			{ id:122, pId:12, name:"随意勾选 1-2-2"},
			{ id:2, pId:0, name:"随意勾选 2", open:true},
			{ id:21, pId:2, name:"随意勾选 2-1"},
			{ id:22, pId:2, name:"随意勾选 2-2", open:true},
			{ id:221, pId:22, name:"随意勾选 2-2-1",},
			{ id:222, pId:22, name:"随意勾选 2-2-2"},
			{ id:23, pId:2, name:"随意勾选 2-3"}
		];
		*/
		var code;
		var ztreeobj;
		
		function setCheck() {
			
			var zTree = $.fn.zTree.getZTreeObj(ztreeobj);
			//py = $("#py").attr("checked")? "p":"",
			//sy = $("#sy").attr("checked")? "s":"",
			//pn = $("#pn").attr("checked")? "p":"",
			//sn = $("#sn").attr("checked")? "s":"",
			type = { "Y" : "ps", "N" : "ps"};
			//setting.check.chkboxType = { "Y" : "ps", "N" : "ps" };
			zTree.setting.check.chkboxType = type;
			showCode('setting.check.chkboxType = { "Y" : "ps", "N" : "ps" };');
		}
		function showCode(str) {
			if (!code) code = $("#code");
			code.empty();
			code.append("<li>"+str+"</li>");
		}

		function getAllChecked() {
			var zTree = $.fn.zTree.getZTreeObj(ztreeobj);
			var array = zTree.getCheckedNodes(true);
			for(var i=0; i<array.length;i++)
			{
				alert(array[i].value);
			}

		}