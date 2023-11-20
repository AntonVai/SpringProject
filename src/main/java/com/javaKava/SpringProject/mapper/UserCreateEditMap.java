package com.javaKava.SpringProject.mapper;

import com.javaKava.SpringProject.dto.UserCreateEditDto;
import com.javaKava.SpringProject.entity.User;
import org.mapstruct.Mapper;

import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserCreateEditMap {
    UserCreateEditMap INSTANCE = Mappers.getMapper(UserCreateEditMap.class);

    @Mapping(source = "email", target = "email")
    @Mapping(source = "nickname", target = "nickname")
    @Mapping(source = "birthDate", target = "birthDate")
    @Mapping(source = "role", target = "role")
    User userCreateEditDtoToUser(UserCreateEditDto userCreateEditDto);
    @Mapping(source = "userCreateEditDto.email", target = "email")
    @Mapping(source = "userCreateEditDto.nickname", target = "nickname")
    @Mapping(source = "userCreateEditDto.birthDate", target = "birthDate")
    @Mapping(source = "userCreateEditDto.role", target = "role")

    User userCreateEditDtoToUser(UserCreateEditDto userCreateEditDto,User user);


}
