package com.jindan.jdy.common.shiro;

import org.apache.shiro.authz.AuthorizationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class MyExceptionHandler {

    @ExceptionHandler(value = AuthorizationException.class)
    @ResponseBody
    public Map<String, String> handleException(AuthorizationException e) {
        Map<String, String> result = new HashMap<String, String>();
        result.put("status", "400");
        String message = e.getMessage();
        String msg=message.substring(message.indexOf("[")+1,message.indexOf("]"));
        if (message.contains("role")) {
            result.put("msg", "对不起，您没有" + msg + "角色");
        } else if (message.contains("permission")) {
            result.put("msg", "对不起，您没有" + msg + "权限");
        } else {
            result.put("msg", "对不起，您的权限有误");
        }
        return result;
    }
}
