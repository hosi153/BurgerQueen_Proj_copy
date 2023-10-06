package com.example.burgerqueen_proj.member.entity;

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
    @JoinColumn(name="MEMBER_ID")
    private Member member;

    public void setMember(Member member){
        this.member = member;
        if (member.getRole()!=this){
            member.setRole(this);
        }

    }
}
