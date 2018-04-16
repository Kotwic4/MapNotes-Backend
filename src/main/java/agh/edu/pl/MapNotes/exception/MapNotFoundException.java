package agh.edu.pl.MapNotes.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Map Not Found.")
public class MapNotFoundException extends Exception {
}
