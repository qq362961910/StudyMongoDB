package client;

import com.alibaba.dubbo.config.annotation.Reference;
import common.TestService;
import org.springframework.stereotype.Component;

@Component
public class TestServiceInvoker {

    @Reference(check = false, version = "1.0")
    private TestService testService;

    public String invokeGetName() {
        return testService.getName();
    }

}
