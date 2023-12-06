package com.dmdev.util;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class LocalDateFormatterTest {

    @Test
    void format() {
        String date = "2020-11-27";

        LocalDate actualResult = LocalDateFormatter.format(date);

    }

}