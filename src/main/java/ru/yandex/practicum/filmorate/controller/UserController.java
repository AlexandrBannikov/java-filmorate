package ru.yandex.practicum.filmorate.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.model.User;

import javax.validation.Valid;
import javax.validation.ValidationException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RequestMapping("/users")
@RestController
public class UserController {

    private int newID = 0;

    private final Map<Integer, User> users = new HashMap<>();

    /*
    Создаем пользователя.
     */
    @PostMapping
    public User createUser(@Valid @RequestBody User user) {
        validateUser(user);
        user.setId(++newID);
        users.put(user.getId(), user);
        log.info("Добавлен новый {} пользователь:", user.getLogin());
        return user;
    }

    /*
    Обновляем пользователя.
     */
    @PutMapping
    public User updateUser(@Valid @RequestBody User user) {
        validateUser(user);
        if (!users.containsKey(user.getId())) {
            throw new ValidationException("Пользователь отсутствует!");
        }
        users.put(user.getId(), user);
        log.info("Пользователь {} обновлен!", user.getName());
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

    public void validateUser(User user) {
        if (user.getName() == null || user.getName().isBlank()) {
            user.setName(user.getLogin());
        }
    }
}
