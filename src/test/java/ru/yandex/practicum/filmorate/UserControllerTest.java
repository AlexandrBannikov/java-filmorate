package ru.yandex.practicum.filmorate;

import org.junit.jupiter.api.Test;
import ru.yandex.practicum.filmorate.controller.UserController;
import ru.yandex.practicum.filmorate.model.User;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class UserControllerTest {

    // когда неверный email
    @Test
    void addUserWhenWrongEmailTest() {
        UserController userController = new UserController();
        User user = new User();
        user.setEmail("mail.ru");
        user.setLogin("login");
        user.setName("name");
        user.setBirthday(LocalDate.of(1977,8,5));
        assertThrows(RuntimeException.class, () -> userController.createUser(user));
    }

    // когда дата из будущего
    @Test
    void addUserWhenWrongDateTest() {
        UserController userController = new UserController();
        User user = new User();
        user.setEmail("bob@yandex.ru");
        user.setLogin("login");
        user.setName("name");
        user.setBirthday(LocalDate.of(2024,11,7));
        assertThrows(RuntimeException.class,() -> userController.createUser(user));
    }

    // когда имя пустое
//    @Test
//    void addUserWhenBlankNameTest() {
//        UserController userController = new UserController();
//        User user = new User();
//        user.setId(1);
//        user.setEmail("bob@yandex.ru");
//        user.setLogin("login");
//        user.setName("  ");
//        user.setBirthday(LocalDate.of(1977,8,5));
//        userController.createUser(user);
//        assertEquals("login", userController.getUserById(1).getName());
//    }

    // когда обновление user
    @Test
    void whenUpdateUserTest() {
        UserController userController = new UserController();
        User user = new User();
        user.setEmail("bob@yandex.ru");
        user.setLogin("login");
        user.setName("name");
        user.setBirthday(LocalDate.of(1977,8,5));
        userController.createUser(user);
        User updateUser = new User();
        updateUser.setId(1);
        updateUser.setEmail("kord@yandex.ru");
        updateUser.setLogin("nextLogin");
        updateUser.setName("secondName");
        updateUser.setBirthday(LocalDate.of(2008,7,19));
        userController.updateUser(updateUser);
        assertEquals("secondName", userController.getUserById(1).getName());
    }
}
