package com.quangtmn.socialnetwork.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "customer")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull
    @Column(name = "firstname")
    private String firstname;
    @NotNull
    @Column(name = "lastname")
    private String lastname;
    @NotNull
    @Column(name = "phone")
    private String phone;
    @Column(name = "address")
    private String address;
}
