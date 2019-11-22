package com.iquestgroup.l2c.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ActiveServicesFactory {
	
	@Autowired
	private ServicePool servicePool;
	
	@SuppressWarnings("unchecked")
	public <T> T getActiveImplementationInstanceForFeature(Feature feature) {
		return (T) servicePool.getActiveImplementationForFeature(feature).getInstance();
	}
}
