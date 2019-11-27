package com.iquestgroup.l2c.persistence.impl;

import com.iquestgroup.l2c.persistence.DeviceRepositoryTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.io.TempDir;

import java.nio.file.Path;

public class SerializationDeviceRepositoryTest implements DeviceRepositoryTest<SerializationDeviceRepository> {

  private static final String DEVICES_FILE_NAME = "devices.txt";

  private SerializationDeviceRepository serializationDeviceRepository;

  @TempDir
  public Path tempDir;

  @BeforeEach
  public void setup() {
    serializationDeviceRepository = new SerializationDeviceRepository(tempDir.resolve(DEVICES_FILE_NAME));
  }

  @Override
  public SerializationDeviceRepository getDeviceRepository() {
    if (serializationDeviceRepository == null) {
      serializationDeviceRepository = new SerializationDeviceRepository(tempDir.resolve(DEVICES_FILE_NAME));
    }
    return serializationDeviceRepository;
  }
}
