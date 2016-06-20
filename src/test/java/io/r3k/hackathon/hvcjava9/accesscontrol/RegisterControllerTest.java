package io.r3k.hackathon.hvcjava9.accesscontrol;

import org.junit.Assert;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import static org.mockito.Mockito.mock;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

public class RegisterControllerTest {

    @Test
    public void shouldRegisterNewUserAndGenerateUserName() {
        // given
        String userName = "x";
        String surname = "y";
        InMemoryUserDetailsManager inMemoryUserDetailsManager = mock(InMemoryUserDetailsManager.class);
        // when
        UserPassword userPassword = new RegisterController(inMemoryUserDetailsManager).register(userName, surname);
        // then
        assertEquals("xy", userPassword.getUser());
        Assert.assertNotNull(userPassword.getPassword());
    }

    @Test
    public void shouldRegisterNewUserUsingFirstLetterOfNameAndSurnameInLowerCase() {
        // given
        String userName = "Richard";
        String surname = "Kolb";
        InMemoryUserDetailsManager inMemoryUserDetailsManager = mock(InMemoryUserDetailsManager.class);
        // when
        UserPassword userPassword = new RegisterController(inMemoryUserDetailsManager).register(userName, surname);
        // then
        assertEquals("rkolb", userPassword.getUser());
        Assert.assertNotNull(userPassword.getPassword());
    }
    @Test
    public void shouldRegisterNewUserWithPassword() {
        // given
        String userName = "Richard";
        String surname = "Kolb";
        InMemoryUserDetailsManager inMemoryUserDetailsManager = mock(InMemoryUserDetailsManager.class);
        // when
        UserPassword userPassword = new RegisterController(inMemoryUserDetailsManager).register(userName, surname);
        // then
        assertEquals(5,userPassword.getPassword().length());
    }
    @Test
    public void shouldRegisterNewUserWithPasswordForSingleLetters() {
        // given
        String userName = "A";
        String surname = "A";
        InMemoryUserDetailsManager inMemoryUserDetailsManager = mock(InMemoryUserDetailsManager.class);
        // when
        UserPassword userPassword = new RegisterController(inMemoryUserDetailsManager).register(userName, surname);
        // then
        assertEquals(5,userPassword.getPassword().length());
    }
    @Test
    public void shouldStripNonLettersFromUserNameAndSurname() {
        // given
        String userName = "@#$%^&*()-=[ ]{}Richard";
        String surname = "Kolb@#$%^&*()-=[]{ }";
        InMemoryUserDetailsManager inMemoryUserDetailsManager = mock(InMemoryUserDetailsManager.class);
        // when
        UserPassword userPassword = new RegisterController(inMemoryUserDetailsManager).register(userName, surname);
        // then
        assertEquals("rkolb", userPassword.getUser());
        Assert.assertNotNull(userPassword.getPassword());
    }


}
