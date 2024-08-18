package com.white.blog.utils;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author white_
 * @version 1.0
 * @project HuaJiao-system
 * @description redis工具类
 * @date 2024/4/25 22:24:24
 */

@Component
public class RedisUtils {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * @param key
     * @param value
     * @return boolean
     * @name: set
     * @author: white_
     * @description: 写入缓存
     * @date: 2024/4/25 22:27:39
     */
    public boolean set(final String key, Object value) {
        boolean result = false;
        try {
            ValueOperations<String, Object> operations = redisTemplate.opsForValue();
            operations.set(key, value);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * @param key
     * @param value
     * @param expireTime
     * @return boolean
     * @name: set
     * @author: white_
     * @description: 写入缓存并设置过期时间
     * @date: 2024/4/25 22:28:03
     */
    public boolean set(final String key, Object value, Long expireTime) {
        boolean result = false;
        try {
            ValueOperations<String, Object> operations = redisTemplate.opsForValue();
            operations.set(key, value);
            redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * @param key
     * @param value
     * @return boolean
     * @name: getAndSet
     * @author: white_
     * @description: 更新缓存
     * @date: 2024/4/25 22:29:07
     */
    public boolean getAndSet(final String key, String value) {
        boolean result = false;
        try {
            redisTemplate.opsForValue().getAndSet(key, value);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * @param keys
     * @return void
     * @name: remove
     * @author: white_
     * @description: 批量删除对应的值
     * @date: 2024/4/25 22:29:26
     */
    public void remove(final String... keys) {
        for (String key : keys) {
            remove(key);
        }
    }

    /**
     * @param pattern
     * @return void
     * @name: removePattern
     * @author: white_
     * @description: 匹配key批量删除
     * @date: 2024/4/25 22:30:12
     */
    public void removePattern(final String pattern) {
        Set<String> keys = redisTemplate.keys(pattern);
        if (CollectionUtils.isNotEmpty(keys)) {
            redisTemplate.delete(keys);
        }
    }

    /**
     * @param key
     * @return void
     * @name: remove
     * @author: white_
     * @description: 删除对应的值
     * @date: 2024/4/25 22:30:33
     */
    public void remove(final String key) {
        if (exists(key)) {
            redisTemplate.delete(key);
        }
    }

    /**
     * @param key
     * @return boolean
     * @name: exists
     * @author: white_
     * @description: 判断缓存中是否存在对应的值
     * @date: 2024/4/25 22:31:00
     */
    public boolean exists(final String key) {
        Boolean isExists = redisTemplate.hasKey(key);
        return BooleanUtils.isTrue(isExists);
    }

    /**
     * @param key
     * @return Object
     * @name: get
     * @author: white_
     * @description: 读取对应的值
     * @date: 2024/4/25 22:31:11
     */
    public Object get(final String key) {
        ValueOperations<String, Object> operations = redisTemplate.opsForValue();
        return operations.get(key);
    }

    /**
     * @param key
     * @param hashKey
     * @param value
     * @return void
     * @name: hmSet
     * @author: white_
     * @description: 哈希写入值
     * @date: 2024/4/25 22:31:24
     */
    public void hmSet(String key, Object hashKey, Object value) {
        HashOperations<String, Object, Object> hash = redisTemplate.opsForHash();
        hash.put(key, hashKey, value);
    }

    /**
     * @param key
     * @param hashKey
     * @return Object
     * @name: hmGet
     * @author: white_
     * @description: 哈希获取对应值
     * @date: 2024/4/25 22:31:38
     */
    public Object hmGet(String key, Object hashKey) {
        HashOperations<String, Object, Object> hash = redisTemplate.opsForHash();
        return hash.get(key, hashKey);
    }

    /**
     * @param k
     * @param v
     * @return void
     * @name: lPush
     * @author: white_
     * @description: 列表添加
     * @date: 2024/4/25 22:32:00
     */
    public void lPush(String k, Object v) {
        ListOperations<String, Object> list = redisTemplate.opsForList();
        list.rightPush(k, v);
    }

    /**
     * @param k
     * @param l
     * @param l1
     * @return List<Object>
     * @name: lRange
     * @author: white_
     * @description: 列表获取
     * @date: 2024/4/25 22:32:09
     */
    public List<Object> lRange(String k, long l, long l1) {
        ListOperations<String, Object> list = redisTemplate.opsForList();
        return list.range(k, l, l1);
    }

    /**
     * @param key
     * @param value
     * @return void
     * @name: addSet
     * @author: white_
     * @description: 集合添加
     * @date: 2024/4/25 22:32:23
     */
    public void addSet(String key, Object value) {
        SetOperations<String, Object> set = redisTemplate.opsForSet();
        set.add(key, value);
    }

    /**
     * @param key
     * @return void
     * @name: removeSetAll
     * @author: white_
     * @description: 删除对应集合下的值
     * @date: 2024/4/25 22:32:46
     */
    public void removeSetAll(String key) {
        SetOperations<String, Object> set = redisTemplate.opsForSet();
        Set<Object> objectSet = set.members(key);
        if (objectSet != null && !objectSet.isEmpty()) {
            for (Object o : objectSet) {
                set.remove(key, o);
            }
        }
    }

    /**
     * @param key
     * @param member
     * @return Boolean
     * @name: isMember
     * @author: white_
     * @description: 判断对应集合内是否包含某个值
     * @date: 2024/4/25 22:33:23
     */
    public Boolean isMember(String key, Object member) {
        SetOperations<String, Object> set = redisTemplate.opsForSet();
        return set.isMember(key, member);
    }

    /**
     * @param key
     * @return Set<Object>
     * @name: setMembers
     * @author: white_
     * @description: 集合获取
     * @date: 2024/4/25 22:33:32
     */
    public Set<Object> setMembers(String key) {
        SetOperations<String, Object> set = redisTemplate.opsForSet();
        return set.members(key);
    }

    /**
     * @param key
     * @param value
     * @param source
     * @return void
     * @name: zAdd
     * @author: white_
     * @description: 有序集合添加
     * @date: 2024/4/25 22:33:39
     */
    public void zAdd(String key, Object value, double source) {
        ZSetOperations<String, Object> zset = redisTemplate.opsForZSet();
        zset.add(key, value, source);
    }

    /**
     * @param key
     * @param source
     * @param source1
     * @return Set<Object>
     * @name: rangeByScore
     * @author: white_
     * @description: 有序集合获取指定范围数据]
     * @date: 2024/4/25 22:33:54
     */
    public Set<Object> rangeByScore(String key, double source, double source1) {
        ZSetOperations<String, Object> zSet = redisTemplate.opsForZSet();
        return zSet.rangeByScore(key, source, source1);
    }

    /**
     * @param key
     * @param source
     * @param source1
     * @return Set<Object>
     * @name: range
     * @author: white_
     * @description: 有序集合升序获取
     * @date: 2024/4/25 22:34:03
     */
    public Set<Object> range(String key, Long source, Long source1) {
        ZSetOperations<String, Object> zSet = redisTemplate.opsForZSet();
        return zSet.range(key, source, source1);
    }

    /**
     * @param key
     * @param source
     * @param source1
     * @return Set<Object>
     * @name: reverseRange
     * @author: white_
     * @description: 有序集合降序获取
     * @date: 2024/4/25 22:34:12
     */
    public Set<Object> reverseRange(String key, Long source, Long source1) {
        ZSetOperations<String, Object> zSet = redisTemplate.opsForZSet();
        return zSet.reverseRange(key, source, source1);
    }
}
