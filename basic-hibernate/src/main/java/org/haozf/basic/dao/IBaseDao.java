package org.haozf.basic.dao;

/**
 * 公共的DAO处理对象，这个对象中包含了Hibernate的所有基本操作和对SQL的操作
 * @author Administrator
 *
 * @param <T>
 */
public interface IBaseDao<T> {
	/**
	 * 添加对象
	 * @param t
	 * @return
	 */
	public T add(T t);
	/**
	 * 更新对象
	 * @param t 更新的对象
	 */
	public void update(T t);
	/**
	 * 根据id删除对象
	 * @param id 删除的对象的id
	 */
	public void delete(int id);
	/**
	 * 根据id加载对象
	 * @param id 加载对象的id
	 * @return 返回加载对象
	 */
	public T load(int id);
	
}

