package ru.yandex.practicum.filmorate.storage;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.exception.UserNotFoundException;
import ru.yandex.practicum.filmorate.model.User;

import javax.validation.ValidationException;
import java.util.*;

@Slf4j
@Component
public class InMemoryUserStorage implements UserStorage {
    private final Map<Integer, User> users = new HashMap<>();
    private Integer userNewID = 1;

    @Override
    public User createUser(User user) {
        log.info("Добавлен новый пользователь {}", user.getName());
        user.setId(userNewID);
        user.setFriendID(new TreeSet<>());
        users.put(userNewID++, user);
        return user;
    }

    @Override
    public User updateUser(User user) {
        if (users.containsKey(user.getId())) {
            log.info("Пользователь {} обновлен!", user.getId());
            user.setFriendID(new TreeSet<>());
            users.put(user.getId(), user);
            return user;
        }
        log.debug("Не найден пользователь {} с таким id", user.getId());
        throw new ValidationException("Пользователь не найден!");
    }

    @Override
    public void addFriend(Integer userID, Integer friendID) {
        if (users.containsKey(userID) && users.containsKey(friendID)) {
            users.get(userID).getFriendID().add(friendID);
            users.get(friendID).getFriendID().add(userID);
            log.debug("Пользователь {} добавил пользователя {} в друзья. ", userID, friendID);
        } else {
            throw new UserNotFoundException("Пользователь не найден!");
        }
    }

    @Override
    public void deleteFriend(Integer userID, Integer friendID) {
        if (users.containsKey(userID) && users.containsKey(friendID)) {
            User user = users.get(userID);
            if (!user.getFriendID().contains(friendID)) {
                users.get(userID).getFriendID().remove(friendID);
                users.get(friendID).getFriendID().remove(userID);
                log.debug("Пользователь {} удалил из друзей пользователя {}. ", userID, friendID);
            }
        } else {
            throw new UserNotFoundException("Пользователь с данным id не найден.");
        }
    }

    @Override
    public User getUserById(Integer idUser) {
        if (users.containsKey(idUser)) {
            log.debug("Получен пользователь {}", idUser);
            return users.get(idUser);
        } else {
            throw new UserNotFoundException("Пользователь не найден!");
        }

    }

    @Override
    public List<User> getAllUsers() {
        log.info("Получен список пользователей, количество = {}", users.size());
        return new ArrayList<>(users.values());
    }

    @Override
    public List<User> getFriendsById(Integer id) {
        if (users.containsKey(id)) {
            Set<Integer> userNumber = users.get(id).getFriendID();
            List<User> userList = new ArrayList<>();
            for (Integer integer : users.keySet()) {
                if (userNumber.contains(integer)) {
                    userList.add(users.get(integer));
                }
            }
            log.info("Получен друг пользователя!");
            return userList;
        } else {
            throw new UserNotFoundException("Пользователь не найден!");
        }
    }
}
