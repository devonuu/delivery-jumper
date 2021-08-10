package com.deliveryjumper.repository;

import com.deliveryjumper.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Project : delivery-jumper
 * Created by gonuu
 * Date : 2021-08-10
 * Time : 오후 2:18
 * Blog : http://devonuu.tistory.com
 * Github : http://github.com/devonuu
 */


public interface CategoryRepository extends JpaRepository<Category, Long> {
}
