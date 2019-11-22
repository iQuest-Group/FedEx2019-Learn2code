package com.iquestgroup.l2c.core.controller;

public class Feature {
	
	private final String name;
	
	private final int id;
	
	private final String description;

	public Feature(com.iquestgroup.l2c.core.Feature feature) {
		this.name = feature.toString();
		this.id = feature.getId();
		this.description = feature.getName();
	}

	public String getName() {
		return name;
	}

	public int getId() {
		return id;
	}

	public String getDescription() {
		return description;
	} 
}
