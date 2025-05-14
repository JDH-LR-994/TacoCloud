package jdh.lr.tacocloud.web;

import jakarta.validation.Valid;
import jdh.lr.tacocloud.TacoOrder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

@Slf4j
@Controller
@RequestMapping("/orders")
@SessionAttributes("tacoOrder")
public class OrderController {

    @GetMapping("/current")
    public String orderForm( Model model ) {
        if ( !model.containsAttribute("tacoOrder") ) {
            model.addAttribute("tacoOrder", new TacoOrder());
        }
        return "orderForm";
    }

    @PostMapping
    public String processOrder( @Valid TacoOrder order, Errors errors,
                                SessionStatus sessionStatus ) {
        if ( errors.hasErrors() ) {
            return "orderForm";
        }
        log.info("Order submitted: {}", order);
        sessionStatus.setComplete();

        return "redirect:/";
    }
}
