package com.carboncapture.carbon.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @author Santa Antilles
 * @description
 * @date 2024/1/20-22:18:37
 */
@Entity
@Table(name = "t_province_carbon_output")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProvinceCarbonOutput {

    @Id @Column(name = "output_id")
    private String outputId;

    @Column(name = "province_id")
    private String provinceId;

    @Column(name = "province_name")
    private String provinceName;

    @Column(name = "output")
    private BigDecimal output;

    @Column(name = "year")
    private String year;
}
