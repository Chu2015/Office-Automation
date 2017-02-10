package com.cpc.oa.service;

import java.util.List;

import com.cpc.oa.base.DaoSupport;
import com.cpc.oa.domain.Reply;
import com.cpc.oa.domain.Topic;

public interface ReplyService extends DaoSupport<Reply>{

	List<Reply> findByTopic(Topic topic);

}
