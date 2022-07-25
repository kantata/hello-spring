package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

    @GetMapping("hello") //request URL
    public String hello(Model model) {
        model.addAttribute("data", "hello!!");
        return "hello"; //return jsp template/+{ViewName}+.html
    }
}
