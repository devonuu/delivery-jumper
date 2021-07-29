package com.deliveryjumper.domain;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * Project : delivery-jumper
 * Created by gonuu
 * Date : 2021-07-26
 * Time : 오후 8:47
 * Blog : http://devonuu.tistory.com
 * Github : http://github.com/devonuu
 */
@Entity
@Getter
@Setter(value = AccessLevel.PRIVATE)
@EqualsAndHashCode
public class Rider extends Member{

    @Enumerated(EnumType.STRING)
    private RiderStatus riderStatus; //라이더 출근 정보
}

