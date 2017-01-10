package com.cpc.oa.dao;

import com.cpc.oa.base.BaseDao;
import com.cpc.oa.domain.User;

public interface UserDao extends BaseDao<User> {

	public void findByPartName(String name);
}
