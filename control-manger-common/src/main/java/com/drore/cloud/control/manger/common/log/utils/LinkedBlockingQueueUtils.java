package com.drore.cloud.control.manger.common.log.utils;

import com.drore.cloud.control.manger.common.log.entity.HttpRequestLogEntity;
import com.drore.cloud.control.manger.common.log.entity.ServerInvokeLogEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * 卓锐科技有限公司
 * Created by wmm on 2017/7/20.
 * email：6492178@gmail.com
 * description:
 *
 * @author wmm
 */
public class LinkedBlockingQueueUtils {
    private static Logger LOGGER = LoggerFactory.getLogger(LinkedBlockingQueueUtils.class);
    private static final LinkedBlockingQueue<ServerInvokeLogEntity> invokeLogEntities = new LinkedBlockingQueue<>(500);
    private static final LinkedBlockingQueue<HttpRequestLogEntity> httpRequestLogEntities = new LinkedBlockingQueue<>(500);

    /**
     * Offer.
     *
     * @param invokeLogEntity the invoke log entity
     */
    public static void offerServerInvoke(ServerInvokeLogEntity invokeLogEntity) {
        invokeLogEntities.offer(invokeLogEntity);
    }

    /**
     * Offer http request.
     *
     * @param httpRequestLogEntity the http request log entity
     */
    public static void offerHttpRequest(HttpRequestLogEntity httpRequestLogEntity) {
        httpRequestLogEntities.offer(httpRequestLogEntity);
    }

    /**
     * Take object.
     *
     * @return the object
     * @throws InterruptedException the interrupted exception
     */
    public static ServerInvokeLogEntity pollServerInvoke() throws InterruptedException {
        return invokeLogEntities.poll();
    }

    /**
     * Take http request http request log entity.
     *
     * @return the http request log entity
     * @throws InterruptedException the interrupted exception
     */
    public static HttpRequestLogEntity pollHttpRequest() throws InterruptedException {
        return httpRequestLogEntities.poll();
    }

    /**
     * Is empty boolean.
     *
     * @return the boolean
     */
    public static boolean isEmpty() {
        return invokeLogEntities.isEmpty() && httpRequestLogEntities.isEmpty();
    }
}
