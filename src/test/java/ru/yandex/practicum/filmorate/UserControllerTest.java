package ru.yandex.practicum.filmorate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.yandex.practicum.filmorate.controller.UserController;
import ru.yandex.practicum.filmorate.model.User;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class UserControllerTest {

    UserController uc = new UserController();
    User user;

    @BeforeEach
    public void createFilm() {
        user = new User();

        user.setName("Name");
        user.setLogin("login");
        user.setEmail("e@mail.fake");
        user.setBirthday(LocalDate.of(2008, 7, 19));
    }

    @Test
    void getAllUsersReturnsArrayList() {
        assertEquals(ArrayList.class, uc.getAllUsers().getClass());
    }

    @Test
    void setsIdOnCreation() {
        int oldId = user.getId();
        uc.createUser(user);
        assertNotEquals(oldId, user.getId());
    }

    @Test
    void putsUserToMapOnCreation() {
        uc.createUser(user);
        assertEquals(user, UserController.getUsers().get(user.getId()));
    }

    @Test
    void returnsSameUserAsInMapOnCreation() {
        assertEquals(uc.createUser(user), UserController.getUsers().get(user.getId()));
    }

    @Test
    void setsLoginValueAsNameIfNameIsNullOnCreation() {
        user.setName(null);
        uc.createUser(user);
        assertEquals(user.getLogin(), user.getName());
    }

    @Test
    void returnsSameUserAsInMapOnUpdate() {
        assertEquals(uc.updateUser(user), UserController.getUsers().get(user.getId()));
    }
}
