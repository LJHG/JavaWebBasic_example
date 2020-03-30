package com.ljh.dao.impl;

import com.ljh.dao.ProvinceDao;
import com.ljh.domain.Province;
import com.ljh.util.jdbcUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;
import java.util.Properties;

public class ProvinceDaoImpl implements ProvinceDao {
    JdbcTemplate jdbcTemplate = new JdbcTemplate(jdbcUtils.getDataSource());

    public ProvinceDaoImpl() throws Exception {
    }

    @Override
    public List<Province> getAllProvince() throws Exception {
        String sql = "select * from province";
        List<Province> list = jdbcTemplate.query(sql,new BeanPropertyRowMapper<Province>(Province.class));
        return list;
    }
}
