package com.shirongbao.cloud.resp;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author ShiRongbao
 * @create 2024/3/11 15:07
 * @description:
 */
@Data
@Accessors(chain = true)
public class ResultData<T> {
    private String code;
    private String message;
    private T data;
    private Long timestamp;

    public ResultData() {
        this.timestamp = System.currentTimeMillis();
    }

    public static<T> ResultData<T> success(T data){
        ResultData resultData = new ResultData<>();  // 在这里调用无参构造器，为timestamp赋值
        resultData.setCode(ReturnCodeEnum.RC200.getCode());
        resultData.setMessage(ReturnCodeEnum.RC200.getMessage());
        resultData.setData(data);
        return resultData;
    }


    public static<T> ResultData<T> fail(String code, String message){
        ResultData resultData = new ResultData<>();  // 在这里调用无参构造器，为timestamp赋值
        resultData.setCode(code);
        resultData.setMessage(message);
        resultData.setData(null);
        return resultData;
    }
}
