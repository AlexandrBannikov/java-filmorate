package ru.yandex.practicum.filmorate.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.exception.ValidationException;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.storage.film.FilmStorage;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class FilmService {

    private final FilmStorage inMemoryFilmStorage;

    @Autowired
    public FilmService(FilmStorage inMemoryFilmStorage) {
        this.inMemoryFilmStorage = inMemoryFilmStorage;
    }

    public Film addLike(Integer filmID, Integer userID) {
        inMemoryFilmStorage.addLike(filmID, userID);
        return inMemoryFilmStorage.getFilmById(filmID);
    }

    public Film deleteLike(Integer filmID, Integer userID) {
        inMemoryFilmStorage.deleteLike(filmID, userID);
        return inMemoryFilmStorage.getFilmById(filmID);
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
        return inMemoryFilmStorage.getAllFilms();
    }

    public Film createFilms(Film film) {
        return inMemoryFilmStorage.createFilm(film);
    }

    public Film updateFilm(Film film) {
        return inMemoryFilmStorage.updateFilm(film);
    }

    public Film getFilmById(Integer id) {
        if (id != null) {
            return inMemoryFilmStorage.getFilmById(id);
        }
        throw new ValidationException("При получении id получили null");
    }
}
