package com.cpc.oa.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cpc.oa.base.DaoSupport;
import com.cpc.oa.base.DaoSupportImpl;
import com.cpc.oa.domain.Forum;
import com.cpc.oa.service.ForumService;

@Service
@Transactional
public class ForumServiceImpl extends DaoSupportImpl<Forum> implements ForumService{
	
	
	
	@Override
	public List<Forum> findAll() {
		return getSession().createQuery("from Forum f order by f.position").list();
	}

	@Override
	public void save(Forum entity) {
		
		super.save(entity);
		int position = entity.getId().intValue();
		entity.setPosition(position);
		
	}

	@Override
	public void moveUp(Long id) {
		Forum forum1 = findById(id);
		int position = forum1.getPosition();
		Forum forum2 = (Forum) getSession().createQuery("from Forum f where f.position<? order by f.position desc")
		.setParameter(0, position)
		.setFirstResult(0)
		.setMaxResults(1).uniqueResult();
		if(forum2==null){
			return;
		}
		forum1.setPosition(forum2.getPosition());
		forum2.setPosition(position);
	}

	@Override
	public void moveDown(Long id) {
		Forum forum1 = findById(id);
		int position = forum1.getPosition();
		Forum forum2 = (Forum) getSession().createQuery("from Forum f where f.position>? order by f.position")
		.setParameter(0, position)
		.setFirstResult(0)
		.setMaxResults(1).uniqueResult();
		
		if(forum2==null){
			return;
		}
		forum1.setPosition(forum2.getPosition());
		forum2.setPosition(position);
		
	}

}
