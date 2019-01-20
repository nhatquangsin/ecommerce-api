package com.quangtmn.socialnetwork.services.impl;

import com.quangtmn.socialnetwork.entities.Cart;
import com.quangtmn.socialnetwork.repositories.CartRepository;
import com.quangtmn.socialnetwork.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImp implements CartService {
    @Autowired
    CartRepository cartRepository;

    @Override
    public void insertCart(Cart cart) {
        cartRepository.save(cart);
    }
}
