package com.cpc.oa.base;

import java.util.List;


public interface DaoSupport<T> {
	
	public void save(T t);
	
	public void delete(Long id);
	
	public void update(T t);	
	
	public T findById(Long id);
	
	public List<T> findByIds(Long[] id);
	
	public List<T> findAll();

}
