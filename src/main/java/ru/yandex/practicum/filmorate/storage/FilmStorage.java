package ru.yandex.practicum.filmorate.storage;

import ru.yandex.practicum.filmorate.model.Film;

import java.util.List;

public interface FilmStorage {
    Film createFilm(Film film);

    Film updateFilm(Film film);

    void addLike(Integer filmID, Integer userID);

    void deleteLike(Integer filmID, Integer userID);

    Film getFilmById(Integer idFilm);

    List<Film> getAllFilms();
}
