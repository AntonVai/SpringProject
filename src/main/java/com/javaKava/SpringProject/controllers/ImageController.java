package com.javaKava.SpringProject.controllers;

import com.javaKava.SpringProject.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class ImageController {
    private final UserService userService;

    @GetMapping("/{id}/avatar")
    public byte[] findAvatar(@PathVariable("id") Long id){
        return userService.findAvatar(id)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
}
