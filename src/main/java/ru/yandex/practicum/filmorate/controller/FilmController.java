package ru.yandex.practicum.filmorate.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.model.Film;

import javax.validation.Valid;
import javax.validation.ValidationException;
import java.time.LocalDate;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/films")
public class FilmController {
    private final LocalDate MIN_RELEASE_DATE = LocalDate.of(1895,12,28);

    private int newID = 0;

    private final Map<Integer, Film> films = new HashMap<>();

    /*
    Создаем, добавляем фильм.
     */
    @PostMapping
    public Film createFilm(@Valid @RequestBody Film film) {
        log.info("Запрос на добавление фильма." + film);
        validateFilm(film);
        film.setId(generateID());
        films.put(film.getId(), film);
        log.info("Добавлен фильм.");
        return film;
    }

    /*
    Обновляем фильм.
     */
    @PutMapping
    public Film updateFilm(@Valid @RequestBody Film film) {
        log.info("Введен запрос на изменение фильма." + film);
        if (!films.containsKey(film.getId())) { //если список фильмов не содержит фильм с данным id
            log.debug("Несуществующий id!");
            throw new ValidationException("Нет фильма с таким id!");
        }
        films.put(film.getId(), film);
        log.info("Фильм обновлен!");
        return film;
    }

    /*
    Получаем все фильмы.
     */
    @GetMapping
    public Collection<Film> getAllFilm() {
        log.info("Все фильмы получены!" + films.size());
        return films.values();
    }

    public void validateFilm(Film film) {
        if (film.getName().isBlank()) {
            throw new ValidationException("Отсутствует название фильма!");
        }
        if (film.getDescription().length() > 200) {
            throw new ValidationException("Слишком длинное описание фильма!");
        }
        if (film.getReleaseDate().isBefore(MIN_RELEASE_DATE)) {
            throw new ValidationException("Неверная дата выхода фильма!");
        }
        if (film.getDuration() < 0) {
            throw new ValidationException("Продолжительность фильма неверная!");
        }
    }

    public Film getFilmById(Integer id) {
        return films.get(id);
    }

    private int generateID() {
        return ++newID;
    }
}
