package com.cpc.oa.service;

import com.cpc.oa.base.DaoSupport;
import com.cpc.oa.domain.Forum;

public interface ForumService extends DaoSupport<Forum>{

	void moveUp(Long long1);

	void moveDown(Long long1);

}
