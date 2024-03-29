package com.iquestgroup.l2c.core;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.UUID;

public class Implementation {

	@JsonIgnore
	private final Object instance;

	@JsonInclude
	private final Class<? extends Object> type;
	
	private final String owner;
	
	private final String description;
	
	private final String version;
	
	private final String uuid = UUID.randomUUID().toString().replaceAll("-", "");

	public Implementation(Object instance, Class<? extends Object> type) {
		if (!isClassAnnotated(type)) {
			throw new RuntimeException("Tried to register a service implementation that is not annotated with the com.iquestgroup.l2c.core.RegistrableService annotation.");
		}
		this.instance = instance;
		this.type = type;
		RegistrableService metadata = type.getAnnotation(RegistrableService.class);
		this.owner = metadata.owner();
		this.description = metadata.description();
		this.version = metadata.version();
	}
	
	private boolean isClassAnnotated(Class<? extends Object> clazz) {
		return isAnnotated(clazz);
	}

	private boolean isAnnotated(Class<? extends Object> clazz) {
		return clazz.getAnnotation(RegistrableService.class) != null;
	}

	public Object getInstance() {
		return instance;
	}

	public Class<? extends Object> getType() {
		return type;
	}

	public String getOwner() {
		return owner;
	}

	public String getDescription() {
		return description;
	}

	public String getVersion() {
		return version;
	}

	public String getUuid() {
		return uuid;
	}
}
