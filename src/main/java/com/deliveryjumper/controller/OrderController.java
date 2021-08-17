package com.deliveryjumper.controller;

import com.deliveryjumper.domain.Address;
import com.deliveryjumper.domain.Order;
import com.deliveryjumper.domain.OrderItem;
import com.deliveryjumper.domain.OrderStatus;
import com.deliveryjumper.repository.MemberRepository;
import com.deliveryjumper.repository.OrderRepository;
import com.deliveryjumper.repository.StoreRepository;
import com.deliveryjumper.service.OrderService;
import com.deliveryjumper.vo.OrderCheckRes;
import com.deliveryjumper.vo.OrderRequest;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Project : delivery-jumper Created by gonuu Date : 2021-08-09 Time : 오전 9:04 Blog :
 * http://devonuu.tistory.com Github : http://github.com/devonuu
 */

@RestController
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final MemberRepository memberRepository;
    private final StoreRepository storeRepository;
    private final OrderRepository orderRepository;

    @PostMapping("/order")
    public ResponseEntity<OrderDto> saveOrder(@RequestBody OrderRequest request) {
        Long saveId = orderService.save(request);
        Order findOrder = orderService.findById(saveId);
        OrderDto result = new OrderDto(findOrder);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @Data
    static class OrderDto {

        private LocalDateTime createDate;
        private OrderStatus orderStatus;
        private Address address;
        private String phoneNumber;

        public OrderDto(Order order) {
            this.createDate = order.getCreateDate();
            this.orderStatus = order.getOrderStatus();
            this.address = order.getAddress();
            this.phoneNumber = order.getPhoneNumber();
        }
    }

    @Data
    static class OrderItemDto{
        private String title;
        private double orderItemPrice;
        private int orderItemQuantity;

        public OrderItemDto(OrderItem orderItem){
            title = orderItem.getItem().getTitle();
            orderItemPrice = orderItem.getOrderItemPrice();
            orderItemQuantity = orderItem.getOrderItemQuantity();

        }
    }

    @GetMapping("/order")
    public ResponseEntity<OrderCheckRes> checkOrder(@RequestParam Long storeId) {

        List<OrderCheckRes> orderList = orderService.orderCheckById(storeId);

        return new ResponseEntity<OrderCheckRes>((OrderCheckRes) orderList, HttpStatus.OK);
    }

}
