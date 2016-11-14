package org.haozf.basic.dao;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

import javax.inject.Inject;

import org.haozf.basic.model.Pager;
import org.haozf.basic.model.SystemContext;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class AbstractHibernateBaseDao {
    @Inject
    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    private Session currentSession;

    public Session getCurrentSession() {
        return currentSession;
    }

    protected Session getSession() {
        currentSession = sessionFactory.getCurrentSession();
        return currentSession;
    }
    
    /**
     * 初始化sql条件
     * 
     * @param sql
     * @return
     */
    protected String initSort(String sql) {
        String order = SystemContext.getOrder();
        String sort = SystemContext.getSort();
        if (sort != null && !"".equals(sort.trim())) {
            sql += " order by " + sort;
            if (!"desc".equals(order))
                sql += " asc";
            else
                sql += " desc";
        }
        return sql;
    }

    /**
     * 初始化参数别名条件
     * 
     * @param query
     * @param alias
     */
    @SuppressWarnings("rawtypes")
    protected void setAliasParameter(Query query, Map<String, Object> alias) {
        if (alias != null) {
            Set<String> keys = alias.keySet();
            for (String key : keys) {
                Object val = alias.get(key);
                if (val instanceof Collection) {
                    // 查询条件是列表
                    query.setParameterList(key, (Collection) val);
                } else {
                    query.setParameter(key, val);
                }
            }
        }
    }

    /**
     * 初始化参数条件
     * 
     * @param query
     * @param args
     */
    protected void setParameter(Query query, Object[] args) {
        if (args != null && args.length > 0) {
            int index = 0;
            for (Object arg : args) {
                query.setParameter(index++, arg);
            }
        }
    }

    /**
     * 初始化分页条件
     * 
     * @param query
     * @param pages
     */
    @SuppressWarnings("rawtypes")
    protected void setPagers(Query query, Pager pages) {
        Integer pageSize = SystemContext.getPageSize();
        Integer pageOffset = SystemContext.getPageOffset();
        if (pageOffset == null || pageOffset < 0)
            pageOffset = Pager.DEFAULT_PAGE_OFFSET;
        if (pageSize == null || pageSize < 0)
            pageSize = Pager.DEFAULT_PAGE_SIZE;
        pages.setOffset(pageOffset);
        pages.setSize(pageSize);
        query.setFirstResult((pageOffset - 1) * pageSize).setMaxResults(pageSize);
    }

    /**
     * 获取页数
     * 
     * @param sql
     * @return
     */
    protected String getCount(String sql) {
        String e = sql.substring(sql.indexOf("from"));
        String c = "select count(*) " + e;
        return c;
    }
}
