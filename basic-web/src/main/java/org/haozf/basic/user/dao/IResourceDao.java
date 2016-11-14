package org.haozf.basic.user.dao;

import java.util.List;

import org.haozf.basic.dao.IBaseDao;
import org.haozf.basic.user.model.Resource;


public interface IResourceDao extends IBaseDao<Resource>{
	public List<Resource> listResource();
}
