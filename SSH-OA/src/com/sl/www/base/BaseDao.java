package com.sl.www.base;

import java.util.List;

public interface BaseDao<T> {
	
	/**
	 * ��ѯ����
	 * @return ����
	 */
	List<T> findAll();
	
	/**
	 * ����ʵ��
	 * @param entity ����
	 */
	void save(T entity);
	
	/**
	 * ����idɾ��ʵ��
	 * @param id
	 */
	void delete(int id);
	
	/**
	 * �޸�ʵ��
	 * @param entity ����
	 */
	void update(T entity);
	
	/**
	 * ����id����ʵ��
	 * @param id
	 * @return ����
	 */
	T getById(int id);
	
	/**
	 * ����id�����ѯһ��ʵ��
	 * @param ids ����
	 * @return ����
	 */
	List<T> getByIds(Integer[] ids);

}
