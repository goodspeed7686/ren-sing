package com.sing.ren.pojo;

public class BasePOJO implements DAOEntity {

	private String updater;
	private String updateTime;
	private String order;
	private Integer rowLimit;
	private Integer fromIndex;
	
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

	public Integer getRowLimit() {
		return rowLimit;
	}

	public void setRowLimit(Integer rowLimit) {
		this.rowLimit = rowLimit;
	}

	public Integer getFromIndex() {
		return fromIndex;
	}

	public void setFromIndex(Integer fromIndex) {
		this.fromIndex = fromIndex;
	}
	
}
