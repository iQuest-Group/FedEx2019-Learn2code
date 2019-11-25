package com.iquestgroup.l2c;

import com.iquestgroup.l2c.core.Implementation;
import com.iquestgroup.l2c.core.controller.Feature;
import com.iquestgroup.l2c.core.controller.ImplementationController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Controller
public class ConfigController {

    private final ImplementationController implementationController;

    public ConfigController(ImplementationController implementationController) {
        this.implementationController = implementationController;
    }

    @GetMapping("/config")
    public String getView(Model model) {

        Map<Feature, List<Implementation>> implementationMapping = new HashMap<>();
        for (Feature feature : implementationController.getFeatures()) {
            implementationMapping.put(feature, implementationController.getImplementations(feature.getId()));
        }

        model.addAttribute("featuresMapping", implementationMapping);

        return "config";
    }
}
