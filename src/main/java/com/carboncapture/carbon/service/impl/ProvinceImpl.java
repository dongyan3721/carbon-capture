package com.carboncapture.carbon.service.impl;

import com.carboncapture.carbon.entity.Province;
import com.carboncapture.carbon.mapper.ProvinceMapper;
import com.carboncapture.carbon.service.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProvinceImpl implements ProvinceService {
    @Autowired
    private ProvinceMapper provinceMapper;
    @Override
    public List<Province> getAllProvince() {
        return provinceMapper.getAllProvinces();
    }
}
