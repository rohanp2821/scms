package com.ackSystem.scms.controller;

import com.ackSystem.scms.entity.AdDocuments;
import com.ackSystem.scms.help.BuyOrder;
import com.ackSystem.scms.entity.OrderHeader;
import com.ackSystem.scms.entity.ProductInfo;
import com.ackSystem.scms.services.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/order")
public class MainController {

    Logger logger = LoggerFactory.getLogger(MainController.class);

    @Autowired
    private OrderService orderService;

    @PostMapping("/save")
    public OrderHeader saveOrder(@RequestBody OrderHeader orderHeader) {
        return orderService.saveOrder(orderHeader);
    }

    @GetMapping("/user/{userId}")
    public OrderHeader getOrder(@PathVariable String userId) {

        return orderService.getOrder(userId);

    }

    @GetMapping("/say")
    public String sayHello() {
        logger.warn("Date Required...");
        LocalDate now = LocalDate.now();
        logger.info("Date Granted : {}",now);
        return "Hello Rohan "+"\n"+ now;
    }

    @PostMapping("/product/save")
    public ProductInfo saveProduct(@RequestBody ProductInfo productInfo){

            return orderService.saveProduct(productInfo);
    }

    @GetMapping("/documents")
    public AdDocuments createDocuments(@RequestBody BuyOrder order) {
        return orderService.AdDocumentGenerateAndPersist(order);
    }



}
