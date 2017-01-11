package com.cpc.oa.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;

import com.cpc.oa.dao.DepartmentDao;
import com.cpc.oa.domain.Department;
import com.cpc.oa.service.DepartmentService;

@Service
public class DepartmentServiceImpl implements DepartmentService {
	@Resource
	private DepartmentDao daoimpl;

	
	@Resource
	private SessionFactory sessionfactory;
	@Override
	public List<Department> list() {
		return daoimpl.findAll();
	}

	@Override
	public void delete(long id) {
		daoimpl.delete(id);
	}

	@Override
	public void add(Department department) {
		daoimpl.save(department);
	}

	@Override
	public void update(Department department) {
		daoimpl.update(department);
	}

	public DepartmentDao getDaoimpl() {
		return daoimpl;
	}

	public void setDaoimpl(DepartmentDao daoimpl) {
		this.daoimpl = daoimpl;
	}

	@Override
	public Department getById(long id) {
		return daoimpl.findById(id);	
	}
	
	public SessionFactory getSessionfactory() {
		return sessionfactory;
	}

	public void setSessionfactory(SessionFactory sessionfactory) {
		this.sessionfactory = sessionfactory;
	}
}
