package ru.yandex.practicum.filmorate.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.storage.user.UserStorage;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UserService {

    private final UserStorage inMemoryUserStorage;

    @Autowired
    public UserService(UserStorage inMemoryUserStorage) {
        this.inMemoryUserStorage = inMemoryUserStorage;
    }

    public User addFriend(Integer userID, Integer friendID) {
        inMemoryUserStorage.addFriend(userID, friendID);
        log.debug("Пользователь id = {} добавил пользователя id = {} друзья. ", userID, friendID);
        return inMemoryUserStorage.getUserById(userID);
    }

    public User userDeleteFriend(Integer userId, Integer friendId) {
        inMemoryUserStorage.deleteFriend(userId, friendId);
        log.debug("Пользователь  id = {} удалил из друзей пользователя id = {}. ", userId, friendId);
        return inMemoryUserStorage.getUserById(userId);
    }

    public List<User> getFriends(Integer userId, Integer friendId) {
        User user = getUserById(userId);
        User friend = getUserById(friendId);
        return user.getFriendID().stream()
                .filter(friend.getFriendID()::contains)
                .map(this::getUserById).collect(Collectors.toList());
    }

    public List<User> getAllUsers() {
        return inMemoryUserStorage.getAllUsers();
    }

    public User createUser(User user) {
        userCheck(user);
        return inMemoryUserStorage.createUser(user);
    }

    public User updateUser(User user) {
        userCheck(user);
        return inMemoryUserStorage.updateUser(user);
    }

    public User getUserById(Integer userId) {
        return inMemoryUserStorage.getUserById(userId);
    }

    public List<User> getFriendsUserById(Integer id) {
        return inMemoryUserStorage.getFriendsById(id);
    }

    private void userCheck(User user) {
        if (user.getName() == null || user.getName().isBlank()) {
            log.debug("Вы не указали ваше имя, по этому использован ваш логин.");
            user.setName(user.getLogin());
        }
    }
}
