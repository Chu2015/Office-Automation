package com.cpc.oa.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cpc.oa.base.DaoSupportImpl;
import com.cpc.oa.domain.Forum;
import com.cpc.oa.domain.Reply;
import com.cpc.oa.domain.Topic;
import com.cpc.oa.service.ReplyService;

@Service
@Transactional
public class ReplyServiceImpl extends DaoSupportImpl<Reply> implements ReplyService{

	@Override
	public List<Reply> findByTopic(Topic topic) {
		
		return getSession().createQuery("from Reply r where r.topic = ? order by r.postTime").setParameter(0, topic).list();
	}

	@Override
	public void save(Reply reply) {
		// 1，保存
		getSession().save(reply);
		
		// 2，维护相关的信息
		Topic topic = reply.getTopic();
		Forum forum = topic.getForum();
		
		forum.setArticleCount(forum.getArticleCount()+1);
		topic.setReplyCount(topic.getReplyCount()+1);
		topic.setLastReply(reply);
		topic.setLastUpdateTime(reply.getPostTime());
		
		getSession().update(forum);
		getSession().update(topic);
	}
	
	

}
