package com.iquestgroup.l2c;

import com.iquestgroup.l2c.event.DeviceEvent;
import com.iquestgroup.l2c.event.DeviceEventType;
import com.iquestgroup.l2c.event.SourceType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.List;

@Controller
public class TestController {
    // inject via application.properties
    @Value("${welcome.message}")
    private String message;

    private List<DeviceEvent> events = Arrays.asList(
            new DeviceEvent("test-1231dad1", 11512972L, ZonedDateTime.now(), "Detailing details", DeviceEventType.PICTURE_TAKEN, SourceType.CAMERA),
            new DeviceEvent("test-cffcdad1", 71816972L, ZonedDateTime.now(), "Detailing another details", DeviceEventType.SPEAKER_OFF, SourceType.SPEAKER)
    );

    @GetMapping("/")
    public String index(Model model) {

        model.addAttribute("message", message);
        model.addAttribute("events", events);

        return "tmpl"; //view
    }
}
