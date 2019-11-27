package com.iquestgroup.l2c.persistence;

import com.iquestgroup.l2c.model.Device;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public interface DeviceRepositoryTest<T extends DeviceRepository> {

  T getDeviceRepository();

  @Test
  default void testShouldNotSaveIfDeviceIsNull() {
    assertThrows(IllegalArgumentException.class, () -> getDeviceRepository().save(null));
  }

  @Test
  default void testResultShouldBeEmptyIfDeviceDoesNotExist() {
    Optional<Device> result = getDeviceRepository().findById(0L);

    assertTrue(result.isEmpty());
  }

  @Test
  default void testDeviceShouldBeFoundIfExists() {
    Device device = new Device();
    device = getDeviceRepository().save(device);
    final long id = device.getId();

    Optional<Device> result = getDeviceRepository().findById(id);

    assertTrue(result.isPresent());
    assertEquals(result.get().getId(), id);
  }

  @Test
  default void testNoDevicesShouldBeFoundIfNoneWasSaved() {
    List<Device> result = getDeviceRepository().findAll();

    assertEquals(result.size(), 0);
  }

  @Test
  default void testAllSavedDevicesShouldBeFound() {
    List<Device> devices = Arrays.asList(new Device(), new Device());
    devices.forEach(device -> getDeviceRepository().save(device));

    List<Device> result = getDeviceRepository().findAll();

    assertEquals(devices.size(), result.size());
    devices.forEach(device -> assertTrue(result.contains(device)));
  }

  @Test
  default void testNullDeviceCannotBeDeleted() {
    assertThrows(IllegalArgumentException.class, () -> getDeviceRepository().delete(null));
  }

  @Test
  default void testSavedDeviceDoesNotExistAfterDeletion() {
    Device device = new Device();
    getDeviceRepository().save(device);
    final long id = device.getId();
    getDeviceRepository().delete(device);

    Optional<Device> result = getDeviceRepository().findById(id);

    assertTrue(result.isEmpty());
  }

  @Test
  default void testSavedDeviceDoesNotExistAfterDeletionById() {
    Device device = new Device();
    getDeviceRepository().save(device);
    final long id = device.getId();
    getDeviceRepository().deleteById(id);

    Optional<Device> result = getDeviceRepository().findById(id);

    assertTrue(result.isEmpty());
  }

  @Test
  default void testNoDeletionHappensIfDeviceDoesNotExist() {
    Device device = new Device();
    final int initialSize = getDeviceRepository().findAll().size();

    getDeviceRepository().delete(device);

    final int currentSize = getDeviceRepository().findAll().size();
    assertEquals(initialSize, currentSize);
  }

  @Test
  default void testNoDeletionHappensIfDeviceWithIdDoesNotExist() {
    final int initialSize = getDeviceRepository().findAll().size();

    getDeviceRepository().deleteById(123456789L);

    final int currentSize = getDeviceRepository().findAll().size();
    assertEquals(initialSize, currentSize);
  }
}
