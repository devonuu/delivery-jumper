package com.deliveryjumper.vo;


import com.deliveryjumper.domain.Address;
import java.util.Collections;
import java.util.List;
import lombok.Data;

@Data
public class OrderCheckRes {

    private List<Long> orderId;
    private Long memberId;
    private Long storeId;
    private Address address;
    private String phoneNumber;
    private List<OrderItemRequest> orderItemRequests;

    public OrderCheckRes(List<Long> orderId, Long memberId, Long storeId, Address address, String phoneNumber,
        List<OrderItemRequest> orderItemRequests) {
        this.orderId = orderId;
        this.memberId = memberId;
        this.storeId = storeId;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.orderItemRequests = orderItemRequests;
    }
}
