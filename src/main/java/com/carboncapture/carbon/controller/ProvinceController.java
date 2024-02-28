package com.carboncapture.carbon.controller;

import com.carboncapture.carbon.core.TableDataInfo;
import com.carboncapture.carbon.entity.*;
import com.carboncapture.carbon.service.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProvinceController {
    @Autowired
    private ProvinceService provinceService;
    @GetMapping("/province/case/query")
    public TableDataInfo queryProvinceCase(@RequestBody ProvinceCase provinceCase,
                                           @RequestParam(value = "pageNum", required = false, defaultValue = "1") int pageNum,
                                           @RequestParam(value = "pageSize", required = false, defaultValue = "6") int pageSize){
        List<ProvinceCase> caseList=provinceService.getCaselist(provinceCase);
        return TableDataInfo.pagination(caseList, caseList.size(), pageNum, pageSize);
    }

    @GetMapping("/province/intent/query")
    public TableDataInfo queryProvinceIntent(@RequestBody ProvinceIntent provinceIntent,
                                             @RequestParam(value = "pageNum", required = false, defaultValue = "1") int pageNum,
                                             @RequestParam(value = "pageSize", required = false, defaultValue = "6") int pageSize){
        List<ProvinceIntent> provinceIntentList=provinceService.getIntentlist(provinceIntent);
        return TableDataInfo.pagination(provinceIntentList, provinceIntentList.size(), pageNum, pageSize);
    }

    @GetMapping("/province/policy/query")
    public TableDataInfo queryProvincePolicy(@RequestBody ProvincePolicy provincePolicy,
                                             @RequestParam(value = "pageNum", required = false, defaultValue = "1") int pageNum,
                                             @RequestParam(value = "pageSize", required = false, defaultValue = "6") int pageSize){
        List<ProvincePolicy> provincePolicyList=provinceService.getPolicylist(provincePolicy);
        return TableDataInfo.pagination(provincePolicyList, provincePolicyList.size(), pageNum, pageSize);
    }

    @GetMapping("/province/news/query")
    public TableDataInfo queryProvinceNews(@RequestBody ProvinceNews provinceNews,
                                           @RequestParam(value = "pageNum", required = false, defaultValue = "1") int pageNum,
                                           @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize)
    {
        List<ProvinceNews> provinceNewsList=provinceService.getNewslist(provinceNews);
        return TableDataInfo.pagination(provinceNewsList, provinceNewsList.size(), pageNum, pageSize);
    }

    @GetMapping("/province/carbon-output/{year}")
    public TableDataInfo requestCarbonOutput(@PathVariable String year){
        List<ProvinceCarbonOutput> provinceCarbonOutputList=provinceService.getTotalCarbonOutput(year);
        return TableDataInfo.success(provinceCarbonOutputList, provinceCarbonOutputList.size());
    }

}
