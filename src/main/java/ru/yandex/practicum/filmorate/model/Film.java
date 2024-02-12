package ru.yandex.practicum.filmorate.model;

import lombok.*;
import org.springframework.validation.annotation.Validated;
import ru.yandex.practicum.filmorate.controller.FilmController;
import ru.yandex.practicum.filmorate.utils.FilmMinimumDate;
import ru.yandex.practicum.filmorate.validation.InCollection;
import ru.yandex.practicum.filmorate.validation.Markers;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.time.LocalDate;

/**
 * Film.
 */
@Data
@Validated
public class Film {

    @InCollection(groups = Markers.OnUpdate.class, setHolder = FilmController.class)
    private int id;

    @NotBlank
    private String name;

    @NotBlank
    @Size(max = 200)
    private String description;

    @NotNull
    @FilmMinimumDate
    private LocalDate releaseDate;

    @Positive
    private int duration;
}
