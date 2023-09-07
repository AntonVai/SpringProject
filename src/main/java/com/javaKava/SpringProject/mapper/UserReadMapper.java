package com.javaKava.SpringProject.mapper;

import com.javaKava.SpringProject.dto.UserReadDto;
import com.javaKava.SpringProject.entity.User;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserReadMapper {
    private final ModelMapper mapper;

    public UserReadDto UserMapToUserReadDto(User user) {
        return mapper.map(user, UserReadDto.class);
    }

}
