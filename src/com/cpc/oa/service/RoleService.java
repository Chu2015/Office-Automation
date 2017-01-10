package com.cpc.oa.service;

import java.util.List;

import com.cpc.oa.domain.Role;

public interface RoleService {

	public List list();
	
	public void delete(long id);

	public void save(Role role);

	public Role getById(long id);

	public void edit(Role role);
}
