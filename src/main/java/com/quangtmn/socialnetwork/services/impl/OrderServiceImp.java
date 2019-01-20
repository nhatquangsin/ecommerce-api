package com.quangtmn.socialnetwork.services.impl;

import com.quangtmn.socialnetwork.dtos.CartDto;
import com.quangtmn.socialnetwork.dtos.OrderDataDto;
import com.quangtmn.socialnetwork.entities.*;
import com.quangtmn.socialnetwork.repositories.CartRepository;
import com.quangtmn.socialnetwork.repositories.OrderSRepository;
import com.quangtmn.socialnetwork.repositories.ProductRepository;
import com.quangtmn.socialnetwork.services.CartService;
import com.quangtmn.socialnetwork.services.CustomerService;
import com.quangtmn.socialnetwork.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class OrderServiceImp implements OrderService {

    @Autowired
    CustomerService customerService;

    @Autowired
    CartService cartService;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CartRepository cartRepository;

    @Autowired
    OrderSRepository orderSRepository;

    @Override
    public void insertOrder(OrderDataDto orderDataDto) {
        int total = 0;
        OrderS orderS = orderSRepository.findTop1ByOrderByIdDesc().get(0);
        for (CartDto cartDto: orderDataDto.getCartData()) {
            Cart cart = new Cart();
            cart.setOrderId(orderS.getId() + 1);
            cart.setProductId(cartDto.getProductId());
            cart.setQuantity(cartDto.getQuantity());
            Product product = productRepository.findById(cartDto.getProductId()).get();
            cart.setPrice(product.getPrice());
            cart.setSalePrice(product.getSalePrice());
            cartRepository.save(cart);
            total += cart.getSalePrice() * cart.getQuantity();
        }
        OrderS order = new OrderS();
        Customer checkCustomer = customerService.getCustomerByPhone(orderDataDto.getCustomer().getPhone());
        if (checkCustomer != null) {
            order.setCustomerId(checkCustomer.getId());
        } else {
            customerService.insertCustomer(orderDataDto.getCustomer());
            Customer customer = customerService.getCustomerByPhone(orderDataDto.getCustomer().getPhone());
            order.setCustomerId(customer.getId());
        }
        order.setTotal(total);

        orderSRepository.save(order);
    }

    @Override
    public List<OrderS> getAllOrders() {
        return orderSRepository.findAll();
    }
}
