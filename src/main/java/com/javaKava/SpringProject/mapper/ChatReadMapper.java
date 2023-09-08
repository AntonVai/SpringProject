package com.javaKava.SpringProject.mapper;

import com.javaKava.SpringProject.dto.ChatReadDto;
import com.javaKava.SpringProject.entity.Chat;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ChatReadMapper {
    private final ModelMapper mapper;

    public ChatReadDto chatMapToReadDto(Chat chat) {
        return mapper.map(chat, ChatReadDto.class);
    }
}
