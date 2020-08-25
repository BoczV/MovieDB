package com.codecool.moviedb.controller;

import com.codecool.moviedb.model.User;
import com.codecool.moviedb.repository.UserRepository;
import com.codecool.moviedb.security.JwtTokenServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/auth")
@CrossOrigin("*")
public class AuthController {

    private final AuthenticationManager authenticationManager;

    private final JwtTokenServices jwtTokenServices;

    private final PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

    @Autowired
    UserRepository userRepository;

    public AuthController(AuthenticationManager authenticationManager, JwtTokenServices jwtTokenServices, UserRepository users) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenServices = jwtTokenServices;
    }

    @PostMapping("/sign-in")
    public ResponseEntity signIn(@RequestBody Map<String, Object> data, HttpServletResponse response) {
        try {
            String username = data.get("username").toString();
            String password = data.get("password").toString();
            // authenticationManager.authenticate calls loadUserByUsername in CustomUserDetailsService
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            List<String> roles = authentication.getAuthorities()
                    .stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(Collectors.toList());

            String token = jwtTokenServices.createToken(username, roles);

            Map<Object, Object> model = new HashMap<>();
            model.put("username", username);
            model.put("roles", roles);
            model.put("token", token);

            Cookie cookie = new Cookie("Authorization", token);
            cookie.setPath("/");
            cookie.setMaxAge(24*60*60);
            cookie.setSecure(true);
            cookie.setHttpOnly(true);
            System.out.println(cookie.getValue());
            response.addCookie(cookie);

            return ResponseEntity.ok(model);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username/password supplied");
        }
    }

    @PostMapping("/registration")
    public ResponseEntity register(@RequestBody Map<String, Object> data, HttpServletResponse response) {
        String username = data.get("username").toString();
        String password = data.get("password").toString();
        String email = data.get("email").toString();
        try {
            User newUser = User.builder().userName(username).password(passwordEncoder.encode(password)).email(email).roles(Arrays.asList("ROLE_USER")).build();
            userRepository.save(newUser);
            String token = jwtTokenServices.createToken(username, Arrays.asList("ROLE_USER"));

            Map<Object, Object> model = new HashMap<>();

            model.put("username", username);
            model.put("roles", Arrays.asList("ROLE_USER"));
            model.put("token", token);

            Cookie cookie = new Cookie("Authorization", token);
            cookie.setPath("/");
            cookie.setMaxAge(24*60*60);
            cookie.setSecure(true);
            cookie.setHttpOnly(true);
            System.out.println(cookie.getValue());
            response.addCookie(cookie);

            return ResponseEntity.ok(model);
        } catch (Exception e) {
            throw new BadCredentialsException("Username/email address already in use!");
        }



    }
}
