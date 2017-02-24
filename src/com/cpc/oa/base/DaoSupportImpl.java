package com.cpc.oa.base;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.springframework.transaction.annotation.Transactional;

import com.cpc.oa.domain.PageBean;
import com.cpc.oa.domain.Reply;
import com.cpc.oa.util.QueryHelper;

import org.hibernate.Query;
@Transactional
@SuppressWarnings("unchecked")
public abstract class DaoSupportImpl<T> implements DaoSupport<T>{
	
	private SessionFactory sessionFactory;
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	@Resource(name="sessionFactory")
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	private Class<T> clazz;
	/**
	 * 使用反射得到子类的真实类型
	 * @return
	 */
	public DaoSupportImpl(){
		 ParameterizedType type= (ParameterizedType) this.getClass().getGenericSuperclass();
		 this.clazz = (Class<T>) type.getActualTypeArguments()[0];
	}
	
	protected Session getSession(){
		return sessionFactory.getCurrentSession();
	}
	
	@Override
	public void save(T Entity) {
		getSession().save(Entity);
	}

	
	@Override
	public void delete(Long id) {
		Object object = findById(id);
		if(object!=null){
			getSession().delete(object);
		}
	}

	@Override
	public void update(T t) {
		getSession().update(t);
	}

	@Override
	public T findById(Long id) {
		if (id == null) {
			return null;
		}else{
			return (T) getSession().get(clazz, id);
		}
	}

	@Override
	public List<T> findByIds(Long[] ids) {
		if(ids==null || ids.length==0){
			return Collections.EMPTY_LIST;
		}else{
			return getSession().createQuery(
					"from "+clazz.getSimpleName()+" where id in (:ids)")
					.setParameterList("ids", ids)
					.list();
		}
	}
	@Override
	public List<T> findAll() {
		return getSession().createQuery("from "+clazz.getSimpleName()).list();
	}
	
	@Deprecated
	@Override
	public PageBean getPageBean(int pageNum, int pageSize, String hql,
			List<Object> parameters) {
		Query query =  getSession().createQuery(hql);
		if(parameters !=null){
			for(int i=0;i<parameters.size();i++){
				query.setParameter(i, parameters.get(i));
			}
		}
		
		 query = query.setFirstResult((pageNum-1)*pageSize);
		 query = query.setMaxResults(pageSize);
		 List<Object> recordList =  query.list();
		
		 Query query2 =  getSession().createQuery("select count(*)"+hql);
			if(parameters !=null){
				for(int i=0;i<parameters.size();i++){
					query2.setParameter(i, parameters.get(i));
				}
			}

		 Long pageCount = (Long) query2.uniqueResult();
		 return new PageBean( recordList, pageNum, pageCount.intValue(), pageSize);
	}

	@Override
	public PageBean getPageBean(int pageNum, int pageSize,
			QueryHelper queryHelper) {
		Query listQuery =  getSession().createQuery(queryHelper.getListQueryHql());
		List<Object> parameters = queryHelper.getParameters();
		if(parameters !=null){
			for(int i=0;i<parameters.size();i++){
				listQuery.setParameter(i, parameters.get(i));
			}
		}
		
		listQuery = listQuery.setFirstResult((pageNum-1)*pageSize);
		listQuery = listQuery.setMaxResults(pageSize);
		List<Object> recordList =  listQuery.list();
		
		 Query countQurey =  getSession().createQuery(queryHelper.getCountQueryHql());
			if(parameters !=null){
				for(int i=0;i<parameters.size();i++){
					countQurey.setParameter(i, parameters.get(i));
				}
			}

		 Long pageCount = (Long) countQurey.uniqueResult();
		 return new PageBean( recordList, pageNum, pageCount.intValue(), pageSize);
	}
}
