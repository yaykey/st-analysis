/**
 * @(#)EhcacheCacheStrategy.java  2011-6-15
 */
package com.st.framework.controller.helper.wro;

import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;
import ro.isdc.wro.cache.CacheStrategy;

/**
 * 类 <code>EhcacheCacheStrategy</code>
 * 
 * @author wangwenyao@feinno.com
 * @version 2011-6-15
 */
public class EhcacheCacheStrategy<K, V> implements CacheStrategy<K, V> {

	private Cache cache = null;

	public EhcacheCacheStrategy(Cache cache) {
		this.cache = cache;
	}

	/*
	 * (non-Jsdoc)
	 * 
	 * @see ro.isdc.wro.cache.CacheStrategy#clear()
	 */
	@Override
	public void clear() {
		cache.removeAll();
	}

	/*
	 * (non-Jsdoc)
	 * 
	 * @see ro.isdc.wro.cache.CacheStrategy#destroy()
	 */
	@Override
	public void destroy() {
		cache.removeAll();
	}

	/*
	 * (non-Jsdoc)
	 * 
	 * @see ro.isdc.wro.cache.CacheStrategy#get(java.lang.Object)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public V get(K key) {
		Element e = cache.get(key);
		if (e == null)
			return null;
		return (V) e.getValue();
	}

	/*
	 * (non-Jsdoc)
	 * 
	 * @see ro.isdc.wro.cache.CacheStrategy#put(java.lang.Object,
	 * java.lang.Object)
	 */
	@Override
	public void put(K key, V value) {
		cache.put(new Element(key, value));
	}

}
