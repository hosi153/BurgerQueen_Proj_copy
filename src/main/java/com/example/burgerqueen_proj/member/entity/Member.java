package com.example.burgerqueen_proj.member.entity;

import com.example.burgerqueen_proj.cart.entity.Cart;
import com.example.burgerqueen_proj.entity.BasicEntity;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor @Builder
@Table
@EntityListeners(AuditingEntityListener.class)
public class Member extends BasicEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long memberId;

    private String email;
    private String password;
    private String phone;
    private String userName;
    private String address1;
    private String address2;
    private String address3;

    @Builder.Default
    @Enumerated(EnumType.STRING)
    private MemberGrade grade = MemberGrade.GRADE_JOIN;

    private int stamp=0;

    @OneToOne(mappedBy = "member")
    private Cart cart;


    @OneToOne(mappedBy = "member", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private  Role role;

    public void setRole(Role role){
        this.role = role;
        if (role.getMember()!= this){
            role.setMember(this);
        }
    }

    public void setCart(Cart cart){
        this.cart = cart;
        if(this.cart.getMember() != this){
            this.cart.setMember(this);
        }
    }

    public void addStamp() {
        this.stamp = this.stamp+1;
        if(this.stamp >= grade.getUpgradeCondition() && !this.grade.equals(MemberGrade.GRADE_MASTER)){
            int gradeOrder = this.grade.ordinal();
            this.grade = MemberGrade.getGradeOrder(gradeOrder+1);
            this.stamp = 0;
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

    @Getter
    public enum MemberGrade{
        GRADE_JOIN(1,"신규", GradeBenefit.NOTHING,"등급혜택 없음",1),
        GRADE_MEMBER(2,"멤버",GradeBenefit.FREE,"무료 감자튀김 증정",2),
        GRADE_FRIEND(3,"친구",GradeBenefit.DISCOUNT,"10",6),
        GRADE_VIP(4,"VIP",GradeBenefit.DISCOUNT,"10",10),
        GRADE_MASTER(5,"최고",GradeBenefit.DISCOUNT,"50",100);

        private int gradeOrder;
        private String gradeDiscription;
        private GradeBenefit benefit;
        private String benefitDetail;
        private int upgradeCondition;

        MemberGrade(int gradeOrder, String gradeDiscription, GradeBenefit benefit, String benefitDetail, int upgradeCondition) {
            this.gradeOrder = gradeOrder;
            this.gradeDiscription = gradeDiscription;
            this.benefit = benefit;
            this.benefitDetail = benefitDetail;
            this.upgradeCondition = upgradeCondition;
        }

        private static final Map<Integer, MemberGrade> BY_NUMBER =
                Stream.of(values()).collect(Collectors.toMap(MemberGrade::getGradeOrder, Function.identity()));

        private static MemberGrade getGradeOrder(int id){
            return BY_NUMBER.get(id);
        }

    }

    @Getter
    public enum GradeBenefit{
        NOTHING("등급혜택 없음"),
        FREE("무료 사은품 증정"),
        DISCOUNT("등급 할인"); //% 할인

        private String benefitName;

        GradeBenefit(String benefitName) {
            this.benefitName = benefitName;
        }
    }





}
