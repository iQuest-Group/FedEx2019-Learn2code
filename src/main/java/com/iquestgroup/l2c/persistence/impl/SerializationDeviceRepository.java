package com.iquestgroup.l2c.persistence.impl;

import com.iquestgroup.l2c.core.AutoRegisterableService;
import com.iquestgroup.l2c.core.Feature;
import com.iquestgroup.l2c.core.RegistrableService;
import com.iquestgroup.l2c.event.SourceType;
import com.iquestgroup.l2c.model.Device;
import com.iquestgroup.l2c.persistence.DeviceRepository;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;
import java.util.Optional;
import java.util.Random;

@RegistrableService(owner = "User", description = "Serialization", version = "1", feature = Feature.PERSISTENCE)
public class SerializationDeviceRepository extends AutoRegisterableService implements DeviceRepository {

  private static final Random RANDOM = new Random();

  private static final String DEVICES_FILE_NAME = "devices.txt";

  public SerializationDeviceRepository() {
    super(Feature.PERSISTENCE);
  }

  @Override
  public Device save(Device device) {
    if (device == null) {
      throw new IllegalArgumentException("Device must not be null!");
    }

    if (device.getId() == null) {
      device.setId(RANDOM.nextLong());
    }

    try (BufferedWriter writer = new BufferedWriter(new FileWriter(DEVICES_FILE_NAME, true))) {
      writer.write(device.toString());
      writer.newLine();
    } catch (IOException e) {
      return device;
    }

    return device;
  }

  @Override
  public Optional<Device> findById(Long id) {
    try (BufferedReader reader = new BufferedReader(new FileReader(DEVICES_FILE_NAME))) {
      String line;
      while ((line = reader.readLine()) != null) {
        Device device = parseDevice(line);

        if (Objects.equals(device.getId(), id)) {
          return Optional.of(device);
        }
      }
    } catch (IOException e) {
      return Optional.empty();
    }
    return Optional.empty();
  }

  private Device parseDevice(String line) {
    Device device = new Device();

    // TODO refactor this !
    int startSeparatorIndex = 0;
    int endSeparatorIndex = 1;
    startSeparatorIndex = line.indexOf('=', startSeparatorIndex);
    endSeparatorIndex = line.indexOf(',', endSeparatorIndex);
    device.setId(Long.parseLong(line.substring(startSeparatorIndex + 1, endSeparatorIndex)));

    startSeparatorIndex = line.indexOf('=', startSeparatorIndex + 1);
    endSeparatorIndex = line.indexOf(',', endSeparatorIndex + 1);
    device.setBrand(line.substring(startSeparatorIndex + 1, endSeparatorIndex));

    startSeparatorIndex = line.indexOf('=', startSeparatorIndex + 1);
    endSeparatorIndex = line.indexOf(',', endSeparatorIndex + 1);
    device.setModel(line.substring(startSeparatorIndex + 1, endSeparatorIndex));

    startSeparatorIndex = line.indexOf('=', startSeparatorIndex + 1);
    endSeparatorIndex = line.indexOf(',', endSeparatorIndex + 1);
    device.setFirmwareVersion(line.substring(startSeparatorIndex + 1, endSeparatorIndex));

    startSeparatorIndex = line.indexOf('=', startSeparatorIndex + 1);
    endSeparatorIndex = line.length() - 1;
    device.setSourceType(SourceType.valueOf(line.substring(startSeparatorIndex + 1, endSeparatorIndex)));

    return device;
  }

  @Override
  public void delete(Device device) {
    if (device == null) {
      throw new IllegalArgumentException("Device must not be null!");
    }
    // TODO implement this !
  }

  @Override
  public void delete(Long id) {
    // TODO implement this !
  }
}
