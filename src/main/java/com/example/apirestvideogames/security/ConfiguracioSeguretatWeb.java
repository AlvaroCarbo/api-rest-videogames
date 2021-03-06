package com.example.apirestvideogames.security;

import com.example.apirestvideogames.model.services.ElMeuUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class ConfiguracioSeguretatWeb extends WebSecurityConfigurerAdapter {
    private final ElMeuAuthenticationEntryPoint elmeuEntryPoint;
    private final ElMeuUserDetailsService elmeuUserDetailsService;

    private final PasswordEncoder xifrat;
    // Per fer proves al principi, per poder fer post i put d'usuaris sense seguretat
/*    @Override
    public void configure(WebSecurity web) {
        web.ignoring().anyRequest();
    }*/

/*    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                .passwordEncoder(xifrat)
                .withUser("Alvaro")
                .password(xifrat.encode("secret"))
                .roles("ADMIN");
    }*/

     @Override
     protected void configure(AuthenticationManagerBuilder auth) throws Exception {
     auth.userDetailsService(elmeuUserDetailsService).passwordEncoder(xifrat);
     }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors() //amb aquesta línia evitem la configuració custom del cors en un fitxer a part
                .and()
                .httpBasic()
                .authenticationEntryPoint(elmeuEntryPoint)
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/me/**").hasRole("USER")/*.hasRole("ADMIN")*/ //per fer proves del forbidden
                .antMatchers(HttpMethod.GET, "/users/**", "/video-games/**").hasRole("USER")
                .antMatchers(HttpMethod.POST, "/users/**", "/video-games/**").hasRole("USER")
                .antMatchers(HttpMethod.PUT, "/video-games/**").hasRole("USER")
                .antMatchers(HttpMethod.DELETE, "/video-games/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/video-games/**").hasAnyRole("USER", "ADMIN")
                .anyRequest().authenticated();
        // .and()
        // .csrf().disable();
    }

/*    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
//per poder accedir al h2-console
                //  .authorizeRequests().antMatchers("/").permitAll().and()
                //  .authorizeRequests().antMatchers("/h2-console/**").permitAll()
                // .and()
                .csrf().disable()
                // .headers().frameOptions().disable()
                // .and()
                .authorizeRequests()
                .anyRequest().authenticated();
    }*/
}
