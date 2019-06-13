package com.hblolj.dubbo.consumer;

import com.hblolj.dubbo.order.IOrderService;
import com.hblolj.dubbo.order.OrderDTO;
import com.hblolj.dubbo.order.OrderVO;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * @auther Ori
 * @Date 2019/6/13 22 05
 * @Description
 */
public class App {

    public static void main(String[] args) throws IOException {

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("order-consumer.xml");
        IOrderService service = (IOrderService) context.getBean("orderService");;

        OrderDTO dto = new OrderDTO();
        dto.setName("Zard");
        OrderVO vo = service.doOrder(dto);
        System.out.println(vo);

        System.in.read();
    }
}
