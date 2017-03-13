package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import service.test.StudentTestService;

import java.util.HashMap;
import java.util.Map;

@RestController
public class HelloWorld {

    @Autowired
    private StudentTestService studentTestService;

    @ResponseBody
    @RequestMapping("/home")
    public Map home() {
        return new HashMap<String, Object>() {{
            put("success", true);
        }};
    }

}
