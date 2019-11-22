package com.iquestgroup.l2c.core;

public enum Feature {
	
	PERSISTENCE(1, "Persistence layer"),
	
	DEFAULT(0, "default");
	
	private final String name;
	
	private final int id;

	private Feature(int id, String name) {
		this.name = name;
		this.id = id;
	}
	
	public String getName() {
		return this.name;
	}
	
	public int getId() {
		return id;
	}
	
	public static Feature findById(int id) {
		for (Feature feature : Feature.values()) {
			if (id == feature.getId()) {
				return feature;
			}
		}
		return DEFAULT;
	}
}
