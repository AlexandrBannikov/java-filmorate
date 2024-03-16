package ru.yandex.practicum.filmorate.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.Set;

/**
 * User
 */
@Data
public class User {

    private Integer id;

    @JsonIgnore
    private Set<Integer> friendID;

    @Email(message = "Электронная почта должна содержать символ @.")
    @NotBlank(message = "Электронная почта не может быть пустой.")
    private String email;

    @NotBlank(message = "Логин не может быть пустым.")
    @Pattern(regexp = "\\S+", message = "Логин не может содержать пробелы.")
    private String login;

    private String name;

    @NotNull
    @PastOrPresent(message = "День рождения не может быть в будущем.")
    private LocalDate birthday;
}
