package com.example.burgerqueen_proj.member.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long memberId;



    @OneToOne(mappedBy = "member", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private  Role role;

    public void setRole(Role role){
        this.role = role;
        if (role.getMember()!= this){
            role.setMember(this);
        }
    }


//    @OneToMany(mappedBy = "user")
//    private List<Order> orders = new ArrayList<>();
//
//    public void setOrder(Order order){
//        orders.add(order);
//        if (order.getMember() != this){
//            order.setMember(this);
//        }
//    }




    private String email;
//
//    private String password;
//
//    private String userName;
//
//    private Date birth;
//
//    private String sex;
//
//    private Date lastLogin;
//
//    private LocalDateTime createAt;
//
//    private LocalDateTime modifiedAt;

}
