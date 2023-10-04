package com.example.burgerqueen_proj.user.entity;

import com.example.burgerqueen_proj.order.entity.Order;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userId;



    @OneToOne(mappedBy = "user", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private  Role role;

    public void setRole(Role role){
        this.role = role;
        if (role.getUser()!= this){
            role.setUser(this);
        }
    }


    @OneToMany(mappedBy = "user")
    private List<Order> orders = new ArrayList<>();

    public void setOrder(Order order){
        orders.add(order);
        if (order.getUser() != this){
            order.setUser(this);
        }
    }




    private String email;

    private String password;

    private String userName;

    private Date birth;

    private String sex;

    private Date lastLogin;

    private LocalDateTime createAt;

    private LocalDateTime modifiedAt;

}
