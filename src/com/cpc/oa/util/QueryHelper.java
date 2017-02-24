package com.cpc.oa.util;

import java.util.ArrayList;
import java.util.List;

import com.cpc.oa.base.DaoSupport;
import com.cpc.oa.domain.PageBean;
import com.opensymphony.xwork2.ActionContext;

public class QueryHelper {

	private String fromClause;
	private String whereClause = "";
	private String orderByClause = "";

	private List<Object> parameters = new ArrayList<Object>();
	
	public QueryHelper(Class clazz,String alias){
		fromClause = "from "+clazz.getSimpleName()+" "+alias;
	}

	public QueryHelper addCondition(String condition,Object... params){
		if(whereClause.length()==0){
			whereClause = " where "+condition;
		}else{
			whereClause += " and "+condition;
		}
		if(params != null){
			for(Object obj : params){
				parameters.add(obj);
			}
		}
		
		return this;
	}

	public QueryHelper addCondition(boolean test,String condition,Object... params){
		if(test){
			addCondition(condition,params);
		}
		return this;
	}

	public QueryHelper addOderProperty(String order,boolean asc){
		if(orderByClause.length()==0){
			orderByClause = " order by "+order+ (asc ? " ASC ":" DESC ");
			
		}else{
			orderByClause += " , "+order+ (asc ? " ASC ":" DESC ");
		}
		return this;
	}
	public QueryHelper addOderProperty(boolean test,String order,boolean asc){
		if(test){
			addOderProperty(order,asc);
		}
		return this;
	}
	
	public String getListQueryHql(){
		return fromClause+whereClause+orderByClause;
	}
	
	public String getCountQueryHql(){
		return "select count(*) "+fromClause+whereClause;
	}
	
	public List<Object> getParameters(){
		return parameters;
	}
	
	public void preparePageBean(DaoSupport<?> service,int pageNum,int pageSize){
		PageBean pageBean = service.getPageBean(pageNum, pageSize, this);
		ActionContext.getContext().getValueStack().push(pageBean);
	}
	
}
