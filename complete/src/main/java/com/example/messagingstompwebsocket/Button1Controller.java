package com.example.messagingstompwebsocket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Button1Controller {

	@Autowired
	private SimpMessagingTemplate template;

	@GetMapping("/click")
	public String Button1Click(@RequestParam("name") String name) throws Exception {
		String topic = "/topic/greetings";
		String message = "Hello, " + name + "!!!";
		template.convertAndSend(topic, new Greeting(message));
		return "Successfully sent message [" + message + "] to topic [" + topic + "]";
	}
}
