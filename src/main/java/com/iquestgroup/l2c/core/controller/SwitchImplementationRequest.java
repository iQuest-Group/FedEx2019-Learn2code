package com.iquestgroup.l2c.core.controller;

public class SwitchImplementationRequest {
	
	private String uuid;
	
	private Integer featureId;

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public Integer getFeatureId() {
		return featureId;
	}

	public void setFeatureId(Integer featureId) {
		this.featureId = featureId;
	}
}
