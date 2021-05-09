package com.jindan.jdy.common.utils.exception;

import com.jindan.jdy.common.utils.api.ResultVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author: xbdyilin
 * @DATE: 2019/6/3
 * @description: 通用异常统一处理
 */
@RestControllerAdvice
@Slf4j
public class ExceptionAdvice {

    private static final String UNKNOWN_EXCEPTION = "未知异常";
    private static final String MISS_PARAMETER_EXCEPTION = "参数缺失";

    /**
     * 业务异常统一返回
     *
     * @param e
     * @return
     */
    @ResponseBody
    @ExceptionHandler(BusinessException.class)
    public ResultVo handleException(BusinessException e) {
        log.error(e.getMessage(), e);
        return ResultVo.failed(e.getErrorCode());
    }


    @ExceptionHandler(BindException.class)
    public ResultVo BindException(BindException result){
        if (result.hasErrors()){
            ObjectError objectError = result.getAllErrors().get(0);
            log.error(objectError.getDefaultMessage());
            return ResultVo.failed(objectError.getDefaultMessage());
        }
        return ResultVo.failed("请重试!");
    }


    /**
     * 统一异常返回
     *
     * @param
     * @return
     */
    @ResponseBody
    @ExceptionHandler(Exception.class)
    public ResultVo handleException(Exception e) {
        log.error(UNKNOWN_EXCEPTION, e);
        return ResultVo.failed(UNKNOWN_EXCEPTION);
    }


    /**
     * 参数缺失异常返回
     *
     * @param
     * @return
     */
    @ResponseBody
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResultVo missingParameterException(MissingServletRequestParameterException e) {
        log.error(MISS_PARAMETER_EXCEPTION, e);
        return ResultVo.failed(MISS_PARAMETER_EXCEPTION);
    }
}
