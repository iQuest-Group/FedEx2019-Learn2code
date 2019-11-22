package com.iquestgroup.l2c.core;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

@InheritedComponent
public abstract class AutoRegisterableService {

	@Autowired
	private ServicePool servicePool;
	
	private final Feature feature;
	
	@PostConstruct
	private void setUp() {
		servicePool.registerServiceImpl(feature, this);
	}
	
	public AutoRegisterableService(Feature feature) {
		this.feature = feature;
	}
}
