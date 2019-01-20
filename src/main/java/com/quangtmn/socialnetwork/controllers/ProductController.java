package com.quangtmn.socialnetwork.controllers;

import com.quangtmn.socialnetwork.entities.Product;
import com.quangtmn.socialnetwork.messages.Message;
import com.quangtmn.socialnetwork.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@CrossOrigin
@RestController
public class ProductController {
    @Autowired
    ProductService productService;

    @GetMapping(path = "/product/{name}")
    public Resource<Product> getProductByName(@PathVariable String name) {
        Product product = productService.getProductByName(name);
        Resource<Product> resource = new Resource<>(product);
        ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).getAllProducts());

        resource.add(linkTo.withRel("all-products"));
        return resource;
    }

    @GetMapping(path = "/all-products")
    public ResponseEntity getAllProducts() {
        return ResponseEntity.ok().body(productService.getAllProducts());
    }

    @PostMapping(path = "/new-product")
    public ResponseEntity insertProduct(@Valid @RequestBody Product product) {
        productService.insertProduct(product);
        return ResponseEntity.ok().body(Message.INSERT_PRODUCT_SUCCESS);
    }

    @PutMapping(path = "/update-product/id/{id}")
    public ResponseEntity updateProductById(@PathVariable int id, @RequestBody Product product) {
        productService.updateProductById(id, product);
        return ResponseEntity.ok().body(Message.UPDATE_PRODUCT_SUCCESS);
    }

    @DeleteMapping(path = "/delete-product/id/{id}")
    public ResponseEntity deleteProductById(@PathVariable int id) {
        productService.deleteProductById(id);
        return ResponseEntity.ok().body(Message.DELETE_PRODUCT_SUCCESS);
    }
}
