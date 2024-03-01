package com.carboncapture.carbon.service.impl;

import com.carboncapture.carbon.core.AjaxResult;
import com.carboncapture.carbon.entity.*;
import com.carboncapture.carbon.mapper.ProvinceMapper;
import com.carboncapture.carbon.service.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ProvinceServiceImpl implements ProvinceService {
    @Autowired
    private ProvinceMapper provinceMapper;

    @Override
    public List<ProvinceCase> getCaselist(ProvinceCase provinceCase) {
        return provinceMapper.getCaselistById(provinceCase);
    }

    @Override
    public List<ProvinceIntent> getIntentlist(ProvinceIntent provinceIntent) {
        return provinceMapper.getIntentlist(provinceIntent);
    }

    @Override
    public List<ProvincePolicy> getPolicylist(ProvincePolicy provincePolicy) {
        return provinceMapper.getPolicylist(provincePolicy);
    }

    @Override
    public List<ProvinceNews> getNewslist(ProvinceNews provinceNews) {
        return provinceMapper.getNewslist(provinceNews);
    }

    @Override
    public List<ProvinceCarbonOutput> getTotalCarbonOutput(String year) {
        return provinceMapper.getAllCarbonOutput(year);
    }

    @Override
    public List<Province> queryAllProvinces() {
        return provinceMapper.queryAllProvinces();
    }
}
