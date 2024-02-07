package ru.yandex.practicum.filmorate.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.exception.ValidationException;
import ru.yandex.practicum.filmorate.model.User;

import javax.validation.Valid;
import java.time.LocalDate;
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
        log.info("Получен запрос на создание пользователя!");
        validateUser(user);
        user.setId(generateID());
        users.put(user.getId(), user);
        log.info("Добавлен новый пользователь: " + user.getLogin());
        return user;
    }

    /*
    Обновляем пользователя.
     */
    @PutMapping
    public User updateUser(@Valid @RequestBody User user) {
        log.info("Получен запрос на обновление пользователя!");
        if (!users.containsKey(user.getId())) {
            log.debug("Введен неверный id!");
            throw new ValidationException("Вы ввели не существующий id!");
        }
        users.replace(user.getId(), user);
        log.info("Пользователь обновлен!");
        return user;
    }

    /*
    Получаем всех пользователей.
     */
    @GetMapping
    public Collection<User> getAllUsers() {
        log.info("Получен список всех пользователей." + users.size());
        return users.values();
    }

    public User getUserById(Integer id) {
        return users.get(id);
    }

    private void validateUser(User user) {
        if (!user.getEmail().contains("@")) {
            throw new ValidationException("Ошибка, введен некорректный формат email!");
        }
        if (user.getEmail().isEmpty() || user.getEmail().contains(" ")) {
            throw new ValidationException("Введите email адрес без пробелов!");
        }
        if (user.getLogin().isEmpty() && user.getLogin().contains(" ")) {
            throw new ValidationException("Ошибка! Введите логин без пробелов!");
        }
        if (user.getName() == null || user.getName().isBlank()) {
            user.setName(user.getLogin());
        }
        if (user.getBirthday().isAfter(LocalDate.now())) {
            throw new ValidationException("Введена неверная дата рождения!");
        }
    }

    private int generateID() {
        return ++newID;
    }
}
