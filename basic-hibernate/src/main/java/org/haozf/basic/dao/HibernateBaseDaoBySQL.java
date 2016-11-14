package org.haozf.basic.dao;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import org.haozf.basic.model.Pager;
import org.haozf.basic.model.SystemContext;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;

public class HibernateBaseDaoBySQL<T> extends HibernateBaseDao<T>{
    /**
     * 通过sql、实体查询
     * 
     * @param sql
     * @param clz
     * @return
     */
    public Object queryObject(String sql, Class<?> clz) {
        return this.queryObject(sql, null, clz);
    }

    /**
     * 通过sql、参数数组、实体查询
     * 
     * @param sql
     * @param args
     * @param clz
     * @return
     */
    public Object queryObject(String sql, Object[] args, Class<?> clz) {
        return this.queryObject(sql, args, null, clz);
    }

    /**
     * 通过sql、参数、实体查询
     * 
     * @param sql
     * @param arg
     * @param clz
     * @return
     */
    public Object queryObject(String sql, Object arg, Class<?> clz) {
        return this.queryObject(sql, new Object[] { arg }, clz);
    }

    /**
     * 通过sql、参数别名、实体查询
     * 
     * @param sql
     * @param alias
     * @param clz
     * @return
     */
    public Object queryObjectByAlias(String sql, Map<String, Object> alias, Class<?> clz) {
        return this.queryObject(sql, null, alias, clz);
    }

    /**
     * 通过sql、参数数组、参数别名、实体查询
     * 
     * @param sql
     * @param args
     * @param alias
     * @param clz
     * @return
     */
    public Object queryObject(String sql, Object[] args, Map<String, Object> alias, Class<?> clz) {
        SQLQuery query = getSession().createSQLQuery(sql);
        setAliasParameter(query, alias);
        setParameter(query, args);
        query.addEntity(clz);
        return query.uniqueResult();
    }
    
    
    /**
     * 通过sql、参数数组、实体、是否含有实体获取分页信息
     * 
     * @param sql
     * @param args
     * @param clz
     * @param hasEntity
     * @return
     */
    public <N extends Object> Pager<N> findBySql(String sql, Object[] args, Class<?> clz, boolean hasEntity) {
        return this.findBySql(sql, args, null, clz, hasEntity);
    }

    /**
     * 通过sql、参数、实体、是否含有实体获取分页信息
     * 
     * @param sql
     * @param arg
     * @param clz
     * @param hasEntity
     * @return
     */
    public <N extends Object> Pager<N> findBySql(String sql, Object arg, Class<?> clz, boolean hasEntity) {
        return this.findBySql(sql, new Object[] { arg }, clz, hasEntity);
    }

    /**
     * 通过sql、实体、是否含有实体获取分页信息
     * 
     * @param sql
     * @param clz
     * @param hasEntity
     * @return
     */
    public <N extends Object> Pager<N> findBySql(String sql, Class<?> clz, boolean hasEntity) {
        return this.findBySql(sql, null, clz, hasEntity);
    }

    /**
     * 通过sql、参数数组、参数别名、实体、是否含有实体获取分页信息
     * 
     * @param sql
     * @param args
     * @param alias
     * @param clz
     * @param hasEntity
     * @return
     */
    public <N extends Object> Pager<N> findBySql(String sql, Object[] args, Map<String, Object> alias, Class<?> clz, boolean hasEntity) {
        Pager<N> pages = new Pager<N>();
        try {
            sql = initSort(sql);
            String cq = getCount(sql);
            SQLQuery sq = getSession().createSQLQuery(sql);
            SQLQuery cquery = getSession().createSQLQuery(cq);
            setAliasParameter(sq, alias);
            setAliasParameter(cquery, alias);
            setParameter(sq, args);
            setParameter(cquery, args);
            setPagers(sq, pages);
            if (hasEntity) {
                sq.addEntity(clz);
            } else {
                sq.setResultTransformer(Transformers.aliasToBean(clz));
            }
            List<N> datas = sq.list();
            pages.setDatas(datas);
            long total = ((BigInteger) cquery.uniqueResult()).longValue();
            pages.setTotal(total);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            SystemContext.removePageOffset();
            SystemContext.removePageSize();
        }
        return pages;
    }

    public <N extends Object> Pager<N> findByAliasSql(String sql, Map<String, Object> alias, Class<?> clz, boolean hasEntity) {
        return this.findBySql(sql, null, alias, clz, hasEntity);
    }
    
    /**
     * 通过sql、参数数组、实体、是否有实体类获取数据list集合
     * 
     * @param sql
     * @param args
     * @param clz
     * @param hasEntity
     * @return
     */
    public <N extends Object> List<N> listBySql(String sql, Object[] args, Class<?> clz, boolean hasEntity) {
        return this.listBySql(sql, args, null, clz, hasEntity);
    }

    /**
     * 通过sql、参数、实体、是否有实体类获取数据list集合
     * 
     * @param sql
     * @param arg
     * @param clz
     * @param hasEntity
     * @return
     */
    public <N extends Object> List<N> listBySql(String sql, Object arg, Class<?> clz, boolean hasEntity) {
        return this.listBySql(sql, new Object[] { arg }, clz, hasEntity);
    }

    /**
     * 通过sql、实体、是否有实体类获取数据list集合
     * 
     * @param sql
     * @param clz
     * @param hasEntity
     * @return
     */
    public <N extends Object> List<N> listBySql(String sql, Class<?> clz, boolean hasEntity) {
        return this.listBySql(sql, null, clz, hasEntity);
    }

    /**
     * 通过sql、参数数组、参数别名、实体、是否有实体类获取数据list集合
     * 
     * @param sql
     * @param args
     * @param alias
     * @param clz
     * @param hasEntity
     * @return
     */
    public <N extends Object> List<N> listBySql(String sql, Object[] args, Map<String, Object> alias, Class<?> clz, boolean hasEntity) {
        sql = initSort(sql);
        SQLQuery sq = getSession().createSQLQuery(sql);
        setAliasParameter(sq, alias);
        setParameter(sq, args);
        if (hasEntity) {
            sq.addEntity(clz);
        } else
            sq.setResultTransformer(Transformers.aliasToBean(clz));
        return sq.list();
    }

    /**
     * 通过sql、参数别名、实体、是否有实体类获取数据list集合
     * 
     * @param sql
     * @param alias
     * @param clz
     * @param hasEntity
     * @return
     */
    public <N extends Object> List<N> listByAliasSql(String sql, Map<String, Object> alias, Class<?> clz, boolean hasEntity) {
        return this.listBySql(sql, null, alias, clz, hasEntity);
    }
    
    /**
     * 通过sql执行更新语句
     * 
     * @param sql
     */
    public void updateBySql(String sql) {
        this.updateBySql(sql, null);
    }

    /**
     * 通过sql、参数执行更新语句
     * 
     * @param sql
     * @param arg
     */
    public void updateBySql(String sql, Object arg) {
        this.updateBySql(sql, new Object[] { arg });
    }

    /**
     * 通过sql、参数数组执行更新语句
     * 
     * @param sql
     * @param args
     */
    public void updateBySql(String sql, Object[] args) {
        Query query = getSession().createSQLQuery(sql);
        setParameter(query, args);
        query.executeUpdate();
    }
    
}
