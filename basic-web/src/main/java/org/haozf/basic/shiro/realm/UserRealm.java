package org.haozf.basic.shiro.realm;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.haozf.basic.user.model.Resource;
import org.haozf.basic.user.model.User;
import org.haozf.basic.user.service.IUserService;

public class UserRealm extends AuthorizingRealm {

	@javax.annotation.Resource
	private IUserService userService;
	
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
		User user = ((User)principals.getPrimaryPrincipal());
		int uid = user.getId();
		System.out.println(user.getId()+","+user.getNickname());
		List<String> roles = userService.listRoleSnByUser(uid);
		List<Resource> reses = userService.listAllResource(uid);
		List<String> permissions = new ArrayList<String>();
		for(Resource r:reses) {
			permissions.add(r.getUrl());
		}
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		info.setRoles(new HashSet<String>(roles));
		info.setStringPermissions(new HashSet<String>(permissions));
		return info;
	}
	

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {
		String username = token.getPrincipal().toString();
		String password = new String((char[])token.getCredentials());
		User user = userService.login(username, password);
		SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, user.getPassword(), this.getName());
		info.setCredentialsSalt(ByteSource.Util.bytes(user.getUsername()));
		return info;
	}


	@Override
	protected void clearCachedAuthorizationInfo(PrincipalCollection principals) {
		//System.out.println("清除授权的缓存");
//		Cache c = this.getAuthorizationCache();
//		Set<Object> keys = c.keys();
//		for(Object o:keys) {
//			System.out.println("授权缓存:"+o+"-----"+c.get(o)+"----------");
//		}
		super.clearCachedAuthorizationInfo(principals);
	}

	@Override
	protected void clearCachedAuthenticationInfo(PrincipalCollection principals) {
		//System.out.println("清除认证的缓存");
//		Cache c = this.getAuthenticationCache();
//		Set<Object> keys = c.keys();
//		for(Object o:keys) {
//			System.out.println("认证缓存:"+o+"----------"+c.get(o)+"----------");
//		}
		User user = ((User)principals.getPrimaryPrincipal());
		SimplePrincipalCollection spc = new SimplePrincipalCollection(user.getUsername(), getName());
		super.clearCachedAuthenticationInfo(spc);
	}
	
	

}
