package com.deliveryjumper.vo;

import com.deliveryjumper.domain.Address;
import com.deliveryjumper.domain.Delivery;
import com.deliveryjumper.domain.DeliveryStatus;
import com.deliveryjumper.domain.OrderItem;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Project : delivery-jumper
 * Created by gonuu
 * Date : 2021-08-12
 * Time : 오전 9:16
 * Blog : http://devonuu.tistory.com
 * Github : http://github.com/devonuu
 */

@Data
@Builder
public class DeliveryRequest implements Serializable {
    private Long deliveryId;
    private Address address;
    private String phoneNumber;
    private DeliveryStatus deliveryStatus;
    private List<DeliveryItemInfo> deliveryItemInfos;
}
