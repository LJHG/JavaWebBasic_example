package com.ljh.service;

import com.ljh.domain.Province;

import java.util.List;

public interface ProvinceService {
    public List<Province> getAllProvince() throws Exception;

    public String getAllProvince_redis() throws Exception;
}
