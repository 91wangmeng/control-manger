package com.drore.cloud.control.manger.common.log.utils;

import com.alibaba.ttl.TransmittableThreadLocal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 卓锐科技有限公司
 * Created by wmm on 2017/7/20.
 * email：6492178@gmail.com
 * description:
 *
 * @author wmm
 */
public class TransmittableThreadLocalUtils {
    private static Logger LOGGER = LoggerFactory.getLogger(TransmittableThreadLocalUtils.class);
    private static final TransmittableThreadLocal<String> TRANSMITTABLE_THREAD_LOCAL = new TransmittableThreadLocal<>();

    /**
     * Set.
     *
     * @param value the value
     */
    public static void set(String value) {
        LOGGER.debug("TransmittableThreadLocalUtils传入值:{}", value);
        TRANSMITTABLE_THREAD_LOCAL.set(value);
    }

    /**
     * Get string.
     *
     * @return the string
     */
    public static String get() {
        return TRANSMITTABLE_THREAD_LOCAL.get();
    }
}
