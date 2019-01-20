package com.quangtmn.socialnetwork.repositories;

import com.quangtmn.socialnetwork.entities.OrderS;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderSRepository extends JpaRepository<OrderS, Integer> {
    List<OrderS> findTop1ByOrderByIdDesc();
}
