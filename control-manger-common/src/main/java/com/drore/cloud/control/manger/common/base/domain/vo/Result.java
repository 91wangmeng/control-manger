package com.drore.cloud.control.manger.common.base.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 卓锐科技有限公司
 * Created by wmm on 2017/4/19.
 * email：6492178@gmail.com
 * description:
 *
 * @param <T> the type parameter
 * @author wmm
 */
@Data
@ApiModel
public class Result<T> implements Serializable {
    private static final long serialVersionUID = -8780917125380864387L;
    /**
     * The Success.
     */
    @ApiModelProperty(value = "是否成功",example = "true")
    public boolean success;
    /**
     * The Code.
     */
    @ApiModelProperty(value = "响应码",example = "200")
    public int code;
    /**
     * The Message.
     */
    @ApiModelProperty(value = "消息体",example = "操作成功")
    public String message;
    /**
     * The Data.
     */
    @ApiModelProperty(value = "返回数据")
    public T data;

    /**
     * Instantiates a new Result.
     */
    public Result() {
        this.setSuccess(true);
        this.setCode(1012);
    }

    /**
     * Error result.
     *
     * @return the result
     */
    public static Result error() {
        return error(500, "服务器异常请联系管理员");
    }

    /**
     * Error result.
     *
     * @param message the message
     * @return the result
     */
    public static Result error(String message) {
        return error(500, message);
    }

    /**
     * Error result.
     *
     * @param code    the code
     * @param message the message
     * @return the result
     */
    public static Result error(int code, String message) {
        Result result = new Result();
        result.setCode(code);
        result.setMessage(message);
        result.setSuccess(false);
        return result;
    }

    /**
     * Success result.
     *
     * @return the result
     */
    public static Result success() {
        return new Result();
    }

    /**
     * Success result.
     *
     * @param message the message
     * @return the result
     */
    public static Result success(String message) {
        Result result = new Result();
        result.setMessage(message);
        return result;
    }

    /**
     * Success result.
     *
     * @param data the data
     * @return the result
     */
    public static Result success(Object data) {
        Result result = new Result();
        result.setData(data);
        return result;
    }
}
