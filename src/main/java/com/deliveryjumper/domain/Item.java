package com.deliveryjumper.domain;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * Project : delivery-jumper
 * Created by gonuu
 * Date : 2021-07-26
 * Time : 오후 8:51
 * Blog : http://devonuu.tistory.com
 * Github : http://github.com/devonuu
 */
@Entity
@Getter
@Setter(value = AccessLevel.PRIVATE)
@EqualsAndHashCode
public class Item {
    @Id
    @GeneratedValue
    private Long itemId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categoryId")
    private Category category;

    private String title;
    private double price;

    @Enumerated(EnumType.STRING)
    private ItemStatus itemStatus;
}