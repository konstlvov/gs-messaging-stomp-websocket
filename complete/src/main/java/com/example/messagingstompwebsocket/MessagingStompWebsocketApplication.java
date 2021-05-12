package com.example.messagingstompwebsocket;

import com.example.messagingstompwebsocket.ngcomponents.HelloComponent;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import java.io.BufferedReader;
import java.io.InputStreamReader;

@SpringBootApplication
@EnableAspectJAutoProxy
public class MessagingStompWebsocketApplication {

	public static void handleCommand(ApplicationContext ctx, String command) throws Exception {
		System.out.println("You typed: " + command);
		GreetingController gc = ctx.getBean(GreetingController.class);
		HelloComponent hc = ctx.getBean(HelloComponent.class);
		if (command.equals("lj")) {
			System.out.println("About to run long job...");
			gc.runLongJob();
		}
		if (command.equals("set")) {
			hc.setHeader("This is header");
			hc.setGreeting("This is greeting");
			hc.setMsg("This is msg");
			hc.setStatusline("This is statusline");
		}
		if (command.equals("get")) {
			String s = hc.getHeader();
		}
		if (command.equals("cl")) {
			String s = "setComponentHeader";
			s = "setMsg";
			String fn = s.replaceAll("^set", "");
			fn = Character.toLowerCase(fn.charAt(0)) + fn.substring(1);
			System.out.println(fn);
		}
		System.out.print(">");
	}

	public static void main(String[] args) throws Exception {
		ConfigurableApplicationContext ctx = SpringApplication.run(MessagingStompWebsocketApplication.class, args);
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.print(">");
		while (true) {
			String command = br.readLine().trim();
			handleCommand(ctx, command);
		}
	}
}
