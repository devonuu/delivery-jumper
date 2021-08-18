package com.deliveryjumper.repository;

import com.deliveryjumper.domain.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Project : delivery-jumper
 * Created by gonuu
 * Date : 2021-08-12
 * Time : 오전 8:53
 * Blog : http://devonuu.tistory.com
 * Github : http://github.com/devonuu
 */


public interface DeliveryRepository extends JpaRepository<Delivery, Long> {
}
