package com.deliveryjumper.domain;

import java.io.Serializable;
import javax.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Project : delivery-jumper
 * Created by gonuu
 * Date : 2021-07-26
 * Time : 오후 8:46
 * Blog : http://devonuu.tistory.com
 * Github : http://github.com/devonuu
 */

@Embeddable
@Getter
@Setter(value = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
public class Address implements Serializable {
    private String zipcode;
    private String address;
    private String detailAddress;
}
