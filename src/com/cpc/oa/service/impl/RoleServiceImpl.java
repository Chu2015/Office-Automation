package com.cpc.oa.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cpc.oa.dao.RoleDao;
import com.cpc.oa.dao.impl.RoleDaoImpl;
import com.cpc.oa.domain.Role;
import com.cpc.oa.service.RoleService;

@Service
@Transactional
public class RoleServiceImpl implements RoleService{

	private RoleDao roleDao;
	public RoleDao getRoleDao() {
		return roleDao;
	}
	@Resource
	public void setRoleDao(RoleDao roleDao) {
		this.roleDao = roleDao;
	}
	
	@Override
	public List<Role> list() {
		return roleDao.findAll();
	}

	@Override
	public void delete(Long id) {
		System.out.println(id);
		roleDao.delete(id);
	}
	@Override
	public void save(Role role) {
		roleDao.save(role);
	}
	@Override
	public Role getById(Long id) {
		return roleDao.findById(id);
	}
	@Override
	public void edit(Role role) {
		roleDao.update(role);
	}
	
}
