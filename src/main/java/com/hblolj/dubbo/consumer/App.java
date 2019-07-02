package com.hblolj.dubbo.consumer;

import com.alibaba.dubbo.rpc.RpcContext;
import com.hblolj.dubbo.consumer.action.AnnotationAction;
import com.hblolj.dubbo.provider.DemoService;
import com.hblolj.dubbo.provider.ProtocolService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.time.Instant;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * @auther Ori
 * @Date 2019/6/13 22 05
 * @Description
 */
public class App {

    public static void main(String[] args) throws IOException, ExecutionException, InterruptedException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("consumer.xml");
//        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ConsumerConfiguration.class);
//        context.start();
//        testAnnotation(context);

        // 异步调用同步
//        testAsyncInvokeProviderSynchronizationMethod(context);

        // 异步调用异步
//        testAsyncInvokeProviderAsyncMethod(context);

        // 同步调用异步

        // 负载均衡 Provider 与 Custom 都可以配置，粒度可以配置到方法级别，建议配置到 Provider 端，方便控制
        DemoService demoService = (DemoService) context.getBean("demoService");
        for (int i = 0; i < 10; i++) {
            System.out.println(demoService.sayBye(i + ""));
        }

        // 同步调用同步
        ProtocolService protocolService = (ProtocolService) context.getBean("protocolService");
        System.out.println(protocolService.testMultiProcotol());

//        System.out.println(demoService.sayBye("Zard"));
        System.out.println("Set Reference Check is false");
        System.in.read();
    }

    // dubbo 2.7 之后才支持 provider 异步执行
    private static void testAsyncInvokeProviderAsyncMethod(ClassPathXmlApplicationContext context) {
        System.out.println(Instant.now());
        DemoService demoService = (DemoService) context.getBean("demoService");
        CompletableFuture<String> future = demoService.anyncSayHello("辛吉德");
        new Thread(() -> {
            try {
                while (future == null || future.get() == null){
                    TimeUnit.MILLISECONDS.sleep(10);
                }
                System.out.println(future.get());
                System.out.println(Instant.now());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }).start();
    }

    // 异步调用 Provider 同步方法
    private static void testAsyncInvokeProviderSynchronizationMethod(ClassPathXmlApplicationContext context) {
        System.out.println(Instant.now());
        DemoService demoService = (DemoService) context.getBean("demoService");
        String result = demoService.sayHello("沃里克");
        Future<String> future = RpcContext.getContext().getFuture();
        new Thread(() -> {
            try {
                while (future.get() == null){
                    TimeUnit.MILLISECONDS.sleep(10);
                }
                System.out.println(future.get());
                System.out.println(Instant.now());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }).start();
        System.out.println(result);
    }

    // 测试注解方式引用服务
    private static void testAnnotation(AnnotationConfigApplicationContext context) {
        AnnotationAction action = (AnnotationAction) context.getBean("annotationAction");
        String result = action.doSayHello("Dubbo Registry In Zookeeper");
        System.out.println(result);
    }
}
