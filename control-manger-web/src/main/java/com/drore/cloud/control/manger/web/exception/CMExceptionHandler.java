package com.drore.cloud.control.manger.web.exception;

import com.drore.cloud.control.manger.common.base.domain.vo.Result;
import com.drore.cloud.control.manger.common.base.exception.CMException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 浙江卓锐科技股份有限公司 版权所有 © Copyright 2017<br/>
 * 说明: 通用异常处理类<br/>
 * 项目名称: control-manger <br/>
 * 创建日期: 2017年07月31日 11:48 <br/>
 * 作者: <a href="6492178@gmail.com">汪萌萌</a>
 */
@RestControllerAdvice
public class CMExceptionHandler {
    @ExceptionHandler(value = {CMException.class})
    public Result handleOtherExceptions(final CMException ex) {
        return Result.error(ex.getCode(), ex.getMessage());
    }
}
