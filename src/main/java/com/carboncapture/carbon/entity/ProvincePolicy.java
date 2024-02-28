package com.carboncapture.carbon.entity;

import com.carboncapture.carbon.entity.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author Santa Antilles
 * @description 省份政策表
 * @date 2024/1/20-22:39:48
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProvincePolicy extends BaseEntity {

    private String policyId;

    private String title;

    private String url;

    private String provinceId;
}
