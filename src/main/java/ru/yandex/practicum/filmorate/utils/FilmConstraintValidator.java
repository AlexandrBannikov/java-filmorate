package ru.yandex.practicum.filmorate.utils;import javax.validation.ConstraintValidator;import javax.validation.ConstraintValidatorContext;import java.time.LocalDate;public class FilmConstraintValidator implements ConstraintValidator<FilmMinimumDate, LocalDate> {    @Override    public boolean isValid(LocalDate date, ConstraintValidatorContext constraintValidatorContext) {        return ((date != null) && (date.isAfter(LocalDate.of(1895, 12, 28)))                && (date.isBefore(LocalDate.now())));    }}