package com.orm;

public class TYuangong
{
	private String id;
	private String org_id;
	private String gonghao;
	private String loginpw;
	
	private String xingming;
	private String xingbie;
	private String chusheng;
	private String zhuzhi;
	
	private String del;
	
	private Torg org;
	
	
	public String getOrg_id() {
		return org_id;
	}
	public void setOrg_id(String org_id) {
		this.org_id = org_id;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getGonghao() {
		return gonghao;
	}
	public void setGonghao(String gonghao) {
		this.gonghao = gonghao;
	}
	public String getLoginpw() {
		return loginpw;
	}
	public void setLoginpw(String loginpw) {
		this.loginpw = loginpw;
	}
	public String getXingming() {
		return xingming;
	}
	public void setXingming(String xingming) {
		this.xingming = xingming;
	}
	public String getXingbie() {
		return xingbie;
	}
	
	public Torg getOrg() {
		return org;
	}
	public void setOrg(Torg org) {
		this.org = org;
	}
	public void setXingbie(String xingbie) {
		this.xingbie = xingbie;
	}
	public String getChusheng() {
		return chusheng;
	}
	public void setChusheng(String chusheng) {
		this.chusheng = chusheng;
	}
	
	public String getZhuzhi() {
		return zhuzhi;
	}
	public void setZhuzhi(String zhuzhi) {
		this.zhuzhi = zhuzhi;
	}
	public String getDel() {
		return del;
	}
	public void setDel(String del) {
		this.del = del;
	}
}
