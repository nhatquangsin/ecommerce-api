package com.quangtmn.socialnetwork.services;

import com.quangtmn.socialnetwork.dtos.OrderDataDto;
import com.quangtmn.socialnetwork.entities.OrderS;

import java.util.List;

public interface OrderService {
    void insertOrder(OrderDataDto orderDataDto);

    List<OrderS> getAllOrders();
}
