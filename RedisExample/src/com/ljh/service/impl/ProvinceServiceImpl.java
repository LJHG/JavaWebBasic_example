package com.ljh.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ljh.dao.ProvinceDao;
import com.ljh.dao.impl.ProvinceDaoImpl;
import com.ljh.domain.Province;
import com.ljh.service.ProvinceService;
import com.ljh.util.JedisPoolUtils;
import redis.clients.jedis.Jedis;

import java.util.List;

public class ProvinceServiceImpl implements ProvinceService {
    ProvinceDao dao = new ProvinceDaoImpl();

    public ProvinceServiceImpl() throws Exception {
    }

    @Override
    public List<Province> getAllProvince() throws Exception {
        return  dao.getAllProvince();
    }

    /**
     * using redis
     * @return
     */
    @Override
    public String getAllProvince_redis() throws Exception {
        //get jedis connection
        Jedis jedis = JedisPoolUtils.getJedis();

        if(jedis.get("province") == null || jedis.get("province").length() == 0)
        {
            System.out.println("redis中没有数据，查询数据库");
            List<Province> list = getAllProvince();
            ObjectMapper objectMapper = new ObjectMapper();
            String json = objectMapper.writeValueAsString(list);
            jedis.set("province",json);
            return json;
        }
        else
        {
            System.out.println("redis中有数据，直接返回");
            return jedis.get("province");
        }
    }
}
