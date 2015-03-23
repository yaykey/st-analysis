/**
 * 格式化数值，其中Formatter对象是birt中的脚本
 * @param data
 * @param formatFlag
 */
function formatNumber(data,formatFlag){
    if(formatFlag==0){
       if(data==null) return 0;
       return  Formatter.format(data,"#,###");
    }else if(formatFlag==2){
       if (data==null) return "0.00";
       return Formatter.format(data,"#0.00");
    }else if(formatFlag==4){
      if(data==null) return "0.00%";
      return   Formatter.format(data,"#0.00%");
    }else{
    	if(data==null) return 0
    	return data;
    }
}

function initParameter(dsSql,showConsole) {
    if (dsSql == null) dsSql = this.queryText;
    var sql = this.queryText;
    parameters = reportContext.getDesignHandle().getAllParameters();
    iter = parameters.iterator();
    while (iter.hasNext()) {
        paramName = iter.next();
        sql = sql.replaceAll("\\$\\{" + paramName.getName() + "\\}", reportContext.getParameterValue(paramName.getName()));
    }
   if(showConsole) {
       Packages.java.lang.System.out.println("-------------------"+this.getName()+"---------------------");
    	Packages.java.lang.System.out.println(sql);
    	Packages.java.lang.System.out.println("----------------------------------------");
   }
    return sql;
}
function initDsParameter(dsSql,showConsole) {
    if (dsSql == null) dsSql = this.queryText;
    var sql = this.queryText;
    var strClient = "";
    for (var i = 0; i < params["client_id"].split(",").length; i++) {
        strClient = params["client_id"].split(",")[i] + "','" + strClient;
    }
    strClient = "'" + strClient;
    strClient = strClient.substring(0, strClient.length - 2);
    sql = sql.replaceAll("\\$\\{client_version\\}", strClient);
    parameters = reportContext.getDesignHandle().getAllParameters();
    iter = parameters.iterator();
    while (iter.hasNext()) {
        paramName = iter.next();
        sql = sql.replaceAll("\\$\\{" + paramName.getName() + "\\}", reportContext.getParameterValue(paramName.getName()));
    }
   if(showConsole) {
       Packages.java.lang.System.out.println("-------------------"+this.getName()+"---------------------");
    	Packages.java.lang.System.out.println(sql);
    	Packages.java.lang.System.out.println("----------------------------------------");
   }
    return sql;
}
function initParam(sql,showConsole) {
    if (sql == null) {
    	sql = this.queryText;
    }
    
    var iter = reportContext.getDesignHandle().getAllParameters().iterator();
    while (iter.hasNext()) {
        var param = iter.next();
        sql = sql.replaceAll("\\$\\{" + param.getName() + "\\}", reportContext.getParameterValue(param.getName()));
    }
   if(showConsole) {
	   Packages.java.lang.System.out.println(sql);
   }
   return sql;
}
function initStatusIdParam(sql, showConsole){
	if (sql == null) {
		sql = this.queryText;
	}
	var tagId = "";
	var tags = params["status_id"].split(",");
	for(var i = 0; i < tags.length; i++){
		tagId += tags[i]+ ",";
	}
	tagId = tagId.substring(0, tagId.length - 2);
	sql = sql.replaceAll("\\$\\{status_id\\}", tagId);
	
	if(showConsole) {
		Packages.java.lang.System.out.println(sql);
	}
	return sql;
	
}
function initUpResourceIdParam(sql, showConsole){
	if (sql == null) {
		sql = this.queryText;
	}
	var tagId = "";
	var tags = params["upresource_id"].split(",");
	for(var i = 0; i < tags.length; i++){
		tagId += tags[i]+ ",";
	}
	tagId = tagId.substring(0, tagId.length - 2);
	sql = sql.replaceAll("\\$\\{upresource_id\\}", tagId);
	
	if(showConsole) {
		Packages.java.lang.System.out.println(sql);
	}
	return sql;
	
}
function initTagIdParam(sql,showConsole){
	if (sql == null) {
		sql = this.queryText;
	}
	var tagId = "'";
	var tags = params["oem_tag"].split(",");
	for(var i = 0; i < tags.length; i++){
		tagId += tags[i]+ "','";
	}
	tagId = tagId.substring(0, tagId.length - 2);
	sql = sql.replaceAll("\\$\\{tag_id\\}", tagId);
	
	if(showConsole) {
		Packages.java.lang.System.out.println(sql);
	}
	return sql;
}
function initDisplayType(sql,showConsole){
	if (sql == null) {
		sql = this.queryText;
	}
	if(params["report_type"] == 1){
		sql = sql.replace("AND m.EFF_DATE<=CURRENT_TIMESTAMP", "AND m.DISPLAY_TYPE != 3 AND m.EFF_DATE<=CURRENT_TIMESTAMP");
	}
	if(showConsole) {
		Packages.java.lang.System.out.println(sql);
	}
	return sql;
}
function initClientVersionParam(param){
	
	var clientVersion = "'";
	var clientVersions = param.split(",");
	for(var i = 0; i < clientVersions.length; i++){
		clientVersion += clientVersions[i]+ "','";
	}
	clientVersion = clientVersion.substring(0, clientVersion.length - 2);
	return clientVersion;
}
/**
判断是否有值，有返回true
*/
function isHaveVal(val){
	var val2=val+"";
    val2 = val2.replace(/(^\s*|\s*$)/g,'');
	if(val2==null || val2=='null' || val2=='' || val2=='undefined'){
		return false;
	}else{
		return true;
	}
}
/**
 * 打印
 * @param str
 */
function print(str){
	Packages.java.lang.System.out.println(str);
}
/**
 * 打印birt报表参数
 */
function printParam(){
	print("------------------birt report parameter list-------------");
	it=reportContext.getDesignHandle().getAllParameters().iterator();
	while(it.hasNext()){
		var paramName=it.next().getName();
		var paramValue=reportContext.getParameterValue(paramName);
		print(paramName+"["+paramValue+"]");
	}
}