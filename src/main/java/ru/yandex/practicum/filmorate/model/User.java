package ru.yandex.practicum.filmorate.model;

import lombok.Data;
import org.springframework.validation.annotation.Validated;
import ru.yandex.practicum.filmorate.controller.UserController;
import ru.yandex.practicum.filmorate.validation.InCollection;
import ru.yandex.practicum.filmorate.validation.Markers;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDate;

/**
 * User
 */
@Data
@Validated
public class User {

    @InCollection(groups = Markers.OnUpdate.class, setHolder = UserController.class)
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
