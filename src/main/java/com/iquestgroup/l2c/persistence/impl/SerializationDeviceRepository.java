package com.iquestgroup.l2c.persistence.impl;

import com.iquestgroup.l2c.core.AutoRegisterableService;
import com.iquestgroup.l2c.core.Feature;
import com.iquestgroup.l2c.core.RegistrableService;
import com.iquestgroup.l2c.event.SourceType;
import com.iquestgroup.l2c.model.Device;
import com.iquestgroup.l2c.persistence.DeviceRepository;

import org.apache.commons.lang3.SystemUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Random;

@RegistrableService(owner = "User", description = "Serialization", version = "1", feature = Feature.PERSISTENCE)
public class SerializationDeviceRepository extends AutoRegisterableService implements DeviceRepository {

  private static final Random RANDOM = new Random();

  private static final String DEVICES_FILE_NAME = "devices.txt";
  private static final String DEVICES_TEMP_FILE_NAME = "devices.txt_tmp";

  private Path devicesFilePath;

  public SerializationDeviceRepository(@Autowired Path devicesFilePath) {
    super(Feature.PERSISTENCE);
    this.devicesFilePath = devicesFilePath;
  }

  @Override
  public Device save(Device device) {
    if (device == null) {
      throw new IllegalArgumentException("Device must not be null!");
    }

    if (device.getId() == null) {
      device.setId(RANDOM.nextLong());
    }

    try (BufferedWriter writer = new BufferedWriter(new FileWriter(devicesFilePath.toFile(), true))) {
      writer.write(buildDeviceOutput(device));
      writer.newLine();
    } catch (IOException e) {
      return device;
    }

    return device;
  }

  private String buildDeviceOutput(Device device) {
    return String.valueOf(device.getId())
           + '#'
           + device.getBrand()
           + '#'
           + device.getModel()
           + '#'
           + device.getFirmwareVersion()
           + '#'
           + device.getSourceType();
  }

  @Override
  public Optional<Device> findById(Long id) {
    try (BufferedReader reader = new BufferedReader(new FileReader(devicesFilePath.toFile()))) {
      String line;
      while ((line = reader.readLine()) != null) {
        Device device = parseDeviceInput(line);

        if (Objects.equals(device.getId(), id)) {
          return Optional.of(device);
        }
      }
    } catch (IOException e) {
      return Optional.empty();
    }
    return Optional.empty();
  }

  @Override
  public List<Device> findAll() {
    List<Device> devices = new ArrayList<>();
    try (BufferedReader reader = new BufferedReader(new FileReader(devicesFilePath.toFile()))) {
      String line;
      while ((line = reader.readLine()) != null) {
        Device device = parseDeviceInput(line);
        devices.add(device);
      }
    } catch (IOException e) {
      return devices;
    }
    return devices;
  }

  private Device parseDeviceInput(String line) {
    String[] deviceValues = line.split("#");

    Device device = new Device();

    device.setId(Long.parseLong(deviceValues[0]));
    device.setBrand(deviceValues[1]);
    device.setModel(deviceValues[2]);
    device.setFirmwareVersion(deviceValues[3]);
    device.setSourceType(SourceType.valueOf(deviceValues[4]));

    return device;
  }

  @Override
  public void delete(Device device) {
    if (device == null) {
      throw new IllegalArgumentException("Device must not be null!");
    }
    deleteInternal(device);
  }

  @Override
  public void deleteById(Long id) {
    Optional<Device> device = findById(id);
    if (device.isEmpty()) {
      return;
    }
    deleteInternal(device.get());
  }

  private void deleteInternal(Device device) {
    File tempFile = devicesFilePath.getParent().resolve(DEVICES_TEMP_FILE_NAME).toFile();

    try (BufferedReader reader = new BufferedReader(new FileReader(devicesFilePath.toFile()));
         BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {
      String line;
      while ((line = reader.readLine()) != null) {
        Device inputDevice = parseDeviceInput(line);

        if (!device.equals(inputDevice)) {
          writer.write(line);
          writer.newLine();
        }
      }
    } catch (IOException e) {
      return;
    }

    tempFile.renameTo(devicesFilePath.toFile());
  }

  @Configuration
  public static class DeviceSerializationConfig {

    @Bean
    public Path getDevicesFilePath() {
      return SystemUtils.getUserDir().toPath().getParent().resolve(DEVICES_FILE_NAME);
    }
  }
}
