package com.carboncapture.carbon.entity.base;

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
public class BaseEntity implements Serializable {

    protected String createBy;

    protected String modifyBy;

    protected Date createTime;

    protected Date modifyTime;

    protected String remark;
}
