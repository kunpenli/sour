package com.sl.www.base;

import java.lang.reflect.ParameterizedType;

import javax.annotation.Resource;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.sl.www.domain.User;
import com.sl.www.service.DepartmentService;
import com.sl.www.service.PrivilegeService;
import com.sl.www.service.RoleService;
import com.sl.www.service.UserService;

public class BaseAction<T> extends ActionSupport implements ModelDriven<T>  {
	
	// =============== ModelDriven��֧�� ==================

	protected T model;

	public BaseAction() {
		try {
			// ͨ�������ȡmodel����ʵ����
			ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
			Class<T> clazz = (Class<T>) pt.getActualTypeArguments()[0];
			// ͨ�����䴴��model��ʵ��
			model = clazz.newInstance();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public T getModel() {
		return model;
	}

	// =============== Serviceʵ�������� ==================
	@Resource
	protected RoleService roleService;
	@Resource
	protected DepartmentService departmentService;
	@Resource
	protected UserService userService;
	@Resource
	protected PrivilegeService privilegeService;

	/**
	 * ��ȡ��ǰ��¼���û�
	 * 
	 * @return
	 */
	protected User getCurrentUser() {
		return (User) ActionContext.getContext().getSession().get("user");
	}

	// ============== ��ҳ�õĲ��� =============

	protected int pageNum = 1; // ��ǰҳ
	protected int pageSize = 10; // ÿҳ��ʾ��������¼

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}


}
