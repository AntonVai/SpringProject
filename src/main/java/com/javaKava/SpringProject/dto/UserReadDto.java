package com.javaKava.SpringProject.dto;

import com.javaKava.SpringProject.entity.Role;


import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;


@Data
@NoArgsConstructor
public class UserReadDto {
    private Long id;
    private String email;
    private String nickname;
    private String image;
    private Role role;

}
