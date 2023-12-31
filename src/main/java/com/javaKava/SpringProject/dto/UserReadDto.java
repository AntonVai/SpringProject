package com.javaKava.SpringProject.dto;

import com.javaKava.SpringProject.entity.Role;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDate;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserReadDto {
    private Long id;
    private String email;
    private String nickname;
    private LocalDate birthDate;
    private Role role;
    private ChatReadDto chat;


}
