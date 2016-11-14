package org.haozf.basic.cache.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.haozf.basic.cache.service.ICacheService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/cache")
public class CacheController {

	@Resource
	private ICacheService ehcacheService;
	
	@RequestMapping(value="/evict/{cacheName}/{key}",produces = "application/json; charset=utf-8")
	@ResponseBody
	public String CacheEvict(@PathVariable String cacheName,@PathVariable int key){
		String info = ehcacheService.CacheEvict(key);
		Map returnMap = new HashMap();
		returnMap.put("msg", info);
		return info;
	}
	
}
