package common.base;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.haozf.basic.model.Pager;
import org.haozf.basic.model.SystemContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

public class AbstractJdbcDao<T> implements IBaseDao<T> {
	@Resource
	private NamedParameterJdbcTemplate jdbcTemplate;

	@Resource
	private DataSource dataSource;
	
	private String sql;

	protected final NamedParameterJdbcTemplate getJdbcTemplate() {
		return this.jdbcTemplate;
	}

	protected final DataSource getDataSource() {
		return this.dataSource;
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

	@Override
	public int add(String sql, T t) {
		//加上KeyHolder这个参数可以得到添加后主键的值 
		KeyHolder keyholder=new GeneratedKeyHolder(); 
		SqlParameterSource paramSource = new BeanPropertySqlParameterSource(t);
		getJdbcTemplate().update(sql, paramSource,keyholder);
		int k=keyholder.getKey().intValue(); 
		return k;
	}

	@Override
	public int update(String sql, T t) {
		SqlParameterSource paramSource = new BeanPropertySqlParameterSource(t);
		return getJdbcTemplate().update(sql, paramSource);
	}

	@Override
	public void delete(String sql, T t) {
		SqlParameterSource paramSource = new BeanPropertySqlParameterSource(t);
		getJdbcTemplate().update(sql, paramSource);
	}

	@Override
	public T load(String sql, T t) {
		RowMapper rowMapper = new BeanPropertyRowMapper(getClz());
		SqlParameterSource paramSource = new BeanPropertySqlParameterSource(t);
		List<T> list = getJdbcTemplate().query(sql, paramSource, rowMapper);
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}
	
	public Object get(String sql, Object t) {
		RowMapper rowMapper = new BeanPropertyRowMapper(t.getClass());
		SqlParameterSource paramSource = new BeanPropertySqlParameterSource(t);
		List<Object> list = getJdbcTemplate().query(sql, paramSource, rowMapper);
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}
	
	public T loadByID(String sql,String paramName,String value){
		RowMapper rowMapper = new BeanPropertyRowMapper(getClz());
		SqlParameterSource paramSource = new MapSqlParameterSource().addValue(paramName, value);
		List<T> list = getJdbcTemplate().query(sql, paramSource, rowMapper);
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}
	
	public int count(String sql,SqlParameterSource paramSource){
		int i = getJdbcTemplate().queryForObject(sql, paramSource,Integer.class);
		return i;
	}
	
	public <N extends Object> Pager<N> findForPager(String sql,String paramName,String value) {
        Pager<N> pages = new Pager<N>();
        try {
            Integer pageSize = SystemContext.getPageSize();
            Integer pageOffset = SystemContext.getPageOffset();
            if (pageOffset == null || pageOffset < 0)
                pageOffset = Pager.DEFAULT_PAGE_OFFSET;
            if (pageSize == null || pageSize < 0)
                pageSize = Pager.DEFAULT_PAGE_SIZE;
            pages.setOffset(pageOffset);
            pages.setSize(pageSize);
            
            StringBuffer paginationSQL = new StringBuffer();
            paginationSQL.append(sql);
            paginationSQL.append(" limit " + (pageOffset - 1) * pageSize + "," + SystemContext.getPageSize());
            //装入结果集
            RowMapper rowMapper = new BeanPropertyRowMapper(getClz());
            SqlParameterSource paramSource = new MapSqlParameterSource().addValue(paramName, value);
            pages.setDatas(getJdbcTemplate().query(sql, paramSource, rowMapper));
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            SystemContext.removePageOffset();
            SystemContext.removePageSize();
        }
        return pages;
    }

	public String getSql() {
		return sql;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}
}
