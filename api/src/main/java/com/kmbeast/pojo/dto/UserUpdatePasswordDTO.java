package com.kmbeast.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserUpdatePasswordDTO {
    private String oldPassword;
    private String newPassword;
    private String againPassword;
}
