package com.example.burgerqueen_proj.delivery.service;

import com.example.burgerqueen_proj.delivery.entity.Delivery;
import com.example.burgerqueen_proj.delivery.repository.DeliveryRepository;
import com.example.burgerqueen_proj.exception.BusinessLogicException;
import com.example.burgerqueen_proj.exception.ExceptionCode;
import com.example.burgerqueen_proj.member.entity.Member;
import com.example.burgerqueen_proj.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DeliveryService {

    private final DeliveryRepository deliveryRepository;

    private final OrderService orderService;


    public Delivery createDelivery(Delivery delivery){
//        Delivery findDelivery = findVerifyDelivery(delivery.getDeliveryId());
        delivery.setDeliveryStatus(Delivery.DeliveryStatus.DELIVERY_READY);

        delivery.setOrder(orderService.findOrder(delivery.getOrder().getOrderId()));

        return deliveryRepository.save(delivery);
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





}
