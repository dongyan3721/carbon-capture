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
 * @date 2024/1/20-22:35:43
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "t_province_news")
public class ProvinceNews extends BaseEntity {

    @Id
    @Column(name = "news_id")
    private String newsId;

    @Column(name = "title")
    private String title;

    @Column(name = "content_url")
    private String contentUrl;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "province_id")
    private String provinceId;
}
