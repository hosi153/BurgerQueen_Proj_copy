package com.example.burgerqueen_proj.config;

import com.example.burgerqueen_proj.delivery.entity.Delivery;
import com.example.burgerqueen_proj.delivery.service.DeliveryService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor

public class SchedulerService {

    private final DeliveryService deliveryService;


    @Scheduled(cron = "* 10 * * * *")
    public void changeDeliveryStatus(){
        System.out.println("스케줄러");
        List<Delivery> deliveries = deliveryService.findDeliveries();
        for (Delivery delivery: deliveries) {
            if (delivery.getDeliveryStatus().equals(Delivery.DeliveryStatus.DELIVERY_READY)){
                delivery.setDeliveryStatus(Delivery.DeliveryStatus.DELIVERY_START);
                deliveryService.updateDelivery(delivery);
            } else if (delivery.getDeliveryStatus().getStatusNumber()==2) {
                delivery.setDeliveryStatus(Delivery.DeliveryStatus.DELIVERY_SUCCESS);
                deliveryService.updateDelivery(delivery);
            }
        }
    }

}
