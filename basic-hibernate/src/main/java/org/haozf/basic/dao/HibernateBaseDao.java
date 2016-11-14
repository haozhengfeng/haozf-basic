package org.haozf.basic.dao;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class HibernateBaseDao<T> extends AbstractHibernateBaseDao implements IBaseDao<T>{
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.haozf.basic.dao.IBaseDao#add(java.lang.Object)
	 */
	@Override
	public T add(T t) {
		getSession().save(t);
		return t;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.haozf.basic.dao.IBaseDao#update(java.lang.Object)
	 */
	@Override
	public void update(T t) {
		getSession().update(t);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.haozf.basic.dao.IBaseDao#delete(int)
	 */
	@Override
	public void delete(int t) {
		getSession().delete(t);
	}

	private Class<T> clz;

	public Class<T> getClz() {
		if (clz == null) {
			Type sType = getClass().getGenericSuperclass();
			Type[] generics = ((ParameterizedType) sType).getActualTypeArguments();
			clz = (Class<T>) (generics[0]);
		}
		return clz;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.haozf.basic.dao.IBaseDao#load(int)
	 */
	@Override
	public T load(int id) {
		return getSession().get(getClz(), id);
	}
}
