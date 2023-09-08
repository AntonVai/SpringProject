package com.javaKava.SpringProject.service;

import com.javaKava.SpringProject.dto.UserCreateEditDto;
import com.javaKava.SpringProject.dto.UserReadDto;
import com.javaKava.SpringProject.mapper.UserCreateEditMapper;
import com.javaKava.SpringProject.mapper.UserReadMapper;
import com.javaKava.SpringProject.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {
    private final UserRepository userRepository;
    private final UserReadMapper userReadMapper;
    private final UserCreateEditMapper userCreateEditMapper;


    public Optional<UserReadDto> findById(Long id) {
        return userRepository.findById(id)
                .map(userReadMapper::UserMapToUserReadDto);

    }


    public List<UserReadDto> findAll() {
        return userRepository.findAll().stream()
                .map(userReadMapper::UserMapToUserReadDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public UserReadDto create(UserCreateEditDto userCreateEditDto) {
        return Optional.of(userCreateEditDto)
                .map(userCreateEditMapper::UserCreateEditDtoMapToUser)
                .map(userRepository::save)
                .map(userReadMapper::UserMapToUserReadDto)
                .orElseThrow();
    }

    @Transactional
    public Optional<UserReadDto> update(Long id, UserCreateEditDto dto) {
        return userRepository.findById(id)
                .map(entity -> userCreateEditMapper.userCreateEditDtoMapToUser(dto, entity))
                .map(userRepository::saveAndFlush)
                .map(userReadMapper::UserMapToUserReadDto);
    }

    @Transactional
    public boolean delete(Long id) {
        return userRepository.findById(id)
                .map(entity -> {
                    userRepository.delete(entity);
                    userRepository.flush();
                    return true;
                })
                .orElse(false);
    }
}
