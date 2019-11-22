package com.iquestgroup.l2c.persistence;

import com.iquestgroup.l2c.model.Device;

import java.util.Optional;

public class DummyDeviceRepository implements DeviceRepository {

  @Override
  public Device save(Device device) {
    return device;
  }

  @Override
  public Optional<Device> findById(Long id) {
    return Optional.empty();
  }

  @Override
  public void delete(Device device) {
    //empty
  }
}
