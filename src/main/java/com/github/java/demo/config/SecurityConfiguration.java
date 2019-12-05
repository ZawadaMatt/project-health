package com.github.java.demo.config;

import com.github.java.demo.repositories.DieticanRepository;
import com.github.java.demo.repositories.PatientsRepository;
import com.github.java.demo.security.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    private final DataSource dataSource;
    private PatientsRepository patientsRepository;
    private DieticanRepository dieticanRepository;


    @Autowired
    SecurityConfiguration(DataSource dataSource, PatientsRepository patientsRepository, DieticanRepository dieticanRepository) {
        this.dataSource = dataSource;
        this.patientsRepository = patientsRepository;
        this.dieticanRepository = dieticanRepository;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/register-dietician").anonymous()
                .antMatchers("/register-patient").anonymous()
                .antMatchers("/add-dietician").anonymous()
                .antMatchers("/add-patient").permitAll()
                .antMatchers("/user-login").anonymous()
                .antMatchers("/offer").permitAll()
                .anyRequest().permitAll();

        http.formLogin()
                .loginPage("/user-login")
                .loginProcessingUrl("/auth")
                .usernameParameter("email")
                .passwordParameter("password")
                .successForwardUrl("/")
                .defaultSuccessUrl("/");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
    }

    @Bean
    @Override
    protected UserDetailsService userDetailsService() {
        return new UserDetailsServiceImpl(this.dieticanRepository, this.patientsRepository);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
