package com.ams.service;

import com.ams.exception.InvalidTokenException;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class InvalidTokenExceptionTest {

    @Test
    void invalidTokenExceptionTest() {

        InvalidTokenException ex =
                new InvalidTokenException("Invalid token");

        assertThat(ex.getMessage())
                .isEqualTo("Invalid token");
    }
}
