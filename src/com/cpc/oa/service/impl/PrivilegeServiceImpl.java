package com.cpc.oa.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cpc.oa.base.DaoSupport;
import com.cpc.oa.base.DaoSupportImpl;
import com.cpc.oa.domain.Privilege;
import com.cpc.oa.service.PrivilegeService;

@Service
@Transactional
public class PrivilegeServiceImpl extends DaoSupportImpl<Privilege> implements PrivilegeService{

}