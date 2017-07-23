package com.drore.cloud.control.manger.common.cache.service.impl;

import com.alibaba.fastjson.JSON;
import com.drore.cloud.control.manger.common.cache.service.RedisBuilder;
import com.drore.cloud.control.manger.common.cache.utils.FastJsonRedisSerializer;
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


    @Resource
    private RedisTemplate<String, Object> redisTemplate;


    @Override
    public void lPush(String key, Object value) {
        redisTemplate.execute((RedisCallback<Long>) connection ->
                connection.lPush(key.getBytes(), JSON.toJSONBytes(value)));
    }

    @Override
    public List<Object> lRange(String key, long start, long end) {
        return redisTemplate.execute((RedisCallback<List<Object>>) connection ->
                new FastJsonRedisSerializer()
                        .deserializeList(connection.lRange(key.getBytes(), start, end)));
    }

    @Override
    public void set(String key, Object value) {
        redisTemplate.execute((RedisCallback) connection -> {
            connection.set(key.getBytes(), JSON.toJSONBytes(value));
            return null;
        });
    }

    @Override
    public void setNX(String key, Object value) {
        redisTemplate.execute((RedisCallback<Boolean>) connection ->
                connection.setNX(key.getBytes(), JSON.toJSONBytes(value)));
    }

    @Override
    public void setEX(String key, Object value, long seconds) {
        redisTemplate.execute((RedisCallback) connection -> {
            connection.setEx(key.getBytes(), seconds, JSON.toJSONBytes(value));
            return null;
        });
    }

    @Override
    public Object get(String key) {
        return redisTemplate.execute((RedisCallback<Object>) connection ->
                new FastJsonRedisSerializer().deserialize(connection.get(key.getBytes())));
    }
}
