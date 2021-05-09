package com.jindan.jdy.common.utils.api;

import lombok.Data;

/**
 * @author xbdyilin
 * @Descriptiozn: 封装api返回的结果
 */

@Data
public class ResultVo<T> {
    private long code;
    private String message;
    private T data;

    public ResultVo() {
    }

    public ResultVo(T data) {
        this.code = ResultCode.SUCCESS.getCode();
        this.data = data;
    }

    public ResultVo(long code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    /**
     * 成功返回结果
     *
     * @param <T>
     * @return
     */
    public static <T> ResultVo<T> success() {
        return new ResultVo<T>(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), null);
    }

    /**
     * 成功返回结果
     *
     * @param data
     * @param <T>
     * @return
     */
    public static <T> ResultVo<T> success(T data) {
        return new ResultVo<T>(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), data);
    }

    /**
     * 成功返回结果
     *
     * @param data
     * @param message
     * @param <T>
     * @return
     */
    public static <T> ResultVo<T> success(T data, String message) {
        return new ResultVo<T>(ResultCode.SUCCESS.getCode(), message, data);
    }

    /**
     * 失败返回结果
     *
     * @param errorCode
     * @param <T>
     * @return
     */
    public static <T> ResultVo<T> failed(IErrorCode errorCode) {
        return new ResultVo<T>(errorCode.getCode(), errorCode.getMessage(), null);
    }

    /**
     * 失败返回结果
     *
     * @param message
     * @param <T>
     * @return
     */
    public static <T> ResultVo<T> failed(String message) {
        return new ResultVo<T>(ResultCode.FAILED.getCode(), message, null);
    }

    /**
     * 失败返回结果
     *
     * @param message
     * @param <T>
     * @return
     */
    public static <T> ResultVo<T> failed(String message,T data) {
        return new ResultVo<T>(ResultCode.FAILED.getCode(), message, data);
    }

    /**
     * 失败返回结果
     *
     * @param message
     * @param <T>
     * @return
     */
    public static <T> ResultVo<T> failed(String message,Integer code) {
        return new ResultVo<T>(code, message, null);
    }


    /**
     * 失败返回结果
     *
     * @param <T>
     * @return
     */
    public static <T> ResultVo<T> failed() {
        return failed(ResultCode.FAILED);
    }


}
