package ru.yandex.practicum.filmorate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.yandex.practicum.filmorate.controller.FilmController;
import ru.yandex.practicum.filmorate.model.Film;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class FilmControllerTest {

    FilmController fc = new FilmController();
    Film film;

    @BeforeEach
    public void createFilm() {
        film = new Film();
        film.setName("Name");
        film.setDescription("description");
        film.setReleaseDate(LocalDate.of(2008, 7, 19));
        film.setDuration(10);
    }

    @Test
    void getAllFilmsReturnsArrayList() {
        assertEquals(ArrayList.class, fc.getAllFilm().getClass());
    }

    @Test
    void controllerSetsIdOnCreation() {
        int oldId = film.getId();
        fc.createFilm(film);
        assertNotEquals(oldId, film.getId());
    }

    @Test
    void putsFilmToMapOnCreation() {
        fc.createFilm(film);
        assertEquals(film, FilmController.getFilms().get(film.getId()));
    }

    @Test
    void returnsSameFilmAsInMapOnCreation() {
        assertEquals(fc.createFilm(film), FilmController.getFilms().get(film.getId()));
    }

    @Test
    void returnsSameFilmAsInMapOnUpdate() {
        assertEquals(fc.updateFilm(film), FilmController.getFilms().get(film.getId()));
    }
}
