package com.orm;

public class Tqingjia
{

	private String id;
	private String kaishishijian;
	private String jieshushijian;
	private String beizhu;
	
	private String yuangong_id;
	private String zt;//a-´ýÉóºË£¬b-ÉóºËÍ¨¹ý¡£c-ÉóºËÎ´Í¨¹ý
	private String huifu;
	
	private TYuangong yuangong;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getKaishishijian() {
		return kaishishijian;
	}

	public void setKaishishijian(String kaishishijian) {
		this.kaishishijian = kaishishijian;
	}

	public String getJieshushijian() {
		return jieshushijian;
	}

	public void setJieshushijian(String jieshushijian) {
		this.jieshushijian = jieshushijian;
	}

	public String getBeizhu() {
		return beizhu;
	}

	public void setBeizhu(String beizhu) {
		this.beizhu = beizhu;
	}

	public String getYuangong_id() {
		return yuangong_id;
	}

	public void setYuangong_id(String yuangong_id) {
		this.yuangong_id = yuangong_id;
	}

	public String getZt() {
		return zt;
	}

	public void setZt(String zt) {
		this.zt = zt;
	}

	public String getHuifu() {
		return huifu;
	}

	public void setHuifu(String huifu) {
		this.huifu = huifu;
	}

	public TYuangong getYuangong() {
		return yuangong;
	}

	public void setYuangong(TYuangong yuangong) {
		this.yuangong = yuangong;
	}

	
}
