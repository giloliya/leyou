package com.leyou.common.vo;

import com.leyou.common.exception.LyException;
import org.joda.time.DateTime;

import java.util.Date;

/**
 * @ProjectName: leyou
 * @Author: Beryl
 * @Date: 2019/6/19 9:51 AM
 * @Version: 1.0
 * @Description: TODO
 */
public class ExceptionResult {

    private int status;
    private String message;
    private String timestamp;

    public ExceptionResult(LyException e){
        this.status = e.getStatus();
        this.message = e.getMessage();
        this.timestamp = DateTime.now().toString("yy-MM-dd HH:mm:ss");
    }


}
