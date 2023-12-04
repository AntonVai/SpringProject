package com.javaKava.SpringProject.service;

import com.javaKava.SpringProject.dto.UserCreateEditDto;
import com.javaKava.SpringProject.dto.UserReadDto;
import com.javaKava.SpringProject.entity.User;

import com.javaKava.SpringProject.mapper.UserCreateEditMap;
import com.javaKava.SpringProject.mapper.UserReadMap;

import com.javaKava.SpringProject.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final UserCreateEditMap userCreateEditMap;




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




    @Transactional
    public UserReadDto create(UserCreateEditDto userCreateEditDto) {
        return Optional.of(userCreateEditDto)
                .map(userCreateEditMap::userCreateEditDtoToUser)
                .map(userRepository::save)
                .map(UserReadMap.INSTANCE::userToDto)
                .orElseThrow();
    }

    @Transactional
    public Optional<UserReadDto> update(Long id, UserCreateEditDto dto) {
        return userRepository.findById(id)
                .map(entity -> userCreateEditMap.userCreateEditDtoToUser(dto,entity))
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

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email)
                .map(user -> new org.springframework.security.core.userdetails.User(
                        user.getEmail(),
                        user.getPassword(),
                        Collections.singleton(user.getRole())
                ))
                .orElseThrow(()-> new UsernameNotFoundException("Failed, email not found: " + email));
    }
}
