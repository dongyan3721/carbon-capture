package com.carboncapture.carbon.entity.passwordmodify;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Santa Antilles
 * @description
 * @date 2024/2/28-16:25:31
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ModifyEntity {
    private String userId;

    private String originalPassword;

    private String newPassword;
}
