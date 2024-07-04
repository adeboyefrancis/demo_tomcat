package com.example.demo_tomcat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class DemoTomcatApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoTomcatApplication.class, args);
	}

	@GetMapping("/")
    public String hello(@RequestParam(value = "name", defaultValue = "Deployment of maven project via Jenkins pipeline to Tomcat") String name) {
    return String.format("%s", name);
}

}
