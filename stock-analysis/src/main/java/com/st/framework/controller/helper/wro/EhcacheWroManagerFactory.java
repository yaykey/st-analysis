/**
 * @(#)EhcacheWroManagerFactory.java  2011-6-15
 */
package com.st.framework.controller.helper.wro;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import ro.isdc.wro.cache.CacheEntry;
import ro.isdc.wro.cache.CacheStrategy;
import ro.isdc.wro.cache.ContentHashEntry;
import ro.isdc.wro.manager.factory.BaseWroManagerFactory;

/**
 * 类 <code>EhcacheWroManagerFactory</code>
 * @author wangwenyao@feinno.com
 * @version 2011-6-15
 */
public class EhcacheWroManagerFactory extends BaseWroManagerFactory {

	/* (non-Jsdoc)
	 * @see ro.isdc.wro.manager.factory.BaseWroManagerFactory#newCacheStrategy()
	 */
	@Override
	protected CacheStrategy<CacheEntry, ContentHashEntry> newCacheStrategy() {
		CacheManager manager = CacheManager.create();
		Cache cache = manager.getCache("wroCache");
		return new EhcacheCacheStrategy<CacheEntry, ContentHashEntry>(cache);
	}
	
	

}
