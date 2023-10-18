package com.example.burgerqueen_proj.order.service;


import com.example.burgerqueen_proj.cart.entity.Cart;
import com.example.burgerqueen_proj.cart.service.CartService;
import com.example.burgerqueen_proj.exception.BusinessLogicException;
import com.example.burgerqueen_proj.exception.ExceptionCode;
import com.example.burgerqueen_proj.member.entity.Member;
import com.example.burgerqueen_proj.order.entity.Order;
import com.example.burgerqueen_proj.order.repository.OrderRepository;
import com.example.burgerqueen_proj.member.service.MemberService;
import com.example.burgerqueen_proj.product.entity.Product;
import com.example.burgerqueen_proj.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ast.Or;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Component
public class OrderService {

    private final OrderRepository orderRepository;
    private final MemberService memberService;
    private final ProductService productService;
    private final CartService cartService;

    public OrderService(OrderRepository orderRepository, MemberService memberService, ProductService productService, CartService cartService) {
        this.orderRepository = orderRepository;
        this.memberService = memberService;
        this.productService = productService;
        this.cartService = cartService;
    }

    @Transactional
    public Order creatOrder(Order order){
        verifyOrder(order);
        stockCheck(order);
        order.setOrderStatus(Order.OrderStatus.ORDER_REQUEST);
        order.setMember(memberService.findMember(order.getMember().getMemberId())); //굳이 주문에서 유저를 찾아갈 필요가 있는가?
        order.getMember().addStamp();



        return orderRepository.save(order);
    }


    public Order findOrder(long orderId){
        return findVerifiedOrder(orderId);
    }


    public Page<Order> findOrders(int page,int size){
        return orderRepository.findAll(PageRequest.of(page,size, Sort.by("orderId").descending()));
    }

    private void verifyOrder(Order order) {


        //상품이 존재하는지 확인
        order.getOrderProducts().stream()
                .forEach(cartProduct ->  cartProduct.setProduct(productService.findProduct(
                        cartProduct.getProduct().getProductId())));


        //주문 총액 계산
        order.getOrderProducts().stream()
                .forEach(cartProduct -> order.setTotalPrice(order.getTotalPrice()+ (
                        cartProduct.getQuantity() * cartProduct.getProduct().getDiscountPrice())));


        // 회원이 존재하는지 확인
        order.setMember(memberService.findMember(order.getMember().getMemberId()));


        if (order.getMember().getGrade().getBenefit().equals(Member.GradeBenefit.DISCOUNT)){
            double v = 0.01*  order.getTotalPrice() * Double.valueOf(order.getMember().getGrade().getBenefitDetail());
            order.setTotalDiscountPrice((int)v);
        }



    }

    public List<Order> findOrderByMember(Member member){

        return orderRepository.findByMember(member);
    }

    public void deleteOrder(long orderId){
        Order findOrder = findVerifiedOrder(orderId);
        findOrder.setOrderStatus(Order.OrderStatus.ORDER_CANCEL);
        orderRepository.save(findOrder);
    }


    private Order findVerifiedOrder(long orderId){
        Optional<Order> optionalOrder = orderRepository.findById(orderId);
        Order findOrder = optionalOrder.orElseThrow(() -> new BusinessLogicException(ExceptionCode.ORDER_NOT_FOUND));
        return findOrder;
    }

    public void stockCheck(Order order){

        order.getOrderProducts().stream()
                .forEach(cartProduct -> cartProduct.getProduct().setProductCount(
                        cartProduct.getProduct().getProductCount() - cartProduct.getQuantity()));



        for (int i = 0; i < order.getOrderProducts().size(); i++) {
            // 재고 확인
            if (order.getOrderProducts().get(i).getProduct().getProductCount() <= 0){
                throw  new BusinessLogicException(ExceptionCode.STOCK_SHORTAGE);
            }
            // 판매 상태 확인
            if (order.getOrderProducts().get(i).getProduct().getProductStatus() == Product.ProductStatus.PRODUCT_STOP)
            {
                throw new BusinessLogicException(ExceptionCode.NOT_FOR_SALE);
            }


        }



    }

}
