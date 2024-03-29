package com.iquestgroup.l2c.core.controller;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iquestgroup.l2c.core.Implementation;
import com.iquestgroup.l2c.core.ServicePool;

@RestController
@RequestMapping(value = "/api/implementations")
public class ImplementationController {
	
	@Autowired
	private ServicePool servicePool;
	
	@GetMapping("/features/{featureId}")
	public Feature getFeatures(@PathVariable("featureId") Integer featureId) {
		return new Feature(com.iquestgroup.l2c.core.Feature.findById(featureId.intValue()));
	}
	
	@GetMapping("/features")
	public List<Feature> getFeatures() {
		return Stream.of(com.iquestgroup.l2c.core.Feature.values())
				.map(Feature::new)
				.collect(Collectors.toList());
	}

	@GetMapping("/{featureId}")
	public List<Implementation> getImplementations(@PathVariable("featureId") Integer featureId) {
		return servicePool.getServiceImplementationsForFeature(com.iquestgroup.l2c.core.Feature.findById(featureId.intValue()))
				.orElse(Collections.emptyList());
	}
	
	@PostMapping(path = "/switchto", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public String switchImplementation(@RequestBody SwitchImplementationRequest switchImplementationRequest) {
		Implementation implementation = getImplementations(switchImplementationRequest.getFeatureId())
				.stream().filter((request) -> request.getUuid().equals(switchImplementationRequest.getUuid()))
					.findAny()
					.orElseThrow(() -> new RuntimeException("Invalid implementation UUID."));
		servicePool.setActiveImplementation(com.iquestgroup.l2c.core.Feature.findById(switchImplementationRequest.getFeatureId().intValue()), implementation);
		return "OK";
	}
}