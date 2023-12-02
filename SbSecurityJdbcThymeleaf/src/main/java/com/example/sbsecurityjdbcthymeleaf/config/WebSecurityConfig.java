package com.example.sbsecurityjdbcthymeleaf.config;



import com.example.sbsecurityjdbcthymeleaf.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfiguration {

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/", "/login", "/logout").permitAll()
                .antMatchers("/userInfo").access("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
                .antMatchers("/admin").access("hasRole('ROLE_ADMIN')")
                .and()
                .exceptionHandling().accessDeniedPage("/403")
                .and()
                .formLogin()
                .loginProcessingUrl("/j_spring_security_check")
                .loginPage("/login")
                .defaultSuccessUrl("/userAccountInfo")
                .failureUrl("/login?error=true")
                .usernameParameter("username")
                .passwordParameter("password")
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/logoutSuccessful");
    }
}
