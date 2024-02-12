package ru.yandex.practicum.filmorate.controller;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.validation.Markers;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RequestMapping("/users")
@RestController
public class UserController {

    private static int newID;

    @Getter
    private static final Map<Integer, User> users = new HashMap<>();

    /*
    Создаем пользователя.
     */
    @PostMapping
    public User createUser(@Valid @RequestBody User user) {
        log.info("Получен запрос на создание пользователя! {}", user);
        validateUser(user);
        user.setId(++newID);
        users.put(user.getId(), user);
        log.info("Добавлен новый пользователь: {}", user.getLogin());
        return user;
    }

    /*
    Обновляем пользователя.
     */
    @PutMapping
    @Validated(Markers.OnUpdate.class)
    public User updateUser(@Valid @RequestBody User user) {
        log.info("Получен запрос на обновление пользователя! {}", user);
        users.put(user.getId(), user);
        log.info("Пользователь обновлен! {}", user);
        return user;
    }

    /*
    Получаем всех пользователей.
     */
    @GetMapping
    public List<User> getAllUsers() {
        log.info("Получен список всех пользователей. {}", users.size());
        return new ArrayList<>(users.values());
    }

    private void validateUser(User user) {
        if (user.getName() == null || user.getName().isBlank()) {
            user.setName(user.getLogin());
        }
    }
}
