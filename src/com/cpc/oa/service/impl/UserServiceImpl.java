package com.cpc.oa.service.impl;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cpc.oa.base.DaoSupportImpl;
import com.cpc.oa.domain.User;
import com.cpc.oa.service.UserService;

@Service
@Transactional
public class UserServiceImpl extends DaoSupportImpl<User> implements UserService{

	@Override
	public User findByLoginNameAndPassword(String loginName, String password) {
		String md5Digest = DigestUtils.md5Hex(password);
		User user = (User) getSession().createQuery("from User u where u.loginName=? and u.password=?")
					.setParameter(0, loginName).setParameter(1, md5Digest).uniqueResult();
		return user;
	}

}
