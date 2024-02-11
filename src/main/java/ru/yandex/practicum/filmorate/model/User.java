package ru.yandex.practicum.filmorate.model;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;

import java.time.LocalDate;

/**
 * User
 */
@Data
public class User {

    private int id;

    @Email
    @NotNull
    private String email;

    @NotNull
    private String login;

    private String name;

    @NotNull
    @PastOrPresent
    private LocalDate birthday;
}
