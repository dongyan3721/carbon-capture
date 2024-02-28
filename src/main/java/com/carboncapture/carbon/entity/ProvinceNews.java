package com.carboncapture.carbon.entity;

import com.carboncapture.carbon.entity.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author Santa Antilles
 * @description
 * @date 2024/1/20-22:35:43
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProvinceNews extends BaseEntity {

    private String newsId;

    private String title;

    private String contentUrl;

    private String imageUrl;

    private String provinceId;
}
