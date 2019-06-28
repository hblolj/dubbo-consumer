package com.hblolj.dubbo.consumer;

import com.hblolj.dubbo.consumer.action.AnnotationAction;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;

/**
 * @auther Ori
 * @Date 2019/6/13 22 05
 * @Description
 */
public class App {

    public static void main(String[] args) throws IOException {
//        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("consumer.xml");
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ConsumerConfiguration.class);
        context.start();

        AnnotationAction action = (AnnotationAction) context.getBean("annotationAction");
        String result = action.doSayHello("Dubbo Annotation Service Exclude Xml Config");
        System.out.println(result);
    }
}
