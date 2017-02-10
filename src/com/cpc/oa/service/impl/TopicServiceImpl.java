package com.cpc.oa.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cpc.oa.base.DaoSupportImpl;
import com.cpc.oa.domain.Forum;
import com.cpc.oa.domain.Topic;
import com.cpc.oa.service.TopicService;

@Service
@Transactional
public class TopicServiceImpl extends DaoSupportImpl<Topic> implements TopicService{

	//如何排序？？
	@Override
	public List<Topic> findByForum(Forum forum) {
		return getSession().createQuery("from Topic t where t.forum=? order by(case t.type when 2 then 2 else 0 end) DESC , t.lastUpdateTime DESC").setParameter(0, forum).list();
	}

	@Override
	public void save(Topic topic) {
		//设置属性并保存
		topic.setType(0);
		topic.setLastReply(null);
		topic.setLastUpdateTime(topic.getPostTime());
		topic.setReplyCount(0);
		getSession().save(topic);
		
		//特殊属性的维护
		Forum forum = topic.getForum();
		forum.setTopicCount(forum.getTopicCount()+1);
		forum.setArticleCount(forum.getArticleCount()+1);
		forum.setLastTopic(topic);
		getSession().update(forum);
		
	}
	
	
}
