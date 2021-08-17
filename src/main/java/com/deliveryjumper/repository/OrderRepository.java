package com.deliveryjumper.repository;

import com.deliveryjumper.domain.Order;
import com.deliveryjumper.domain.OrderStatus;
import com.deliveryjumper.vo.OrderCheckRes;
import io.lettuce.core.dynamic.annotation.Param;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Project : delivery-jumper
 * Created by gonuu
 * Date : 2021-08-09
 * Time : 오전 8:20
 * Blog : http://devonuu.tistory.com
 * Github : http://github.com/devonuu
 */

public interface OrderRepository extends JpaRepository<Order, Long> {

//    @Query("select distinct o from Order o join fetch com.deliveryjumper.domain.orderItem oi "
//            + "where o.storeId = storeId and o.orderStatus = 'ORDER'") //and o.orderId in :orderId
//    List<OrderCheckRes> findByStoreIdAndOrderStatus(Long storeId, OrderStatus orderStatus);

    @Query(value = "select distinct o from Order o join fetch o.com.deliveryjumper.domain.orderItem oi "
        + "where o.storeId = :storeId and o.orderStatus = :orderStatus", nativeQuery = true) //and o.orderId in :orderId
    List<OrderCheckRes> findByStoreIdAndOrderStatus(@Param("storeId") Long storeId, @Param("orderStatus") OrderStatus orderStatus);


}
