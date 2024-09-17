package com.chatbot.telegram_chatbot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.chatbot")
public class TelegramChatbotApplication {

	public static void main(String[] args) {
		SpringApplication.run(TelegramChatbotApplication.class, args);
	}

}
