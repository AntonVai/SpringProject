package com.javaKava.SpringProject.mapper;

import com.javaKava.SpringProject.dto.ChatReadDto;
import com.javaKava.SpringProject.entity.Chat;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ChatReadMap {
    ChatReadMap INSTANCE = Mappers.getMapper(ChatReadMap.class);
    ChatReadDto chatToReadDto(Chat chat);
}
