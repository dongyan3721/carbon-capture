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
 * @date 2024/1/20-22:10:39
 */
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "t_carbon_user")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarbonUser extends BaseEntity {
    @Id
    @Column(name = "user_id")
    private String userId;

    @Column(name = "user_type")
    private String userType;

    @Column(name = "email")
    private String email;

    @Column(name = "nickname")
    private String nickname;

    @Column(name = "avatar")
    private String avatar;

}
