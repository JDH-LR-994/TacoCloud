package jdh.lr.tacocloud.web;


import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import jdh.lr.tacocloud.Taco;
import jdh.lr.tacocloud.TacoOrder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model; //Объект для общения представлений, ответственных за использование этих данных в HTML
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import lombok.extern.slf4j.Slf4j; //Simple Logging Facade for Java

import jdh.lr.tacocloud.Ingredient;
import jdh.lr.tacocloud.Ingredient.Type;

@Slf4j //Генерит свойство типа Logger
@Controller //Пометили как контроллер, чтобы среда автоматически могла создать экземпляр класса в виде bean - компонента
@RequestMapping("/design") //Класс будет обрабатывать запросы пути, которые начинаются с /design
@SessionAttributes("tacoOrder")  //Объект TacoOrder должен поддерживаться на уровне сеанса
public class DesignTacoController {

    @ModelAttribute
    public void addIngredientToModel(Model model) //Создание списка ингредиентов для формы
    {
        List<Ingredient> ingredients = Arrays.asList(
                new Ingredient("FLTO", "Flour Tortilla", Ingredient.Type.WRAP),
                new Ingredient("COTO", "Corn Tortilla", Ingredient.Type.WRAP),
                new Ingredient("GRBF", "Ground Beef", Ingredient.Type.PROTEIN),
                new Ingredient("CARN", "Carnitas", Ingredient.Type.PROTEIN),
                new Ingredient("TMTO", "Diced Tomatoes", Ingredient.Type.VEGETABLE),
                new Ingredient("LETC", "Lettuce", Ingredient.Type.VEGETABLE),
                new Ingredient("CHED", "Cheddar", Ingredient.Type.CHEESE),
                new Ingredient("JACK", "Monterrey Jack", Ingredient.Type.CHEESE),
                new Ingredient("SLSA", "Salsa", Ingredient.Type.SAUCE),
                new Ingredient("SRCR", "Sour Cream", Ingredient.Type.SAUCE)
        );

        Type[] types = Ingredient.Type.values();
        for (Type type : types) {
            model.addAttribute(type.toString().toLowerCase(),
                    filterByType(ingredients, type)); //Добавляем список типов ингредиентов в атрибуты Model
        }
    }

    @ModelAttribute(name = "tacoOrder") //Создание того самого объекта для класса (26 строка)
    public TacoOrder order() {
        return new TacoOrder();
    }

    @ModelAttribute(name = "taco") //Объект для формы
    public Taco taco() {
        return new Taco();
    }

    @GetMapping //(В связке с классом) Помечает метод для обработки HTTP - запроса GET с путём /design
    public String showDeignForm() {
        return "design";
    }

    @PostMapping //Обрабатывает POST запрос с /design (Добавляет созданный тако в заказ)
    public String processTaco(Taco taco,
                              @ModelAttribute TacoOrder tacoOrder) //Использовать объект, помещённый в модель
    {
        tacoOrder.addTaco(taco);
        log.info("Processing taco: {}", taco);

        return "redirect:/orders/current";
    }

    private Iterable<Ingredient> filterByType(
            List<Ingredient> ingredients, Type type) {
        return ingredients
                .stream()
                .filter(x -> x.getType().equals(type))
                .collect(Collectors.toList());
    }
}
