package com.pop.cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Set;


@Repository
public class RedisOperateImp implements RedisOperate {

    private static final Logger Logger = LoggerFactory.getLogger(RedisOperateImp.class);

    @Resource
    private RedisPool redispool;

    @Override
    public void set(String key, String value) {
        Jedis con = null;
        try {
            con = redispool.getConnection();
            con.set(key, value);
        } finally {
            redispool.closeConnection(con);
        }
    }

    @Override
    public void set(String key, String value, int second) {
        Jedis con = null;
        try {
            con = redispool.getConnection();
            con.set(key, value);
            con.expire(key, second);
        } finally {
            redispool.closeConnection(con);
        }


    }

    @Override
    public void createCountKey(String key, int value) {
        Jedis con = null;
        try {
            con = redispool.getConnection();
            con.set(key, Integer.toString(value));
        } finally {
            redispool.closeConnection(con);
        }

    }

    @Override
    public void del(String key) {
        Jedis con = null;
        try {
            con = redispool.getConnection();
            if (con != null) {
                con.del(key);
            }
        } finally {
            redispool.closeConnection(con);
        }

    }

    @Override
    public String getStringByKey(String key) {
        Jedis con = null;
        String res = null;
        try {
            con = redispool.getConnection();
            res = con.get(key);
        } finally {
            redispool.closeConnection(con);
        }
        return res;
    }

    @Override
    public List<String> getStringByKeys(List<String> keys){
        if (CollectionUtils.isEmpty(keys)){
            return null;
        }
        Jedis con = null;
        List<String> res = null;
        try {
            con = redispool.getConnection();
            res = con.mget(keys.toArray(new String[keys.size()]));
        } finally {
            redispool.closeConnection(con);
        }
        return res;
    }

    @Override
    public void keyDecrement(String key, int step) {
        Jedis con = null;
        try {
            con = redispool.getConnection();
            con.decrBy(key, step);
        } finally {

            redispool.closeConnection(con);
        }
    }

    @Override
    public long keyIncrement(String key, int step) {
        Jedis con = null;
        try {
            con = redispool.getConnection();
            return con.incrBy(key, step);
        } finally {
            redispool.closeConnection(con);
        }


    }

    @Override
    public void setAdd(String key, String value) {
        Jedis con = null;
        try {
            con = redispool.getConnection();
            con.sadd(key, value);
        } finally {
            redispool.closeConnection(con);
        }


    }

    @Override
    public void setDel(String key, String value) {
        Jedis con = null;
        try {
            con = redispool.getConnection();
            con.srem(key, value);
        } finally {
            redispool.closeConnection(con);
        }


    }

    @Override
    public Set<String> getSetMember(String key) {
        Jedis con = null;
        Set<String> set = null;
        try {
            con = redispool.getConnection();
            set = con.smembers(key);
        } finally {
            redispool.closeConnection(con);
        }
        return set;
    }

    @Override
    public long getSetSize(String key) {
        Jedis con = null;
        long size;
        try {
            con = redispool.getConnection();
            size = con.scard(key);
        } finally {
            redispool.closeConnection(con);
        }
        return size;
    }

    @Override
    public void zsetAdd(String key, long score, String value) {
        Jedis con = null;
        try {
            con = redispool.getConnection();
            con.zadd(key, score, value);
        } finally {
            redispool.closeConnection(con);
        }

    }

    @Override
    public void zsetDel(String key, String value) {
        Jedis con = null;
        try {
            con = redispool.getConnection();
            con.zrem(key, value);
        } finally {
            redispool.closeConnection(con);
        }
    }

    @Override
    public long getZsetSize(String key) {
        Jedis con = null;
        long size;
        try {
            con = redispool.getConnection();
            size = con.zcard(key);
        } finally {
            redispool.closeConnection(con);
        }
        return size;
    }

