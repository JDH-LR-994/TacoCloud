package jdh.lr.tacocloud;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller //Контроллер
public class HomeController {

    @GetMapping("/") //Обрабатывает запросы с корневым путём /
    public String home() {
        return "home"; //Возвращает имя представления
    }

}
