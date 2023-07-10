package com.database.databsssee.config;

import java.util.Date;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class TokenUtil {

     private final String secret = "cccccccc";
     public String generateTokenadmin(){
          Date now = new Date();
        Date expiryDate = new Date(now.getTime() + 86400000); // Token validity for 24 hours




         return Jwts.builder()
                .setSubject("")
                .setIssuedAt(now)
                .claim("Role", "admin")             
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
     }
         public String getRoleFromToken(String token) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            Claims body = claims.getBody();
            String role = (String) body.get("Role");
            return role;
        } catch (Exception e) {
            return null;
        }
    }
}
