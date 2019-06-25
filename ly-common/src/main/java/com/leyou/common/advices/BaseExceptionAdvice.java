package com.leyou.common.advices;

import com.leyou.common.exception.LyException;
import com.leyou.common.vo.ExceptionResult;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @ProjectName: leyou
 * @Author: Beryl
 * @Date: 2019/6/17 3:32 PM
 * @Version: 1.0
 * @Description: TODO
 */
@ControllerAdvice
public class BaseExceptionAdvice {

    @ExceptionHandler(LyException.class)
    public ResponseEntity<ExceptionResult> handlerException(LyException e){
         return ResponseEntity.status(e.getStatus()).body(new ExceptionResult(e));
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResult> handlerException(Exception e){
        return ResponseEntity.status(500).body(new ExceptionResult(new LyException(500,e.getMessage(),e)));

    }
}
