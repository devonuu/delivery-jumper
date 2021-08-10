package com.deliveryjumper.handler;

import lombok.Getter;

/**
 * Project : delivery-jumper
 * Created by gonuu
 * Date : 2021-08-10
 * Time : 오후 3:48
 * Blog : http://devonuu.tistory.com
 * Github : http://github.com/devonuu
 */

@Getter
public enum ErrorCode {

    INVALID_INPUT_VALUE(400, "Invalid Input Value");

    private int status;
    private final String message;

    ErrorCode(int status, String message){
        this.status = status;
        this.message = message;
    }
}
