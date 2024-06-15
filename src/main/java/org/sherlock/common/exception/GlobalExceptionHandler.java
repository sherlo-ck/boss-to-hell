package org.sherlock.common.exception;

import lombok.extern.slf4j.Slf4j;
import org.sherlock.common.entiry.Result;
import org.sherlock.common.enums.ErrorCodeEnum;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {
    //自定义异常
    @ExceptionHandler(BusinessExcepiton.class)
    @ResponseBody
    public Result error(BusinessExcepiton e) {
        e.printStackTrace();
        log.error("BusinessExcepiton error: ", e);
        return new Result.ResultBuilder<>().code(ErrorCodeEnum.ResultCode.FAIL.getCode())
                .success(ErrorCodeEnum.ResultCode.FAIL.getMessage()).message(e.getMessage()).build();
    }
}