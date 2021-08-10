package com.deliveryjumper.vo;

import com.deliveryjumper.domain.Address;
import java.util.List;
import lombok.Data;

/**
 * Project : delivery-jumper
 * Created by gonuu
 * Date : 2021-08-09
 * Time : 오전 9:28
 * Blog : http://devonuu.tistory.com
 * Github : http://github.com/devonuu
 */

@Data
public class OrderRequest {

    private Long memberId;
    private Long storeId;
    private Address address;
    private String phoneNumber;
    private List<OrderItemRequest> orderItemRequests;

    public OrderRequest(Long memberId, Long storeId, Address address, String phoneNumber,
        List<OrderItemRequest> orderItemRequests) {
        this.memberId = memberId;
        this.storeId = storeId;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.orderItemRequests = orderItemRequests;
    }
}
