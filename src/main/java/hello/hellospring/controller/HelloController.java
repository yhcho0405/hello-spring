package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("hello") // localhost:8080/hello로 들어오면
    public String hello(Model model) {
        model.addAttribute("data", "Hello!!"); // model data에 "hello!!" 담아서
        return "hello"; // templates/hello.html을 열어라
    }

    @GetMapping("hello-mvc")
    // @RequestParam("name") String name 때문에 localhost:8080/hello-mvc?name= 이런식으로
    public String helloMvc(@RequestParam("name") String name, Model model) {
        model.addAttribute("name", name);
        return "hello-template";
    }


    // API 방식, 얜 veiw 같은거 없이 문자 그대로 보냄, 위에 템플릿 엔진을 사용한 방식들은 화면을 렌더링해서 보내줌
    @GetMapping("hello-string")
    @ResponseBody // http res body에 return을 직접 넣겠다.
    public String helloString(@RequestParam("name") String name) {
        return "hello " + name;
    }

    // API방식 json으로 반환
    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    }

    static class Hello {
        private  String name;

        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
    }
}
