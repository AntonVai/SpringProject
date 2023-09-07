package com.javaKava.SpringProject.dto;

import com.javaKava.SpringProject.entity.Role;

import lombok.Data;
import lombok.NoArgsConstructor;

import lombok.experimental.FieldNameConstants;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;


@Data
@NoArgsConstructor
@FieldNameConstants
public class UserCreateEditDto {

    @Email
    private String email;
    @NotNull
    @Size(min = 3, max = 64)
    private String nickname;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDate;

    private Role role;


}
