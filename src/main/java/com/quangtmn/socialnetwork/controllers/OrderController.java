package com.quangtmn.socialnetwork.controllers;

import com.quangtmn.socialnetwork.dtos.OrderDataDto;
import com.quangtmn.socialnetwork.entities.OrderS;
import com.quangtmn.socialnetwork.messages.Message;
import com.quangtmn.socialnetwork.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OrderController {
    @Autowired
    OrderService orderService;

    @PostMapping(path = "/new-order")
    public ResponseEntity createNewOrder(@RequestBody OrderDataDto orderDataDto) {
        orderService.insertOrder(orderDataDto);
        return ResponseEntity.ok().body(Message.INSERT_ORDER_SUCCESS);
    }

    @GetMapping(path = "/all-orders")
    public List<OrderS> getAllOrders() {
        return orderService.getAllOrders();
    }
}