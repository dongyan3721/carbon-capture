package com.carboncapture.carbon.entity.base;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Santa Antilles
 * @description
 * @date 2024/1/13-23:50:04
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public class BaseEntity implements Serializable {

    @Column(name = "create_by")
    protected String createBy;

    @Column(name = "modify_by")
    protected String modifyBy;

    @Column(name = "create_time")
    protected Date createTime;

    @Column(name = "modify_time")
    protected Date modifyTime;

    @Column(name = "remark")
    protected String remark;
}
