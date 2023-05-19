package io.github.marcperez06.link_checker_web_service.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {
	
	@GetMapping(value = {"", "/", "/home"} )
	public String homePage() {
		return "home.html";
	}

}