package com.iquestgroup.l2c.persistence.impl;

import com.iquestgroup.l2c.model.Device;
import com.iquestgroup.l2c.persistence.DeviceRepository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

@Disabled
@ExtendWith(SpringExtension.class)
public class SerializationDeviceRepositoryTest {

  @Autowired
  private DeviceRepository deviceRepository;

  @Test
  public void testShouldNotSaveIfDeviceIsNull() {
    Assertions.assertThrows(IllegalArgumentException.class, () -> deviceRepository.save(null));
  }

  @Test
  public void testDeviceShouldNotBeRetrievedIfDoesNotExist() {
    Optional<Device> result = deviceRepository.findById(0L);

    Assertions.assertTrue(result.isEmpty());
  }

  @Test
  public void testDeviceShouldBeFoundIfExists() {
    Device device = new Device();
    device = deviceRepository.save(device);
    final long id = device.getId();

    Optional<Device> result = deviceRepository.findById(id);

    Assertions.assertTrue(result.isPresent());
    Assertions.assertEquals(result.get().getId(), id);
  }

  @Test
  public void testNullDeviceCannotBeDeleted() {
    Assertions.assertThrows(IllegalArgumentException.class, () -> deviceRepository.delete(null));
  }

  @Test
  public void testSavedDeviceDoesNotExistAfterDeletion() {
    Device device = new Device();
    deviceRepository.save(device);
    final long id = device.getId();
    deviceRepository.delete(device);

    Optional<Device> result = deviceRepository.findById(id);

    Assertions.assertTrue(result.isEmpty());
  }

  @TestConfiguration
  static class SerializationRepositoryTestConfiguration {
    @Bean
    public DeviceRepository deviceRepository() {
      return new SerializationDeviceRepository();
    }
  }
}
