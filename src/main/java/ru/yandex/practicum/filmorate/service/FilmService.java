package ru.yandex.practicum.filmorate.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.storage.film.FilmStorage;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class FilmService {

    private final FilmStorage filmStorage;

    public Film addLike(Integer filmID, Integer userID) {
        filmStorage.addLike(filmID, userID);
        return filmStorage.getFilmById(filmID);
    }

    public Film deleteLike(Integer filmID, Integer userID) {
        filmStorage.deleteLike(filmID, userID);
        return filmStorage.getFilmById(filmID);
    }

    public List<Film> getPopularFilms(Integer count) {
        log.debug("Получен список популярных фильмов в количестве {}.", count);
        return getAllFilms()
                .stream()
                .sorted((film1, film2) -> film2.getLike().size() - film1.getLike().size())
                .limit(count)
                .collect(Collectors.toList());
    }

    public List<Film> getAllFilms() {
        return filmStorage.getAllFilms();
    }

    public Film createFilms(Film film) {
        return filmStorage.createFilm(film);
    }

    public Film updateFilm(Film film) {
        return filmStorage.updateFilm(film);
    }

    public Film getFilmById(Integer id) {
        return filmStorage.getFilmById(id);
    }
}
