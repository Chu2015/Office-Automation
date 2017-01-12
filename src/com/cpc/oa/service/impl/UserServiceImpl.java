package com.cpc.oa.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cpc.oa.base.DaoSupportImpl;
import com.cpc.oa.domain.User;
import com.cpc.oa.service.UserService;

@Service
@Transactional
public class UserServiceImpl extends DaoSupportImpl<User> implements UserService{

}
