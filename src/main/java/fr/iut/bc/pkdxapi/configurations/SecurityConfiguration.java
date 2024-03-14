package fr.iut.bc.pkdxapi.configurations;
import fr.iut.bc.pkdxapi.repositories.UserRepository;
import fr.iut.bc.pkdxapi.services.CustomUserDetailsService;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    private UserRepository userRepository;
    
    public SecurityConfiguration(UserRepository userRepository){
        this.userRepository = userRepository;
    }
  
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((authorizeRequests) -> 
            authorizeRequests       
                .requestMatchers("/users/login").authenticated()
                .requestMatchers("/users/register").permitAll()
                .requestMatchers(HttpMethod.PUT, "/pkmn/**", "/pkmn").hasAuthority("ROLE_ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/pkmn/**", "/pkmn").hasAuthority("ROLE_ADMIN")
                .requestMatchers("/pkmn/**", "/pkmn").permitAll() 
                .requestMatchers("/trainer/**", "/trainer").authenticated()
            )                    
            .httpBasic(Customizer.withDefaults()).csrf(csrf->csrf.disable());

        return http.build();
    }
 
    @Bean
    public UserDetailsService userDetailsService(){
        return new CustomUserDetailsService(userRepository);
    }

    @Bean
    public static PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
