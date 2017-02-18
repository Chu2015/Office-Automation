package com.cpc.oa.service;

import java.util.List;

import com.cpc.oa.base.DaoSupport;
import com.cpc.oa.domain.Forum;
import com.cpc.oa.domain.PageBean;
import com.cpc.oa.domain.Topic;

public interface TopicService extends DaoSupport<Topic>{

	/**
	 * 查指定板块中的所有主题，置顶帖在最上面，并按最后的更新排序，让新的状态在上面。
	 * @param forum
	 * @return
	 */
	List<Topic> findByForum(Forum forum);

	PageBean getPageBeanByForum(int pageNum, int pageSize, Forum forum);

}
