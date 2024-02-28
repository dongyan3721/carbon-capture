package com.carboncapture.carbon.entity;

import com.carboncapture.carbon.entity.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @author Santa Antilles
 * @description
 * @date 2024/1/20-22:40:45
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarbonUerBelong extends BaseEntity {

    private String belongId;

    private String userId;

    private String userNickname;

    private String provinceId;

    private String provinceName;

    private String industry;

    private String industryProcess;

    private BigDecimal blurCarbonOutput;
}
