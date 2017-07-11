package com.sing.ren.pojo;

public class BasePOJO implements DAOEntity {

	private String updater;
	private String updateTime;
	private String order;
	private String rowLimit;
	private String fromIndex;
	
	public BasePOJO() {
		super();
	}
	
	public void setUpdater(String updater) {
		this.updater = updater;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public String updater() {
		return this.updater;
	}

	public String updateTime() {
		return this.updateTime;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public String getRowLimit() {
		return rowLimit;
	}

	public void setRowLimit(String rowLimit) {
		this.rowLimit = rowLimit;
	}

	public String getFromIndex() {
		return fromIndex;
	}

	public void setFromIndex(String fromIndex) {
		this.fromIndex = fromIndex;
	}
	
}
