package edu.MapNotes.pin;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Pin Not Found.")
public class PinNotFoundException extends Exception {
}
