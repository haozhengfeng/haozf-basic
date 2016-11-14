package net.zhxm.frame.base.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

public class NamedParamJdbcDao extends BaseJdbcDao {
	
	@Resource
	NamedParameterJdbcTemplate namedParamJdbc;
	
	/**
	 * 
	 * @Title: update 
	 * @Description:  执行INSERT/UPDATE/DELETE等通用语句
	 * @param @param finder sql实体类
	 * @param @param pageMap sql参数
	 * @param @return    设定文件 
	 * @return int    返回类型 
	 * @throws
	 */
	public int update(Finder finder,Map<String, String> paramMap){
		return namedParamJdbc.update(finder.getSql(), paramMap);
	}
	
	/**
	 * 
	 * @Title: queryForList 
	 * @Description: 分页查询 
	 * @param @param finder sql实体类
	 * @param @param page 分页信息类
	 * @param @param paramMap sql参数
	 * @param @return    设定文件 
	 * @return List<Map<String,Object>>    返回类型 
	 * @throws
	 */
	public List<Map<String, Object>> queryForList(Finder finder, Page page,Map<String, String> paramMap) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		String pageSql = super.getPageSql(page, finder);
		if (pageSql == null){
			return list;
		}
		return namedParamJdbc.queryForList(pageSql, paramMap);
	}
	
	/**
	 * 
	 * @Title: queryForList 
	 * @Description: 查询
	 * @param @param finder sql实体类
	 * @param @param paramMap sql参数
	 * @param @return    设定文件 
	 * @return List<Map<String,Object>>    返回类型 
	 * @throws
	 */
	public List<Map<String, Object>> queryForList(Finder finder,Map<String, String> paramMap) {
		return namedParamJdbc.queryForList(finder.getSql(), paramMap);
	}
	
}
