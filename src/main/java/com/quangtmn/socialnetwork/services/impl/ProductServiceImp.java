package com.quangtmn.socialnetwork.services.impl;

import com.quangtmn.socialnetwork.entities.Product;
import com.quangtmn.socialnetwork.exceptions.ProductNotFoundException;
import com.quangtmn.socialnetwork.repositories.ProductRepository;
import com.quangtmn.socialnetwork.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImp implements ProductService {
    @Autowired
    ProductRepository productRepository;

    @Override
    public Product getProductByName(String name) {
        Product product = productRepository.getProductByName(name);
        if (product != null) {
            return product;
        } else {
            throw new ProductNotFoundException("Product name is not exist");
        }
    }

    @Override
    public List<Product> getAllProducts() {
        List<Product> products = productRepository.findAll();
        if (products.size() > 0) {
            return products;
        } else {
            throw new ProductNotFoundException("There is no record");
        }
    }

    @Override
    public void insertProduct(Product product) {
        productRepository.save(product);
    }

    @Override
    public void updateProductById(int id, Product product) {
        Optional<Product> dataProduct = productRepository.findById(id);
        if (dataProduct.isPresent()) {
            if (product.getName() != null) dataProduct.get().setName(product.getName());
            if (product.getImage() != null) dataProduct.get().setImage(product.getImage());
            if (product.getPrice() > 0) dataProduct.get().setPrice(product.getPrice());
            if (product.getSalePrice() > 0) dataProduct.get().setSalePrice(product.getSalePrice());
            productRepository.save(dataProduct.get());
        } else {
            throw new ProductNotFoundException("Product id do not exist");
        }
    }

    @Override
    public void deleteProductById(int id) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()) {
            productRepository.deleteById(id);
        } else {
            throw new ProductNotFoundException("Product id do not exist");
        }
    }
}
