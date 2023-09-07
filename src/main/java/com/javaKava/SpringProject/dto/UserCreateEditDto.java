package com.javaKava.SpringProject.dto;

import com.javaKava.SpringProject.entity.Role;

import lombok.Data;
import lombok.NoArgsConstructor;

import lombok.experimental.FieldNameConstants;




@Data
@NoArgsConstructor
@FieldNameConstants
public class UserCreateEditDto {

    private String email;

    private String nickname;

    private Role role;


}
