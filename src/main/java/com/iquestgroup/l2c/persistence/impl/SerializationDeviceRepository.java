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
import java.nio.file.Path;
import java.util.Objects;
import java.util.Optional;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RegistrableService(owner = "User", description = "Serialization", version = "1", feature = Feature.PERSISTENCE)
public class SerializationDeviceRepository extends AutoRegisterableService implements DeviceRepository {

  private static final Random RANDOM = new Random();

  private static final String DEVICES_FILE_NAME = "devices.txt";

  private static final Path DEVICES_PATH = Path.of(System.getProperty("user.dir")).getParent().resolve(DEVICES_FILE_NAME);

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

    try (BufferedWriter writer = new BufferedWriter(new FileWriter(DEVICES_PATH.toFile(), true))) {
      writer.write(device.toString());
      writer.newLine();
    } catch (IOException e) {
      return device;
    }

    return device;
  }

  @Override
  public Optional<Device> findById(Long id) {
    try (BufferedReader reader = new BufferedReader(new FileReader(DEVICES_PATH.toFile()))) {
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
    Pattern pattern = Pattern.compile("Device\\((,* *(id=(?<id>.+?)|brand=(?<brand>.+?)|model=(?<model>.+?)|firmwareVersion=(?<firmwareVersion>.+?)|sourceType=(?<sourceType>.+?)))+\\)");
    Matcher matcher = pattern.matcher(line);
    matcher.find();

    String id = matcher.group("id");
    String brand = matcher.group("brand");
    String model = matcher.group("model");
    String firmwareVersion = matcher.group("firmwareVersion");
    String sourceType = matcher.group("sourceType");

    Device device = new Device();

    device.setId(Long.parseLong(id));
    device.setBrand(brand);
    device.setModel(model);
    device.setFirmwareVersion(firmwareVersion);
    device.setSourceType(SourceType.valueOf(sourceType));

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
