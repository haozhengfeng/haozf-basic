package com.base.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.base.dao.dialect.IDialect;

public class BaseJdbcDao {

	/**
	 * 数据库的jdbc,对应spring配置的 jdbc bean
	 */
	@Resource
	JdbcTemplate jdbc;

	/**
	 * mssqlDialect 是sqlserver的方言,springBean的name,可以参考IDialect的实现
	 */
	@Resource
	public IDialect mssqlDialect;

	public IDialect getDialect() {
		return mssqlDialect;
	}

	// ////////////////帮助工具方法 ////////////////////////////

	/**
	 * 是否是java的基本类型
	 * 
	 * zhengxingmiao Aug 27, 2014 2:25:20 PM
	 * 
	 * @param clazz
	 * @return
	 */
	public static boolean isBaseType(Class clazz) {
		if (clazz == null) {
			return false;
		}
		String className = clazz.getName().toLowerCase();
		if (className.startsWith("java.")) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 根据page 和finder对象,拼装返回分页查询的语句
	 * 
	 * zhengxingmiao Apr 21, 2014 11:19:45 AM
	 * 
	 * @param page
	 * @param finder
	 * @return
	 */
	public String getPageSql(Page page, Finder finder) {
		String sql = finder.getSql();
		String orderSql = finder.getOrderSql();

		// System.out.println("sql:"+sql);
		// System.out.println("orderSql:"+orderSql);

		// 查询sql、统计sql不能为空
		if (StringUtils.isBlank(sql)) {
			return null;
		}

		if (getOrderByIndex(sql) > -1) {
			if (StringUtils.isBlank(orderSql)) {
				orderSql = sql.substring(getOrderByIndex(sql));
				sql = sql.substring(0, getOrderByIndex(sql));
			}
		} else {
			orderSql = " order by id";
		}

		if (page == null) {
			if (StringUtils.isNotBlank(orderSql))
				return sql + " " + orderSql;
			else
				return sql;
		}

		Integer count = null;

		if (finder.getCountFinder() == null) {
			String countSql = new String(sql);
			int order_int = getOrderByIndex(countSql);
			if (order_int > 1) {
				countSql = countSql.substring(0, order_int);
			}

			/**
			 * 特殊关键字过滤, distinct ,union ,group by
			 */
			if (countSql.toLowerCase().indexOf(" distinct ") > -1
					|| countSql.toLowerCase().indexOf(" union ") > -1
					|| getGroupByIndex(countSql) > -1) {
				countSql =
						"SELECT count(*)  frame_row_count FROM (" + countSql
								+ ") temp_frame_noob_table_name WHERE 1=1 ";
			} else {
				int fromIndex = getFromIndex(countSql);
				if (fromIndex > -1) {
					countSql = "SELECT COUNT(*) " + countSql.substring(fromIndex);
				} else {
					countSql =
							"SELECT count(*)  frame_row_count FROM (" + countSql
									+ ") temp_frame_noob_table_name WHERE 1=1 ";
				}

			}
			count = jdbc.queryForObject(countSql, finder.getParams(), Integer.class);
		} else {
			count = queryForObject(finder.getCountFinder(), Integer.class);
		}
		// 记录总行数(区分是否使用占位符)
		if (count == 0) {
			return null;
		} else {
			page.setTotalCount(count);
		}
		return getDialect().getPageSql(sql, orderSql, page);
	}

	/**
	 * 获取 order by 开始位置
	 * 
	 * @param orderbysql
	 * @return
	 */
	public static int getOrderByIndex(String orderbysql) {
		int index = -1;
		if (StringUtils.isBlank(orderbysql)) {
			return index;
		}
		String regStr = "\\s+(order)\\s+(by)";
		Pattern pattern = Pattern.compile(regStr);
		Matcher matcher = pattern.matcher(orderbysql.toLowerCase());
		if (matcher.find()) {
			index = matcher.start();
		}
		return index;
	}

	/**
	 * 获取 group by 开始位置
	 * 
	 * @param groupbysql
	 * @return
	 */
	public static int getGroupByIndex(String groupbysql) {
		int index = -1;
		if (StringUtils.isBlank(groupbysql)) {
			return index;
		}
		String regStr = "\\s+(group)\\s+(by)";
		Pattern pattern = Pattern.compile(regStr);
		Matcher matcher = pattern.matcher(groupbysql.toLowerCase());
		if (matcher.find()) {
			index = matcher.start();
		}
		return index;
	}

	/**
	 * 获取 group by 开始位置
	 * 
	 * @param sql
	 * @return
	 */
	public static int getFromIndex(String sql) {
		int index = -1;
		if (StringUtils.isBlank(sql)) {
			return index;
		}

		String s = replaceFrom(sql).toLowerCase();

		int startIndex = s.indexOf(" from ");
		int lastIndex = s.lastIndexOf(" from ");
		if (startIndex - lastIndex != 0) {
			return index;
		}
		index = startIndex;
		return index;
	}

	/**
	 * 去掉无用的 from
	 * 
	 * @param str
	 * @return
	 */
	private static String replaceFrom(String str) {
		Pattern pt = Pattern.compile("\\(([\\s\\S]+?)\\)", Pattern.CASE_INSENSITIVE);
		Matcher matcher = pt.matcher(str);
		while (matcher.find()) {
			String group = matcher.group(1);
			String t1 = group.toLowerCase().replace(" from ", " abcd ");
			str = str.replace(group, t1);
		}
		return str;
	}

	/**
	 * 转义sql数据 包括like % '等
	 * 
	 * @author zhengxingmiao
	 * @param str
	 * @return
	 */
	public String escapeSql(String str) {
		// str = StringEscapeUtils.escapeSql(str);
		if (str.indexOf("%") > -1) {
			str = str.replaceAll("%", "[%]");
		}
		return str;
	}

	/**
	 * 如果字符串数据null或者""则替换为new_str
	 * 
	 * @author zhengxingmiao
	 * @param str
	 * @param new_str
	 * @return
	 */
	public static String getNotNullStringValue(String str, String new_str) {
		if (str == null) {
			return new_str;
		} else if (str.length() == 0) {
			return new_str;
		}
		return str;
	}


	////////////////// 具体方法如下 //////////////////////////////
	
	
	/**
	 * 执行INSERT/UPDATE/DELETE等通用语句
	 * 
	 * zhengxingmiao Jun 17, 2015 9:01:08 AM
	 * @param finder
	 * @return
	 */
	public int update(Finder finder){
		return jdbc.update(finder.getSql(), finder.getParams());
	}

	/**
	 * 批量执行指定的SQL语句
	 * 
	 * 使用示例
	 * Object[][] params = new Object[len][3];
	 * List<Object[]> list = new ArrayList<Object[]>();
	 * for(int a=0;a<len;a++){
	 * params[a][0] = 值1;
	 * params[a][1] = 值2;
	 * params[a][2] = 值3;
	 * list.add(params[a]);
	 * }
	 * zhengxingmiao Jun 17, 2015 4:31:21 PM
	 * @param finder
	 * @param list
	 * @return
	 */
	public int[] batch(Finder finder,List<Object[]> list){
		return jdbc.batchUpdate(finder.getSql(), list);
	}

	/**
	 * 添加数据并返回自增长主键
	 * 
	 * zhengxingmiao Jun 17, 2015 4:32:21 PM
	 * @param finder
	 * @return
	 */
	public int insert(final Finder finder){
		KeyHolder keyholder = new GeneratedKeyHolder();
		jdbc.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
				int i = 0;
				PreparedStatement ps = conn.prepareStatement(finder.getSql(), Statement.RETURN_GENERATED_KEYS);
				for (i = 0; i < finder.getParams().length; i++) {
					Object value = finder.getParams()[i];
					if (value != null) {
						ps.setObject(i + 1, value);
//						if (value instanceof java.lang.Integer) {
//							ps.setInt(i + 1, (Integer) value);
//						} else if (value instanceof java.lang.Long) {
//							ps.setLong(i + 1, (Long) value);
//						} else if (value instanceof java.util.Date) {
//							ps.setDate(i + 1,new java.sql.Date(((Date) value).getTime()));
//							ps.setTimestamp(i + 1, new java.sql.Timestamp(((Date) value).getTime()));
//						} else if (value instanceof java.lang.String) {
//							ps.setString(i + 1, value.toString());
//						} else if (value instanceof java.lang.Double) {
//							ps.setDouble(i + 1, (Double) value);
//						} else if (value instanceof java.lang.Byte) {
//							ps.setByte(i + 1, (Byte) value);
//						} else if (value instanceof java.lang.Character) {
//							ps.setString(i + 1, value.toString());
//						} else if (value instanceof java.lang.Float) {
//							ps.setFloat(i + 1, (Float) value);
//						} else if (value instanceof java.lang.Boolean) {
//							ps.setBoolean(i + 1, (Boolean) value);
//						} else if (value instanceof java.lang.Short) {
//							ps.setShort(i + 1, (Short) value);
//						} else {
//							ps.setObject(i + 1, value);
//						}
					} else {
						ps.setNull(i + 1, Types.NULL);
					}
				}
				return ps;
			}
			
		}, keyholder);
		int m = keyholder.getKey().intValue();
		return m;
	}
	
	/**
	 * 统计count
	 * 
	 * zhengxingmiao Jun 17, 2015 4:33:49 PM
	 * @param finder
	 * @return
	 */
	public long count(Finder finder) {
		return jdbc.queryForObject(finder.getSql(), finder.getParams(), Long.class);
	}
	
	
	//////////////////封装Map对象,然会存储到List操作 //////////////////////
	
	
	/**
	 * 自定义查询List集合接口
	 * 
	 * zhengxingmiao Jun 17, 2015 4:46:55 PM
	 * @param sql
	 * @param params
	 * @return
	 */
	private List<Map<String, Object>> getMapListHandler(String sql,Object... params){
		//System.out.println("自定义查询List集合接口");
		return (List<Map<String, Object>>) jdbc.query(sql, params, new ResultSetExtractor(){
			@Override
			public Object extractData(ResultSet rs) throws SQLException,DataAccessException{
				//System.out.println(rs);
				ArrayList<LinkedHashMap<String, String>> list = new ArrayList<LinkedHashMap<String, String>>();
				while (rs.next()) {
					LinkedHashMap<String, String> hm = new LinkedHashMap<String, String>();
					ResultSetMetaData rsmd = rs.getMetaData();
					int cols = rsmd.getColumnCount();
					for (int i = 1; i <= cols; i++) {
						// System.out.println(rsmd.getColumnName(i));
						// System.out.println(rs.getString(i));
						hm.put(rsmd.getColumnName(i), getNotNullStringValue(rs.getString(i), ""));
					}
					//System.out.println(hm);
					list.add(hm);
				}
				return list;
			}
		});
	}
	
	/**
	 * 执行查询，将每行的结果保存到一个Map对象中，然后将所有Map对象保存到List中
	 * 
	 * zhengxingmiao Jun 17, 2015 4:46:48 PM
	 * @param finder
	 * @return
	 */
	public List<Map<String, Object>> queryForList(Finder finder) {
		// 调用自定义方法
		return getMapListHandler(finder.getSql(), finder.getParams());
		//return jdbc.queryForList(finder.getSql(), finder.getParams());
	}
	
	/**
	 * 执行查询，将每行的结果保存到一个Map对象中，然后将所有Map对象保存到List中
	 * 
	 * zhengxingmiao Apr 21, 2014 11:19:39 AM
	 * @param finder
	 * @param page
	 * @return
	 */
	public List<Map<String, Object>> queryForList(Finder finder, Page page) {
		String pageSql = getPageSql(page, finder);
		if (pageSql == null){
			return null;
		}
		finder.setPageSql(pageSql);
		//return jdbc.queryForList(pageSql, finder.getParams());
		return getMapListHandler(pageSql, finder.getParams());
	}
	
	/**
	 * 查询某一条记录，并封装成Map对象
	 * 
	 * zhengxingmiao Apr 21, 2014 10:18:23 AM
	 * @param finder
	 * @return
	 */
	public Map<String, Object> queryForMap(Finder finder) {
		Map<String, Object> map = null;
		try {
			map = (Map<String, Object>) jdbc.query(finder.getSql(), finder.getParams(), new ResultSetExtractor(){
				@Override
				public Object extractData(ResultSet rs) throws SQLException,DataAccessException{
					if (rs.next()) {
						HashMap<String, Object> hm = new HashMap<String, Object>();
						ResultSetMetaData rsmd = rs.getMetaData();
						int cols = rsmd.getColumnCount();
						for (int i = 1; i <= cols; i++) {
							//System.out.println(rsmd.getColumnName(i));
							//System.out.println(rs.getString(i));
							hm.put(rsmd.getColumnName(i), getNotNullStringValue(rs.getString(i), ""));
						}
						return hm;
					}
					return null;
				}
			});
		} catch (EmptyResultDataAccessException e) {
			map = null;
		}
		return map;
	}
	
	
	
	
	//////////////////封装Beadn对象,然会存储到List操作 //////////////////////
	
	/**
	 * 执行查询，将每行的结果保存到Bean中，然后将所有Bean保存到List中 
	 * 
	 * zhengxingmiao Jun 17, 2015 4:47:35 PM
	 * @param <T>
	 * @param finder
	 * @param clazz
	 * @return
	 */
	public <T> List<T> queryForList(Finder finder, Class<T> clazz) {
		return queryForList(finder, clazz, null);
	}
	
	/**
	 * 执行查询，将每行的结果保存到Bean中，然后将所有Bean保存到List中 
	 * 
	 * zhengxingmiao Jun 17, 2015 4:48:00 PM
	 * @param <T>
	 * @param finder
	 * @param clazz
	 * @param page
	 * @return
	 */
	public <T> List<T> queryForList(Finder finder, Class<T> clazz, Page page) {
		String pageSql = getPageSql(page, finder);
		if (pageSql == null){
			return null;  
		}
		finder.setPageSql(pageSql);
		if (getDialect().isRowNumber()) {
			return jdbc.query(pageSql, finder.getParams(),BeanPropertyRowMapper.newInstance(clazz));
		} else {
			return jdbc.queryForList(pageSql, finder.getParams(), clazz);
			//ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
			//return (List<T>) list;
		}
	}
	
	/**
	 * 查询某一条记录，并封装成对象
	 * 
	 * zhengxingmiao Apr 21, 2014 10:17:11 AM
	 * @param <T>
	 * @param lclazzazz
	 * @param finder
	 * @return
	 */
	public <T> T queryForObject(Finder finder, Class<T> clazz){
		T t = null;
		try {
			if (isBaseType(clazz)) {
				t = (T) jdbc.queryForObject(finder.getSql(), finder.getParams(), clazz);
			} else {
				t = jdbc.queryForObject(finder.getSql(), finder.getParams(), BeanPropertyRowMapper.newInstance(clazz));
			}
		} catch (EmptyResultDataAccessException e) {
			t = null;
		}
		return t;
	}
	
	
	
	
	
	
	
	
	
	
}
