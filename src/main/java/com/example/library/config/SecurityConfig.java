package com.example.library.config;
import com.example.library.repository.AppUserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
@Configuration
public class SecurityConfig {
 @Bean PasswordEncoder passwordEncoder(){return new BCryptPasswordEncoder();}
 @Bean UserDetailsService userDetailsService(AppUserRepository users){
  return username -> users.findByUsername(username)
   .map(u -> User.withUsername(u.getUsername()).password(u.getPassword()).roles(u.getRole()).disabled(!u.isEnabled()).build())
   .orElseThrow(() -> new UsernameNotFoundException("User not found"));
 }
 @Bean SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
  http.authorizeHttpRequests(auth -> auth
    .requestMatchers("/css/**","/js/**","/h2-console/**","/login").permitAll()
    .requestMatchers("/students/**","/issue/**","/return/**","/transactions/**").hasRole("ADMIN")
    .requestMatchers("/admin/**").hasRole("ADMIN")
    .anyRequest().authenticated())
   .formLogin(form -> form.loginPage("/login").defaultSuccessUrl("/",true).permitAll())
   .logout(logout -> logout.logoutSuccessUrl("/login?logout").permitAll())
   .headers(headers -> headers.frameOptions(frame -> frame.sameOrigin()));
  return http.build();
 }
}