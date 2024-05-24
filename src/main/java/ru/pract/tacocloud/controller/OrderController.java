package ru.pract.tacocloud.controller;

import jakarta.validation.Valid;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.ConstructorBinding;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import ru.pract.tacocloud.entity.TacoOrder;
import ru.pract.tacocloud.entity.UserTable;
import ru.pract.tacocloud.repository.OrderRepository;
import ru.pract.tacocloud.web.OrderProps;


@Controller
@RequestMapping(value = "/orders", method = { RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE })
@SessionAttributes(value = "tacoOrder")
public class OrderController {

    private OrderRepository orderRepository;
    private OrderProps orderProps;

    public OrderController(OrderRepository orderRepository, OrderProps orderProps){
        this.orderRepository = orderRepository;
        this.orderProps = orderProps;
    }

    @ModelAttribute("userTable")
    public UserTable userTable(){
        return (UserTable) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    @GetMapping("/ordersInfo")
    public String getOrders(){

        return "orderInfo";
    }

    @GetMapping
    public String ordersForUser(
            @AuthenticationPrincipal UserTable userTable, Model model) {

        Pageable pageable = PageRequest.of(0, orderProps.getPageSize());
        model.addAttribute("orders",
                orderRepository.findByUser_table_idOrderByPlaced_atDesc(userTable, pageable));
        return "orderList";
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
