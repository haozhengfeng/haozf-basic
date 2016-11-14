package net.zhxm.frame.base.dao;

import java.util.ArrayList;

public class Finder {
	
	private Object[] params = null;
	
	private ArrayList<Object> param_list = new ArrayList<Object>();
	
	private StringBuffer sql = new StringBuffer();
	
	private String orderSql = null;
	
	private String pageSql = null;
	
	//设置总条数查询的finder
	private Finder countFinder = null;
	
	public Finder() {}
	
	public Finder(String s) {
		this.sql.append(s);
	}
	
	/** 添加子句 */
	public Finder append(String s) {
		sql.append(s);
		//System.out.println(sql);
		return this;
	}

	/**
	 * 设置参数。
	 * @param param
	 * @param value
	 * @return
	 */
	public Finder setParam(Object value) {
		param_list.add(value);
		return this;
	}
	
	public Object[] getParams() {
		this.params = param_list.toArray();
		return params;
	}
	
	public String getSql() {
		if (sql == null) return null;
		return sql.toString();
	}

	public void setSql(String sql) {
		this.sql = new StringBuffer(sql);
	}

	public String getOrderSql() {
		return orderSql;
	}

	public void setOrderSql(String orderSql) {
		this.orderSql = orderSql;
	}

	public String getPageSql() {
		return pageSql;
	}

	public void setPageSql(String pageSql) {
		this.pageSql = pageSql;
	}
	
	/**
	 * 查询总条数的 Finder对象
	 * @return Finder
	 */
	public Finder getCountFinder() {
		return countFinder;
	}

	public void setCountFinder(Finder countFinder) {
		this.countFinder = countFinder;
	}

	
	public void showSqlParams() {
		System.out.println(getSql());
		System.out.println(param_list);
		
	}
	
}