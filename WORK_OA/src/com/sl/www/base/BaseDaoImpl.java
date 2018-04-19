package com.sl.www.base;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class BaseDaoImpl<T> implements BaseDao<T>{
	
	//�������ʵ�Ĳ�������
	private Class clazz;
	
	public BaseDaoImpl(){
		//��õ��Ǹ��������
		ParameterizedType  pt=(ParameterizedType) this.getClass().getGenericSuperclass();
		//��ø������ʵ�Ĳ�������
		clazz=(Class) pt.getActualTypeArguments()[0];
	}
	
	@Resource
	protected SessionFactory sessionFactory;
	
	protected Session getSession(){
		return sessionFactory.getCurrentSession();
	}

	public void delete(int id) {
		getSession().delete(getById(id));
	}

	public List<T> findAll() {
		//�и�����
		String hql="from "+clazz.getSimpleName();
		Query query=getSession().createQuery(hql);
		return query.list();
	}

	public T getById(int id) {
		// TODO Auto-generated method stub
		return (T) getSession().get(clazz, id);
	}

	public List<T> getByIds(Integer[] ids) {
		String hql="from "+clazz.getSimpleName()+" where id in(:ids)";
		Query query=getSession().createQuery(hql).setParameterList("ids", ids);
		return query.list();
	}

	public void save(T entity) {
		getSession().save(entity);
	}

	public void update(T entity) {
		getSession().update(entity);
	}

}
