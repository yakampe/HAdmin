package dev.equalcoding;

import java.util.TimeZone;

import javax.annotation.PostConstruct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HAdminApplication {

	public static void main(String[] args) {
		SpringApplication.run(HAdminApplication.class, args);
	}

	
	@PostConstruct
	public void init() {
	   TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
	   // OR you can use the line below
	   // System.setProperty("user.timezone", "UTC")
	}
}
