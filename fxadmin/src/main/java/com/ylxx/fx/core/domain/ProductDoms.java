package com.ylxx.fx.core.domain;

import com.ylxx.fx.service.po.User;

public class ProductDoms extends User {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String ptnm;
	
	public String getPtnm() {
		return ptnm;
	}
	public void setPtnm(String ptnm) {
		this.ptnm = ptnm;
	}
	
}
