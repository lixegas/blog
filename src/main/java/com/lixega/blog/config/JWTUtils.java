package com.lixega.blog.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Date;

@Component
@AllArgsConstructor
@Slf4j
public class JWTUtils {

    private final RSAKeyProperties rsaKeyProperties;
    private static final long jwtExpirationMillis = 30000;


    public String getSubject(String jwt) {
        JwtParser jwtParser =
                Jwts.parser()
                        .decryptWith(rsaKeyProperties.privateKey())
                        .build();
        try {
            Claims claims = jwtParser.parseSignedClaims(jwt).getPayload();
            return claims.getSubject();
        } catch (Exception e) {
            log.error("Encounter an error while decoding JWT:", e);
            return null;
        }
    }

    public String generateTokenWithUsername(String username) {
        return Jwts
                .builder()
                .signWith(rsaKeyProperties.publicKey())
                .issuedAt(Date.from(Instant.now()))
                .expiration(Date.from(Instant.now().plusMillis(jwtExpirationMillis)))
                .compact();
    }


}
