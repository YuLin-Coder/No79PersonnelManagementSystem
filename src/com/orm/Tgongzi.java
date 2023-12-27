package com.orm;

public class Tgongzi
{
	private String id;
	private String yuangong_id;
	private String yuefen;
	private int jiben;
	
	private int jiangjin;
	private int kouchu;
	private String beizhu;
	private int zong;
	
	private TYuangong yuangong;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getYuangong_id() {
		return yuangong_id;
	}

	public void setYuangong_id(String yuangong_id) {
		this.yuangong_id = yuangong_id;
	}

	public String getYuefen() {
		return yuefen;
	}

	public void setYuefen(String yuefen) {
		this.yuefen = yuefen;
	}

	public int getJiben() {
		return jiben;
	}

	public void setJiben(int jiben) {
		this.jiben = jiben;
	}

	public int getJiangjin() {
		return jiangjin;
	}

	public void setJiangjin(int jiangjin) {
		this.jiangjin = jiangjin;
	}

	public int getKouchu() {
		return kouchu;
	}

	public void setKouchu(int kouchu) {
		this.kouchu = kouchu;
	}

	public String getBeizhu() {
		return beizhu;
	}

	public void setBeizhu(String beizhu) {
		this.beizhu = beizhu;
	}

	public int getZong() {
		return zong;
	}

	public void setZong(int zong) {
		this.zong = zong;
	}

	public TYuangong getYuangong() {
		return yuangong;
	}

	public void setYuangong(TYuangong yuangong) {
		this.yuangong = yuangong;
	}

}
