package ru.yandex.practicum.filmorate.validation;

import ru.yandex.practicum.filmorate.controller.FilmController;
import ru.yandex.practicum.filmorate.controller.UserController;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Collections;
import java.util.Set;


public class IdValidator implements ConstraintValidator<InCollection, Integer> {

    private Class<?> field;

    @Override
    public void initialize(InCollection inCollection) {
        if (inCollection.setHolder() != null) {
            field = inCollection.setHolder();
        }
    }

    @Override
    public boolean isValid(Integer integer, ConstraintValidatorContext constraintValidatorContext) {
        return getSet().contains(integer);
    }

    public Set<Integer> getSet() {
        if (field.equals(FilmController.class)) {
            return FilmController.getFilms().keySet();
        }
        if (field.equals(UserController.class)) {
            return UserController.getUsers().keySet();
        }
        return Collections.emptySet();
    }
}
