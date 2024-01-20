package com.carboncapture.carbon.entity;

import com.carboncapture.carbon.entity.base.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
@Table(name = "t_user_belong")
public class CarbonUerBelong extends BaseEntity {

    @Id
    @Column(name = "belong_id")
    private String belongId;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "user_nick_name")
    private String userNickname;

    @Column(name = "province_id")
    private String provinceId;

    @Column(name = "province_name")
    private String provinceName;

    @Column(name = "industry")
    private String industry;

    @Column(name = "industry_process")
    private String industryProcess;

    @Column(name = "blur_carbon_output")
    private BigDecimal blurCarbonOutput;
}
