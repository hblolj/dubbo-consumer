package com.hblolj.dubbo.consumer;

import com.hblolj.dubbo.consumer.action.AnnotationAction;
import com.hblolj.dubbo.provider.DemoService;
import com.hblolj.dubbo.provider.ProtocolService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * @auther Ori
 * @Date 2019/6/13 22 05
 * @Description
 */
public class App {

    public static void main(String[] args) throws IOException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("consumer.xml");
//        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ConsumerConfiguration.class);
        context.start();

        // 这里使用 Zookeeper 主要作为订阅使用，所以 Zookeeper 没有启动的话，也不会报错
//        AnnotationAction action = (AnnotationAction) context.getBean("annotationAction");
//        String result = action.doSayHello("Dubbo Registry In Zookeeper");
//        System.out.println(result);

        DemoService demoService = (DemoService) context.getBean("demoService");
        ProtocolService protocolService = (ProtocolService) context.getBean("protocolService");

        System.out.println(demoService.sayBye("Zard"));
        System.out.println(protocolService.testMultiProcotol());
        System.out.println("Set Reference Check is false");
        System.in.read();
    }
}
