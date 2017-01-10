package com.cpc.oa.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cpc.oa.domain.Department;

@Service
@Transactional
public interface DepartmentService {

	List<Department> list();

	void delete(long id);

	void add(Department department);

	void update(Department department);

	Department getById(long id);

}
