package com.leyou.common.exception;

import com.leyou.common.enums.ExceptionEnum;
import lombok.Getter;

/**
 * @ProjectName: leyou
 * @Author: Beryl
 * @Date: 2019/6/17 8:38 PM
 * @Version: 1.0
 * @Description: TODO
 */
@Getter
public class LyException extends RuntimeException{

    private int status;

    //自定义枚举值
    public LyException(ExceptionEnum em) {
        super(em.getMessage());
        this.status = em.getStatus();
    }

    public LyException(int status, String message) {
        super(message);
        this.status = status;
    }

    public LyException(ExceptionEnum em, Throwable cause) {
        super(em.getMessage(), cause);
        this.status = status;
    }

    public LyException(int status, String message, Throwable cause) {
        super(message, cause);
        this.status = status;
    }
}
