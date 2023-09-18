package com.javaKava.SpringProject.util;

import com.javaKava.SpringProject.dto.UserCreateEditDto;
import com.javaKava.SpringProject.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
@RequiredArgsConstructor
@Component
public class UserValid implements ConstraintValidator<UniqueEmail, String> {
    private final UserService userService;
    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return userService.findByEmail(s).isEmpty();
    }
}
