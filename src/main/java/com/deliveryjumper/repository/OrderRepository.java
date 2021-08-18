package com.deliveryjumper.repository;

import com.deliveryjumper.domain.Order;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Project : delivery-jumper
 * Created by gonuu
 * Date : 2021-08-09
 * Time : 오전 8:20
 * Blog : http://devonuu.tistory.com
 * Github : http://github.com/devonuu
 */

public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query("select o from Order o join fetch o.orderItems where o.id = :id")
    Optional<Order> findByIdWithOrderItems(@Param("id") Long id);
}
