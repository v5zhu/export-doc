/*
 *
 *                    .___               __
 *   _____   ____   __| _/____   _______/  |_
 *  /     \ /  _ \ / __ |/ __ \ /  ___/\   __\
 * |  Y Y  (  <_> ) /_/ \  ___/ \___ \  |  |
 * |__|_|  /\____/\____ |\___  >____  > |__|
 *       \/            \/    \/     \/
 *
 * -------- By Liuyihua. -------- '''' -------
 *
 * @url http://www.modest.ren
 */

package com.zhuxl.exportdoc.component.handler;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author 2810010108@qq.com
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 处理各种未知运行异常
     */
    @ExceptionHandler(value = {Exception.class})
    public final ResponseEntity<?> handleException(Exception e) {
        log.error("[未知异常]", e);
        JSONObject json = new JSONObject();
        json.put("code", 500);
        json.put("msg", "内部错误");
        return new ResponseEntity(json, HttpStatus.OK);
    }

    /**
     * 处理RuntimeException
     */
    @ExceptionHandler(value = {RuntimeException.class})
    public final ResponseEntity<?> handleException(RuntimeException e) {
        log.error("[运行时异常]", e);
        JSONObject json = new JSONObject();
        json.put("code", 500);
        json.put("msg", e.getMessage());
        return new ResponseEntity(json, HttpStatus.OK);
    }

}
