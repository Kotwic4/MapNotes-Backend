package agh.edu.pl.MapNotes.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception raised when Pin is not found in database.
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Pin Not Found.")
public class PinNotFoundException extends Exception {
}
