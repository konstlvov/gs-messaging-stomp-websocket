package com.example.messagingstompwebsocket;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import java.util.Arrays;

@Aspect
@Configuration
public class NgComponentAspect {

	@Autowired
	private SimpMessagingTemplate template;

	@Before("execution(* com.example.messagingstompwebsocket.ngcomponents.*.set*(..))")
	public void before(JoinPoint joinPoint){
		System.out.println("before called, joinPoint: " + joinPoint);
		System.out.println("joinPoint.getSignature().getName(): " + joinPoint.getSignature().getName());
		System.out.println("joinPoint.getSignature().getArgs(): " + Arrays.asList(joinPoint.getArgs()));
	}

	@After("execution(* com.example.messagingstompwebsocket.ngcomponents.*.set*(..))")
	public void after(JoinPoint joinPoint){
		System.out.println("after called, joinPoint: " + joinPoint);
		//System.out.println("joinPoint.getSignature().getArgs(): " + Arrays.asList(joinPoint.getArgs()));
		String[] fqn = joinPoint.getSignature().getDeclaringType().getName().split("\\.");
		String component = fqn[fqn.length - 1];
		String method = joinPoint.getSignature().getName();
		String field = method.replaceAll("^set", "");
		field = Character.toLowerCase(field.charAt(0)) + field.substring(1);
		String newFieldValue = String.valueOf(joinPoint.getArgs()[0]);
		System.out.println("component: " + component);
		System.out.println("method: " + method);
		System.out.println("field: " + field);
		System.out.println("argument: " + newFieldValue);
		UpdateComponentFieldMsg msg = new UpdateComponentFieldMsg(component, field, newFieldValue);
		String topic = "/topic/" + component;
		template.convertAndSend(topic, msg);
	}
}
