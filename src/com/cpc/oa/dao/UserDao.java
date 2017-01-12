package com.cpc.oa.dao;

import com.cpc.oa.base.DaoSupport; 
import com.cpc.oa.domain.User;

@Deprecated
public interface UserDao extends DaoSupport<User> {

	public void findByPartName(String name);
}
