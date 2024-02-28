package com.carboncapture.carbon.mapper;

import com.carboncapture.carbon.entity.Province;
import org.apache.ibatis.annotations.Select;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Santa Antilles
 * @description
 * @date 2024/1/14-00:08:26
 */
@Repository
public interface ProvinceMapper extends
        CrudRepository<Province, String>,
        PagingAndSortingRepository<Province, String> {


    /**
     *
     * @return
     */
    @Select("select * from t_provinces")
    List<Province> getAllProvinces();
}
