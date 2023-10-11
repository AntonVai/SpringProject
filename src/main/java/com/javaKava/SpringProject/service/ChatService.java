package com.javaKava.SpringProject.service;

import com.javaKava.SpringProject.dto.ChatReadDto;
import com.javaKava.SpringProject.mapper.ChatReadMap;
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


    public Optional<ChatReadDto> findById(Integer id) {
        return chatRepository.findById(id)
                .map(ChatReadMap.INSTANCE::chatToReadDto);
    }

    public List<ChatReadDto> findAll() {
        return chatRepository.findAll().stream()
                .map(ChatReadMap.INSTANCE::chatToReadDto)
                .toList();
    }
}
