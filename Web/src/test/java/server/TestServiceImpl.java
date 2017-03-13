package server;

import com.alibaba.dubbo.config.annotation.Service;
import common.TestService;

@Service(version = "1.0", timeout = 5000)
public class TestServiceImpl implements TestService {

    @Override
    public String getName() {
        return "John";
    }

}
