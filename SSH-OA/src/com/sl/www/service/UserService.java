package com.sl.www.service;

import java.util.List;

import com.sl.www.domain.Department;
import com.sl.www.domain.User;

public interface UserService {
	
	List<User> findAll();

	void save(User model);

	User getById(int id);

	void update(User department);

	void delete(int id);

	/**
	 * ���ݵ�¼���������ѯ�û�
	 * 
	 * @param loginName
	 * @param password
	 *            ��������
	 * @return
	 */
	User findByLoginNameAndPassword(String loginName, String password);

}
