package com.deliveryjumper.domain;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Project : delivery-jumper
 * Created by gonuu
 * Date : 2021-07-26
 * Time : 오후 8:50
 * Blog : http://devonuu.tistory.com
 * Github : http://github.com/devonuu
 */
@Entity
@Getter
@Setter(value = AccessLevel.PRIVATE)
@EqualsAndHashCode
@NoArgsConstructor
public class Delivery extends BaseTimeEntity{
    @Id
    @GeneratedValue
    @Column(name = "delivery_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "memberId")
    private Member member; // 배달 라이더 id

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "delivery")
    private Order order;  // 배달 대상 주문 id

    @Enumerated(EnumType.STRING)
    private DeliveryStatus deliveryStatus;

    private LocalDateTime startDate;
    private LocalDateTime endDate;

    public void startDelivery() {
        this.deliveryStatus = DeliveryStatus.START;
        this.startDate = LocalDateTime.now();
    }

    public void completeDelivery(){
        this.deliveryStatus = DeliveryStatus.COMP;
        this.endDate = LocalDateTime.now();
    }

    public Delivery (DeliveryStatus status){
        this.deliveryStatus = status;
    }
}