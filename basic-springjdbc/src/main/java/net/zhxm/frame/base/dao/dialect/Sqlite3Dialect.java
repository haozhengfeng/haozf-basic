package net.zhxm.frame.base.dao.dialect;

import net.zhxm.frame.base.dao.Page;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component("sqlite3Dialect")
public class Sqlite3Dialect implements IDialect {

	@Override
	public String getPageSql(String sql, String orderby, Page page) {
		// 设置分页参数
		int pageSize = page.getPageSize();
		int pageNo = page.getPageIndex();
		StringBuffer sb = new StringBuffer(sql);
		if (StringUtils.isNotBlank(orderby)) {
			sb.append(" ").append(orderby);
		}
		sb.append(" limit ").append(pageSize * (pageNo - 1)).append(",").append(pageSize);
		return sb.toString();
	}

	@Override
	public String getDataDaseType() {
		return "sqlite3";
	}

	@Override
	public boolean isRowNumber() {
		return false;
	}

}
