package com.eafit.middleware.shared.services;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.eafit.middleware.shared.client.ApiClient;
import com.eafit.middleware.shared.dtos.NewUserDto;
import com.eafit.middleware.shared.dtos.UserAuthenticated;
import com.eafit.middleware.shared.dtos.UserCredentials;
import com.eafit.middleware.shared.dtos.UserRegisteredDto;

import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
public class AuthenticationService {

    private static final String JWT_SECRET = "df312428929bf76fdacbb6a7b8f80f2553c40fe3b5c28b2b7b0445cb985ae865e6d6ca2c3222749e655a38c309bff3b9ba186b7bff5d1536df884f7df1cdb8a6";
    private static final long JWT_EXPIRATION = 3600000L; // 1 hora en milisegundos
    private static final String REDIS_PREFIX = "auth:";

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private ApiClient apiClient;

    public UserAuthenticated login(UserCredentials userCredentials) {
        UserAuthenticated user = apiClient.login(userCredentials);

        if (Objects.isNull(user)) {
            // throw exception
        }

        // Genera el token JWT
        List<String> roles = Collections.singletonList(user.rol());
        String token = generateJwtToken(user, roles);

        // Active when Redis is running
        // if (Objects.nonNull(token)) {
        //     saveInRedis(token, userCredentials.dni());
        // }

        return new UserAuthenticated(user.id(), user.username(), user.rol(), user.changePassword(), token);
    }

    public List<UserRegisteredDto> register(List<NewUserDto> newUser) {
        List<UserRegisteredDto> errors = apiClient.register(newUser);

        List<UserRegisteredDto> response = newUser.stream().map(UserRegisteredDto::new).peek(user -> {
            user.created = !errors.contains(user);
        }).toList();

        return response;
    }

    public void saveInRedis(String token, String username) {
        // Almacena el token JWT en Redis
        String key = REDIS_PREFIX + token;
        redisTemplate.opsForValue().set(key, username);
        redisTemplate.expire(key, 1, TimeUnit.HOURS); // Establece un tiempo de expiración para el token almacenado, en
                                                      // este caso, 1 hora.
    }

    public Claims validateTokenAndAuthenticate(String token) {
        try {
            String key = REDIS_PREFIX + token;
            //String username = (String) redisTemplate.opsForValue().get(key);

            // if (Objects.nonNull(username)) {
            //     return Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(token).getBody();
            // }

            return Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(token).getBody();

        } catch (Exception e) {
        }
        return null;
    }

    private String generateJwtToken(UserAuthenticated user, List<String> roles) {
        List<GrantedAuthority> grantedAuthorities = roles.stream()
                .map(AuthorityUtils::commaSeparatedStringToAuthorityList)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());

        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + JWT_EXPIRATION);

        return Jwts.builder()
                .setSubject(user.username())
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .claim("authorities",
                        grantedAuthorities.stream().map(GrantedAuthority::getAuthority)
                                .toList())
                .signWith(SignatureAlgorithm.HS512, JWT_SECRET)
                .compact();
    }

}
