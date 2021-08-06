package com.deliveryjumper.domain;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Transient;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Project : delivery-jumper
 * Created by gonuu
 * Date : 2021-07-26
 * Time : 오후 8:45
 * Blog : http://devonuu.tistory.com
 * Github : http://github.com/devonuu
 */


@Entity
@Getter
@Setter(value = AccessLevel.PRIVATE)
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
@AllArgsConstructor
public class Member extends BaseTimeEntity{
    @Id
    @GeneratedValue
    private Long memberId;
    private String email;
    private String password;
    private String name;
    private String picture;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Embedded
    private Address address;

    @Enumerated(EnumType.STRING)
    private MemberStatus memberStatus;

    public Member update(String name, String picture){
        this.name = name;
        this.picture = picture;
        return this;
    }

/*    *//**
     * spring security에서 사용될 권한 정보를 가져오기 위해 DiscriminatorValue값을 가져온다.
     *//*
    @Transient //
    public String getDecriminatorValue() {
        return this.getClass().getAnnotation(DiscriminatorValue.class).value();
    }*/

    public void encodingPassword(String password){
        this.password = password;
    }

    public static class Builder{
        private String email;
        private Role role;
        private String password;
        private String name = "";
        private String picture = "";
        private Address address = null;

        public Builder email(String email){
            this.email = email;
            return this;
        }

        public Builder role(Role role){
            this.role = role;
            return this;
        }

        public Builder name(String name){
            this.name = name;
            return this;
        }

        public Builder picture(String picture){
            this.picture = picture;
            return this;
        }

        public Builder address(Address address){
            this.address = address;
            return this;
        }

        public Builder password(String password){
            this.password = password;
            return this;
        }

        public Member build(){
            return new Member(this);
        }
    }

    private Member(Builder builder){
        email = builder.email;
        password = builder.password;
        name = builder.name;
        picture = builder.picture;
        address = builder.address;
        role = builder.role;
    }
}
