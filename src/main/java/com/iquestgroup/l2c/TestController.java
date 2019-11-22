package com.iquestgroup.l2c;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class TestController {
	// inject via application.properties
	@Value("${welcome.message}")
	private String message;

	@GetMapping("/")
	public String index(Model model) {

		model.addAttribute("message", message);

		return "tmpl"; //view
	}
}
