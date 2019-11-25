package com.iquestgroup.l2c.persistence.impl;

import com.iquestgroup.l2c.core.AutoRegisterableService;
import com.iquestgroup.l2c.core.Feature;
import com.iquestgroup.l2c.core.RegistrableService;
import com.iquestgroup.l2c.model.Device;
import com.iquestgroup.l2c.persistence.DeviceRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;
import java.util.Optional;
import java.util.Random;

@RegistrableService(owner = "User", description = "InMemory", version = "1", feature = Feature.PERSISTENCE)
public class InMemoryDeviceRepository extends AutoRegisterableService implements DeviceRepository {

  private static final Random RANDOM = new Random();

  private final Collection<Device> devices;

  public InMemoryDeviceRepository() {
    super(Feature.PERSISTENCE);
    devices = new ArrayList<>();
  }

  @Override
  public Device save(Device device) {
    if (device == null) {
      throw new IllegalArgumentException("Device must not be null!");
    }

    if (device.getId() == null) {
      device.setId(RANDOM.nextLong());
    }
    devices.add(device);
    return device;
  }

  @Override
  public Optional<Device> findById(Long id) {
    return devices.stream()
        .filter(device -> Objects.equals(id, device.getId()))
        .findFirst();
  }

  @Override
  public void delete(Device device) {
    if (device == null) {
      throw new IllegalArgumentException("Device must not be null!");
    }

    devices.remove(device);
  }

  @Override
  public void delete(Long id) {
    Optional<Device> toRemove = findById(id);
    if (toRemove.isEmpty()) {
      return;
    }

    devices.remove(toRemove.get());
  }
}
