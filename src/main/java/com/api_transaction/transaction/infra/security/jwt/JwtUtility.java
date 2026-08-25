package com.api_transaction.transaction.infra.security.jwt;

import static com.api_transaction.transaction.domain.util.GlobalConstants.TOKEN_SUBSTRING;

public class JwtUtility {
    private JwtUtility() {
        throw new AssertionError();
    }

    public static String extractJwt(String authHeader) {
        return authHeader.substring(TOKEN_SUBSTRING);
    }
}
