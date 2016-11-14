/**
 * 
 */
package org.haozf.basic.dao;

import java.util.List;
import java.util.Map;


import org.hibernate.Query;


/**
 * @author Administrator
 * 
 */
@SuppressWarnings("unchecked")
public class HibernateBaseDaoByHQL<T> extends HibernateBaseDao<T> {
	

	/**
	 * 通过hql、参数数组查询
	 * 
	 * @param hql
	 * @param args
	 * @return
	 */
	public Object queryObjectByHQL(String hql, Object[] args) {
		return this.queryObjectByHQL(hql, args, null);
	}

	/**
	 * 通过hql、参数查询
	 * 
	 * @param hql
	 * @param arg
	 * @return
	 */
	public Object queryObjectByHQL(String hql, Object arg) {
		return this.queryObjectByHQL(hql, new Object[] { arg });
	}

	/**
	 * 通过hql查询
	 * 
	 * @param hql
	 * @return
	 */
	public Object queryObjectByHQL(String hql) {
		return this.queryObjectByHQL(hql, new Object[] {});
	}

	/**
	 * 通过hql、参数别名查询
	 * 
	 * @param hql
	 * @param alias
	 * @return
	 */
	public Object queryObjectByHQLAndAlias(String hql, Map<String, Object> alias) {
		return this.queryObjectByHQL(hql, null, alias);
	}

	/**
	 * 通过hql、参数数组、参数别名查询
	 * 
	 * @param hql
	 * @param args
	 * @param alias
	 * @return
	 */
	public Object queryObjectByHQL(String hql, Object[] args, Map<String, Object> alias) {
		Query query = getSession().createQuery(hql);
		setAliasParameter(query, alias);
		setParameter(query, args);
		return query.uniqueResult();
	}

	/**
	 * 通过HQL执行更新语句
	 * 
	 * @param hql
	 */
	public void updateByHQL(String hql) {
		this.updateByHQL(hql, null);
	}

	/**
	 * 通过HQL、参数执行更新语句
	 * 
	 * @param hql
	 * @param arg
	 */
	public void updateByHQL(String hql, Object arg) {
		this.updateByHQL(hql, new Object[] { arg });
	}

	/**
	 * 通过hql、参数数组执行更新语句
	 * 
	 * @param hql
	 * @param args
	 */
	public void updateByHQL(String hql, Object[] args) {
		Query query = getSession().createQuery(hql);
		setParameter(query, args);
		query.executeUpdate();
	}

	/**
	 * 通过hql、参数数组获取数据list集合
	 * 
	 * @param hql
	 * @param args
	 * @return
	 */
	public List<T> listByHQL(String hql, Object[] args) {
		return this.listByHQL(hql, args, null);
	}

	/**
	 * 通过hql、参数获取数据list集合
	 * 
	 * @param hql
	 * @param arg
	 * @return
	 */
	public List<T> listByHQL(String hql, Object arg) {
		return this.listByHQL(hql, new Object[] { arg });
	}

	/**
	 * 通过hql获取数据list集合
	 * 
	 * @param hql
	 * @return
	 */
	public List<T> listByHQL(String hql) {
		return this.listByHQL(hql, new Object[] {});
	}

	/**
	 * 通过hql、参数别名获取数据list集合
	 * 
	 * @param hql
	 * @param alias
	 * @return
	 */
	public List<T> listByByHQLAndAlias(String hql, Map<String, Object> alias) {
		return this.listByHQL(hql, null, alias);
	}

	/**
	 * 通过hql、参数数组、参数别名获取数据list集合
	 * 
	 * @param hql
	 * @param args
	 * @param alias
	 * @return
	 */
	public List<T> listByHQL(String hql, Object[] args, Map<String, Object> alias) {
		hql = initSort(hql);
		Query query = getSession().createQuery(hql);
		setAliasParameter(query, alias);
		setParameter(query, args);
		return query.list();
	}

	/**
	 * 通过hql、参数数组获取对象list集合
	 * 
	 * @param hql
	 * @param args
	 * @return
	 */
	public <N extends Object> List<N> listObj(String hql, Object[] args) {
		return this.listObj(hql, args, null);
	}

	/**
	 * 通过hql、参数获取对象list集合
	 * 
	 * @param hql
	 * @param arg
	 * @return
	 */
	public <N extends Object> List<N> listObj(String hql, Object arg) {
		return this.listObj(hql, new Object[] { arg });
	}

	/**
	 * 通过hql获取对象list集合
	 * 
	 * @param hql
	 * @return
	 */
	public <N extends Object> List<N> listObj(String hql) {
		return this.listObj(hql, new Object[] {});
	}

	/**
	 * 通过hql、参数别名获取对象list集合
	 * 
	 * @param hql
	 * @param alias
	 * @return
	 */
	public <N extends Object> List<N> listObjByAlias(String hql, Map<String, Object> alias) {
		return this.listObj(hql, null, alias);
	}

	/**
	 * 通过hql、参数数组、参数别名获取对象list集合
	 * 
	 * @param hql
	 * @param args
	 * @param alias
	 * @return
	 */
	public <N extends Object> List<N> listObj(String hql, Object[] args, Map<String, Object> alias) {
		hql = initSort(hql);
		Query query = getSession().createQuery(hql);
		setAliasParameter(query, alias);
		setParameter(query, args);
		return query.list();
	}

}
