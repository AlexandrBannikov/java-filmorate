package ru.yandex.practicum.filmorate.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.exception.ValidationException;
import ru.yandex.practicum.filmorate.model.User;

import javax.validation.Valid;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RequestMapping("/users")
@RestController
public class UserController {

    private static int newID = 0;

    private final Map<Integer, User> users = new HashMap<>();

    /*
    Создаем пользователя.
     */
    @PostMapping
    public User createUser(@Valid @RequestBody User user) {
        log.info("Получен запрос на создание пользователя! {}", user);
        validateUser(user);
        user.setId(generateID());
        users.put(user.getId(), user);
        log.info("Добавлен новый пользователь: {}", user.getLogin());
        return user;
    }

    /*
    Обновляем пользователя.
     */
    @PutMapping
    public User updateUser(@Valid @RequestBody User user) {
        log.info("Получен запрос на обновление пользователя! {}", user);
        if (!users.containsKey(user.getId())) {
            log.debug("Введен неверный id! {}", user.getId());
            throw new ValidationException("Вы ввели не существующий id!");
        }
        users.replace(user.getId(), user);
        log.info("Пользователь обновлен! {}", user);
        return user;
    }

    /*
    Получаем всех пользователей.
     */
    @GetMapping
    public Collection<User> getAllUsers() {
        log.info("Получен список всех пользователей. {}", users.size());
        return users.values();
    }

    public User getUserById(Integer id) {
        return users.get(id);
    }

    private void validateUser(User user) {
        if (user.getName() == null || user.getName().isBlank()) {
            user.setName(user.getLogin());
        }
    }

    private int generateID() {
        return ++newID;
    }
}
