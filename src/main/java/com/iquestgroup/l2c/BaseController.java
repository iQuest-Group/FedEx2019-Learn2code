package com.iquestgroup.l2c;

import com.iquestgroup.l2c.event.DeviceEvent;
import com.iquestgroup.l2c.event.DeviceEventType;
import com.iquestgroup.l2c.event.SourceType;
import com.iquestgroup.l2c.model.Device;
import org.apache.el.lang.FunctionMapperImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Controller
public class BaseController {
    // inject via application.properties
    @Value("${welcome.message}")
    private String message;

    private List<DeviceEvent> events = Arrays.asList(
            new DeviceEvent("test-1231dad1", 11512972L, ZonedDateTime.now(), "Detailing details", DeviceEventType.PICTURE_TAKEN, SourceType.CAMERA),
            new DeviceEvent("test-cffcdad1", 71816972L, ZonedDateTime.now(), "Detailing another details", DeviceEventType.SPEAKER_OFF, SourceType.SPEAKER)
    );

    private List<Device> devices = Arrays.asList(
            new Device(11512972L, "Huawei", "TS-125", "1.3.3", SourceType.CAMERA),
            new Device(11512975L, "Huawei", "TS-125", "1.3.4", SourceType.CAMERA),
            new Device(11512977L, "Huawei", "TS-125", "1.3.3", SourceType.CAMERA),
            new Device(11512988L, "Huawei", "TS-125", "1.3.3", SourceType.CAMERA)
    );

    @GetMapping("/")
    public String index(Model model) {

        model.addAttribute("message", message);
        model.addAttribute("events", events);
        model.addAttribute("devices", devices);
        model.addAttribute("brands", getBrands());

        return "base"; //view
    }

    @ResponseBody
    @GetMapping("/devices")
    public List<Device> getDevices() {
        return devices;
    }

    @ResponseBody
    @GetMapping("/devices/brands")
    public List<String> getBrands() {
        return devices.stream()
                .map(Device::getBrand)
                .distinct()
                .collect(Collectors.toList());
    }

    @ResponseBody
    @GetMapping("/devices/brands/{brand}/models")
    public List<String> getModel(@PathVariable String brand) {
        return getModelForBrand(brand);
    }

    @ResponseBody
    @GetMapping("/devices/brands/{brand}/models/{model}/firmware/distribution")
    public Map<String, Long> getFirmwareDistribution(@PathVariable String brand, @PathVariable String model) {
        return getFirmwareDistributionForModel(brand, model);
    }

    private List<String> getModelForBrand(@PathVariable String brand) {
        return devices.stream()
                .filter(it -> brand.equals(it.getBrand()))
                .map(Device::getModel)
                .distinct()
                .collect(Collectors.toList());
    }

    private Map<String, Long> getFirmwareDistributionForModel(@PathVariable String brand, @PathVariable String model) {
        return devices.stream()
                .filter(it -> brand.equals(it.getBrand()) && model.equals(it.getModel()))
                .map(Device::getFirmwareVersion)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    }
}
