package com.deliveryjumper.controller;

import com.deliveryjumper.service.DeliveryService;
import com.deliveryjumper.service.OrderService;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Project : delivery-jumper
 * Created by gonuu
 * Date : 2021-08-12
 * Time : 오전 11:24
 * Blog : http://devonuu.tistory.com
 * Github : http://github.com/devonuu
 */

@RestController
@RequiredArgsConstructor
public class DeliveryController {

    private final DeliveryService deliveryService;

    @PostMapping("/delivery")
    public ResponseEntity save(@RequestBody DeliveryRequest deliveryRequest){
        deliveryService.save(deliveryRequest.getOrderId());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Data
    static class DeliveryRequest{
        private Long orderId;
    }
}
