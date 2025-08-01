package dangelodavide.U2_W3_D5_Friday.security;

import dangelodavide.U2_W3_D5_Friday.entities.User;
import dangelodavide.U2_W3_D5_Friday.exception.UnauthorizedException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtils {

    @Value("${jwt.secret}")
    private String secret;

    private final long expiration = 1000L * 60 * 60 * 24 * 7;

    public String createToken(User user) {
        return Jwts.builder()
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .setSubject(String.valueOf(user.getId()))
                .claim("role", user.getRole().name())
                .signWith(Keys.hmacShaKeyFor(secret.getBytes()))
                .compact();
    }

    public void verifyToken(String accessToken) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(Keys.hmacShaKeyFor(secret.getBytes()))
                    .build().parseClaimsJws(accessToken);
        }catch (Exception e){
            throw new UnauthorizedException("token scaduto o non valido");
        }
    }

    public String getUserIdFromToken(String accessToken) {
        return Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(secret.getBytes()))
                .build()
                .parseClaimsJws(accessToken)
                .getBody()
                .getSubject();
    }

    public String getUserRoleFromToken(String accesToken) {
        return Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(secret.getBytes()))
                .build()
                .parseClaimsJws(accesToken)
                .getBody()
                .get("role", String.class);
    }

}
