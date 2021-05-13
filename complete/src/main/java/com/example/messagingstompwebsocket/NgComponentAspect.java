package com.example.messagingstompwebsocket;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import java.util.Arrays;

@Aspect
@Configuration
public class NgComponentAspect {

	@Autowired
	private SimpMessagingTemplate template;

	@After("execution(* com.example.messagingstompwebsocket.ngcomponents.*.set*(..))")
	public void after(JoinPoint joinPoint){
		String[] fqn = joinPoint.getSignature().getDeclaringType().getName().split("\\.");
		String component = fqn[fqn.length - 1];
		String method = joinPoint.getSignature().getName();
		String field = method.replaceAll("^set", "");
		field = Character.toLowerCase(field.charAt(0)) + field.substring(1);
		String newFieldValue = String.valueOf(joinPoint.getArgs()[0]);
		UpdateComponentFieldMsg msg = new UpdateComponentFieldMsg(component, field, newFieldValue);
		String topic = "/topic/" + component;
		template.convertAndSend(topic, msg);
	}
}
