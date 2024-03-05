package ru.yandex.practicum.filmorate.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.service.UserService;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RequestMapping("/users")
@RestController
@Validated
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /*
        Создаем пользователя.
     */
    @PostMapping
    public User createUser(@Valid @RequestBody User user) {
        return userService.createUser(user);
    }

    /*
    Обновляем пользователя.
     */
    @PutMapping
    public User updateUser(@Valid @RequestBody User user) {
        return userService.updateUser(user);
    }

    /*
    Получаем всех пользователей.
     */
    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PutMapping("/{id}/friends/{friendID}")
    public User userAddFriend(@PathVariable("id") final Integer userID,
                              @PathVariable("friendID") final Integer friendID) {
        return userService.addFriend(userID, friendID);
    }

    @DeleteMapping("/{id}/friends/{friendID}")
    public User userDeleteFriend(@PathVariable("id") final Integer userID,
                                 @PathVariable("friendID") final Integer friendID) {
        return userService.userDeleteFriend(userID, friendID);
    }

    @GetMapping("/{id}/friends/common/{otherID}")
    public List<User> getListFriend(@PathVariable("id") final Integer userID,
                                    @PathVariable("otherID") final Integer friendID) {
        return userService.getFriends(userID, friendID);
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable("id") Integer id) {
        return userService.getUserById(id);
    }

    @GetMapping("/{id}/friends")
    public List<User> getFriendsUserById(@PathVariable("id") Integer id) {
        return userService.getFriendsUserById(id);
    }
}
