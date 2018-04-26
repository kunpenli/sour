package com.sl.www.util;

import java.util.ArrayList;
import java.util.List;

import com.sl.www.domain.Department;

/*
 * ���ڻ�ò������Ĺ�����
 */
public class DepartmentUtil {
    
	/**
	 * ���ݶ������Ż�ò������ṹ�б�
	 * @param list �������ţ����ݶ�����������
	 * @return
	 */
	public static List<Department> getTreeList(List<Department> list) {
		List<Department> treeList=new ArrayList<Department>();
		for (Department department : list) {
			walkTree(department,"��",treeList);
		}
		
		return treeList;
	}

	private static void walkTree(Department department, String prefix,List<Department> treeList) {
		//��װ����
		//System.out.println();
		//��һ��copy
		Department copy=new Department();
		copy.setId(department.getId());
		copy.setName(prefix+department.getName());
		
		
		treeList.add(copy);
		
		//�ݹ����
		for (Department department2 : department.getChildren()) {
			walkTree(department2, "��"+prefix, treeList);
		}
		
	}
	

}
