package client;

import config.DubboClientConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ClientStart {
    public static void main(String[] args) throws Exception{
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DubboClientConfig.class);
//        context.refresh();
        TestServiceInvoker client = context.getBean(TestServiceInvoker.class);
        String name = client.invokeGetName();
        System.out.println("name: " + name);
        Thread.sleep(1000000);
    }
}
