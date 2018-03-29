package edu.MapNotes;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SampleTest {

    @ParameterizedTest
    @CsvSource({
            "2, 2, 4",
            "3, 5, 8",
    })
    void sampleTest(Long a, Long b, Long c) {
        Long result = a + b;
        assertEquals(result, c);
    }
}
