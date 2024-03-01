package com.carboncapture.carbon.service;

import com.carboncapture.carbon.core.AjaxResult;
import com.carboncapture.carbon.entity.*;

import java.util.List;

public interface ProvinceService {

    List<ProvinceCase> getCaselist(ProvinceCase provinceCase);

    List<ProvinceIntent> getIntentlist(ProvinceIntent provinceIntent);

    List<ProvincePolicy> getPolicylist(ProvincePolicy provincePolicy);

    List<ProvinceNews> getNewslist(ProvinceNews provinceNews);

    List<ProvinceCarbonOutput> getTotalCarbonOutput(String  year);

    List<Province> queryAllProvinces();
}
