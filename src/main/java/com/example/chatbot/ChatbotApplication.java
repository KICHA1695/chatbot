package com.example.chatbot;



	import org.springframework.boot.SpringApplication;
	import org.springframework.boot.autoconfigure.SpringBootApplication;
	import org.springframework.boot.builder.SpringApplicationBuilder;
	import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

	@SpringBootApplication
	public class ChatbotApplication extends SpringBootServletInitializer {

	    // Required for building the WAR deployment context natively
	    @Override
	    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
	        return application.sources(ChatbotApplication.class);
	    }

	    public static void main(String[] args) {
	        SpringApplication.run(ChatbotApplication.class, args);
	    }
	}