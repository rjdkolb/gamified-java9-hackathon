package io.r3k.hackathon.hvcjava9;

import java.util.Properties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.session.HttpSessionEventPublisher;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public InMemoryUserDetailsManager inMemoryUserDetailsManager() {
        final Properties users = new Properties();
        users.put("user", "pass,ROLE_USER,enabled"); //add whatever other user you need
        return new InMemoryUserDetailsManager(users);
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(inMemoryUserDetailsManager());
    }

    @Bean
    public SessionRegistry sessionRegistry() {
        return new SessionRegistryImpl();
    }

    @Bean
    public ServletListenerRegistrationBean<HttpSessionEventPublisher> httpSessionEventPublisher() {
        return new ServletListenerRegistrationBean<>(new HttpSessionEventPublisher());
    }

    @Override
    public void configure(WebSecurity webSecurity) throws Exception {
        webSecurity
                .ignoring()
                // All of Spring Security will ignore the requests
                .antMatchers("/fullchain.pem")
                .antMatchers("/register/getallusers")
                .antMatchers("/cacerts")
                .antMatchers("/")
                .antMatchers("/progress.html")
                .antMatchers("/*.js")
                .antMatchers("/css/*.css")
                .antMatchers("/js/vendor/*.js")
                .antMatchers("/population.csv")
                .antMatchers("/scores.csv")
                .antMatchers("/index.html")
                .antMatchers("/index_.html")
                .antMatchers("/add/*/*")
                .antMatchers("/login2.html")
                .antMatchers("/go")
                .antMatchers("/go/*/*")
                .antMatchers("/go/pass/")
                .antMatchers("/register/")
                .antMatchers("/register/user")
                .antMatchers("/user/add/*/*")
                .antMatchers("/user/exists/*");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/js/vendor/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin().defaultSuccessUrl("/javaversion.html", true)
                .loginPage("/login").defaultSuccessUrl("/javaversion.html", true)
                .permitAll()
                .and()
                .logout()
                .permitAll().and().authorizeRequests()
                .antMatchers(HttpMethod.GET, "/go*").permitAll()
                .regexMatchers(HttpMethod.PUT, "/go/*/jshellput").anonymous()
                .regexMatchers(HttpMethod.POST, "/go/*/jshellpost").anonymous()
                .regexMatchers(HttpMethod.PUT, "go/*/letsencrypt").anonymous();
        http.csrf().disable().sessionManagement().maximumSessions(-1).sessionRegistry(sessionRegistry());
    }
}
