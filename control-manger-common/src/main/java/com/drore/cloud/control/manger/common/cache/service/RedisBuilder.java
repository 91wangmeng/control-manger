package com.drore.cloud.control.manger.common.cache.service;

import java.util.List;

/**
 * 浙江卓锐科技股份有限公司 版权所有 © Copyright 2017<br/>
 * 说明: 带global为全局缓存<br/>
 * 项目名称: control-manger <br/>
 * 创建日期: 2017年07月23日 21:03 <br/>
 * 作者: <a href="6492178@gmail.com">汪萌萌</a>
 */
public interface RedisBuilder {
    /**
     * L push.
     *
     * @param key   the key
     * @param value the value
     */
    void lPush(String key, Object value);

    /**
     * Global l push.
     *
     * @param key   the key
     * @param value the value
     */
    void globalLPush(String key, Object value);

    /**
     * L range list.
     *
     * @param key   the key
     * @param start the start
     * @param end   the end
     * @return the list
     */
    List<Object> lRange(String key, long start, long end);

    /**
     * Global l range list.
     *
     * @param key   the key
     * @param start the start
     * @param end   the end
     * @return the list
     */
    List<Object> globalLRange(String key, long start, long end);

    /**
     * Set.
     *
     * @param key   the key
     * @param value the value
     */
    void set(String key, Object value);

    /**
     * Sets global.
     *
     * @param key   the key
     * @param value the value
     */
    void setGlobal(String key, Object value);

    /**
     * Sets nx.
     *
     * @param key   the key
     * @param value the value
     */
    void setNX(String key, Object value);

    /**
     * Sets global nx.
     *
     * @param key   the key
     * @param value the value
     */
    void setGlobalNX(String key, Object value);

    /**
     * Sets ex.
     *
     * @param key     the key
     * @param value   the value
     * @param seconds the seconds
     */
    void setEX(String key, Object value, long seconds);

    /**
     * Sets global ex.
     *
     * @param key     the key
     * @param value   the value
     * @param seconds the seconds
     */
    void setGlobalEX(String key, Object value, long seconds);

    /**
     * Get object.
     *
     * @param key the key
     * @return the object
     */
    Object get(String key);

    /**
     * Gets global.
     *
     * @param key the key
     * @return the global
     */
    Object getGlobal(String key);

    /**
     * Exit key boolean.
     *
     * @param key the key
     * @return the boolean
     */
    boolean exitKey(String key);

    /**
     * Exit global key boolean.
     *
     * @param key the key
     * @return the boolean
     */
    boolean exitGlobalKey(String key);

    /**
     * Del key long.
     *
     * @param key the key
     * @return the long
     */
    Long delKey(String key);

    /**
     * Del global key long.
     *
     * @param key the key
     * @return the long
     */
    Long delGlobalKey(String key);
}
