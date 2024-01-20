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
 * @description
 * @date 2024/1/20-22:40:10
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "t_province_intent")
@Entity
public class ProvinceIntent extends BaseEntity {

    @Id
    @Column(name = "intent_id")
    private String intentId;

    @Column(name = "title")
    private String title;

    @Column(name = "url")
    private String url;

    @Column(name = "province_id")
    private String provinceId;
}
