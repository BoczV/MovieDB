package com.codecool.moviedb.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    private final JwtTokenServices jwtTokenServices;

    public SecurityConfig(JwtTokenServices jwtTokenServices) {
        this.jwtTokenServices = jwtTokenServices;
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic().disable()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/").permitAll() // allowed by anyone
                .antMatchers(HttpMethod.POST,"/auth/sign-in").permitAll() // allowed by anyone

//                .antMatchers(HttpMethod.POST,"/auth/register").permitAll() // allowed by anyone

                .antMatchers(HttpMethod.GET,"/movie-url/**").permitAll() // allowed by anyone
                .antMatchers(HttpMethod.GET,"/genre-search/**").permitAll() // allowed by anyone
                .antMatchers(HttpMethod.GET,"/movie/**").permitAll() // allowed by anyone
                .antMatchers(HttpMethod.GET,"/search-result/**").permitAll() // allowed by anyone
                .antMatchers(HttpMethod.GET,"/random-movie/**").permitAll() // allowed by anyone
                .antMatchers(HttpMethod.GET,"/latest-movies/**").permitAll() // allowed by anyone
                .antMatchers(HttpMethod.GET,"/popular-movies/**").permitAll() // allowed by anyone

//                .antMatchers(HttpMethod.GET, "/users").hasRole("ADMIN")
//                .antMatchers(HttpMethod.DELETE, "/user").hasRole("ADMIN")

                .anyRequest().authenticated() // anything else requires authentication
                .and()
                .addFilterBefore(new JwtTokenFilter(jwtTokenServices), UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
