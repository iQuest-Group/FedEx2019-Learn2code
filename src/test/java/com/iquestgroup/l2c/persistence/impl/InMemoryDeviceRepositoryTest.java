package com.iquestgroup.l2c.persistence.impl;

import com.iquestgroup.l2c.persistence.DeviceRepositoryTest;

import org.junit.jupiter.api.BeforeEach;

public class InMemoryDeviceRepositoryTest implements DeviceRepositoryTest<InMemoryDeviceRepository> {

  private InMemoryDeviceRepository inMemoryDeviceRepository;

  @BeforeEach
  public void setup() {
    inMemoryDeviceRepository = new InMemoryDeviceRepository();
  }

  @Override
  public InMemoryDeviceRepository getDeviceRepository() {
    if (inMemoryDeviceRepository == null) {
      inMemoryDeviceRepository = new InMemoryDeviceRepository();
    }
    return inMemoryDeviceRepository;
  }
}
