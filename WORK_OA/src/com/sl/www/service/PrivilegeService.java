package com.sl.www.service;

import java.util.Collection;
import java.util.List;

import com.sl.www.domain.Privilege;

public interface PrivilegeService{
	
	/**
	 * ��ѯ���ж�����Ȩ��
	 * 
	 * @return
	 */
	List<Privilege> findTopList();

	/**
	 * ��ѯ����Ȩ�޶�Ӧ��URL���ϣ����ظ���
	 * @return
	 */
	Collection<String> getAllPrivilegeUrls();
	
	/**
	 * ��ѯ����
	 * @return
	 */
	List<Privilege> findAll();
	
	/**
	 * ��ѯһ��
	 * @param privilegeIds
	 * @return
	 */
	List<Privilege> getByIds(Integer[] privilegeIds);


}
