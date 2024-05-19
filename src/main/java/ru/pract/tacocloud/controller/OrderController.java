package ru.pract.tacocloud.controller;

import jakarta.validation.Valid;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import ru.pract.tacocloud.entity.TacoOrder;
import ru.pract.tacocloud.entity.UserTable;
import ru.pract.tacocloud.repository.OrderRepository;

@Controller
@RequestMapping(value = "/orders", method = { RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE })
@SessionAttributes(value = "tacoOrder")
public class OrderController {

    private OrderRepository orderRepository;

    public OrderController(OrderRepository orderRepository){
        this.orderRepository = orderRepository;
    }

    @GetMapping(value = {"/current", "/"})
    public String orderForm(){
        return "orderForm";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    @ResponseBody
    public void deleteAllOrders(){
        orderRepository.deleteAll();
    }

    @PostMapping("/process")
    public String processOrder(@Valid TacoOrder order, Errors errors, SessionStatus sessionStatus, @AuthenticationPrincipal UserTable userTable){
        if(errors.hasErrors()){
            return "orderForm";
        }

        order.setUserTable(userTable);

        orderRepository.save(order);
        sessionStatus.setComplete();

        return "redirect:/";
    }
}
