package com.carboncapture.carbon.mapper;

import com.carboncapture.carbon.entity.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ProvinceMapper {
    /**
     * 根据province_id 返回案例列表
     * @param provinceCase
     * @return
     */
    @Select("select * from t_province_case where province_id=#{provinceId}")
    List<ProvinceCase> getCaselistById(ProvinceCase provinceCase);

    /**
     * 根据province_id 返回intent列表
     * @param provinceIntent
     * @return
     */
    @Select("select * from t_province_intent where province_id=#{provinceId} ")
    List<ProvinceIntent> getIntentlist(ProvinceIntent provinceIntent);

    /**
     * 根据province_id 返回政策列表
     * @param provincePolicy
     * @return
     */
    @Select("select *from t_province_policy where province_id=#{provinceId}")
    List<ProvincePolicy> getPolicylist(ProvincePolicy provincePolicy);

    @Select("select *from t_province_news where province_id=#{provinceId}")
    List<ProvinceNews> getNewslist(ProvinceNews provinceNews);

    @Select("select * from t_province_carbon_output where year=#{year}")
    List<ProvinceCarbonOutput> getAllCarbonOutput(String year);
}
