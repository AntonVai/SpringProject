package com.javaKava.SpringProject.mapper;

import com.javaKava.SpringProject.dto.UserCreateEditDto;
import com.javaKava.SpringProject.entity.Chat;
import com.javaKava.SpringProject.entity.User;
import com.javaKava.SpringProject.repository.ChatRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;
import java.util.function.Predicate;

@Component
@RequiredArgsConstructor
public class UserCreateEditMapper {
    private final ChatRepository chatRepository;

    public User userCreateEditDtoMapToUser(UserCreateEditDto userCreateEditDto, User user) {
        copy(userCreateEditDto, user);
        return user;

    }

    public User UserCreateEditDtoMapToUser(UserCreateEditDto userCreateEditDto) {
        User user = new User();
        copy(userCreateEditDto,user);
        return user;

    }

    private void copy(UserCreateEditDto userCreateEditDto, User user) {
        user.setEmail(userCreateEditDto.getEmail());
        user.setNickname(userCreateEditDto.getNickname());
        user.setRole(userCreateEditDto.getRole());
        user.setBirthDate(userCreateEditDto.getBirthDate());
        user.setChat(getChat(userCreateEditDto.getChatId()));

        Optional.ofNullable(userCreateEditDto.getImage())
                .filter(Predicate.not(MultipartFile::isEmpty))
                .ifPresent(image -> user.setImage(image.getOriginalFilename()));

    }
    public Chat getChat(Integer id){
        return Optional.ofNullable(id)
                .flatMap(chatRepository::findById)
                .orElse(null);
    }

}
