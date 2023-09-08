package com.javaKava.SpringProject.repository;

import com.javaKava.SpringProject.entity.Chat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRepository extends JpaRepository<Chat,Integer> {
}
