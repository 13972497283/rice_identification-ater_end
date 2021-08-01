package com.tenglong.handler;

import com.baomidou.mybatisplus.extension.api.R;
import com.tenglong.entity.Result;
import com.tenglong.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.UnknownAccountException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
@Order(value = Ordered.HIGHEST_PRECEDENCE)
public class GlobalExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    @ExceptionHandler(BusinessException.class)
    public Result businessExceptionHandler(BusinessException e){
        return new Result(false,500, e.getMessage(), null);
    }
    @ExceptionHandler(UnknownAccountException.class)
    public Result UnknownAccountExceptionHandler(UnknownAccountException e){
        return new Result(false,500,"用户名不存在",null);
    }
    @ExceptionHandler(Exception.class)
    public Result exceptionHandler(Exception e){
        e.printStackTrace();
        return new Result(false,500,"系统内部错误",null);
    }
}