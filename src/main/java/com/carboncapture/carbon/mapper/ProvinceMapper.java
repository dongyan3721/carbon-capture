package com.carboncapture.carbon.mapper;

import com.carboncapture.carbon.entity.Province;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author Santa Antilles
 * @description
 * @date 2024/1/14-00:08:26
 */
//@Repository
public interface ProvinceMapper extends
        CrudRepository<Province, String>,
        PagingAndSortingRepository<Province, String> {

}
