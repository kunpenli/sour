package com.ylxx.fx.core.domain;

import java.io.Serializable;

public class ElecTellerManagerVo implements Serializable{
	private static final long serialVersionUID = -2682305557890221059L;
	private String ogcd;
	private String trtl;
	private String tltp;
	private String bhid;
	private String chnl;
	private String ognm;
	private String tellerid;
	private String pass;
	private  String userKey;
	
	public String getUserKey() {
		return userKey;
	}
	public void setUserKey(String userKey) {
		this.userKey = userKey;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getOgcd() {
		return ogcd;
	}
	public void setOgcd(String ogcd) {
		this.ogcd = ogcd;
	}
	public String getTrtl() {
		return trtl;
	}
	public void setTrtl(String trtl) {
		this.trtl = trtl;
	}
	public String getTltp() {
		return tltp;
	}
	public void setTltp(String tltp) {
		this.tltp = tltp;
	}
	public String getBhid() {
		return bhid;
	}
	public void setBhid(String bhid) {
		this.bhid = bhid;
	}
	public String getChnl() {
		return chnl;
	}
	public void setChnl(String chnl) {
		this.chnl = chnl;
	}
	public String getOgnm() {
		return ognm;
	}
	public void setOgnm(String ognm) {
		this.ognm = ognm;
	}
	public String getTellerid() {
		return tellerid;
	}
	public void setTellerid(String tellerid) {
		this.tellerid = tellerid;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	
	
	

}
