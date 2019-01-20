package com.quangtmn.socialnetwork.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull
    @Size(min = 3, message = "Username should have at least 3 characters")
    private String username;
    @NotNull
    @Size(min = 3, message = "Password should have at least 3 characters")
    private String password;
    @Email(message = "Invalid email")
    private String email;
    @Column(name = "enabled")
    private int enabled;
    @Column(name = "role")
    private String role;

    public User(@NotNull @Size(min = 3, message = "Username should have at least 3 characters") String username, @NotNull @Size(min = 3, message = "Password should have at least 3 characters") String password) {
        this.username = username;
        this.password = password;
    }
}
