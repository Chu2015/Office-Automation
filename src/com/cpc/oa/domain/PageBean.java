package com.cpc.oa.domain;

import java.util.List;

public class PageBean {
	private List recordList;//	本页的数据列表
	private int currentPage	;//	当前页
	private int pageSize;//		每页显示多少条
	private int recordCount ;//	总记录数
	
	private int pageCount;	//总页数
	private int beginPageIndex ;//页码列表的开始索引
	private int endPageIndex;//	页码列表的结束索引
	
	public PageBean(List recordList,int currentPage,int recordCount, int pageSize){
		this.recordList = recordList;
		this.currentPage = currentPage;
		this.recordCount = recordCount;
		this.pageSize = pageSize;
		
		this.pageCount = (recordCount + pageSize -1)/ pageSize;
		if (pageCount <= 10) {
			beginPageIndex = 1;
			endPageIndex = pageCount;
		}else{
			if(currentPage<=4){
				this.beginPageIndex = 1;
				this.endPageIndex = 10;
			}else if(currentPage>pageCount-5){
				this.beginPageIndex = pageCount-9;
				this.endPageIndex = pageCount;
			}else{
				this.beginPageIndex = currentPage - 4 ;
				this.endPageIndex = currentPage+5;
			}
		}
	}

	public List getRecordList() {
		return recordList;
	}

	public void setRecordList(List recordList) {
		this.recordList = recordList;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getRecordCount() {
		return recordCount;
	}

	public void setRecordCount(int recordCount) {
		this.recordCount = recordCount;
	}

	public int getBeginPageIndex() {
		return beginPageIndex;
	}

	public void setBeginPageIndex(int beginPageIndex) {
		this.beginPageIndex = beginPageIndex;
	}

	public int getEndPageIndex() {
		return endPageIndex;
	}

	public void setEndPageIndex(int endPageIndex) {
		this.endPageIndex = endPageIndex;
	}
	
	
}
