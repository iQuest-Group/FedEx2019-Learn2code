package com.iquestgroup.l2c.persistence;

import com.iquestgroup.l2c.core.AutoRegisterableService;
import com.iquestgroup.l2c.core.Feature;
import com.iquestgroup.l2c.core.RegistrableService;

@RegistrableService(owner = "Zoli")
public class DummyPersistenceService extends AutoRegisterableService implements Persiteable {

	public DummyPersistenceService() {
		super(Feature.PERSISTENCE);
	}

	@Override
	public void save() {
		// TODO Auto-generated method stub
	}
}
