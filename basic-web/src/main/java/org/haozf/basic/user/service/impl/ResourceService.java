package org.haozf.basic.user.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.haozf.basic.user.dao.IResourceDao;
import org.haozf.basic.user.model.Resource;
import org.haozf.basic.user.service.IResourceService;
import org.springframework.stereotype.Service;

@Service("resourceService")
public class ResourceService implements IResourceService {
	@Inject
	private IResourceDao resourceDao;
	public void add(Resource res) {
		resourceDao.add(res);
	}

	public void update(Resource res) {
		resourceDao.update(res);
	}

	public void delete(int id) {
		resourceDao.delete(id);
	}

	public Resource load(int id) {
		return resourceDao.load(id);
	}

	public List<Resource> listResource() {
		return resourceDao.listResource();
	}
}
