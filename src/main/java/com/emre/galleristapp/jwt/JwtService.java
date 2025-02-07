package com.emre.galleristapp.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.function.Function;

@Service
public class JwtService {
    public static  final String SECRET_KEY="iJeRwkOZW0DbblAW78RgxFTdg6ZUl+HNhHDRMC0NkNs=";


    public String generateToken(UserDetails userDetails) {
       return Jwts
                .builder()
                .setSubject(userDetails.getUsername()) //token sahibi olan kullanıcı adı belirtiliyor
                .setIssuedAt(new Date()) //tokenın oluşturulduğu zaman
                .setExpiration(new Date(System.currentTimeMillis() + (1000 * 60 * 60 * 2))) // tokenın geçerlilik
               // süresi 2 saat
                .signWith(getSecretKey(),SignatureAlgorithm.HS256) // Token'ı HMAC SHA256 ile imzalıyor
               .compact(); //Oluşturulan tokenı geri döner
    }


    public Claims getClaims(String token){
        return Jwts.parserBuilder() //oluşturmuş olduğumuz tokenı çözer
                .setSigningKey(getSecretKey())  //gizli anahtarımız
                .build()
                .parseClaimsJws(token) //token çözüldü
                .getBody(); // payload kısmını dön
    }



    public Key getSecretKey() {
        byte[] bytes = Decoders.BASE64.decode(SECRET_KEY); //Stringi BASE64 ile ham hale getiriyor
        return Keys.hmacShaKeyFor(bytes); //Güçlü anahtar oluşturuyor
    }

    public <T> T exportToken(String token, Function<Claims, T> claimsResolver) {
        Claims claims = getClaims(token); //iat , username gibi değerler
        return claimsResolver.apply(claims); // token ın içerisinden rasgele bir claims i alırız
    }

    public String getUsernameByToken(String token){
        return exportToken(token, Claims::getSubject); // token claims içerisinden kullanıcı adını bize döner
    }

    public boolean validateToken(String token) {
         Date tokenExpirationDate = exportToken(token,Claims::getExpiration);
         return new Date().before(tokenExpirationDate);
    }

}
