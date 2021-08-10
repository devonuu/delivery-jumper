package com.deliveryjumper.domain;

import java.time.LocalDateTime;
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
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Project : delivery-jumper
 * Created by gonuu
 * Date : 2021-07-26
 * Time : 오후 8:48
 * Blog : http://devonuu.tistory.com
 * Github : http://github.com/devonuu
 */
@Entity
@Getter
@Setter(value = AccessLevel.PRIVATE)
@EqualsAndHashCode
@NoArgsConstructor
public class Store extends BaseTimeEntity{
    @Id
    @GeneratedValue
    @Column(name = "store_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    private String storeName;

    private String storeTel;

    @Embedded
    private Address address;

    @Enumerated(EnumType.STRING)
    private StoreStatus storeStatus;

    private double minimumOrderAmount;

    public Store(Builder builder) {
        this.member = builder.member;
        this.storeName = builder.storeName;
        this.storeTel = builder.storeTel;
        this.address = builder.address;
        this.storeStatus = builder.storeStatus;
        this.minimumOrderAmount = builder.minimumOrderAmount;
    }

    public static class Builder{
        private final Member member;
        private final String storeName;
        private final Address address;
        private String storeTel;
        private StoreStatus storeStatus;
        private double minimumOrderAmount;

        public Builder(Member member, String storeName, Address address) {
            this.member = member;
            this.storeName = storeName;
            this.address = address;
        }

        public Builder storeStatus(StoreStatus storeStatus){
            this.storeStatus = storeStatus;
            return this;
        }

        public Builder minimumOrderAmount(Long minimumOrderAmount){
            this.minimumOrderAmount = minimumOrderAmount;
            return this;
        }

        public Builder storeTel(String storeTel){
            this.storeTel = storeTel;
            return this;
        }

        public Store build(){
            return new Store(this);
        }
    }
}