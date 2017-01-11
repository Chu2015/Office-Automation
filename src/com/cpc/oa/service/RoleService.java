package com.cpc.oa.service;

import java.util.List;

import com.cpc.oa.domain.Role;

public interface RoleService {

	public List list();
	
	public void delete(Long id);

	public void save(Role role);

	public Role getById(Long id);

	public void edit(Role role);
}
