package com.javaKava.SpringProject.integration;


import com.javaKava.SpringProject.dto.UserCreateEditDto;
import com.javaKava.SpringProject.dto.UserReadDto;
import com.javaKava.SpringProject.entity.Role;
import com.javaKava.SpringProject.entity.User;
import com.javaKava.SpringProject.service.UserService;
import lombok.RequiredArgsConstructor;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RequiredArgsConstructor
public class UserServiceIT extends IntegrationTestBase {
    private final UserService userService;

    @Test
    void create() {
        UserCreateEditDto userDto = new UserCreateEditDto(
                "test@gmail.com",
                "test",
                LocalDate.now(),
                2,
                Role.ALESHA
        );
        UserReadDto result = userService.create(userDto);
        assertEquals(userDto.getEmail(), result.getEmail());
        assertEquals(userDto.getNickname(), result.getNickname());
        assertEquals(userDto.getBirthDate(), result.getBirthDate());
        assertEquals(userDto.getChatId(), result.getChat().getId());
        assertEquals(userDto.getRole(), result.getRole());
    }

    @Test
    void findAll() {
        List<UserReadDto> result = userService.findAll();
        assertThat(result).hasSize(5);
    }

    @Test
    void findById() {
        Optional<UserReadDto> user = userService.findById(1L);
        assertTrue(user.isPresent());
        user.ifPresent(userReadDto -> assertEquals("Orau@gmail.com", userReadDto.getEmail()));
    }



    @Test
    void findByEmail() {
        Optional<User> user = userService.findByEmail("Orau@gmail.com");
        assertTrue(user.isPresent());
        user.ifPresent(userReadDto -> assertEquals("Orau@gmail.com", userReadDto.getEmail()));

    }

    @Test
    void update() {
        UserCreateEditDto userDto = new UserCreateEditDto(
                "test@gmail.com",
                "test",
                LocalDate.now(),
                2,
                Role.ALESHA
        );
        Optional<UserReadDto> result = userService.update(1L, userDto);
        assertTrue(result.isPresent());
        result.ifPresent(user -> {
            assertEquals(userDto.getEmail(), user.getEmail());
            assertEquals(userDto.getNickname(), user.getNickname());
            assertEquals(userDto.getBirthDate(), user.getBirthDate());
            assertEquals(userDto.getChatId(), user.getChat().getId());
            assertEquals(userDto.getRole(), user.getRole());
        });
    }
    @Test
    void delete(){
        boolean result = userService.delete(1L);
        assertTrue(result);
    }

}
