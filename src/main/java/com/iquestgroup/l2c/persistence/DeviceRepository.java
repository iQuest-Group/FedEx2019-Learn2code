package com.iquestgroup.l2c.persistence;

import com.iquestgroup.l2c.model.Device;

import java.util.List;
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
   * @throws IllegalArgumentException if the given device is {@code null}
   */
  Device save(Device device);

  /**
   * Retrieves a {@link Device} given its ID.
   *
   * @param id the ID to retrieve the device by
   * @return an optional wrapper containing the device, if found, or empty otherwise
   */
  Optional<Device> findById(Long id);

  /**
   * Retrieves all {@link Device} entities.
   *
   * @return all device entities or empty if no device exists
   */
  List<Device> findAll();

  /**
   * Removes the given {@link Device}.
   *
   * @param device the device to remove
   * @throws IllegalArgumentException if the given device is {@code null}
   */
  void delete(Device device);

  /**
   * Removes the {@link Device} with the given id.
   *
   * @param id the id of the device to remove
   */
  void deleteById(Long id);
}
