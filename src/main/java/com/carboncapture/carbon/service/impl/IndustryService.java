package com.carboncapture.carbon.service.impl;

import com.carboncapture.carbon.entity.IndustryYearlyCarbon;
import com.carboncapture.carbon.mapper.IndustryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IndustryService {

    @Autowired
    private IndustryMapper industryMapper;

    public List<IndustryYearlyCarbon> getYearlyCarbonOutputByYear(int year) {
        return industryMapper.selectCarbonOutputByYear(year);
    }
}
