package com.javainfinite.main;

import java.nio.file.Files;
import java.nio.file.Path;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;

public class ApacheRouter extends RouteBuilder {
	
	Path src = null;
	String Destination = "D:\\805626Eclipse\\ShareOut\\";
	String path;
	
    public ApacheRouter(Path fPath) {
		super();
		// TODO Auto-generated constructor stub
		this.src = fPath;
	}

	@Override
    public void configure() throws Exception {
		path = src.toString().substring(0, src.toString().lastIndexOf('\\'));
		path = path.replace('\\', '/');
		System.out.println("\n\n\n"+path+"\n\n\n");
        from("file:/"+path+"/")
        .to("file:/"+Destination);
//                .to("file:/D:/805626Eclipse/ShareOut/");
        
    }
}