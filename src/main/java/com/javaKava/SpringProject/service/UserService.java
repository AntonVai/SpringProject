package com.javaKava.SpringProject.service;

import com.javaKava.SpringProject.dto.UserCreateEditDto;
import com.javaKava.SpringProject.dto.UserReadDto;
import com.javaKava.SpringProject.entity.User;

import com.javaKava.SpringProject.mapper.UserCreateEditMap;
import com.javaKava.SpringProject.mapper.UserReadMap;

import com.javaKava.SpringProject.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {
    private final UserRepository userRepository;

    private final ImageService imageService;


    public Optional<UserReadDto> findById(Long id) {
        return userRepository.findById(id)
                .map(UserReadMap.INSTANCE::userToDto);

    }


    public List<UserReadDto> findAll() {
        return userRepository.findAll().stream()
                .map(UserReadMap.INSTANCE::userToDto)
                .collect(Collectors.toList());
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public Optional<byte[]> findAvatar(Long id){
        return userRepository.findById(id)
                .map(User::getImage)
                .filter(StringUtils::hasText)
                .flatMap(imageService::getImage);
    }


    @Transactional
    public UserReadDto create(UserCreateEditDto userCreateEditDto) {
        return Optional.of(userCreateEditDto)
                .map(dto -> {
                    imageService.upload(dto.getImage());
                    return UserCreateEditMap.INSTANCE.userCreateEditDtoToUser(dto);
                })
                .map(userRepository::save)
                .map(UserReadMap.INSTANCE::userToDto)
                .orElseThrow();
    }

    @Transactional
    public Optional<UserReadDto> update(Long id, UserCreateEditDto dto) {
        return userRepository.findById(id)
                .map(entity -> {
                    imageService.upload(dto.getImage());
                    return UserCreateEditMap.INSTANCE.userCreateEditDtoToUser(dto,entity);
                })
                .map(userRepository::saveAndFlush)
                .map(UserReadMap.INSTANCE::userToDto);

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
