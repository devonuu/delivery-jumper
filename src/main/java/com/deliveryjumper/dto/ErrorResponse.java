package com.deliveryjumper.dto;

import com.deliveryjumper.handler.ErrorCode;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

/**
 * Project : delivery-jumper
 * Created by gonuu
 * Date : 2021-08-10
 * Time : 오후 3:31
 * Blog : http://devonuu.tistory.com
 * Github : http://github.com/devonuu
 */

@Getter
@NoArgsConstructor
public class ErrorResponse {
    private String message;
    private int status;
    private List<FieldError> errors;

    public ErrorResponse(ErrorCode errorCode, BindingResult result){
        this.message = errorCode.getMessage();
        this.status = errorCode.getStatus();
        this.errors = result.getFieldErrors();
    }
}
