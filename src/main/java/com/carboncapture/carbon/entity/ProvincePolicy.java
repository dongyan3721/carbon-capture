package com.carboncapture.carbon.entity;

import com.carboncapture.carbon.entity.base.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
@Entity
@Table(name = "t_province_policy")
public class ProvincePolicy extends BaseEntity {

    @Id
    @Column(name = "policy_id")
    private String policyId;

    @Column(name = "title")
    private String title;

    @Column(name = "url")
    private String url;

    @Column(name = "province_id")
    private String provinceId;
}
