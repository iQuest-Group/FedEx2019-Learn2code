package com.iquestgroup.l2c.persistence.example;

import com.iquestgroup.l2c.event.SourceType;
import com.iquestgroup.l2c.model.Device;
import com.iquestgroup.l2c.persistence.DeviceRepository;

import org.springframework.stereotype.Repository;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Objects;
import java.util.Optional;
import java.util.Random;
import java.util.Scanner;

@Repository
public class SerializationDeviceRepository implements DeviceRepository {

  private static final Random RANDOM = new Random();

  private static final String DEVICES_FILE_NAME = "devices.txt";

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
    try (Scanner scanner = new Scanner(new File(DEVICES_FILE_NAME))) {
      while (scanner.hasNextLine()) {
        String line = scanner.nextLine();
        Device device = parseDevice(line);

        if (Objects.equals(device.getId(), id)) {
          return Optional.of(device);
        }
      }
    } catch (FileNotFoundException e) {
      return Optional.empty();
    }
    return Optional.empty();
  }

  private Device parseDevice(String line) {
    Device device = new Device();

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
//
//    try (BufferedReader reader = new BufferedReader(new FileReader(DEVICES_FILE_NAME)), BufferedWriter wr) {
//
//
//
//
//    }
  }
}
