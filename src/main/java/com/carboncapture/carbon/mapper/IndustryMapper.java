package com.carboncapture.carbon.mapper;

import com.carboncapture.carbon.entity.IndustryYearlyCarbon;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface IndustryMapper {

    // scope1_total一项计总排量
    @Select("select * from t_industry_yearly_carbon where year = #{year} and carbon_source = 'scope1_total'")
    List<IndustryYearlyCarbon> selectCarbonOutputByYear(@Param("year") Integer year);

}
