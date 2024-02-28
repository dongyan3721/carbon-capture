package com.carboncapture.carbon.entity;

import com.carboncapture.carbon.entity.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


/**
 * @author Santa Antilles
 * @description
 * @date 2024/1/20-22:40:10
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProvinceIntent extends BaseEntity {

    private String intentId;

    private String title;

    private String url;

    private String provinceId;
}
