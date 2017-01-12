package com.cpc.oa.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.cpc.oa.domain.Department;

public class DepartmentTreeList {

	public static List<Department> getTreeList(List<Department> list) {
		List<Department> treeList = new ArrayList<Department>();
		walkDepartmentTree(list, "┣", treeList);
		return treeList;
	}
	
	public static List<Department> walkDepartmentTree(Collection<Department> list,String prefix,List<Department> treeList){
		for(Department top : list){
	    	Department d = new Department();
			d.setId(top.getId());
			d.setDepartmentname(prefix+top.getDepartmentname());
			treeList.add(d); //拷贝以不改变数据库中的内容
			
		/*	top.setDepartmentname(prefix+top.getDepartmentname());
			treeList.add(top);*/  //此时的list对象处于detach的状态（在service中事务已经提交），所以不会自动更新数据库中的内容
			walkDepartmentTree(top.getChildren(),"　　"+prefix,treeList);
		}
		return treeList;
	}
}
