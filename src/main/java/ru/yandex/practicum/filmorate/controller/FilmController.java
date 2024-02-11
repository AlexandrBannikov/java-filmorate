package ru.yandex.practicum.filmorate.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.model.Film;

import javax.validation.Valid;
import javax.validation.ValidationException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/films")
public class FilmController {

    private int newID = 0;

    private final Map<Integer, Film> films = new HashMap<>();

    /*
    Создаем, добавляем фильм.
     */
    @PostMapping
    public Film createFilm(@Valid @RequestBody Film film) {
        log.info("Запрос на добавление фильма. {}", film);
        //validateFilm(film);
        film.setId(generateID());
        films.put(film.getId(), film);
        log.info("Добавлен фильм. {}", film);
        return film;
    }

    /*
    Обновляем фильм.
     */
    @PutMapping
    public Film updateFilm(@Valid @RequestBody Film film) {
        log.info("Введен запрос на обновление фильма. {}", film);
        if (!films.containsKey(film.getId())) { //если список фильмов не содержит фильм с данным id
            log.debug("Несуществующий id! {}", film.getId());
            throw new ValidationException("Нет фильма с таким id!");
        }
        films.put(film.getId(), film);
        log.info("Фильм обновлен! {}", film);
        return film;
    }

    /*
    Получаем все фильмы.
     */
    @GetMapping
    public Collection<Film> getAllFilm() {
        log.info("Все фильмы получены! {}", films.size());
        return films.values();
    }

    public Film getFilmById(Integer id) {
        return films.get(id);
    }

    private int generateID() {
        return ++newID;
    }
}
