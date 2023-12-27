package com.orm;

public class TChuqin
{
	private String id;
	private String yuangong_id;
	private String riqi;
	private String qiandaoshi;

	private String qiandaoshuxing;
	private String qiantuishi;
	private String qiantuishuxing;
	
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

	public String getRiqi() {
		return riqi;
	}

	public void setRiqi(String riqi) {
		this.riqi = riqi;
	}

	public String getQiandaoshi() {
		return qiandaoshi;
	}

	public void setQiandaoshi(String qiandaoshi) {
		this.qiandaoshi = qiandaoshi;
	}

	public String getQiandaoshuxing() {
		return qiandaoshuxing;
	}

	public void setQiandaoshuxing(String qiandaoshuxing) {
		this.qiandaoshuxing = qiandaoshuxing;
	}

	public String getQiantuishi() {
		return qiantuishi;
	}

	public void setQiantuishi(String qiantuishi) {
		this.qiantuishi = qiantuishi;
	}

	public String getQiantuishuxing() {
		return qiantuishuxing;
	}

	public void setQiantuishuxing(String qiantuishuxing) {
		this.qiantuishuxing = qiantuishuxing;
	}

	public TYuangong getYuangong() {
		return yuangong;
	}

	public void setYuangong(TYuangong yuangong) {
		this.yuangong = yuangong;
	}
	
}