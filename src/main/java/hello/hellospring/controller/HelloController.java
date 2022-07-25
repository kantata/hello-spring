package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("hello") //request URL
    public String hello(Model model) {
        model.addAttribute("data", "hello!!");
        return "hello"; //return jsp template/+{ViewName}+.html
    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam(value = "name", required = false) String name, Model model) {
        model.addAttribute("name", name);
        return "hello-template";
    }

    @GetMapping("hello-spring")
    @ResponseBody //api 형태 -> 그대로 데이터를 넣어줌
    public String helloString(@RequestParam("name") String name) {
        return "hello " + name; // 문자라서 그냥 리턴함 ViewResolver
    }

    @GetMapping("hello-api") // /hello-api?name=spring
    @ResponseBody
    public Hello hellpApi(@RequestParam("name") String name) {
        Hello hello = new Hello();
        hello.setName(name);
        return hello; // 객체가 오면 return json HttpMessageConverter
    }

    static class Hello {

        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
