package ru.yandex.practicum.filmorate.model;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

/**
 * Film.
 */
@Data
public class Film {

    private Integer id;

    @NotBlank
    private String name;

    @NotBlank
    private String description;

    private LocalDate releaseDate;

    private int duration;
}
