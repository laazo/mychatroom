package com.assessment.azolachat.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = { "com.assessment.azolachat.service", "com.assessment.azolachat.repo" })
public class ChatRoomConfig {
}
