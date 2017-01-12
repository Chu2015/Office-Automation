package com.cpc.oa.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cpc.oa.base.DaoSupport;
import com.cpc.oa.domain.Department;

@Service
@Transactional
public interface DepartmentService extends DaoSupport<Department>{

//	List<Department> list();
//
//	void delete(Long id);
//
//	void add(Department department);
//
//	void update(Department department);
//
//	Department getById(Long id);

	List<Department> findTopList();

	List<Department> findChildren(Long parentId);

}
