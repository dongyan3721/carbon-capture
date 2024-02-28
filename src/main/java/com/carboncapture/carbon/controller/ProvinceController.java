package com.carboncapture.carbon.controller;

import com.carboncapture.carbon.core.AjaxResult;
import com.carboncapture.carbon.entity.Province;
import com.carboncapture.carbon.service.ProvinceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
public class ProvinceController {
    @Autowired
    private ProvinceService provinceService;

    @GetMapping("/province/query")
    public AjaxResult requestAllProvinces(){
        List<Province> provinces=provinceService.getAllProvince();
        return AjaxResult.success(provinces);
    }

}
