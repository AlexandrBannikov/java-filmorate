package ru.yandex.practicum.filmorate;
import org.junit.jupiter.api.Test;
import ru.yandex.practicum.filmorate.controller.FilmController;
import ru.yandex.practicum.filmorate.model.Film;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;
public class FilmControllerTest {

    // добавить фильм с пустым названием
    @Test
    void addMovieWhenEmptyTitle() {
        FilmController filmController = new FilmController();
        Film film = new Film();
        film.setDescription("Каникулы в простоквашино.");
        film.setReleaseDate(LocalDate.of(1980, 2, 15));
        film.setDuration(15);
        assertThrows(RuntimeException.class, () -> filmController.createFilm(film));
    }

    // когда описание фильма длиннее 200 символов
    @Test
    void addMovieWhenTooLongDescription() {
        FilmController filmController = new FilmController();
        Film film = new Film();
        film.setName("title");
        film.setDescription("sfsdfsdfsdfsdfsdfsdfsdfsdvfbghtrhthdfbcfgdrgdrggdggrggththjfghfthergdfdfgdgdfgdfgdfgdfgdfgbjghn" +
                "bgfjfgjfgjndfghfgjgkdgtjhfghsfgnfgmghmhgmghmdghmnhgmghmghmghmghmjgytdsergergdfbdfsbdfbnfgmngfmttgncfgnmf" +
                "dfgfgmghmghmcbmvbnmcbmchmgmgh,ghdgnfgnfgnxfgnvhmhmbnmcnhgfbxfbcvbcvngfssdfdfbdfbdfgjgjkukghngnxvbvbxcbfgb" +
                "mghmghmhgmghmbvmnxgbsdfsdfsdewecxcvxv;lk;kdsl;mdvmsldmlsmdlms;,;,smvdsdvsdnkvnkdsjnvknksjndvksdnvdvnlsndv" +
                "gfjdgnsfgaldfvmaldkfmbvadkfmbknadfnblamdflbkvmaldfmblakdmflkmblkmlakmdfkbmdkmflbmdlfmbfmblkmldfmblfbalbl");
        film.setReleaseDate(LocalDate.of(1980, 2,15));
        film.setDuration(15);
        assertThrows(RuntimeException.class, () -> filmController.createFilm(film));
    }

    // добавить фильм когда неправильная дата тест
    @Test
    void addMovieWhenDateIncorrectTest() {
        FilmController filmController = new FilmController();
        Film film = new Film();
        film.setName("title");
        film.setDescription("Каникулы в простоквашино");
        film.setReleaseDate(LocalDate.of(1234,11,22));
        film.setDuration(15);
        assertThrows(RuntimeException.class, () -> filmController.createFilm(film));
    }

    // обновить фильм
    @Test
    void updateFilmTest() {
        FilmController filmController = new FilmController();
        Film film = new Film();
        film.setName("title");
        film.setDescription("Каникулы в простоквашино.");
        film.setReleaseDate(LocalDate.of(1986,2,15));
        film.setDuration(15);
        filmController.createFilm(film);
        Film updatedMovie = new Film();
        updatedMovie.setId(1);
        updatedMovie.setName("name");
        updatedMovie.setDescription("Кавказская пленница.");
        updatedMovie.setReleaseDate(LocalDate.of(1989,4,1));
        updatedMovie.setDuration(95);
        filmController.updateFilm(updatedMovie);
        assertEquals("name",filmController.getFilmById(1).getName());
    }
}
