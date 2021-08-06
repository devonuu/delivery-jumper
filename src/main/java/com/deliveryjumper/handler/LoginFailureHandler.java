package com.deliveryjumper.handler;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.Data;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

/**
 * Project : delivery-jumper
 * Created by gonuu
 * Date : 2021-08-05
 * Time : 오후 2:35
 * Blog : http://devonuu.tistory.com
 * Github : http://github.com/devonuu
 */
/*
 * LoginFailureHandler는 상속을 통한 확장이 필요가 없다.
 *
 * */
@Data
public final class LoginFailureHandler implements AuthenticationFailureHandler {

    private final String LOGIN_ID_PARAMETER_NAME = "username";
    private final String LOGIN_PASSWORD_PARAMETER_NAME = "password";

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
        AuthenticationException exception) throws IOException, ServletException {

        String failureId = request.getParameter(LOGIN_ID_PARAMETER_NAME);
        String failurePw = request.getParameter(LOGIN_PASSWORD_PARAMETER_NAME);

        throw exception; //예외 발생
    }
}

