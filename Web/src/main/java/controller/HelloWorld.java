package controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class HelloWorld {

    @ResponseBody
    @RequestMapping("/home")
    public Map home() {
        return new HashMap<String, Object>() {{
            put("success", true);
        }};
    }

}
