package com.example.burgerqueen_proj.order.service;


import com.example.burgerqueen_proj.exception.BusinessLogicException;
import com.example.burgerqueen_proj.exception.ExceptionCode;
import com.example.burgerqueen_proj.order.entity.Order;
import com.example.burgerqueen_proj.order.repository.OrderRepository;
import com.example.burgerqueen_proj.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final MemberService memberService;

    public Order creatOrder(Order order){
        order.setOrderStatus(Order.OrderStatus.ORDER_REQUEST);
        order.setMember(memberService.findUser(order.getMember().getMemberId())); //굳이 주문에서 유저를 찾아갈 필요가 있는가?
        return orderRepository.save(order);
    }

    public Order findOrder(long orderId){
        return findVerifiedOrder(orderId);
    }

    public Page<Order> findOrders(int page,int size){
        return orderRepository.findAll(PageRequest.of(page,size, Sort.by("orderId").descending()));
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

}
