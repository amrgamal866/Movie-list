
package com.example.Movie_list.Config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class JwtUtil {

    // Secret key for signing the JWT (use a secure and private key in production)
    @Value("${jwt.secret}")
    private String secretKey;

    // Token expiration time (e.g., 10 minutes)
    @Value("${jwt.expiration}")
    private long expirationTime;

    // Generate JWT token from username and roles
    public String generateToken(String username, List<String> roles) {
        return JWT.create()
                .withSubject(username)  // Store the username in the token's subject
                .withClaim("roles", roles)  // Store roles as claims
                .withIssuedAt(new Date())  // Add issue date
                .withExpiresAt(new Date(System.currentTimeMillis() + expirationTime))  // Set expiration time
                .sign(Algorithm.HMAC256(secretKey));  // Sign with HMAC and secret key
    }

    // Validate the JWT token
    public boolean validateToken(String token) {
        try {
            DecodedJWT decodedJWT = JWT.require(Algorithm.HMAC256(secretKey))
                    .build()
                    .verify(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // Extract username from the token
    public String extractUsername(String token) {
        DecodedJWT decodedJWT = JWT.require(Algorithm.HMAC256(secretKey))
                .build()
                .verify(token);
        return decodedJWT.getSubject();  // Get the subject (username)
    }

    // Extract roles from the token
    public List<String> extractRoles(String token) {
        DecodedJWT decodedJWT = JWT.require(Algorithm.HMAC256(secretKey))
                .build()
                .verify(token);
        // Get roles from the token's "roles" claim
        return decodedJWT.getClaim("roles").asList(String.class);
    }
}
