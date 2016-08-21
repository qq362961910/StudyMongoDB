package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.test.StudentTestService;

@RestController
public class HelloWorld {

    @Autowired
    private StudentTestService studentTestService;

    @RequestMapping(value = "/home")
    public String home(){
        return "home";
    }

}
