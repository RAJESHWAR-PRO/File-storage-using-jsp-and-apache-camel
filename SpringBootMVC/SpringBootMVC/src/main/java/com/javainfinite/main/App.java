package com.javainfinite.main;

import org.apache.camel.CamelContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javainfinite.controller.SpringController;



/**
 * Hello world!
 *
 */
@SpringBootApplication
@ComponentScan("com.javainfinite")
public class App {
	private static ApplicationContext ctx;
	public static void main(String[] args) throws Exception {
		ctx = SpringApplication.run(App.class, args);
		SpringController sc = new SpringController(ctx);
	}
}
