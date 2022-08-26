package com.ceypetco.allocationservice;

import com.ceypetco.allocationservice.controller.AllocationController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AllocationServiceApplication {

	@Autowired
	AllocationController allocationController;

	public static final Logger logger = LoggerFactory.getLogger(AllocationServiceApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(AllocationServiceApplication.class, args);
	}

}
