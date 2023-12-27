package com.orm;


public class TQiantui{

	// Fields

	private String id;
	private String yuangong_id;
	private String riqi;
	private String shijian;
	
	private String shuxing;
	
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

}