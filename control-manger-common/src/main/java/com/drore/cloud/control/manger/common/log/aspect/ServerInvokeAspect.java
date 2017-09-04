package com.drore.cloud.control.manger.common.log.aspect;

import com.alibaba.fastjson.JSON;
import com.drore.cloud.control.manger.common.log.annotations.ServerInvokeLog;
import com.drore.cloud.control.manger.common.log.entity.HttpRequestLogEntity;
import com.drore.cloud.control.manger.common.log.entity.ServerInvokeLogEntity;
import com.drore.cloud.control.manger.common.log.utils.LinkedBlockingQueueUtils;
import com.drore.cloud.control.manger.common.log.utils.TransmittableThreadLocalUtils;
import com.drore.cloud.control.manger.common.message.service.RabbitBuilder;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * 卓锐科技有限公司
 * Created by wmm on 2017/4/20.
 * email：6492178@gmail.com
 * description: 服务调用切面
 *
 * @author wmm
 */
@Aspect
@Component
@Slf4j
public class ServerInvokeAspect {
    @Value("${spring.application.name}")
    private String application_name;
    @Resource
    private RabbitBuilder rabbitBuilder;

    /**
     * Instantiates a new Server invoke aspect.
     */
    public ServerInvokeAspect() {
        save();
    }

    private void save() {
        new Thread(() -> {
            while (true) {
                while (LinkedBlockingQueueUtils.isEmpty()) {
                    try {
                        //如果没有,则延时两秒
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                ServerInvokeLogEntity serverInvokeLogEntity = null;
                try {
                    serverInvokeLogEntity = LinkedBlockingQueueUtils.pollServerInvoke();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (serverInvokeLogEntity != null) {
                    //TODO 保存服务调用日志
                    rabbitBuilder.sendServerInvokeLog(serverInvokeLogEntity);
                }
                HttpRequestLogEntity httpRequestLogEntity = null;
                try {
                    httpRequestLogEntity = LinkedBlockingQueueUtils.pollHttpRequest();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (httpRequestLogEntity != null) {
                    //TODO 保存网络请求日志
                    rabbitBuilder.sendHttpRequestLog(httpRequestLogEntity);
                }
            }
        }).start();
    }

    /**
     * Server invoke.
     */
    @Pointcut(value = "execution(* com.drore.cloud.control..*.*(..)) " +
            "&& @annotation(com.drore.cloud.control.manger.common.log.annotations.ServerInvokeLog))")
    public void serverInvoke() {

    }

    /**
     * Do around object.
     *
     * @param joinPoint the join point
     * @return the object
     */
    @Around(value = "serverInvoke()")
    public Object doAround(ProceedingJoinPoint joinPoint) {
        long startTs = System.currentTimeMillis();
        ServerInvokeLogEntity logEntity = getServerInvokeLogEntity(joinPoint);
        logEntity.setApplicationName(application_name);
        UUID uuid = UUID.randomUUID();
        String id = String.valueOf(uuid);
        TransmittableThreadLocalUtils.set(id);
        logEntity.setId(id);
        Object result = null;
        try {
            result = joinPoint.proceed();
        } catch (Throwable throwable) {
            logEntity.setSuccess(false);
            logEntity.setFailCause(throwable.getMessage());
        }
        long endTs = System.currentTimeMillis();
        long consumeTime = endTs - startTs;
        logEntity.setResponse(JSON.toJSONString(result));
        logEntity.setConsumeTime(consumeTime);
        LinkedBlockingQueueUtils.offerServerInvoke(logEntity);
        return result;
    }

    /**
     * Gets server invoke log entity.
     *
     * @param joinPoint the join point
     * @return the server invoke log entity
     */
    public static ServerInvokeLogEntity getServerInvokeLogEntity(ProceedingJoinPoint joinPoint) {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        String remoteAddr = request.getRemoteAddr();
        //获取通知中所有的方法
        Method[] methods = joinPoint.getTarget().getClass().getMethods();
        //获取当前调用的方法名
        String methodName = joinPoint.getSignature().getName();
        //遍历获取当前调用的方法
        Method targetMethod = Arrays.stream(methods)
                .filter(method -> methodName.equals(method.getName()))
                .findFirst()
                .get();
        ServerInvokeLog annotation = targetMethod.getAnnotation(ServerInvokeLog.class);
        ServerInvokeLogEntity entity = new ServerInvokeLogEntity();
        entity.setClientIp(remoteAddr);
        entity.setInvokeTime(System.currentTimeMillis() / 1000);
        entity.setServerApi(request.getRequestURI());
        entity.setSuccess(true);
        entity.setServerDescription(annotation.serverDescription());
        List<Integer> collect = Arrays.stream(annotation.invoker())
                .map(invokerType -> invokerType.getValue())
                .collect(Collectors.toList());
        Integer[] invokers = collect.toArray(new Integer[collect.size()]);
        entity.setInvoker(invokers);
        entity.setServerType(annotation.logType().getValue());
        return entity;
    }

}
