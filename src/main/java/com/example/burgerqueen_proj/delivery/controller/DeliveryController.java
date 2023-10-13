package com.example.burgerqueen_proj.delivery.controller;

import com.example.burgerqueen_proj.delivery.dto.DeliveryPostDto;
import com.example.burgerqueen_proj.delivery.entity.Delivery;
import com.example.burgerqueen_proj.delivery.mapper.DeliveryMapper;
import com.example.burgerqueen_proj.delivery.service.DeliveryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/delivery")
@RequiredArgsConstructor
public class DeliveryController {

    private final DeliveryMapper deliveryMapper;
    private final DeliveryService deliveryService;

    @PostMapping
    public ResponseEntity postDelivery(@RequestBody DeliveryPostDto deliveryPostDto){
        Delivery delivery = deliveryService.createDelivery(deliveryMapper.deliveryPostDtoToDelivery(deliveryPostDto));

        return new ResponseEntity(deliveryMapper.deliveryToDeliveryResponseDto(delivery),HttpStatus.CREATED);
    }

    @GetMapping("/{delivery-id}")
    public ResponseEntity findDelivery(@PathVariable("delivery-id")long deliverId){
        Delivery delivery = deliveryService.findDelivery(deliverId);
        return new ResponseEntity<>(deliveryMapper.deliveryToDeliveryResponseDto(delivery),HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity findDeliveries(){
        List<Delivery> deliveries = deliveryService.findDeliveries();
        return new ResponseEntity<>(deliveryMapper.deliveryToDeliveryResponseDtos(deliveries),HttpStatus.OK);
    }


}
