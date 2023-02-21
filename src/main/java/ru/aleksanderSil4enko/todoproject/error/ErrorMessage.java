package ru.aleksanderSil4enko.todoproject.error;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import java.time.LocalDateTime;

@Getter
public class ErrorMessage {
    private final String message;
    private final HttpStatus status;
    private final LocalDateTime timestamp;

    public ErrorMessage(String message, HttpStatus status) {
        this.message = message;
        this.status = status;
        this.timestamp = LocalDateTime.now();
    }
}
