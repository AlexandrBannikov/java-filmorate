package ru.yandex.practicum.filmorate.controller;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.validation.Markers;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/films")
@Validated
public class FilmController {

    @Getter
    private static final Map<Integer, Film> films = new HashMap<>();
    private static int newID;

    /*
    Создаем, добавляем фильм.
     */
    @PostMapping
    public Film createFilm(@Valid @RequestBody Film film) {
        log.info("Запрос на добавление фильма. {}", film);
        film.setId(++newID);
        films.put(film.getId(), film);
        log.info("Добавлен фильм. {}", film);
        return film;
    }

    /*
    Обновляем фильм.
     */
    @PutMapping
    @Validated(Markers.OnUpdate.class)
    public Film updateFilm(@Valid @RequestBody Film film) {
        log.info("Введен запрос на обновление фильма. {}", film);
        films.put(film.getId(), film);
        log.info("Фильм обновлен! {}", film);
        return film;
    }

    /*
    Получаем все фильмы.
     */
    @GetMapping
    public List<Film> getAllFilm() {
        log.info("Все фильмы получены! {}", films.size());
        return new ArrayList<>(films.values());
    }
}
