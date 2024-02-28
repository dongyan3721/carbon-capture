package com.carboncapture.carbon.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @author Santa Antilles
 * @description
 * @date 2024/1/20-22:18:37
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProvinceCarbonOutput {

    private String outputId;

    private String provinceId;

    private String provinceName;

    private BigDecimal output;

    private String year;
}
