package agh.edu.pl.MapNotes.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Sample test controller.
 */
@RestController
public class SampleController {

    /**
     * Controller to not show error on index page.
     * @return just "Hello World!".
     */
    @GetMapping
    public String sampleApi() {
        return "Hello World!";
    }
}
