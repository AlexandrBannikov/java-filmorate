package ru.yandex.practicum.filmorate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.yandex.practicum.filmorate.model.Film;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.time.LocalDate;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FilmControllerTest {

    private Film film;

    private static Validator validator;

    @BeforeEach
    void setUp() {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    @Test
    void validateFilmName() {
        film = Film.builder()
                .id(1)
                .name("")
                .description("Описание фильма")
                .releaseDate(LocalDate.of(2002, 2, 2))
                .duration(100)
                .build();
        Set<ConstraintViolation<Film>> violations = validator.validate(film);
        assertEquals(1, violations.size());
        assertEquals("Название фильма не может быть пустым!", violations.iterator().next().getMessage());
    }

    @Test
    void validateFilmDescription() {
        film = Film.builder()
                .id(1)
                .name("Film")
                .description("Из-под покрова тьмы ночной,\n" +
                        "Из чёрной ямы страшных мук\n" +
                        "Благодарю я всех богов\n" +
                        "За мой непокорённый дух.\n" +
                        "\n" +
                        "И я, попав в тиски беды,\n" +
                        "Не дрогнул и не застонал,\n" +
                        "И под ударами судьбы\n" +
                        "Я ранен был, но не упал.\n" +
                        "\n" +
                        "Т")
                .releaseDate(LocalDate.of(2002, 2, 2))
                .duration(100)
                .build();
        Set<ConstraintViolation<Film>> violations = validator.validate(film);
        assertEquals(1, violations.size());
        assertEquals("Размер должен находиться в диапазоне от 1 до 200!", violations.iterator().next().getMessage());
    }

    @Test
    void validateFilmReleaseDate() {
        film = Film.builder()
                .id(1)
                .name("Film")
                .description("Описание фильма")
                .releaseDate(LocalDate.of(1895, 12, 27))
                .duration(100)
                .build();
        Set<ConstraintViolation<Film>> violations = validator.validate(film);
        assertEquals(1, violations.size());
        assertEquals("Введите дату релиза не ранее 28 декабря 1895 года.",
                violations.iterator().next().getMessage());
    }

    @Test
    void validateFilmDuration() {
        film = Film.builder()
                .id(1)
                .name("Film")
                .description("Описание фильма")
                .releaseDate(LocalDate.of(1895, 12, 29))
                .duration(-100)
                .build();
        Set<ConstraintViolation<Film>> violations = validator.validate(film);
        assertEquals(1, violations.size());
        assertEquals("Продолжительность фильма должна быть больше 0.",
                violations.iterator().next().getMessage());
    }
}
