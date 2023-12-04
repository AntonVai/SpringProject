package com.javaKava.SpringProject.dto;

import com.javaKava.SpringProject.entity.Role;


import com.javaKava.SpringProject.util.UniqueEmail;
import com.javaKava.SpringProject.util.group.CreateAction;
import lombok.*;

import lombok.experimental.FieldDefaults;
import lombok.experimental.FieldNameConstants;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;


@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldNameConstants
@Builder
public class UserCreateEditDto {

    @Email
    @UniqueEmail(message = "this email is already in use")
    private String email;
    @NotBlank(groups = CreateAction.class)
    private String password;
    @NotBlank
    @Size(min = 3, max = 64)
    private String nickname;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDate;
    private Integer chatId;

    private Role role;


}
