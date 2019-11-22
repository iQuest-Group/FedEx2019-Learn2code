package com.iquestgroup.l2c.core;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Component;

@Component
public class ServicePool {
	
	private Map<Feature, List<Implementation>> implementations = new HashMap<>();
	
	public void registerServiceImpl(Feature feature, Implementation implementation) {
		if (implementations.containsKey(feature)) {
			implementations.get(feature).add(implementation);
			return;
		}
		implementations.put(feature, Arrays.asList(implementation));
	}

	public void registerServiceImpl(Feature feature, Object instance) {
		Class<? extends Object> type = instance.getClass();
		registerServiceImpl(feature, new Implementation(instance, type));
	}
	
	public Optional<List<Implementation>> getServiceImplementationsForFeature(Feature feature) {
		return Optional.ofNullable(implementations.get(feature));
	}
}
