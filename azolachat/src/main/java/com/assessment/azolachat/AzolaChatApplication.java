package com.assessment.azolachat;

import com.assessment.azolachat.service.ChatRoomService;
import com.assessment.azolachat.config.ChatRoomConfig;
import lombok.SneakyThrows;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;

@SpringBootApplication
public class AzolaChatApplication {

	@SneakyThrows
	public static void main(String[] args) {
		ConfigurableApplicationContext applicationContext = SpringApplication.run(AzolaChatApplication.class, args);
		System.out.println(Arrays.toString(applicationContext.getBeanDefinitionNames()));
		/*ApplicationContext applicationContext = new AnnotationConfigApplicationContext(ChatRoomConfig.class);
		ChatRoomService chatRoomService = applicationContext.getBean(ChatRoomService.class);
		chatRoomService.createChatRoom("ChatRoom-1");*/
	}
}