    @Override
    public Set<String> getZsetByscoreByPage(String key, String min, String max,
                                            int offset, int count) {
        Jedis con = null;
        Set<String> sets = null;
        try {
            con = redispool.getConnection();
            sets = con.zrangeByScore(key, min, max, offset, count);
        } finally {
            redispool.closeConnection(con);
        }
        return sets;
    }

    @Override
    public Set<String> getZsetByScore(String key, String min, String max) {

        Jedis con = null;
        Set<String> sets = null;
        try {
            redispool.getConnection();
            sets = con.zrangeByScore(key, min, max);
        } finally {
            redispool.closeConnection(con);
        }
        return sets;
    }

    @Override
    public long zsetRemrangebyScore(String key, String min, String max) {
        Jedis con = null;
        long size;
        try {
            redispool.getConnection();
            size = con.zremrangeByScore(key, min, max);
        } finally {
            redispool.closeConnection(con);
        }
        return size;
    }

    @Override
    public void listAdd(String key, String value) {
        Jedis con = null;
        try {
            con = redispool.getConnection();
            con.lpush(key, value);
        } finally {
            redispool.closeConnection(con);
        }

    }

    @Override
    public List<String> getListAll(String key, int start, int stop) {
        Jedis con = null;
        List<String> list = null;
        try {
            con = redispool.getConnection();
            list = con.lrange(key, start, stop);
        } finally {
            redispool.closeConnection(con);
        }
        return list;
    }

    @Override
    public void listRangeDel(String key, int start, int stop) {
        Jedis con = null;
        try {
            con = redispool.getConnection();
            con.ltrim(key, start, stop);
        } finally {
            redispool.closeConnection(con);
        }

    }

    @Override
    public long listDelByValue(String key, long count, String value) {
        Jedis con = null;
        long amount;
        try {
            con = redispool.getConnection();
            amount = con.lrem(key, count, value);
        } finally {
            redispool.closeConnection(con);
        }
        return amount;
    }

    @Override
    public String hmset(String key, Map<String, String> map) {
        Jedis con = null;
        String res = null;
        try {
            con = redispool.getConnection();
            res = con.hmset(key, map);
        } finally {
            redispool.closeConnection(con);
        }
        return res;
    }

    @Override
    public long hincrby(String key, String field, long step) {
        Jedis con = null;
        long size;
        try {
            con = redispool.getConnection();
            size = con.hincrBy(key, field, step);
        } finally {
            redispool.closeConnection(con);
        }
        return size;
    }

    @Override
    public String hget(String key, String field) {
        Jedis con = null;
        String res = null;
        try {
            con = redispool.getConnection();
            res = con.hget(key, field);
        } finally {
            redispool.closeConnection(con);
        }
        return res;
    }

    @Override
    public Map<String, String> hgetall(String key) {
        Jedis con = null;
        Map<String, String> res = null;
        try {
            con = redispool.getConnection();
            res = con.hgetAll(key);
        } finally {
            redispool.closeConnection(con);
        }
        return res;
    }

    @Override
    public List<Object> getZsetByScoreAndRemove(String key, String min, String max) {
        Jedis con = null;
        List<Object> res = null;
        try {
            con = redispool.getConnection();
            Transaction tx = con.multi();
            tx.zrangeByScore(key, min, max);
            tx.zremrangeByScore(key, min, max);
            res = tx.exec();
        } finally {
            redispool.closeConnection(con);
        }
        return res;
    }

    @Override
    public long setExpire(String key, int seconds) {
        Jedis con = null;
        Long expire = null;
        try {
            con = redispool.getConnection();
            expire = con.expire(key, seconds);
        } finally {
            redispool.closeConnection(con);
        }
        return expire;
    }

    @Override
    public void setnx(String key,String value,int second){
        Jedis con = null;
        con = redispool.getConnection();
        try {
            con.setnx(key,value);
            con.expire(key,second);
        }finally {
            redispool.closeConnection(con);
        }
    }
}
