package com.javaKava.SpringProject.integration.controllers;


import com.javaKava.SpringProject.dto.UserCreateEditDto;
import com.javaKava.SpringProject.dto.UserReadDto;
import com.javaKava.SpringProject.entity.Role;

import com.javaKava.SpringProject.integration.IntegrationTestBase;
import com.javaKava.SpringProject.service.ChatService;
import com.javaKava.SpringProject.service.UserService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static com.javaKava.SpringProject.dto.UserCreateEditDto.Fields.*;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.testcontainers.shaded.org.hamcrest.Matchers.hasSize;


@RequiredArgsConstructor
@AutoConfigureMockMvc
class UserControllerTest extends IntegrationTestBase {

    private final MockMvc mockMvc;


    private final UserService userService;


    @Test
    void findAll() throws Exception {
        mockMvc.perform(get("/users"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(view().name("users"))
                .andExpect(model().attributeExists("users"))
                .andExpect(model().attribute("users", userService.findAll()))
                .andDo(print());
    }

    @Test
    void findById() throws Exception {
        Optional<UserReadDto> user1 = Optional.of(userService.findById(1L).orElse(new UserReadDto()));
        mockMvc.perform(get("/users/{id}", user1.get().getId()))
                .andExpect(model().attribute("user", user1))
                .andExpect(status().is2xxSuccessful())
                .andExpect(view().name("user"))
                .andExpect(model().attributeExists("user"))
                .andExpect(model().attributeExists("roles"))
                .andExpect(model().attributeExists("chats"))
                .andDo(print());
    }

    @Test
    void negativeFindById() throws Exception {
        Long id = 10000000000L;
        mockMvc.perform(get("/users/{id}", id))
                .andExpect(status().isNotFound())
                .andDo(print());
    }

    @Test
    void registration() throws Exception {
        mockMvc.perform(get("/users/registration"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(view().name("registration"))
                .andExpect(model().attributeExists("user"))
                .andExpect(model().attributeExists("roles"))
                .andExpect(model().attributeExists("chats"))
                .andDo(print());
    }

    @Test
    void create() throws Exception {
        mockMvc.perform(post("/users")
                        .param(email, "test@gmail.com")
                        .param(nickname, "test")
                        .param(role, Role.MEMBER.name())
                        .param(birthDate, "1995-02-10")
                        .param(chatId, "2")
                )
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/users"))
                .andDo(print());
    }

    @Test
    void update() throws Exception {
        UserCreateEditDto userDto = UserCreateEditDto.builder()
                .email("test@gmail.com")
                .nickname("test")
                .role(Role.MEMBER)
                .birthDate(LocalDate.now())
                .build();
        userService.update(1L, userDto);
        mockMvc.perform(patch("/users/{id}", 1L))
                .andExpect(status().is3xxRedirection())
                .andDo(print());
    }

    @Test
    void delete() throws Exception {
        mockMvc.perform(patch("/users/{id}", 1L))
                .andExpect(status().is3xxRedirection())
                .andDo(print());
        userService.delete(1L);
        assertEquals(4,userService.findAll().size());

    }
}