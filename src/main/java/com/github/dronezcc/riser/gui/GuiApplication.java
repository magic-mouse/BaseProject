package com.github.dronezcc.riser.gui;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

@SpringBootApplication
public class GuiApplication {
	public static void main(String[] args) {
		SpringApplication.run(GuiApplication.class, args);
	}
}
