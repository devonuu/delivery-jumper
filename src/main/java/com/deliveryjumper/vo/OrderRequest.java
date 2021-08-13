package com.deliveryjumper.vo;

import com.deliveryjumper.domain.Address;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Project : delivery-jumper
 * Created by gonuu
 * Date : 2021-08-09
 * Time : 오전 9:28
 * Blog : http://devonuu.tistory.com
 * Github : http://github.com/devonuu
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequest {

    private Long memberId;
    private Long storeId;
    private Address address;
    private String phoneNumber;
    private List<OrderItemRequest> orderItemRequests;
}
