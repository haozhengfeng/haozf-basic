package org.haozf.basic.cache.service;

public interface ICacheService {
	
	/**
	 * 解除缓存cacheName中的key的缓存
	 * @param cacheName
	 * @param key
	 */
	public String CacheEvict(int key);

}
