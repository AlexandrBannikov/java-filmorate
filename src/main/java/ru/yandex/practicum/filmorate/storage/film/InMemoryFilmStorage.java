package ru.yandex.practicum.filmorate.storage.film;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.exception.EntityNotFoundException;
import ru.yandex.practicum.filmorate.model.Film;

import javax.validation.ValidationException;
import java.util.*;

@Slf4j
@Component
public class InMemoryFilmStorage implements FilmStorage {
    private final Map<Integer, Film> films = new HashMap<>();
    private int newFilmID = 1;

    @Override
    public Film createFilm(Film film) {
        log.info("Добавлен новый фильм {} в список.", film.getName());
        film.setId(newFilmID);
        film.setLike(new TreeSet<>());
        films.put(newFilmID++, film);
        return film;
    }

    @Override
    public Film updateFilm(Film film) {
        if (films.containsKey(film.getId())) {
            log.info("Фильм id = {} обновлен в списке.", film.getId());
            film.setLike(new TreeSet<>());
            films.put(film.getId(), film);
            return film;
        }
        log.debug("Фильм в списке не найден");
        throw new ValidationException("Нет фильма с таким id!");
    }

    @Override
    public void addLike(Integer filmID, Integer userID) {
        if (films.containsKey(filmID)) {
            Film film = films.get(filmID);
            film.getLike().add(userID);
            log.info("Пользоватлеь id = {} добавил лайк фильму id = {} ", userID, filmID);
        } else {
            throw new EntityNotFoundException("Фильм отсутствует!");
        }
    }

    @Override
    public void deleteLike(Integer filmID, Integer userID) {
        if (films.containsKey(filmID)) {
            Film film = films.get(filmID);
            film.getLike().remove(userID);
            log.debug("Пользователь {} удалил лайк с фильма с id = {}.", userID, filmID);
        } else {
            throw new EntityNotFoundException("Фильм не найден.");
        }
    }

    @Override
    public Film getFilmById(Integer idFilm) {
        if (films.containsKey(idFilm)) {
            log.info("Найден фильм id = {} !", idFilm);
            return films.get(idFilm);
        } else {
            throw new ValidationException("Фильм отсутствует!");
        }
    }

    @Override
    public List<Film> getAllFilms() {
        log.debug("Все фильмы полученны. Количество фильмов = {}", films.size());
        return new ArrayList<>(films.values());
    }
}
