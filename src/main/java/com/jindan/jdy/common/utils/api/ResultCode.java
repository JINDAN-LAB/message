package com.jindan.jdy.common.utils.api;

/**
 * @author xbdyilin
 * @Descriptiozn: 封装api操作码
 */
public enum ResultCode implements IErrorCode {
    SUCCESS(200, "调用成功"),
    FAILED(500, "调用失败");
    private int code;
    private String message;

    private ResultCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
