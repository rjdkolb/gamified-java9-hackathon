package io.r3k.hackathon.hvcjava9.accesscontrol;

import io.r3k.hackathon.hvcjava9.controllers.SubmitController;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegisterController {

    private static final org.apache.logging.log4j.Logger log = org.apache.logging.log4j.LogManager.getLogger(RegisterController.class);

    private final InMemoryUserDetailsManager inMemoryUserDetailsManager;

    @Autowired
    public RegisterController(InMemoryUserDetailsManager inMemoryUserDetailsManager) {
        this.inMemoryUserDetailsManager = inMemoryUserDetailsManager;
    }

    @RequestMapping(value = "register/", method = RequestMethod.POST)
    public UserPassword register(@RequestParam(value = "username") String username, @RequestParam(value = "surname") String surname) {
        String user = (username.replaceAll("[^A-Za-z0-9]", "").charAt(0) + surname).toLowerCase().replaceAll("[^A-Za-z0-9]", "");
        UserPassword userPassword = new UserPassword(user, Obfuscate.obfuscate(user).substring(3, 8));
        if (!inMemoryUserDetailsManager.userExists(user)) {
            inMemoryUserDetailsManager.createUser(new User(userPassword.getUser(), userPassword.getPassword(), new ArrayList<GrantedAuthority>()));
            SubmitController.getScore(user);
        }
        log.info("Created user " + userPassword);
        return userPassword;

    }

}
