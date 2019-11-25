package com.iquestgroup.l2c.core.controller;

import com.iquestgroup.l2c.core.ActiveServicesFactory;
import com.iquestgroup.l2c.core.Feature;
import com.iquestgroup.l2c.event.SourceType;
import com.iquestgroup.l2c.model.Device;
import com.iquestgroup.l2c.persistence.DeviceRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/persistence")
public class PersistenceController {

  @Autowired
  private ActiveServicesFactory activeServicesFactory;

  @PostMapping(value = "/save")
  @ResponseBody
  public String save(@RequestParam String brand,
                     @RequestParam String model,
                     @RequestParam(required = false) String firmwareVersion) {
    DeviceRepository deviceRepository = activeServicesFactory.getActiveImplementationInstanceForFeature(Feature.PERSISTENCE);
    Device device = new Device(null, brand, model, firmwareVersion, SourceType.OS);

    device = deviceRepository.save(device);
    return device.getId().toString();
  }

  @GetMapping(value = "/get/{id}")
  @ResponseBody
  public ResponseEntity<String> get(@PathVariable long id) {
    DeviceRepository deviceRepository = activeServicesFactory.getActiveImplementationInstanceForFeature(Feature.PERSISTENCE);
    Optional<Device> foundDevice = deviceRepository.findById(id);
    if (foundDevice.isPresent()) {
      return ResponseEntity.of(Optional.of(foundDevice.get().toString()));
    }
    return ResponseEntity.notFound().build();
  }

  @PutMapping(value = "/delete/{id}")
  public void delete(@PathVariable long id) {
    DeviceRepository deviceRepository = activeServicesFactory.getActiveImplementationInstanceForFeature(Feature.PERSISTENCE);
    deviceRepository.delete(id);
  }
}
