package com.javaKava.SpringProject.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "email")
@Entity
@Builder
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String email;
    private String nickname;
    private LocalDate birthDate;
    private String image;
    @Enumerated(EnumType.STRING)
    private Role role;

}
