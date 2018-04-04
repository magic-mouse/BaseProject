package com.github.dronezcc.riser.gui;

import com.github.dronezcc.riser.customers.CustomersController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

@Import(CustomersController.class)
@SpringBootApplication
@ComponentScan(basePackages = {"com.github.dronezcc.riser.*"})
public class GuiApplication {

	public static void main(String[] args) {
		SpringApplication.run(GuiApplication.class, args);
	}


}
