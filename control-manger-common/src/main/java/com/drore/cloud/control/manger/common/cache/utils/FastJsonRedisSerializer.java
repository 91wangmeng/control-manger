package com.drore.cloud.control.manger.common.cache.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

import java.nio.charset.Charset;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 卓锐科技有限公司
 * Created by wmm on 2017/4/20.
 * email：6492178@gmail.com
 * description:
 *
 * @author wmm
 */
public class FastJsonRedisSerializer implements RedisSerializer<Object> {
    /**
     * The constant DEFAULT_CHARSET.
     */
    public static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");


    @Override
    public byte[] serialize(Object t) throws SerializationException {
        if (t == null) {
            return new byte[0];
        }
        return JSON.toJSONString(t, SerializerFeature.WriteClassName).getBytes(DEFAULT_CHARSET);
    }


    @Override
    public Object deserialize(byte[] bytes) throws SerializationException {
        if (bytes == null || bytes.length <= 0) {
            return null;
        }
        String str = new String(bytes, DEFAULT_CHARSET);
        //获取
        return JSON.parseObject(str, Object.class);
    }

    /**
     * Deserialize list list.
     *
     * @param list the list
     * @return the list
     * @throws SerializationException the serialization exception
     */
    public List<Object> deserializeList(List<byte[]> list) throws SerializationException {
        if (list == null && list.size() <= 0) {
            return null;
        }
        return list.stream().map(bytes -> {
            if (bytes == null || bytes.length <= 0) {
                return null;
            }
            String str = new String(bytes, DEFAULT_CHARSET);
            return JSON.parseObject(str, Object.class);

        }).collect(Collectors.toList());
    }
}
