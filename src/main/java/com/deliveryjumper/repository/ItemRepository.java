package com.deliveryjumper.repository;

import com.deliveryjumper.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Project : delivery-jumper
 * Created by gonuu
 * Date : 2021-08-09
 * Time : 오전 9:39
 * Blog : http://devonuu.tistory.com
 * Github : http://github.com/devonuu
 */


public interface ItemRepository extends JpaRepository<Item, Long> {
}
