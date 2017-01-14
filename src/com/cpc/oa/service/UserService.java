package com.cpc.oa.service;

import com.cpc.oa.base.DaoSupport;
import com.cpc.oa.domain.User;

public interface UserService extends DaoSupport<User>{

	User findByLoginNameAndPassword(String loginName, String password);

}
