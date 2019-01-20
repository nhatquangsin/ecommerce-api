package com.quangtmn.socialnetwork.services;

import com.quangtmn.socialnetwork.entities.Product;

import java.util.List;

public interface ProductService {
    Product getProductByName(String name);

    List<Product> getAllProducts();

    void insertProduct(Product product);

    void updateProductById(int id, Product product);

    void deleteProductById(int id);
}
