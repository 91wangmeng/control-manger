package com.drore.cloud.control.manger.common.cache.service.impl;

import com.alibaba.fastjson.JSON;
import com.drore.cloud.control.manger.common.cache.service.RedisBuilder;
import com.drore.cloud.control.manger.common.cache.utils.FastJsonRedisSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * 卓锐科技有限公司
 * Created by wmm on 2017/4/20.
 * email：6492178@gmail.com
 * description: redis工具类
 *
 * @author wmm
 */
@Component
public class RedisBuilderImpl implements RedisBuilder {

    @Value("${scenic.name}")
    private String scenic_name;

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 为了区分缓存所属景区,所有key统一增加景区前缀
     *
     * @param key
     * @return
     */
    private String getKey(String key) {
        return scenic_name + "_" + key;
    }

    @Override
    public void lPush(String key, Object value) {
        globalLPush(getKey(key), value);
    }

    @Override
    public void globalLPush(String key, Object value) {
        redisTemplate.execute((RedisCallback<Long>) connection ->
                connection.lPush(key.getBytes(), JSON.toJSONBytes(value)));
    }

    @Override
    public List<Object> lRange(String key, long start, long end) {
        return globalLRange(getKey(key), start, end);
    }

    @Override
    public List<Object> globalLRange(String key, long start, long end) {
        return redisTemplate.execute((RedisCallback<List<Object>>) connection ->
                new FastJsonRedisSerializer()
                        .deserializeList(connection.lRange(key.getBytes(), start, end)));
    }

    @Override
    public void set(String key, Object value) {
        setGlobal(getKey(key), value);
    }

    @Override
    public void setGlobal(String key, Object value) {
        redisTemplate.execute((RedisCallback) connection -> {
            connection.set(key.getBytes(), JSON.toJSONBytes(value));
            return null;
        });
    }

    @Override
    public void setNX(String key, Object value) {
        setGlobalNX(getKey(key), JSON.toJSONBytes(value));
    }

    @Override
    public void setGlobalNX(String key, Object value) {
        redisTemplate.execute((RedisCallback<Boolean>) connection ->
                connection.setNX(key.getBytes(), JSON.toJSONBytes(value)));
    }

    @Override
    public void setEX(String key, Object value, long seconds) {
        setGlobalEX(getKey(key), value, seconds);
    }

    @Override
    public void setGlobalEX(String key, Object value, long seconds) {
        redisTemplate.execute((RedisCallback) connection -> {
            connection.setEx(key.getBytes(), seconds, JSON.toJSONBytes(value));
            return null;
        });
    }

    @Override
    public Object get(String key) {
        return getGlobal(getKey(key));
    }

    @Override
    public Object getGlobal(String key) {
        return redisTemplate.execute((RedisCallback<Object>) connection ->
                new FastJsonRedisSerializer().deserialize(connection.get(key.getBytes())));
    }

    @Override
    public boolean exitKey(String key) {
        return exitGlobalKey(getKey(key));
    }

    @Override
    public boolean exitGlobalKey(String key) {
        return redisTemplate.execute((RedisCallback<Boolean>) connection -> connection.exists(key.getBytes()));
    }

    @Override
    public Long delKey(String key) {
        return delGlobalKey(getKey(key));
    }

    @Override
    public Long delGlobalKey(String key) {
        return redisTemplate.execute((RedisCallback<Long>) connection -> connection.del(key.getBytes()));
    }
}
