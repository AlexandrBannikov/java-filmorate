package ru.yandex.practicum.filmorate.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.ConstraintViolationException;

@RestController("ru.yandex.practicum.filmorate.controller")
@Slf4j
public class HandlerException {

    @ExceptionHandler({EntityNotFoundException.class, ValidationException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleUserIncorrect(final RuntimeException e) {
        log.debug("Ошибка пользователя!");
        return new ErrorResponse("Ошибка пользователя!", e.getMessage());
    }

    @ExceptionHandler({MethodArgumentNotValidException.class, ConstraintViolationException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse badRequest(final Exception e) {
        log.debug("Ошибка валидации!");
        return new ErrorResponse("Ошибка валидации!", e.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse serverError(final Throwable e) {
        log.debug("Неизвестная ошибка!");
        return new ErrorResponse("Неизвестная ошибка!", e.getMessage());
    }
}
