package com.tenglong.exception;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
public class BusinessException  extends RuntimeException{
    public BusinessException(){super();}
    public BusinessException(String message) {
        super(message);
    }
    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }
}
