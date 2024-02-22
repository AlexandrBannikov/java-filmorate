package ru.yandex.practicum.filmorate.controller;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.model.Film;

import javax.validation.Valid;
import javax.validation.ValidationException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/films")
public class FilmController {

    @Getter
    private final Map<Integer, Film> films = new HashMap<>();
    private int newID;

    /*
    Создаем, добавляем фильм.
     */
    @PostMapping
    public Film createFilm(@Valid @RequestBody Film film) {
        film.setId(++newID);
        films.put(film.getId(), film);
        log.info("Добавлен {} фильм.", film.getName());
        return film;
    }

    /*
    Обновляем фильм.
     */
    @PutMapping
    public Film updateFilm(@Valid @RequestBody Film film) {
        int id = film.getId();
        if (!films.containsKey(id)) {
            log.debug("Фильм {} отсутствует!", film.getId());
        throw new ValidationException("Фильм отсутствует!");
        }
        films.put(film.getId(), film);
        log.info("Фильм {} обновлен!", film.getName());
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
