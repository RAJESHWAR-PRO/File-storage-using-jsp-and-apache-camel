package com.javainfinite.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.camel.CamelContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.javainfinite.main.ApacheRouter;
import com.javainfinite.model.FileData;

@Controller
public class SpringController {
	ApplicationContext ac;
	public SpringController(ApplicationContext ctx) {
		super();
		// TODO Auto-generated constructor stub
		this.ac = ctx;
	}

	@Autowired
	FileData fdata;

	@RequestMapping(value = "/")
	public String welcome(Model model) {
		model.addAttribute("FileData", fdata);
		return "home";
	}
	
//	@RequestMapping(value="/welcome", method=RequestMethod.POST)
//	public String welcomeUser(@RequestParam("empName") String name, Model model) throws Exception {
//		model.addAttribute("name", name);
//		
//		CamelContext camelContext = (CamelContext)ac.getBean("camelContext");
//		 
//		camelContext.addRoutes(new ApacheRouter(name));
//		camelContext.start();
// 
//		Thread.sleep(60*100);
//		camelContext.stop();
//		return "welcome";
//	}
//	@RequestMapping("/")
//	public ModelAndView showUpload() {
//		return new ModelAndView("home");
//	}
	@PostMapping("/status")
	public ModelAndView fileUpload(@RequestParam("fName") MultipartFile file, RedirectAttributes redirectAttributes) throws Exception {
		String UPLOAD_FOLDER = "D:\\Test\\";
		if (file.isEmpty()) {
			return new ModelAndView("status", "message", "Please select a file and try again");
		}

		try {
			byte[] bytes = file.getBytes();
			Path path = Paths.get(UPLOAD_FOLDER + file.getOriginalFilename());
			Path fname = Files.write(path, bytes);
			CamelContext camelContext = (CamelContext)ac.getBean("camelContext");
			
			camelContext.addRoutes(new ApacheRouter(fname));
			camelContext.start();
	 
			Thread.sleep(60*100);
			camelContext.stop();
			 

		} catch (IOException e) {
			e.printStackTrace();
		}

		return new ModelAndView("status", "message", "File Uploaded sucessfully");
	}
}
