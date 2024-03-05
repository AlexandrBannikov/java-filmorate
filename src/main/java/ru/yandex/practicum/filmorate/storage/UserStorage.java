package ru.yandex.practicum.filmorate.storage;

import ru.yandex.practicum.filmorate.model.User;

import java.util.List;

public interface UserStorage {
    User createUser(User user);

    User updateUser(User user);

    void addFriend(Integer userID, Integer friendID);

    void deleteFriend(Integer userID, Integer friendID);

    User getUserById(Integer idUser);

    List<User> getAllUsers();

    List<User> getFriendsById(Integer id);
}
