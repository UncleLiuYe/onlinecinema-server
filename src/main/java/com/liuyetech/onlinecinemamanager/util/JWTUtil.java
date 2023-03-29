package com.liuyetech.onlinecinemamanager.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

public class JWTUtil {
    public static final String JWT_TOKEN_SECRET_KEY = "liuye2000";

    private JWTUtil() {
    }

    public static String createToken(Integer userid) {
        Algorithm algorithm = Algorithm.HMAC384(JWT_TOKEN_SECRET_KEY);
        return JWT.create().withClaim("userid", userid)
                .withExpiresAt(Instant.now().plus(30L, ChronoUnit.SECONDS))
                .sign(algorithm);
    }

    public static boolean verifyToken(String token) {
        try {
            JWT.require(Algorithm.HMAC384(JWT_TOKEN_SECRET_KEY)).build().verify(token);
            return true;
        } catch (JWTVerificationException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static Integer getUserByToken(String token) {
        return JWT.decode(token).getClaim("userid").asInt();
    }
}
