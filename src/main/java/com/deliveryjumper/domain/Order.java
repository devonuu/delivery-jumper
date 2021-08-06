package com.deliveryjumper.domain;

import java.time.LocalDateTime;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * Project : delivery-jumper
 * Created by gonuu
 * Date : 2021-07-26
 * Time : 오후 8:49
 * Blog : http://devonuu.tistory.com
 * Github : http://github.com/devonuu
 */
@Entity
@Table(name = "ORDERS")
@Getter
@Setter(value = AccessLevel.PRIVATE)
@EqualsAndHashCode
public class Order {

    @Id
    @GeneratedValue
    private Long orderId;   // PRIMARY KEY

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "memberId")
    private Member member;  // 배달의 대상이 되는 사용자 아이디

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "storeId")
    private Store store;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "deliveryId")
    private Delivery delivery;

    @Embedded
    private Address address;

    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    private LocalDateTime startDate;
}
