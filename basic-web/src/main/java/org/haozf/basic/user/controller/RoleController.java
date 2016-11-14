package org.haozf.basic.user.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;

import org.haozf.basic.user.model.Resource;
import org.haozf.basic.user.model.Role;
import org.haozf.basic.user.service.IResourceService;
import org.haozf.basic.user.service.IRoleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/admin/role")
public class RoleController {
	
	@Inject
	private IRoleService roleService;
	@Inject
	private IResourceService resourceService;
	
	@RequestMapping("/list")
	public String list(Model model) {
		model.addAttribute("roles", roleService.list());
		return "role/list";
	}
	
	@RequestMapping(value="/add",method=RequestMethod.GET)
	public String add(Model model) {
		model.addAttribute("role", new Role());
		return "role/add";
	}
	
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public String add(Role role,Model model) {
		roleService.add(role);
		return "redirect:/admin/role/list";
	}
	
	@RequestMapping(value="/update/{id}",method=RequestMethod.GET)
	public String update(Model model,@PathVariable int id) {
		model.addAttribute("role", roleService.load(id));
		return "role/update";
	}
	
	@RequestMapping(value="/update/{id}",method=RequestMethod.POST)
	public String add(@PathVariable int id,Role role,Model model) {
		Role tr = roleService.load(id);
		tr.setName(role.getName());
		tr.setSn(role.getSn());
		roleService.update(tr);
		return "redirect:/admin/role/list";
	}
	
	@RequestMapping("/listRes/{id}")
	public String listRes(Model model,@PathVariable int id) {
		List<Resource> hasRes = roleService.listRoleResource(id);
		List<Integer> hrIds = new ArrayList<Integer>();
		for(Resource r:hasRes) {
			hrIds.add(r.getId());
		}
		model.addAttribute("rids", hrIds);
		model.addAttribute("reses", resourceService.listResource());
		model.addAttribute("role", roleService.load(id));
		return "role/res";
	}
	
	@RequestMapping(value="/setRes",method=RequestMethod.POST)
	public void setRes(int resId,int roleId,int c,HttpServletResponse resp) throws IOException {
		if(c==0) {
			roleService.deleteRoleResource(roleId, resId);
		} else {
			roleService.addRoleResource(roleId, resId);
		}
		resp.getWriter().println("ok");
	}
	
}
