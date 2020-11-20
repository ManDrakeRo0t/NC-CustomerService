package ru.bogatov.customerservice.configuration;

import io.jsonwebtoken.*;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.bogatov.customerservice.entity.Role;
import ru.bogatov.customerservice.service.CustomerService;

import javax.xml.crypto.Data;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Set;
import java.util.UUID;

@Component
@Log
public class JwtProvider {
    //@Value("${jwt.secret}")
    private String JwtSecret = "amps";
    private CustomerService customerService;

    public JwtProvider(@Autowired CustomerService customerService){
        this.customerService = customerService;
    }

    public String generateToken(String email,String password){
        Date date = Date.from(LocalDate.now().plusDays(10).atStartOfDay(ZoneId.systemDefault()).toInstant());
        HashMap<String,Object> claims = new HashMap<>();
        claims.put("email",email);
        claims.put("roles",customerService.findByEmail(email).getRoles());
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(date)
                .signWith(SignatureAlgorithm.HS512,JwtSecret)
                .compact();
    }

    public boolean isM2mToken(String token){
        Claims claims = Jwts.parser().setSigningKey(JwtSecret).parseClaimsJws(token).getBody();
        if(claims.get("M2M") != null){
            return "valid".equals(claims.get("M2M").toString());
        }
        return false;
    }

    public boolean validateToken(String token){
        try{
            Claims claims = Jwts.parser().setSigningKey(JwtSecret).parseClaimsJws(token).getBody();
            if(isM2mToken(token) || (claims.get("gateway").toString().equals("valid"))) return true;
        }catch (ExpiredJwtException expEx) {
            log.severe("Token expired");
        } catch (UnsupportedJwtException unsEx) {
            log.severe("Unsupported jwt");
        } catch (MalformedJwtException mjEx) {
            log.severe("Malformed jwt");
        } catch (SignatureException sEx) {
            log.severe("Invalid signature");
        } catch (Exception e) {
            log.severe("invalid token");
        }
        return false;
    }

    public String getLoginFromToken(String token){
        Claims claims = Jwts.parser().setSigningKey(JwtSecret).parseClaimsJws(token).getBody();
        return claims.get("email").toString();
    }

    public UUID getIdFormToken(String token){
        return customerService.getIdFormEmail(getLoginFromToken(token));
    }
}
