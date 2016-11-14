package org.haozf.basic.user.dao.impl;

import java.util.List;

import org.haozf.basic.dao.HibernateBaseDaoByHQL;
import org.haozf.basic.user.dao.IResourceDao;
import org.haozf.basic.user.model.Resource;
import org.springframework.stereotype.Repository;

@Repository("resourceDao")
public class ResourceDao extends HibernateBaseDaoByHQL<Resource> implements IResourceDao {
//	public List<Resource> listResource() {
//		String sql = "select * from t_resource";
//		return super.listBySql(sql, Resource.class, true);
//	}
	
	public List<Resource> listResource() {
		return super.listByHQL("from Resource");
	}
}
