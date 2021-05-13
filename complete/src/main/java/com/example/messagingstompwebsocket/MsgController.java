package com.example.messagingstompwebsocket;

import com.example.messagingstompwebsocket.ngcomponents.HelloComponent;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContextAware;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.context.ApplicationContext;

import javax.servlet.ServletContext;

/**
 * Controller for handling incoming messages
 */
@Controller
public class MsgController implements ApplicationContextAware {

	@Autowired
	private SimpMessagingTemplate template;


	private ApplicationContext ctx;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.ctx = applicationContext;
	}

	@MessageMapping("/echo")
	@SendTo("/topic/echo")
	public Msg getEcho(Msg msg) throws Exception {
		Thread.sleep(200); // simulated delay
		return new Msg("s", "You sent msg with type [" + msg.getType() + "] and text [" + msg.getText() + "]");
	}

	@MessageMapping("/msgtoserver")
	public void handleIncomingMessage(Msg msg) throws InterruptedException {
		// do anything what needed
		HelloComponent hc = ctx.getBean(HelloComponent.class);
		hc.setStatusline("Hello! msg received, type: [" + msg.getType() + "], text: [" + msg.getText() + "]");
		if (msg.getType().equals("s") && msg.getText().equals("job")) {
			for (int i = 0; i < 100; i++) {
				Thread.sleep(100);
				hc.setNewField("Doing job, " + i + " % done...");
			}
			hc.setNewField("Done!");
		}

	}

}
