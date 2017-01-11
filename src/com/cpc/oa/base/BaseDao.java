package com.cpc.oa.base;

import java.util.List;


public interface BaseDao<T> {
	
	public void save(T t);
	
	public void delete(long id);
	
	public void update(T t);	
	
	public T findById(Long id);
	
	public List<T> findByIds(long[] id);
	
	public List<T> findAll();

}
