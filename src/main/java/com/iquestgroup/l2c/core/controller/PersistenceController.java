package com.iquestgroup.l2c.core.controller;

import com.iquestgroup.l2c.core.ActiveServicesFactory;
import com.iquestgroup.l2c.core.Feature;
import com.iquestgroup.l2c.model.Device;
import com.iquestgroup.l2c.persistence.DeviceRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/persistence")
public class PersistenceController {

  @Autowired
  private ActiveServicesFactory activeServicesFactory;

  @RequestMapping(value = "/save", method = RequestMethod.POST)
  public void save() {
    DeviceRepository deviceRepository = activeServicesFactory.getActiveImplementationInstanceForFeature(Feature.PERSISTENCE);
    // TODO implement this !
    //    deviceRepository.save();
  }

  @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
  @ResponseBody
  public String get(@PathVariable long id) {
    DeviceRepository deviceRepository = activeServicesFactory.getActiveImplementationInstanceForFeature(Feature.PERSISTENCE);
    Optional<Device> foundDevice = deviceRepository.findById(id);
    if (foundDevice.isPresent()) {
      return foundDevice.get().toString();
    }
    return "";
  }

  @RequestMapping(value = "/delete/{id}", method = RequestMethod.PUT)
  public void delete(@PathVariable long id) {
    DeviceRepository deviceRepository = activeServicesFactory.getActiveImplementationInstanceForFeature(Feature.PERSISTENCE);
    // TODO implement this !
    //    deviceRepository.delete();
  }
}
