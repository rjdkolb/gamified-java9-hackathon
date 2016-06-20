package io.r3k.hackathon.hvcjava9;

import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

//@EnableWebMvc
//@Configuration
public class WebMvcConfiguration extends WebMvcConfigurerAdapter {

    // ...

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/index").setViewName("index");
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
    }
}