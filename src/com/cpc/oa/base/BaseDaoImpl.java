package com.cpc.oa.base;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.Query;

import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;

@SuppressWarnings("unchecked")
public abstract class BaseDaoImpl<T> implements BaseDao<T>{
	
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
	public BaseDaoImpl(){
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
	public void delete(long id) {
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
	public List<T> findByIds(long[] ids) {
		return getSession().createQuery("from "+clazz.getSimpleName()+" where id in (:ids)").setParameter("ids", ids).list();
	}

	@Override
	public List<T> findAll() {
		return getSession().createQuery("from "+clazz.getSimpleName()).list();
	}

}
