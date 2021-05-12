package com.example.messagingstompwebsocket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.util.HtmlUtils;

import javax.servlet.ServletContext;

@Controller
public class GreetingController implements ServletContextAware {

	@Autowired
	private SimpMessagingTemplate template;

	private ServletContext servletContext;

	@Override
	public void setServletContext(ServletContext servletContext) {
		this.servletContext=servletContext;
		System.out.println("servlet context set to " + servletContext);
	}

	@MessageMapping("/hello")
	@SendTo("/topic/greetings")
	public Greeting greeting(HelloMessage message) throws Exception {
		System.out.println("Endpoint app/hello received message, name is: " + message.getName());
		Thread.sleep(200); // simulated delay
		return new Greeting("Hello, " + HtmlUtils.htmlEscape(message.getName()) + "!");
	}

	@MessageMapping("/run-long-job")
	public void runLongJob() throws Exception {
		String topic = "/topic/msg";
		for (int i = 0; i < 100; i++) {
			String sMsg = "Doing job, " + i + " percents done...";
			Msg msg = new Msg("statusline", sMsg);
			template.convertAndSend(topic, msg);
			Thread.sleep(100); // simulated delay
		}
		Msg msg = new Msg("statusline", "Done!");
		template.convertAndSend(topic, msg);
	}

}
