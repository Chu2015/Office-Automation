package com.cpc.oa.base;

import java.util.List;

import com.cpc.oa.domain.PageBean;
import com.cpc.oa.domain.Topic;
import com.cpc.oa.util.QueryHelper;


public interface DaoSupport<T> {
	
	public void save(T t);
	
	public void delete(Long id);
	
	public void update(T t);	
	
	public T findById(Long id);
	
	public List<T> findByIds(Long[] id);
	
	public List<T> findAll();

	PageBean getPageBean(int pageNum, int pageSize,String hql, List<Object> parameters); 
	
	PageBean getPageBean(int pageNum, int pageSize, QueryHelper queryHelper);
}
