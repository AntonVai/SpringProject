package com.javaKava.SpringProject.integration;


import com.javaKava.SpringProject.dto.UserCreateEditDto;
import com.javaKava.SpringProject.dto.UserReadDto;
import com.javaKava.SpringProject.entity.Role;
import com.javaKava.SpringProject.entity.User;
import com.javaKava.SpringProject.service.ImageService;
import com.javaKava.SpringProject.service.UserService;
import lombok.RequiredArgsConstructor;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;
import org.testcontainers.shaded.com.google.common.net.MediaType;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@RequiredArgsConstructor
public class UserServiceIT extends IntegrationTestBase {
    private final UserService userService;
    private final ImageService imageService;

    @SneakyThrows
    @Test
    void create() {
        UserCreateEditDto userDto = new UserCreateEditDto(
                "test@gmail.com",
                "test",
                LocalDate.now(),
                2,
                Role.ALESHA,
                new MockMultipartFile("test",new byte[0])
        );
        UserReadDto actualResult = userService.create(userDto);
        assertEquals(userDto.getEmail(), actualResult.getEmail());
        assertEquals(userDto.getNickname(), actualResult.getNickname());
        assertEquals(userDto.getBirthDate(), actualResult.getBirthDate());
        assertSame(userDto.getRole(), actualResult.getRole());
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
    @SneakyThrows
    void update() {
        UserCreateEditDto userDto = new UserCreateEditDto(
                "test@gmail.com",
                "test",
                LocalDate.now(),
                2,
                Role.ALESHA,
                new MockMultipartFile("test", new byte[0])

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
    void delete() {
        boolean result = userService.delete(1L);
        assertTrue(result);
    }

}
