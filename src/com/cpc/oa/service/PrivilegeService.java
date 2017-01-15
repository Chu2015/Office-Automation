package com.cpc.oa.service;

import java.util.List;

import com.cpc.oa.base.DaoSupport;
import com.cpc.oa.domain.Privilege;

public interface PrivilegeService extends DaoSupport<Privilege> {

	List<Privilege> findTopList();

	List<Privilege> findAllWithUrl();

}
