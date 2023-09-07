package com.javaKava.SpringProject.mapper;

import com.javaKava.SpringProject.dto.UserCreateEditDto;
import com.javaKava.SpringProject.entity.User;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserCreateEditMapper {
    private final ModelMapper mapper;

    public User userCreateEditDtoMapToUser(UserCreateEditDto userCreateEditDto, User user) {
        copy(userCreateEditDto, user);
        return user;

    }

    public User UserCreateEditDtoMapToUser(UserCreateEditDto userCreateEditDto) {
        return mapper.map(userCreateEditDto, User.class);

    }

    private void copy(UserCreateEditDto userCreateEditDto, User user) {
        user.setEmail(userCreateEditDto.getEmail());
        user.setNickname(userCreateEditDto.getNickname());
        user.setRole(userCreateEditDto.getRole());

    }

}
