package com.cpc.oa.base;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.Query;

import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.springframework.transaction.annotation.Transactional;

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

}
