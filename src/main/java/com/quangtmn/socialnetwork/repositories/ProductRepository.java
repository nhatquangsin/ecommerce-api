package com.quangtmn.socialnetwork.repositories;

import com.quangtmn.socialnetwork.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    Product getProductByName(String name);
}
