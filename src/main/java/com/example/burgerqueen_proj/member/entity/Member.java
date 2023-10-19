package com.example.burgerqueen_proj.member.entity;

import com.example.burgerqueen_proj.cart.entity.Cart;
import com.example.burgerqueen_proj.entity.BasicEntity;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor @Builder
@Table
@EntityListeners(AuditingEntityListener.class)
public class Member extends BasicEntity implements UserDetails {




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

    @OneToOne(mappedBy = "member", cascade = {CascadeType.ALL, CascadeType.REMOVE})
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
//            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>.");
//            System.out.println(gradeOrder+" "+this.grade.getGradeDiscription());
            this.stamp = 0;
        }
    }

    public void update(Member member) {
        Optional.ofNullable(member.getPassword()).ifPresent(this::setPassword);
        Optional.ofNullable(member.getUserName()).ifPresent(this::setUserName);
        Optional.ofNullable(member.getAddress1()).ifPresent(this::setAddress1);
        Optional.ofNullable(member.getAddress2()).ifPresent(this::setAddress2);
        Optional.ofNullable(member.getAddress3()).ifPresent(this::setAddress3);
        Optional.ofNullable(member.getPhone()).ifPresent(this::setPhone);
    }

    public String getUserName() {
        return this.userName;
    }

    @Transient
    private Collection<SimpleGrantedAuthority> authorities;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }
    @Override
    public String getUsername() {
        return this.email;
    }


    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
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
        GRADE_JOIN(0,"신규", GradeBenefit.NOTHING,"등급혜택 없음",1),
        GRADE_MEMBER(1,"멤버",GradeBenefit.DISCOUNT,"5",2),
        GRADE_FRIEND(2,"친구",GradeBenefit.DISCOUNT,"10",3),
        GRADE_VIP(3,"VIP",GradeBenefit.DISCOUNT,"15",4),
        GRADE_MASTER(4,"최고",GradeBenefit.DISCOUNT,"50",100);

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



    public String getRoleKey() {
        return this.getGrade().getGradeDiscription();
    }

    public Member update(String name) {
        this.userName = name;

        return this;
    }

    @Column
    @Enumerated(EnumType.STRING)
    private SocialType socialType;

    public Member(String email) {
        this.email = email;
    }



    private String provider;
    private String providerId;



}
