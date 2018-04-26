package com.sl.www.domain;

import java.util.HashSet;
import java.util.Set;

public class Privilege {
	
	private Integer id;
	private String url;
	private String name; // Ȩ������
	private Set<Role> roles = new HashSet<Role>();
	private Privilege parent; // �ϼ�Ȩ��
	private Set<Privilege> children = new HashSet<Privilege>(); // �¼�Ȩ��

	public Privilege() {
	}

	public Privilege(String name, String url, Privilege parent) {
		this.name = name;
		this.url = url;
		this.parent = parent;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public Privilege getParent() {
		return parent;
	}

	public void setParent(Privilege parent) {
		this.parent = parent;
	}

	public Set<Privilege> getChildren() {
		return children;
	}

	public void setChildren(Set<Privilege> children) {
		this.children = children;
	}


}
