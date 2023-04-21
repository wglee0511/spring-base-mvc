package springstart.springstart.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {
    @GetMapping("hello")
    public String hello(Model model) {
        model.addAttribute("data", "hello!!");
        model.addAttribute("name", "what");
        return "hello";
    }
    @GetMapping("mvc_1")
    public  String mvc_1(@RequestParam("name") String name, Model model) {
        model.addAttribute("data", "etc data");
        model.addAttribute("name", name);
        return "mvc_test_1";
    }

    @GetMapping("apiTest1")
    @ResponseBody
    public  String apiTest1(@RequestParam("name") String name){
        return "name" + name;
    }

    @GetMapping("apiTest2")
    @ResponseBody
    public ApiTest1 apiTest2(@RequestParam("name") String name) {
        ApiTest1 apiTest2 = new ApiTest1();
        apiTest2.setName(name);
        return apiTest2;
    }

    static class ApiTest1 {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
