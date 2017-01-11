package com.cpc.oa.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
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
	public void delete(Long id) {
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
	public Department getById(Long id) {
		return daoimpl.findById(id);	
	}

	@Override
	public List<Department> findTopList() {
		Session session = sessionfactory.getCurrentSession();
		List<Department> list=session.createQuery("from Department d where d.parent is null").list();
		return list;
	}

	@Override
	public List<Department> findChildren(Long parentId) {
		Session session = sessionfactory.getCurrentSession();
		List<Department> list = session.createQuery("from Department d where d.parent.id=?").setParameter(0, parentId).list();
		return list;
	}
	
/*	public SessionFactory getSessionfactory() {
		return sessionfactory;
	}

	public void setSessionfactory(SessionFactory sessionfactory) {
		this.sessionfactory = sessionfactory;
	}*/
}
