package com.sing.ren.pojo;

public class BasePOJO implements DAOEntity {

	private String updater;
	private String updateTime;
	
	public BasePOJO() {
		super();
	}
	
	@Override
	public void setUpdater(String updater) {
		this.updater = updater;
	}

	@Override
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	@Override
	public String updater() {
		return this.updater;
	}

	@Override
	public String updateTime() {
		return this.updateTime;
	}

}
