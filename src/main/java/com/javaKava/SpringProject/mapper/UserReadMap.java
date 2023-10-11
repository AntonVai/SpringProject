package com.javaKava.SpringProject.mapper;

import com.javaKava.SpringProject.dto.UserReadDto;
import com.javaKava.SpringProject.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserReadMap {
    UserReadMap INSTANCE = Mappers.getMapper(UserReadMap.class);
    UserReadDto userToDto(User user);
}
