package com.carboncapture.carbon.controller;

import com.carboncapture.carbon.core.AjaxResult;
import com.carboncapture.carbon.entity.*;
import com.carboncapture.carbon.service.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProvinceController {
    @Autowired
    private ProvinceService provinceService;
    @GetMapping("/province/case/query")
    public AjaxResult queryProvinceCase(@RequestBody ProvinceCase provinceCase){
        List<ProvinceCase> caseList=provinceService.getCaselist(provinceCase);
        return AjaxResult.success(caseList);
    }

    @GetMapping("/province/intent/query")
    public AjaxResult queryProvinceIntent(@RequestBody ProvinceIntent provinceIntent){
        List<ProvinceIntent> provinceIntentList=provinceService.getIntentlist(provinceIntent);
        return AjaxResult.success(provinceIntentList);
    }

    @GetMapping("/province/policy/query")
    public AjaxResult queryProvincePolicy(ProvincePolicy provincePolicy){
        List<ProvincePolicy> provincePolicyList=provinceService.getPolicylist(provincePolicy);
        return AjaxResult.success(provincePolicyList);
    }

    @GetMapping("/province/news/query")
    public AjaxResult queryProvinceNews(ProvinceNews provinceNews){
        List<ProvinceNews> provinceNewsList=provinceService.getNewslist(provinceNews);
        return AjaxResult.success(provinceNews);

    }

    @GetMapping("/province/carbon-output/{year}")
    public AjaxResult requestCarbonOutput(@PathVariable String year){
        List<ProvinceCarbonOutput> provinceCarbonOutputList=provinceService.getTotalCarbonOutput(year);
        return AjaxResult.success(provinceCarbonOutputList);
    }

}
