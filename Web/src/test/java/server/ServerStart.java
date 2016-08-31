package server;

import config.DubboServerConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ServerStart {

    public static void main(String[] args) throws Exception{
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DubboServerConfig.class);
//        context.refresh();
        Thread.sleep(1000000);
    }
}
