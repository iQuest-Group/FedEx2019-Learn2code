package com.iquestgroup.l2c.persistence;

import com.iquestgroup.l2c.model.Device;

import java.util.Optional;

/**
 * Handles the persistence of the {@link Device} entities.
 */
public interface DeviceRepository {

  /**
   * Saves the given device.
   *
   * @param device the device to save
   * @return the saved entity
   */
  Device save(Device device);

  /**
   * Retrieves a {@link Device} given its ID.
   *
   * @param id the ID to retrieve the device by
   * @return the
   */
  Optional<Device> findById(Long id);

  /**
   * Removes the given {@link Device}.
   *
   * @param device the device to remove
   */
  void delete(Device device);
}
