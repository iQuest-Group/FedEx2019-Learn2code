package com.iquestgroup.l2c.persistence.impl;

import com.iquestgroup.l2c.core.AutoRegisterableService;
import com.iquestgroup.l2c.core.Feature;
import com.iquestgroup.l2c.core.RegistrableService;
import com.iquestgroup.l2c.model.Device;
import com.iquestgroup.l2c.persistence.DeviceRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RegistrableService(owner = "User", description = "Dummy", version = "1", feature = Feature.PERSISTENCE)
public class DummyDeviceRepository extends AutoRegisterableService implements DeviceRepository {

  public DummyDeviceRepository() {
    super(Feature.PERSISTENCE);
  }

  @Override
  public Device save(Device device) {
    device.setId(0L);
    return device;
  }

  @Override
  public Optional<Device> findById(Long id) {
    return Optional.empty();
  }

  @Override
  public List<Device> findAll() {
    return Collections.emptyList();
  }

  @Override
  public void delete(Device device) {
    //empty
  }

  @Override
  public void deleteById(Long id) {
    //empty
  }
}
