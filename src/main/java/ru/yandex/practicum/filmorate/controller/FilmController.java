package ru.yandex.practicum.filmorate.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.service.FilmService;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;

@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/films")
public class FilmController {

    private final FilmService filmService;

    /*
        Создаем, добавляем фильм.
    */
    @PostMapping
    public Film createFilm(@Valid @RequestBody Film film) {
        return filmService.createFilms(film);
    }

    /*
    Обновляем фильм.
     */
    @PutMapping
    public Film updateFilm(@Valid @RequestBody Film film) {
        return filmService.updateFilm(film);
    }

    /*
    Получаем все фильмы.
     */
    @GetMapping
    public List<Film> getAllFilms() {
        return filmService.getAllFilms();
    }

    @PutMapping("/{id}/like/{userID}")
    public Film addLike(@PathVariable("id") Integer id, @PathVariable("userID") Integer userID) {
        return filmService.addLike(id, userID);
    }

    @DeleteMapping("/{id}/like/{userID}")
    public Film deleteLike(@PathVariable("id") Integer id, @PathVariable("userID") Integer userID) {
        return filmService.deleteLike(id, userID);
    }

    @GetMapping("/popular")
    public List<Film> getPopularFilms(@Positive @RequestParam(defaultValue = "10") Integer count) {
        return filmService.getPopularFilms(count);
    }

    @GetMapping("/{id}")
    public Film getFilmById(@PathVariable("id") int id) {
        return filmService.getFilmById(id);
    }
}
