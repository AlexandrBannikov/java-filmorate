package ru.yandex.practicum.filmorate.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.storage.user.UserStorage;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {

    private final UserStorage userStorage;

    public User addFriend(Integer userID, Integer friendID) {
        userStorage.addFriend(userID, friendID);
        log.debug("Пользователь id = {} добавил пользователя id = {} друзья. ", userID, friendID);
        return userStorage.getUserById(userID);
    }

    public User userDeleteFriend(Integer userId, Integer friendId) {
        userStorage.deleteFriend(userId, friendId);
        log.debug("Пользователь  id = {} удалил из друзей пользователя id = {}. ", userId, friendId);
        return userStorage.getUserById(userId);
    }

    public List<User> getFriends(Integer userId, Integer friendId) {
        User user = getUserById(userId);
        User friend = getUserById(friendId);
        return user.getFriendID().stream()
                .filter(friend.getFriendID()::contains)
                .map(this::getUserById).collect(Collectors.toList());
    }

    public List<User> getAllUsers() {
        return userStorage.getAllUsers();
    }

    public User createUser(User user) {
        userCheck(user);
        return userStorage.createUser(user);
    }

    public User updateUser(User user) {
        userCheck(user);
        return userStorage.updateUser(user);
    }

    public User getUserById(Integer userId) {
        return userStorage.getUserById(userId);
    }

    public List<User> getFriendsUserById(Integer id) {
        return userStorage.getFriendsById(id);
    }

    private void userCheck(User user) {
        if (user.getName() == null || user.getName().isBlank()) {
            log.debug("Вы не указали ваше имя, по этому использован ваш логин.");
            user.setName(user.getLogin());
        }
    }
}
