package com.javaKava.SpringProject.dto;

import com.javaKava.SpringProject.entity.Role;

import lombok.Data;
import lombok.NoArgsConstructor;

import lombok.experimental.FieldNameConstants;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Data
@NoArgsConstructor
@FieldNameConstants
public class UserCreateEditDto {

    @Email
    private String email;
    @NotNull
    @Size(min = 3, max = 64)
    private String nickname;

    private Role role;


}
