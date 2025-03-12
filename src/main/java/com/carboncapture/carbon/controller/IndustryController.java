package com.carboncapture.carbon.controller;


import com.carboncapture.carbon.core.TableDataInfo;
import com.carboncapture.carbon.entity.IndustryYearlyCarbon;
import com.carboncapture.carbon.framework.annotation.AccessWithoutVerification;
import com.carboncapture.carbon.service.impl.IndustryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("industry")
public class IndustryController {

    @Autowired
    IndustryService industryService;

    @AccessWithoutVerification
    @GetMapping("/byYear/{year}")
    public TableDataInfo getIndustryCarbonOutputByYear(@PathVariable("year") int year){
        List<IndustryYearlyCarbon> byYear = industryService.getYearlyCarbonOutputByYear(year);
        return TableDataInfo.success(byYear, byYear.size());
    }
}
