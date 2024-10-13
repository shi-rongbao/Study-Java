package com.shirongbao.cloud.exp;

import com.shirongbao.cloud.resp.ResultData;
import com.shirongbao.cloud.resp.ReturnCodeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author ShiRongbao
 * @create 2024/3/11 16:08
 * @description:
 */
@Slf4j
// @RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)  // 标识响应状态码
    public ResultData<String> exception(Exception ex) {
        log.error("全局异常信息：{}", ex.getMessage());
        return ResultData.fail(ReturnCodeEnum.RC500.getCode(), ReturnCodeEnum.RC500.getMessage());
    }
}
