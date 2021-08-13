package com.deliveryjumper.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
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
@NoArgsConstructor
public class Order extends BaseTimeEntity{

    @Id
    @GeneratedValue
    @Column(name = "order_id")
    private Long id;   // PRIMARY KEY

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "memberId")
    private Member member;  // 배달의 대상이 되는 사용자 아이디

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "storeId")
    private Store store;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "deliveryId")
    private Delivery delivery;

    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems = new ArrayList<>();

    @Embedded
    private Address address;

    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    /*
    * 연관관계 메서드
    * 객체 그래프로 탐색을 하기 때문에 orderItems와 orderItem 각각에 추가해준다.
    * */
    public void addOrderItem(OrderItem orderItem){
        orderItems.add(orderItem);

    }

    /*
    * 연관관계 메서드
    * 배달 정보를 생성한다.
    * */
    public void createDelivery(Delivery delivery){
        this.delivery = delivery;
    }

    private Order(Builder builder){
        id = builder.id;
        member = builder.member;
        store = builder.store;
        delivery = builder.delivery;
        address = builder.address;
        phoneNumber = builder.phoneNumber;
        orderStatus = builder.orderStatus;
        createDate = builder.createDate;
    }

    public static class Builder{
        private Long id;
        private Member member;
        private Store store;
        private Delivery delivery;
        private Address address;
        private String phoneNumber;
        private OrderStatus orderStatus;
        private LocalDateTime createDate;

        public Builder orderId(Long id){
            this.id = id;
            return this;
        }

        public Builder member(Member member){
            this.member = member;
            return this;
        }
        public Builder store(Store store){
            this.store = store;
            return this;
        }
        public Builder delivery(Delivery delivery){
            this.delivery = delivery;
            return this;
        }
        public Builder address(Address address){
            this.address = address;
            return this;
        }
        public Builder phoneNumber(String phoneNumber){
            this.phoneNumber = phoneNumber;
            return this;
        }
        public Builder orderStatus(OrderStatus orderStatus){
            this.orderStatus = orderStatus;
            return this;
        }
        public Builder createDate(LocalDateTime createDate){
            this.createDate = createDate;
            return this;
        }

        public Order build(){
            return new Order(this);
        }
    }
}
