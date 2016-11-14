package com.base.dao.dialect;

import com.base.dao.Page;

/**
 * 数据库方言接口
 * 
 * <pre>Title: IDialect.java</pre> 
 * <pre>Description:IT部</pre> 
 * <pre>Copyright: Copyright (c) 2014</pre> 
 * <pre>Company:石家庄天远科技有限公司</pre>
 * 
 * @author zhengxingmiao
 * @date Mar 10, 2014 4:36:39 PM
 * @version V1.0
 */
public interface IDialect {

	/**
	 * 得到分页语句
	 * 
	 * @param sql 正常的select 语句,没有order by
	 * @param orderby order by 语句
	 * @param page 分页对象
	 * @return
	 */
	String getPageSql(String sql, String orderby, Page page);

	/**
	 * 获取数据库类型
	 * 
	 * @return
	 */
	String getDataDaseType();

	/**
	 * 是否包含 rownum 列
	 * 
	 * @return
	 */
	boolean isRowNumber();

}
