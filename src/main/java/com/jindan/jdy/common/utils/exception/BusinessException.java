package com.jindan.jdy.common.utils.exception;

import com.jindan.jdy.common.utils.api.IErrorCode;
import lombok.Data;

/**
 * @author:xbdyilin
 * @DATE: 2019/6/3
 * @description: 封装业务相关的异常
 */
@Data
public class BusinessException extends RuntimeException {

    private IErrorCode errorCode;

    public BusinessException(Throwable cause) {
        super(cause);
    }

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }

    public BusinessException(IErrorCode errorCode) {
        this.errorCode = errorCode;
    }

}
