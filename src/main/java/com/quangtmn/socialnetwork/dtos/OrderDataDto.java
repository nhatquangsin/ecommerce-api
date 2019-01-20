package com.quangtmn.socialnetwork.dtos;

import com.quangtmn.socialnetwork.entities.Customer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDataDto {
    private Customer customer;
    private List<CartDto> cartData;
}
