package com.xuan.xdesktop.restful.www.exception.handler;

import com.xuan.xdesktop.restful.www.entity.Result;
import com.xuan.xdesktop.restful.www.enums.Code;
import com.xuan.xdesktop.restful.www.exception.XpException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by xuan on 17/2/22.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = XpException.class)
    @ResponseBody
    public Result xpExceptionHandler(HttpServletRequest req, Exception e) throws Exception {
        return new Result(Code.ERROR_PARAM);
    }

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result defaultExceptionHandler(HttpServletRequest req, Exception e) throws Exception {
        return new Result(Code.ERROR);
    }
}
