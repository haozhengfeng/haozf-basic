package org.haozf.basic.collection.service;

import java.util.List;

/**
 * 数据采集接口
 * 
 * @author haozhengfeng
 */
public interface ICollection<T> {
	/**
	 * 连接url
	 * 
	 * @param url
	 */
	public void connect(String url);

	/**
	 * 生成数据
	 */
	public List<T> generateData();

	/**
	 * 保存数据
	 */
	public T save(T t);
}
