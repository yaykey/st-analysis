package com.st.framework.utils.config;
///*
// * 文件名： HibernateAnnotationSessionFactoryBean.java
// * 
// * 创建日期： 2011-4-11
// *
// * Copyright(C) 2011, by xiaozhi.
// *
// * 原始作者: <a href="mailto:xiaozhi19820323@hotmail.com">xiaozhi</a>
// *
// */
//package com.feinno.framework.utils.config;
//
//import java.io.IOException;
//
//import javax.persistence.Entity;
//
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
//import org.hibernate.HibernateException;
//import org.hibernate.cfg.AnnotationConfiguration;
//import org.springframework.core.io.Resource;
//import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
//import org.springframework.core.io.support.ResourcePatternResolver;
//import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
//import org.springframework.core.type.classreading.MetadataReader;
//import org.springframework.core.type.classreading.MetadataReaderFactory;
//import org.springframework.core.type.filter.AnnotationTypeFilter;
//import org.springframework.core.type.filter.TypeFilter;
//import org.springframework.orm.hibernate3.annotation.AnnotationSessionFactoryBean;
//import org.springframework.util.ClassUtils;
//
///**
// * 搜索Hibernate持久化域模型（带Entity属性的Model,域模型Model必须指定为@Entity）然后加载
// *
// * @author <a href="mailto:xiaozhi19820323@hotmail.com">xiaozhi</a>
// *
// * @version $Revision: 1.1 $
// *
// * @Date 上午11:20:47
// *
// * @since 2011-4-11
// */
//@SuppressWarnings("deprecation")
//public class HibernateAnnotationSessionFactoryBean extends AnnotationSessionFactoryBean{
//
//	private static Log logger = LogFactory.getLog(HibernateAnnotationSessionFactoryBean.class) ;
//	
//	/**
//	 * 搜索的class文件路径，默认所有下级的目录的class文件
//	 */
//	private static final String DEFAULT_RESOURCE_PATTERN = "**/*.class";
//
//	private ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
//
//	private MetadataReaderFactory metadataReaderFactory = new CachingMetadataReaderFactory(
//			this.resourcePatternResolver);
//
//	private final TypeFilter entityFilter = new AnnotationTypeFilter(Entity.class);
//
//	private String resourcePattern = DEFAULT_RESOURCE_PATTERN;
//
//	private String[] basePackages;
//
//	public void setBasePackages(String... basePackages) {
//		this.basePackages = basePackages;
//	}
//
//	/*
//	 * 
//	 * (non-Javadoc)
//	 * 
//	 * @see
//	 * org.springframework.orm.hibernate3.annotation.AnnotationSessionFactoryBean
//	 * #
//	 * postProcessAnnotationConfiguration(org.hibernate.cfg.AnnotationConfiguration
//	 * )
//	 */
//	protected void postProcessAnnotationConfiguration(AnnotationConfiguration config) throws HibernateException {
//		logger.info("begin load hibernate entity...") ;	
//		// 读取包
//		for (String basePackage : basePackages) {
//			try {
//				String packageSearchPath = ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX+ ClassUtils.convertClassNameToResourcePath(basePackage)
//						+ "/" + this.resourcePattern;
//				Resource[] resources = this.resourcePatternResolver
//						.getResources(packageSearchPath);
//				for (int i = 0; i < resources.length; i++) {
//					Resource resource = resources[i];
//					MetadataReader metadataReader = this.metadataReaderFactory.getMetadataReader(resource);
//					if (isEntity(metadataReader)) {
//						String classFileFullPath = resource.getURL().getPath();
//						String basePackageResourcePath = ClassUtils.convertClassNameToResourcePath(basePackage);
//						int startIndex = classFileFullPath
//								.indexOf(basePackageResourcePath);
//						final String classFilePath = classFileFullPath.substring(startIndex, classFileFullPath.length()- ClassUtils.CLASS_FILE_SUFFIX.length());
//						Class<?> entityClass = null;
//						try {
//							entityClass = ClassUtils.forName(ClassUtils.convertResourcePathToClassName(classFilePath));
//						}
//						catch (ClassNotFoundException e) {
//							logger
//									.error(
//											"Entity class not found during classpath scanning",
//											e);
//
//							throw new HibernateException(
//									"Entity class not found during classpath scanning",
//									e);
//						}
//						config.addAnnotatedClass(entityClass);
//					}
//				}
//			}
//			catch (IOException ex) {
//				throw new HibernateException(
//						"I/O failure during classpath scanning", ex);
//			}
//		}
//		logger.info("finish load hibernate entity...") ;
//	}
//
//	/**
//	 * 判断要对应的数据是否有Entity这个实体
//	 * 
//	 * @param metadataReader 被验证的数据对象
//	 * 
//	 * @return 有没有这个实体
//	 * 
//	 * @throws IOException
//	 */
//	private boolean isEntity(MetadataReader metadataReader) throws IOException {
//		if (entityFilter.match(metadataReader, this.metadataReaderFactory)) {
//			return true;
//		}	
//		return false;
//	}
//}
