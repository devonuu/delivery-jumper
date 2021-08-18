package com.deliveryjumper.service;

import com.deliveryjumper.domain.Delivery;
import com.deliveryjumper.domain.DeliveryStatus;
import com.deliveryjumper.domain.Order;
import com.deliveryjumper.repository.DeliveryRepository;
import com.deliveryjumper.repository.OrderRepository;
import com.deliveryjumper.vo.DeliveryItemInfo;
import com.deliveryjumper.vo.DeliveryInfo;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Project : delivery-jumper
 * Created by gonuu
 * Date : 2021-08-12
 * Time : 오전 9:00
 * Blog : http://devonuu.tistory.com
 * Github : http://github.com/devonuu
 */

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DeliveryService {

    private final DeliveryRepository deliveryRepository;
    private final OrderRepository orderRepository;
    private final RedisTemplate redisTemplate;
    private String DELIVERY_HASH_KEY = "DELIVERY";

    @Transactional
    public Long save(Long orderId){
        Order findOrder = orderRepository.findByIdWithOrderItems(orderId)
            .orElseThrow();

        Delivery delivery = new Delivery(DeliveryStatus.WAIT);

        deliveryRepository.save(delivery);
        findOrder.createDelivery(delivery);

        List<DeliveryItemInfo> deliveryItemInfos = findOrder.getOrderItems()
            .stream()
            .map(orderItem -> DeliveryItemInfo.builder()
                .title(orderItem.getTitle())
                .orderItemPrice(orderItem.getOrderItemPrice())
                .orderItemQuantity(orderItem.getOrderItemQuantity())
                .build())
            .collect(Collectors.toList());

        DeliveryInfo deliveryInfo = DeliveryInfo.builder()
            .deliveryId(delivery.getId())
            .deliveryStatus(delivery.getDeliveryStatus())
            .address(findOrder.getAddress())
            .deliveryItemInfos(deliveryItemInfos)
            .phoneNumber(findOrder.getPhoneNumber())
            .build();

        redisTemplate.opsForHash().put(DELIVERY_HASH_KEY, deliveryInfo.getDeliveryId(), deliveryInfo);
        return delivery.getId();
    }
}
