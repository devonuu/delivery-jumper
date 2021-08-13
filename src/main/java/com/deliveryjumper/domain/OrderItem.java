package com.deliveryjumper.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
 * Time : 오후 9:00
 * Blog : http://devonuu.tistory.com
 * Github : http://github.com/devonuu
 */
@Entity
@Getter
@Setter(value = AccessLevel.PRIVATE)
@EqualsAndHashCode
@NoArgsConstructor
public class OrderItem {

    @Id
    @GeneratedValue
    @Column(name = "order_item_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "orderId")
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "itemId")
    private Item item;

    private int orderItemQuantity;
    private double orderItemPrice;

    public OrderItem(Builder builder){
        order = builder.order;
        item = builder.item;
        orderItemQuantity = builder.orderItemQuantity;
        orderItemPrice = builder.orderItemPrice;
    }

    public static class Builder{
        private Order order;
        private Item item;
        private int orderItemQuantity;
        private double orderItemPrice;

        public Builder order(Order order){
            this.order = order;
            return this;
        }
        public Builder item(Item item){
            this.item = item;
            return this;
        }
        public Builder orderItemQuantity(int orderItemQuantity){
            this.orderItemQuantity = orderItemQuantity;
            return this;
        }
        public Builder orderItemPrice(double orderItemPrice){
            this.orderItemPrice = orderItemPrice;
            return this;
        }

        public OrderItem build(){
            return new OrderItem(this);
        }
    }
}
