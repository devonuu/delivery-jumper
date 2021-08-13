package com.deliveryjumper.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Project : delivery-jumper
 * Created by gonuu
 * Date : 2021-08-09
 * Time : 오전 9:58
 * Blog : http://devonuu.tistory.com
 * Github : http://github.com/devonuu
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemRequest {
    private Long itemId;
    private int orderItemQuantity;
    private double orderItemPrice;
}
