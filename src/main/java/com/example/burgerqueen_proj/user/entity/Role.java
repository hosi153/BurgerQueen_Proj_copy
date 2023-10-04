package com.example.burgerqueen_proj.user.entity;

import com.example.burgerqueen_proj.promotion.entity.PromotionDetails;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long roleId;

    private String Name;

    @OneToMany(mappedBy = "role")
    private List<PromotionDetails> promotionDetails = new ArrayList<>();

    @OneToOne
    @JoinColumn(name="USER_ID")
    private User user;

    public void setUser(User user){
        this.user =user;
        if (user.getRole()!=this){
            user.setRole(this);
        }

    }
}
