package com.javaKava.SpringProject.integration.controllers;


import com.javaKava.SpringProject.dto.UserCreateEditDto;
import com.javaKava.SpringProject.dto.UserReadDto;
import com.javaKava.SpringProject.entity.Role;

import com.javaKava.SpringProject.integration.IntegrationTestBase;

import com.javaKava.SpringProject.service.UserService;
import lombok.RequiredArgsConstructor;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.Test;


import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;


import javax.validation.constraints.AssertTrue;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Optional;

import static com.javaKava.SpringProject.dto.UserCreateEditDto.Fields.*;


import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RequiredArgsConstructor
@AutoConfigureMockMvc
class UserControllerTest extends IntegrationTestBase {

    private final MockMvc mockMvc;


    @MockBean
    private final UserService userService;

    @Test
    void findAll() throws Exception {
        UserReadDto user1 = UserReadDto.builder()
                .id(1L)
                .email("test1@gmail.com")
                .nickname("test1")
                .build();
        UserReadDto user2 = UserReadDto.builder()
                .id(2L)
                .email("test@gmail.com")
                .nickname("test2")
                .build();

        Mockito.when(userService.findAll()).thenReturn(Arrays.asList(user1, user2));

        mockMvc.perform(get("/users"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(view().name("users"))
                .andExpect(model().attributeExists("users"))
                .andDo(print());
    }

    @Test
    void findById() throws Exception {
        UserReadDto user1 = UserReadDto.builder()
                .id(1L)
                .email("test1@gmail.com")
                .nickname("test1")
                .role(Role.OWNER)
                .build();
        Mockito.when(userService.findById(Mockito.any())).thenReturn(Optional.of(user1));

        mockMvc.perform(get("/users/{id}", user1.getId()))
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
        Long id = 1L;
        Optional<UserReadDto> userReadDto = userService.findById(id);
        UserCreateEditDto userDto = new UserCreateEditDto(
                "test@gmail.com",
                "test",
                LocalDate.now(),
                2,
                Role.ALESHA
        );
        when(userService.update(id, userDto)).thenReturn(userReadDto);
        mockMvc.perform(patch("/users/{id}", id))
                .andExpect(status().is3xxRedirection())
                .andDo(print());
    }

    @Test
    void delete() throws Exception {
        UserReadDto user1 = UserReadDto.builder()
                .id(1L)
                .email("test1@gmail.com")
                .nickname("test1")
                .role(Role.OWNER)
                .build();
        when(userService.delete(user1.getId())).thenReturn(true);
        mockMvc.perform(patch("/users/{id}", user1.getId()))
                .andExpect(status().is3xxRedirection())
                .andDo(print());

    }
}