package com.example.burgerqueen_proj.delivery.service;

import com.example.burgerqueen_proj.cart.entity.Cart;
import com.example.burgerqueen_proj.cart.repository.CartProductRepository;
import com.example.burgerqueen_proj.cart.repository.CartRepository;
import com.example.burgerqueen_proj.cart.service.CartService;
import com.example.burgerqueen_proj.delivery.entity.Delivery;
import com.example.burgerqueen_proj.delivery.repository.DeliveryRepository;
import com.example.burgerqueen_proj.exception.BusinessLogicException;
import com.example.burgerqueen_proj.exception.ExceptionCode;
import com.example.burgerqueen_proj.member.entity.Member;
import com.example.burgerqueen_proj.order.entity.Order;
import com.example.burgerqueen_proj.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DeliveryService {

    private final DeliveryRepository deliveryRepository;

    private final OrderService orderService;
    private  final CartService cartService;
    private  final CartProductRepository cartProductRepository;


    @Transactional
    public Delivery createDelivery(Delivery delivery){
//        Delivery findDelivery = findVerifyDelivery(delivery.getDeliveryId());
        delivery.setDeliveryStatus(Delivery.DeliveryStatus.DELIVERY_READY);

        delivery.setOrder(orderService.findOrder(delivery.getOrder().getOrderId()));


        Order order= orderService.findOrder(delivery.getOrder().getOrderId());
        Cart cart = cartService.findCartByMember(order.getMember());
        Cart findCart = cartService.findVerifiedCart(cart.getCartId());
        cartProductRepository.deleteAllByCart(findCart);




        System.out.println(cart.getCartId());
        cartService.clearCartProduct(cart.getCartId());

        System.out.println("뒤져라");


        return deliveryRepository.save(delivery);
    }

    public void  updateDelivery(Delivery delivery){
        deliveryRepository.save(delivery);

    }
    public Delivery findDelivery(long deliveryId){
        return findVerifyDelivery(deliveryId);
    }

    public List<Delivery> findDeliveries(){
        return deliveryRepository.findAll(Sort.by(Sort.Direction.DESC,"createdAt"));
    }



    private Delivery findVerifyDelivery(long deliveryId){
        Optional<Delivery> optionalDelivery = deliveryRepository.findById(deliveryId);
        Delivery delivery = optionalDelivery.orElseThrow(() ->
                new BusinessLogicException(ExceptionCode.DELIVERY_NOT_FOUND));

        return delivery;
    }


    public void changeDeliveryStatus(){
        List<Delivery> deliveries = findDeliveries();
        for (Delivery delivery: deliveries) {
            if (delivery.getDeliveryStatus().getStatusNumber()==1){
                delivery.setDeliveryStatus(Delivery.DeliveryStatus.DELIVERY_READY);
            } else if (delivery.getDeliveryStatus().getStatusNumber()==2) {
                delivery.setDeliveryStatus(Delivery.DeliveryStatus.DELIVERY_SUCCESS);
            }
        }
    }





}
