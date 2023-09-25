package com.javaKava.SpringProject.integration.controllers;


import com.javaKava.SpringProject.dto.UserCreateEditDto;
import com.javaKava.SpringProject.dto.UserReadDto;
import com.javaKava.SpringProject.entity.Role;

import com.javaKava.SpringProject.integration.IntegrationTestBase;
import com.javaKava.SpringProject.repository.UserRepository;
import com.javaKava.SpringProject.service.UserService;
import lombok.RequiredArgsConstructor;

import org.junit.jupiter.api.Test;


import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;


import java.time.LocalDate;
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
        mockMvc.perform(get("/users"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(view().name("users"))
                .andExpect(model().attributeExists("users"))
                .andDo(print());
    }

    @Test
    void findById() throws Exception {
        Long id = 1L;
        mockMvc.perform(get("/users/{id}", id))
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
        Long id = 1L;
        when(userService.delete(id)).thenReturn(true);
        mockMvc.perform(patch("/users/{id}", id))
                .andExpect(status().is3xxRedirection())
                .andDo(print());

    }
}