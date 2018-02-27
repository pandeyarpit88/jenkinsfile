package com.experian.api.demo;

import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@SpringBootApplication
@RestController
public class DemoApplication {
	@RequestMapping("/")
	String home() {
        Map<String, String> env = System.getenv();
        StringBuilder sb = new StringBuilder ();
        for (String envName : env.keySet()) {
            sb.append(envName).append(" : ").append("<br>");
        }
		return sb.toString();
	}


	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
}
