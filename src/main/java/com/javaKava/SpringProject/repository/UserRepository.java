package com.javaKava.SpringProject.repository;

import com.javaKava.SpringProject.entity.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    @Override
    @EntityGraph(attributePaths = {"chat"})
    Optional<User> findById(Long aLong);

    @EntityGraph(attributePaths = {"chat"})
    @Override
    List<User> findAll();
}
