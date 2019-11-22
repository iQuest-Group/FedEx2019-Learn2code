package com.iquestgroup.l2c.persistence.example;

import com.iquestgroup.l2c.model.Device;
import com.iquestgroup.l2c.persistence.DeviceRepository;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;
import java.util.Optional;
import java.util.Random;

@Repository
public class InMemoryDeviceRepository implements DeviceRepository {

  private static final Random RANDOM = new Random();

  private final Collection<Device> devices;

  public InMemoryDeviceRepository() {
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
}
