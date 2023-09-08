package com.javaKava.SpringProject.service;

import com.javaKava.SpringProject.dto.ChatReadDto;
import com.javaKava.SpringProject.mapper.ChatReadMapper;
import com.javaKava.SpringProject.repository.ChatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class ChatService {
    private final ChatRepository chatRepository;
    private final ChatReadMapper chatReadMapper;


    public Optional<ChatReadDto> findById(Integer id) {
        return chatRepository.findById(id)
                .map(chatReadMapper::chatMapToReadDto);
    }

    public List<ChatReadDto> findAll(){
        return chatRepository.findAll().stream()
                .map(chatReadMapper::chatMapToReadDto)
                .toList();
    }
}
