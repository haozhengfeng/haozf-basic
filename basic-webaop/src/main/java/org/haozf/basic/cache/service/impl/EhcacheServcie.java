package org.haozf.basic.cache.service.impl;

import org.haozf.basic.cache.service.ICacheService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

@Service("ehcacheServcie")
public class EhcacheServcie implements ICacheService {

	@Override
	@CacheEvict(value = "usersCache", key = "#key")
	public String CacheEvict(int key) {
		System.out.println("解除缓存usersCache中的"+key);
		return "解除缓存usersCache中的"+key;
	}

}
