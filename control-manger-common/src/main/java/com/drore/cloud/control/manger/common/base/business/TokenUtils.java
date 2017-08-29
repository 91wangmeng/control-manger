package com.drore.cloud.control.manger.common.base.business;

import com.drore.cloud.control.manger.common.base.exception.CMException;
import com.drore.cloud.sdk.builder.UcBuilder;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Optional;

/**
 * 卓锐科技有限公司
 * Created by wmm on 2017/8/28.
 * email：6492178@gmail.com
 * description:
 *
 * @author wmm
 */
@Component
@ConditionalOnExpression("'${spring.application.name}'!='control-manger'")
public class TokenUtils {
    private static final ThreadLocal<String> threadLocal = new ThreadLocal<>();
    @Resource
    private UcBuilder ucBuilder;

    /**
     * Gets token. 无法从请求路径上获取token时使用该方法
     *
     * @param userName the user name
     * @param password the password
     * @return the token
     */
    public String getToken(String userName, String password) {
        return ucBuilder.login(userName, password).getToken();
    }

    /**
     * Gets token. 可以从请求路径上获取token时使用
     *
     * @return the token
     */
    public static String getToken() {
        return Optional.ofNullable(threadLocal.get()).orElseThrow(() -> new CMException("token未设置无法获取"));
    }

    /**
     * Sets token.
     *
     * @param token the token
     */
    public static void setToken(String token) {
        threadLocal.set(token);
    }
}
