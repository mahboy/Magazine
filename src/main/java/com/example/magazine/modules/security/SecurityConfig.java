package com.example.magazine.modules.security;
import com.example.magazine.modules.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter  {


    private final DataSource dataSource;
    private final UserService userService;
    private JwtFilter jwtFilter;

    @Autowired
    public SecurityConfig(DataSource dataSource, UserService userService, JwtFilter jwtFilter) {
        this.dataSource = dataSource;
        this.userService = userService;
        this.jwtFilter = jwtFilter;
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/guest/**","/index/**","/images/**","/css/**","/fonts/**","/js/**","/scripts/**","/server/**","/src/**")
                .permitAll()
                .antMatchers("/admin/*","/user/*").hasAuthority("ADMIN")
                .antMatchers("/user/*").hasAuthority("USER")
                .anyRequest().authenticated()
                .and().formLogin().loginPage("/guest/login").defaultSuccessUrl("/index",true).permitAll()
                .and().rememberMe().rememberMeCookieName("remember").rememberMeParameter("remember")
                .and().logout().deleteCookies("remember").permitAll()
                .and()
                .exceptionHandling().accessDeniedPage("/guest/403");
//                .and()
//                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
   }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
       User.UserBuilder userBuilder=User.withDefaultPasswordEncoder();
       auth.inMemoryAuthentication().withUser(userBuilder.username("mehdi").password("123").roles("ADMIN"));
       auth.userDetailsService(userService);
    }

//    @Bean
//    public PasswordEncoder passwordEncoder(){
//        return NoOpPasswordEncoder.getInstance();
//    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
