package com.manav.securebanking.service;

import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

public class JwtServiceTest {

    private final JwtService jwtService =
            new JwtService(System.getenv("JWT_SECRET"));
    @Test
    void shouldGenerateAndExtractUsername() {

        String username = "manav";

        String token = jwtService.generateToken(username);

        String extractedUsername = jwtService.extractUsername(token);

        assertEquals(username, extractedUsername);
    }

    @Test
    void shouldRejectInvalidToken() {

        String invalidToken = "invalid-token";

        assertThrows(
                Exception.class,
                () -> jwtService.extractUsername(invalidToken)
        );
    }

    @Test
    void shouldGenerateTokenWithExpiration() {

        String token = jwtService.generateToken("manav");
        assertNotNull(token);


    }
}