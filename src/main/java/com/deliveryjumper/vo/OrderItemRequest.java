package com.deliveryjumper.vo;

import lombok.Data;

/**
 * Project : delivery-jumper
 * Created by gonuu
 * Date : 2021-08-09
 * Time : 오전 9:58
 * Blog : http://devonuu.tistory.com
 * Github : http://github.com/devonuu
 */

@Data
public class OrderItemRequest {
    private Long itemId;
    private int orderItemQuantity;
    private double orderItemPrice;

    public OrderItemRequest(Long itemId, int orderItemQuantity, double orderItemPrice) {
        this.itemId = itemId;
        this.orderItemQuantity = orderItemQuantity;
        this.orderItemPrice = orderItemPrice;
    }
}
