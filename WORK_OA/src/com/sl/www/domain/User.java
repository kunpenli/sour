package com.sl.www.domain;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import com.opensymphony.xwork2.ActionContext;

public class User {
	
	private int id;
	private String name;//��ʵ����
	private String loginName;//��¼��
	private String password;
	private String gender;
	private String phoneNumber;
	private String description;
	private String email;
	
	private Department department;//��������
	private Set<Role> roles=new HashSet<Role>();//ӵ�еĸ�λ
	
	
	
	public User() {
		super();
	}
	
	
	
	public User(int id, String name, String loginName, String password,
			String gender, String phoneNumber, String description,
			String email, Department department, Set<Role> roles) {
		super();
		this.id = id;
		this.name = name;
		this.loginName = loginName;
		this.password = password;
		this.gender = gender;
		this.phoneNumber = phoneNumber;
		this.description = description;
		this.email = email;
		this.department = department;
		this.roles = roles;
	}


	
	/**
	 * �жϱ��û��Ƿ���ָ�����Ƶ�Ȩ��
	 * 
	 * @param name
	 * @return
	 */
	public boolean hasPrivilegeByName(String name) {
		// �������������е�Ȩ��
		if (isAdmin()) {
			return true;
		}

		// ��ͨ�û�Ҫ�ж��Ƿ������Ȩ��
		for (Role role : roles) {
			for (Privilege priv : role.getPrivileges()) {
				if (priv.getName().equals(name)) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * �жϱ��û��Ƿ���ָ��URL��Ȩ��
	 * 
	 * @param privUrl
	 * @return
	 */
	public boolean hasPrivilegeByUrl(String privUrl) {
		// �������������е�Ȩ��
		if (isAdmin()) {
			return true;
		}

		// >> ȥ������Ĳ���
		int pos = privUrl.indexOf("?");
		if (pos > -1) {
			privUrl = privUrl.substring(0, pos);
		}
		// >> ȥ��UI��׺
		if (privUrl.endsWith("UI")) {
			privUrl = privUrl.substring(0, privUrl.length() - 2);
		}

		// �����URL����Ҫ���ƣ����¼�û��Ϳ���ʹ��
		Collection<String> allPrivilegeUrls = (Collection<String>) ActionContext.getContext().getApplication().get("allPrivilegeUrls");
		if (!allPrivilegeUrls.contains(privUrl)) {
			return true;
		} else {
			// ��ͨ�û�Ҫ�ж��Ƿ������Ȩ��
			for (Role role : roles) {
				for (Privilege priv : role.getPrivileges()) {
					if (privUrl.equals(priv.getUrl())) {
						return true;
					}
				}
			}
			return false;
		}
	}

	/**
	 * �жϱ��û��Ƿ��ǳ�������Ա
	 * 
	 * @return
	 */
	public boolean isAdmin() {
		return "admin".equals(loginName);
	}


	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}



	public String getLoginName() {
		return loginName;
	}



	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	public String getGender() {
		return gender;
	}



	public void setGender(String gender) {
		this.gender = gender;
	}



	public String getPhoneNumber() {
		return phoneNumber;
	}



	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}



	public String getDescription() {
		return description;
	}



	public void setDescription(String description) {
		this.description = description;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public Department getDepartment() {
		return department;
	}



	public void setDepartment(Department department) {
		this.department = department;
	}



	public Set<Role> getRoles() {
		return roles;
	}



	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	
	

}
