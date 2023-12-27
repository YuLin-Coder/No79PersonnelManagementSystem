package com.orm;

public class TQiandao
{

	private String id;
	private String yuangong_id;
	private String riqi;
	private String shijian;

	private String shuxing;
	private TYuangong yuangong;
	
	private String qiantuishi;
	private String qiantuishuxing;
	
	
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
	public String getShijian() {
		return shijian;
	}
	public void setShijian(String shijian) {
		this.shijian = shijian;
	}
	public String getShuxing() {
		return shuxing;
	}
	public void setShuxing(String shuxing) {
		this.shuxing = shuxing;
	}
	public TYuangong getYuangong() {
		return yuangong;
	}
	public void setYuangong(TYuangong yuangong) {
		this.yuangong = yuangong;
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
	
}