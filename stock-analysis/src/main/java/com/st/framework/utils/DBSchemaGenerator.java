package com.st.framework.utils;
///**
// * @(#)DBSchemaCreate.java  2011-4-20
// */
//package com.feinno.circle.framework.utils;
//
//import java.io.File;
//import java.io.IOException;
//import java.sql.Connection;
//import java.sql.DatabaseMetaData;
//import java.sql.PreparedStatement;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Set;
//
//import javax.sql.DataSource;
//
//import org.apache.commons.lang.StringUtils;
//import org.apache.log4j.Logger;
//import org.springframework.core.io.Resource;
//import org.springframework.orm.hibernate3.LocalSessionFactoryBean;
//
//import com.feinno.Global;
//import com.feinno.dataplatform.viewframe.business.interfaces.IVfConfigManager;
//import com.feinno.dataplatform.viewframe.business.interfaces.nav.IVfNavWidgetManager;
//import com.feinno.dataplatform.viewframe.business.interfaces.nav.IVfNavWidgetModuleDataManager;
//import com.feinno.dataplatform.viewframe.business.interfaces.nav.IVfNavWidgetModuleManager;
//import com.feinno.dataplatform.viewframe.model.VfConfig;
//import com.feinno.dataplatform.viewframe.model.VfNavWidget;
//import com.feinno.dataplatform.viewframe.model.VfNavWidgetModule;
//import com.feinno.dataplatform.viewframe.model.VfNavWidgetModuleData;
//import com.feinno.framework.business.interfaces.groupcode.IGroupCodeManager;
//import com.feinno.framework.business.interfaces.system.function.IFunctionManager;
//import com.feinno.framework.business.interfaces.system.module.IModuleManager;
//import com.feinno.framework.business.interfaces.system.role.IRoleManager;
//import com.feinno.framework.business.interfaces.user.IUserGroupManager;
//import com.feinno.framework.business.interfaces.user.IUserManager;
//import com.feinno.framework.exceptions.ApplicationBaseException;
//import com.feinno.framework.model.groupcode.GroupCode;
//import com.feinno.framework.model.system.function.Function;
//import com.feinno.framework.model.system.module.Module;
//import com.feinno.framework.model.system.role.Role;
//import com.feinno.framework.model.user.UserGroup;
//import com.feinno.framework.model.user.UserGroupCommonObject;
//import com.feinno.framework.model.user.UserInfo;
//import com.feinno.framework.utils.cache.RoleRightsMap;
//import com.feinno.framework.utils.encrypt.IEncryptor;
//import com.feinno.framework.utils.encrypt.impl.EncryptorMD5Impl;
//import com.thoughtworks.xstream.XStream;
//
///**
// * 类 <code>DatabaseSchemaGenerator</code>
// * 
// * @author wangwenyao@feinno.com
// * @version 2011-4-20
// */
//@SuppressWarnings("unchecked")
//public class DBSchemaGenerator {
//	
//	
//	/**
//	 * Logger for this class
//	 */
//	private static final Logger LOG = Logger.getLogger(DBSchemaGenerator.class);
//
//	private static IGroupCodeManager groupCodeManager = (IGroupCodeManager) Global._ctx
//			.getBean("groupCodeManager");
//
//	private static IUserGroupManager userGroupManager = (IUserGroupManager) Global._ctx
//			.getBean("userGroupManager");
//
//	private static IUserManager userManager = (IUserManager) Global._ctx
//			.getBean("userManager");
//
//	private static IModuleManager moduleManager = (IModuleManager) Global._ctx
//			.getBean("moduleManager");
//
//	private static IFunctionManager functionManager = (IFunctionManager) Global._ctx
//			.getBean("functionManager");
//
//	private static IRoleManager roleManager = (IRoleManager) Global._ctx
//			.getBean("roleManager");
//	
//	private static IVfNavWidgetManager vfNavWidgetManager = (IVfNavWidgetManager)Global._ctx
//		.getBean("vfNavWidgetManager");
//	
//	private static IVfNavWidgetModuleManager vfNavWidgetModuleManager = (IVfNavWidgetModuleManager)Global._ctx
//		.getBean("vfNavWidgetModuleManager");
//	
//	private static IVfNavWidgetModuleDataManager vfNavWidgetModuleDataManager = (IVfNavWidgetModuleDataManager)Global._ctx
//	.getBean("vfNavWidgetModuleDataManager");
//	
//	private static IVfConfigManager vfConfigManager = (IVfConfigManager)Global._ctx
//	.getBean("vfConfigManager");
//
//	private static IEncryptor encryptor = new EncryptorMD5Impl();
//	
//	/**
//	 * isInit 判断数据库是否已经初始化
//	 */
//	private static boolean isInited = false;
//	
//	public static  boolean isInited(){
//		return isInited;
//	}
//	
//	public static void isInited(boolean inited){
//		isInited = inited;
//	}
//
//	/**
//	 * 根据hibernate注解的实体生成数据库表结构
//	 */
//	public static void initSchemaToDB() {
//		LOG.info("开始创建数据库表！");
//		((LocalSessionFactoryBean) Global._ctx.getBean("&sessionFactory")).updateDatabaseSchema();
//		LOG.info("完成创建数据库表！");
//	}
//	
//	public static void initDataToDB() throws ApplicationBaseException {
//		LOG.info("开始初始化数据库数据");
//		// 初始化组结构
//		LOG.info("开始初始化组结构");
//		GroupCode groupCode = new GroupCode();
//		groupCode.setGroupType("UserGroup");
//		groupCode.setNextCode(1);
//		groupCodeManager.saveOrUpdateGroupCode(groupCode);
//		LOG.info("完成初始化组结构");
//
//		// 初始化用户组
//		LOG.info("开始初始化用户组");
//		UserGroup userGroup = new UserGroup();
//		userGroup.setName("新媒传信");
//		userGroup.setIsLeaf(0);
//		userGroup.setParentGroupId(0);
//		userGroupManager.saveOrUpdate(userGroup);
//		LOG.info("完成初始化用户组");
//
//		// 初始化用户信息
//		LOG.info("开始初始化用户信息！");
//		UserInfo adminUser = new UserInfo();
//		Set<UserGroupCommonObject> userGroups = new HashSet<UserGroupCommonObject>();
//		userGroups.add(userGroup);
//		adminUser.setLoginId(Global.ADMIN_LOGIN_ID);
//		adminUser.setGroups(userGroups);
//		adminUser.setName("admin");
//		adminUser.setMailAddress("fetionbiv4@fetionyy.com.cn");
//		adminUser.setPassword(encryptor.encrypt("admin"));
//		userManager.saveOrUpdate(adminUser);
//		LOG.info("完成初始化用户信息！");
//		
//		// 初始化超级管理员角色
//		LOG.info("开始初始化角色信息！");
//		Role adminRole = new Role();
//		adminRole.setName(Global.ADMIN_ROLE_NAME);
//		Integer userGroupId = userGroupManager.getRootGroup().getIdentifier();
//		adminRole.setUserGroupId(userGroupId);
//		adminRole.setRoleLevel(1);
//		Set<UserInfo> users = new HashSet<UserInfo>();
//		users.add(adminUser);
//		adminRole.setUsers(users);
//		adminRole.setViewType(Global.VIEWTYPE_BACK);
//		roleManager.saveOrUpdate(adminRole);
//		
//		// 初始化前台角色
//		//String frontRoleName = "frontUser";		
//		Role  frontRole = new Role();
//		frontRole.addUser(adminUser);
//		frontRole.setName(Global.FRONT_ROLE_NAME);
//		frontRole.setRoleLevel(1);
//		frontRole.setUserGroupId(userGroupId);
//		frontRole.setViewType(Global.VIEWTYPE_FRONT);
//		roleManager.saveOrUpdate(frontRole);
//		LOG.info("完成初始化角色信息！");
//		
//		DataSource dataSource  =  (DataSource) Global._ctx.getBean("dataSource");
//		
//		// 初始化模块信息
//		LOG.info("开始初始化模块信息！");
//		List<Module> modules = loadModuleFromXml();
//		try {
//			initModulsToDB( dataSource.getConnection() , modules);
//		} catch (SQLException e) {
//			String message = "不能获取数据库连接";
//			LOG.error(message);
//			LOG.error(e.getMessage());
//			throw new ApplicationBaseException(message,e);
//		}
//		LOG.info("完成初始化模块信息！");
//
//		// 初始化功能信息
//		LOG.info("开始初始化功能信息！");
//		List<Function> functions = loadFunctionFromXml("function-back.xml");
//		try {
//			initFunctionsToDB( dataSource.getConnection(), functions);
//		} catch (SQLException e) {
//			String message = "不能获取数据库连接";
//			LOG.error(message);
//			LOG.error(e.getMessage());
//			throw new ApplicationBaseException(message,e);
//		}
//		LOG.info("完成初始化功能信息！");
//		
//		//初始化前台信息;
//		LOG.info("开始初始化前台信息！");
//		List<Function> functionFronts = loadFunctionFromXml("function-front.xml");
//		try {
//			initFunctionFrontsToDB( dataSource.getConnection(), functionFronts);
//		} catch (SQLException e) {
//			String message = "不能获取数据库连接";
//			LOG.error(message);
//			LOG.error(e.getMessage());
//			throw new ApplicationBaseException(message,e);
//		}
//		LOG.info("完成初始化前台信息！");
//		
//		//初始化权限
//		LOG.info("开始初始化权限！");
//		adminRole.setModules(new HashSet<Module>(moduleManager.getAllModules()));
//		List<Function> roleFunctions = functionManager.getAll();
//		adminRole.setFunctions(new HashSet<Function>(roleFunctions));
//		roleManager.saveOrUpdate(adminRole);
//		
//		List<Module> frontModules = moduleManager.getModuleByViewType(Global.VIEWTYPE_FRONT);		
//		frontRole.setModules(new HashSet<Module>(frontModules));		
//		roleManager.saveOrUpdate(frontRole);
//		
//		RoleRightsMap.put(adminRole.getIdentifier(), roleFunctions);
//		LOG.info("完成初始化权限！");
//		
//		// 初始化系统任务信息
//		LOG.info("开始初始化系统任务信息！");
//		try {
//			initTasksToDB( dataSource.getConnection());
//		} catch (SQLException e) {
//			String message = "不能获取数据库连接";
//			LOG.error(message);
//			LOG.error(e.getMessage());
//			throw new ApplicationBaseException(message,e);
//		}
//		LOG.info("完成初始化系统任务信息！");
//		
//		LOG.info("开始初始化前台系统配置信息！");
//		initFrontConfigDataToDB();
//		LOG.info("完成初始化前台系统配置信息！");
//		
//		
//		LOG.info("完成初始化数据库数据");
//	}
//	
//	/**
//	 * 将配置好的模块数据解析出来，以便初始化到数据库中
//	 * 
//	 * @return
//	 */
//	private static List<Module> loadModuleFromXml() {
//		Resource resource = Global._ctx.getResource("classpath:data/module.xml");
//		XStream xStream = new XStream();
//		xStream.alias("modules", ArrayList.class);
//		xStream.alias("module", Module.class);
//		List<Module> modules = null;
//		try {
//			modules = (List<Module>) xStream.fromXML(resource.getInputStream());
//		} catch (IOException e) {
//			LOG.error("在解析module.xml文件时出错");
//			LOG.error(e.getMessage());
//		}
//		return modules;
//	}
//
//	/**
//	 * 将配置好的功能数据解析出来，以便初始化到数据库中
//	 * 
//	 * @return
//	 */
//	private static List<Function> loadFunctionFromXml(String xmlName) {
//		
//		Resource resource = Global._ctx.getResource("classpath:data/" + xmlName);
//		XStream xStream = new XStream();
//		xStream.alias("functions", ArrayList.class);
//		xStream.alias("function", Function.class);
//		xStream.alias("module", Module.class);
//		List<Function> functions = null;
//		try {
//			functions = (List<Function>) xStream.fromXML(resource
//					.getInputStream());
//		} catch (IOException e) {
//			LOG.error("在解析function.xml文件时出错");
//			LOG.error(e.getMessage());
//		}
//		return functions;
//	}
//	
//	/**
//	 * 将模块信息初始化到数据库中
//	 * @param modules
//	 * @throws ApplicationBaseException 
//	 */
//	private static void initModulsToDB(Connection connection , List<Module> modules) throws ApplicationBaseException{
//		
//		PreparedStatement stmt = null;
//		try {
//			String sql = "insert into UI_FW_MODULE(id,name,action_url,img_url,module_level,parent_id,sort,view_type) values(?,?,?,?,?,?,?,?)";
//			stmt = connection.prepareStatement(sql);
//			for (Module module : modules) {
//				stmt.setInt(1, module.getIdentifier());
//				stmt.setString(2, module.getName());
//				stmt.setString(3, module.getActionURL());
//				stmt.setString(4, module.getImgURL());
//				stmt.setInt(5, module.getLevel());
//				stmt.setInt(6, module.getParentModuleId());
//				stmt.setShort(7, module.getSort());
//				stmt.setString(8, module.getViewType());
//				stmt.addBatch();
//			}
//			stmt.executeBatch();
//		} catch (SQLException e) {
//			String message = "初始化模块信息的时候出现数据库错误！";
//			LOG.error(message);
//			LOG.error(e.getMessage());
//			throw new ApplicationBaseException(message,e);
//		}finally{
//			try {
//				connection.close();
//				stmt.close();
//			} catch (SQLException e) {
//				String message = "初始化模块信息的时候出现数据库错误！";
//				LOG.error(message);
//				LOG.error(e.getMessage());
//				throw new ApplicationBaseException(message,e);
//			}
//		}
//	}
//	
//	/**
//	 * 将功能信息初始化到数据库中
//	 * @param connection
//	 * @param functions
//	 * @throws ApplicationBaseException
//	 */
//	private static  void initFunctionFrontsToDB(Connection connection, List<Function> functions) throws ApplicationBaseException{
//		
//		String viewImgUrl = "/viewframe/themes/base/img/widget/view-1.png";
//		String viewType = "view";
//		String color = "red";
//		String loadingType = Global.VIEWFRAME_Module_LOADING_TYPE_DOM;
//		String moduleType = "baseModule";
//		
//		for (Function function : functions) {
//			
//			Module module = moduleManager.getModuleById(function.getModule().getIdentifier());
//			function.setModule(module);
//			
//			functionManager.saveOrUpdate(function);
//			
//			VfNavWidget widget = new VfNavWidget();			
//			widget.setFunction(function);
//			widget.setViewImgUrl(viewImgUrl);
//			widget.setViewType(viewType);			
//			
//			vfNavWidgetManager.saveOrUpdate(widget);
//			
//			VfNavWidgetModule widgetModule = new VfNavWidgetModule();
//			
//			widgetModule.setColor(color);
//			widgetModule.setContentUrl(function.getActionUrl());
//			widgetModule.setIconUrl(widget.getViewImgUrl());
//			widgetModule.setLoadingType(loadingType);
//			widgetModule.setModuleType(moduleType);
//			widgetModule.setRecommendCols((short)4);
//			widgetModule.setStatus((short)0);
//			widgetModule.setTitle(function.getName());
//			widgetModule.setWidget(widget);
//			
//			vfNavWidgetModuleManager.saveOrUpdate(widgetModule);
//			
//			
//			VfNavWidgetModuleData dataPdf = new VfNavWidgetModuleData();
//			dataPdf.setDataLabel("pdf");
//			dataPdf.setDataValue("birt");
//			dataPdf.setWidgetModule(widgetModule);
//			
//			vfNavWidgetModuleDataManager.saveOrUpdate(dataPdf);
//			
//			VfNavWidgetModuleData dataWord = new VfNavWidgetModuleData();
//			dataWord.setDataLabel("word");
//			dataWord.setDataValue("birt");
//			dataWord.setWidgetModule(widgetModule);
//			
//			vfNavWidgetModuleDataManager.saveOrUpdate(dataWord);
//			
//			VfNavWidgetModuleData dataExcel = new VfNavWidgetModuleData();
//			dataExcel.setDataLabel("excel");
//			dataExcel.setDataValue("birt");
//			dataExcel.setWidgetModule(widgetModule);
//			
//			vfNavWidgetModuleDataManager.saveOrUpdate(dataExcel);
//			
//			VfNavWidgetModuleData dataPrint = new VfNavWidgetModuleData();
//			dataPrint.setDataLabel("print");
//			dataPrint.setDataValue("birt");
//			dataPrint.setWidgetModule(widgetModule);
//			
//			vfNavWidgetModuleDataManager.saveOrUpdate(dataPrint);
//			
//		}
//		
//	}
//	
//	public static void initFrontConfigDataToDB () {
//		{
//			//用户可以添加页码标签页上限
//			VfConfig config = new VfConfig();
//			config.setDataLabel("thr_tab");
//			config.setDataValue("10");
//			vfConfigManager.saveOrUpdate(config);
//		}
//		{
//			//全局。用户每个标签页内，可以添加报表模块的上限。
//			VfConfig config = new VfConfig();
//			config.setDataLabel("thr_tab_module");
//			config.setDataValue("10");
//			vfConfigManager.saveOrUpdate(config);
//		}
//		{
//			// 标题名长度上限。
//			VfConfig config = new VfConfig();
//			config.setDataLabel("thr_tab_name");
//			config.setDataValue("10");
//			vfConfigManager.saveOrUpdate(config);
//		}
//		{
//			// 添加内容按钮，默认打开方式。1：打开；0：关闭；空值默认关闭。
//			VfConfig config = new VfConfig();
//			config.setDataLabel("btn_addContent");
//			config.setDataValue("1");
//			vfConfigManager.saveOrUpdate(config);
//		}
//	}
//	
//	
//	/**
//	 * 将功能信息初始化到数据库中
//	 * @param connection
//	 * @param functions
//	 * @throws ApplicationBaseException
//	 */
//	private static  void initFunctionsToDB(Connection connection, List<Function> functions) throws ApplicationBaseException{
//		PreparedStatement stmt = null;
//		try {
//			String sql = "insert into UI_FW_FUNCTION(name,action_url,module_id,sort) values(?,?,?,?)";
//			stmt = connection.prepareStatement(sql);
//			for (Function function : functions) {
//				stmt.setString(1, function.getName());
//				stmt.setString(2, function.getActionUrl());
//				stmt.setInt(3, function.getModule().getIdentifier());
//				stmt.setShort(4, function.getSort());
//				stmt.addBatch();
//			}
//			stmt.executeBatch();
//		} catch (SQLException e) {
//			String message = "初始化功能信息的时候出现数据库错误！";
//			LOG.error(message);
//			LOG.error(e.getMessage());
//			throw new ApplicationBaseException(message,e);
//		}finally{
//			try {
//				connection.close();
//				stmt.close();
//			} catch (SQLException e) {
//				String message = "初始化功能信息的时候出现数据库错误！";
//				LOG.error(message);
//				LOG.error(e.getMessage());
//				throw new ApplicationBaseException(e);
//			}
//		}
//	}
//
//
//	/**
//	 * 将系统任务信息初始化到数据库中
//	 * @param connection
//	 * @throws ApplicationBaseException
//	 */
//	private static  void initTasksToDB(Connection connection ) throws ApplicationBaseException{
//		PreparedStatement stmt = null;
//		try {
//			String sql = "insert  into UI_TASK(ID,TASK_NAME,TASK_IMPL,TASK_DESC,TASK_TYPE,START_DATE,"+
//				"NODE_NAME,IF_ALL_NODE,SEPERATE,AVAIL_FLAG,CREATE_DATE,UPD_EMP_NO,UPD_DATE) values (1,"+
//				"'告警短信发送任务','com.feinno.framework.special.task.SendMessageTask','告警短信发送任务',"+
//				"1,'2011-01-01 15:00:00','fetion-datapt-pot01',0,'M',0,'2010-11-05 15:44:08','admin','2011-05-09 16:28:53')";
//			stmt = connection.prepareStatement(sql);
//			stmt.executeBatch();
//		} catch (SQLException e) {
//			String message = "初始化系统任务信息的时候出现数据库错误！";
//			LOG.error(message);
//			LOG.error(e.getMessage());
//			throw new ApplicationBaseException(message,e);
//		}finally{
//			try {
//				connection.close();
//				stmt.close();
//			} catch (SQLException e) {
//				String message = "初始化系统任务信息的时候出现数据库错误！";
//				LOG.error(message);
//				LOG.error(e.getMessage());
//				throw new ApplicationBaseException(e);
//			}
//		}
//	}
//
//	/**
//	 * 生成数据库sql脚本
//	 * 
//	 * 默认生成resource/dbscript/{数据库产品名称}/{数据库产品名称}_schema.sql
//	 * 
//	 * @param path
//	 *            数据库脚本路径
//	 * @param dbName
//	 *            数据库名称
//	 */
//	public static void generateScript(String path, String dbName) {
//		
//		LocalSessionFactoryBean sessionFactory = (LocalSessionFactoryBean) Global._ctx.getBean("&sessionFactory");
//		Configuration hbmConf = sessionFactory.getConfiguration();
//		SchemaExport dbExport = new SchemaExport(hbmConf);
//
//		DatabaseMetaData dbmd;
//		try {
//			dbmd = sessionFactory.getDataSource().getConnection().getMetaData();
//			String dbType = dbmd.getDatabaseProductName();
//			String exportPath = null;
//			if (StringUtils.isBlank(path)) {
//				exportPath = "./resource//dbscript//" + dbType;
//			}
//			else {
//				exportPath = path + dbType;
//			}
//
//			File filePath = new File(exportPath);
//			if (!filePath.exists()) {
//				filePath.mkdirs();
//			}
//			String sqlFile = null;
//			if (StringUtils.isBlank(dbName)) {
//				sqlFile = exportPath + "//" + dbType + "_schema.sql";
//			}
//			else {
//				sqlFile = exportPath + "//" + dbName + "_" + dbType
//						+ "_schema.sql";
//			}
//			dbExport.setOutputFile(sqlFile);
//			dbExport.setDelimiter(";");
//			dbExport.create(false, false);
//		}
//		catch (SQLException e) {
//			LOG.error(e.getMessage());
//		}
//	}
//
//}
