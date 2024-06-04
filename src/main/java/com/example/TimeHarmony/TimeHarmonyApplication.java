package com.example.TimeHarmony;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import com.example.TimeHarmony.service.MemberService;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class, UserDetailsServiceAutoConfiguration.class })
public class TimeHarmonyApplication {

	@Autowired
	private MemberService test;

	public static void main(String[] args) {
		SpringApplication.run(TimeHarmonyApplication.class, args);
	}

	@EventListener(ApplicationReadyEvent.class)
	public void testF() {
		System.out.println(test.getAllAccessHistories("de75cc33-a556-490b-a140-9d618d461569"));
		
	}

}
