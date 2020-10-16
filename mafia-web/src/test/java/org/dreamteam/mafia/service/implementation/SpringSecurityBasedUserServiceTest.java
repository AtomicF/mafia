package org.dreamteam.mafia.service.implementation;

import org.dreamteam.mafia.dao.UserDAO;
import org.dreamteam.mafia.dto.RegistrationDTO;
import org.dreamteam.mafia.exceptions.UserAuthenticationException;
import org.dreamteam.mafia.exceptions.UserRegistrationException;
import org.dreamteam.mafia.model.SecurityUserDetails;
import org.dreamteam.mafia.model.User;
import org.dreamteam.mafia.repository.api.CrudUserRepository;
import org.dreamteam.mafia.service.api.TokenService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;

public class SpringSecurityBasedUserServiceTest {

    @InjectMocks
    private SpringSecurityBasedUserService testedService;

    @Mock
    CrudUserRepository mockRepository;
    @Mock
    PasswordEncoder mockEncoder;
    @Mock
    TokenService mockTokenService;

    RegistrationDTO dtoNormal, dtoPasswordMismatch;
    UserDAO daoNormal;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        Mockito.when(mockEncoder.encode(Mockito.anyString())).thenAnswer(i -> i.getArguments()[0]);

        daoNormal = new UserDAO();
        daoNormal.setLogin("a");
        daoNormal.setPasswordHash("b");

        dtoNormal = new RegistrationDTO();
        dtoNormal.setLogin("a");
        dtoNormal.setPassword("b");
        dtoNormal.setPasswordConfirmation("b");

        dtoPasswordMismatch = new RegistrationDTO();
        dtoPasswordMismatch.setLogin("c");
        dtoPasswordMismatch.setPassword("d");
        dtoPasswordMismatch.setPasswordConfirmation("e");
    }

    @Test
    public void loginUserPositive() {
        Mockito.when(mockRepository.findByLogin(daoNormal.getLogin()))
                .thenReturn(Collections.singletonList(daoNormal));
        Mockito.when(mockEncoder.matches(Mockito.anyString(), Mockito.anyString())).thenReturn(true);
        try {
            testedService.loginUser(dtoNormal.getLoginData());
        } catch (UserAuthenticationException e) {
            Assert.fail("Failed to login valid user");
        }
        Mockito.verify(mockRepository, Mockito.times(1)).findByLogin(Mockito.anyString());
        Mockito.verify(mockTokenService, Mockito.times(1)).getTokenFor(Mockito.any(User.class));
    }

    @Test
    public void loginUserWrongPassword() {
        Mockito.when(mockRepository.findByLogin(daoNormal.getLogin()))
                .thenReturn(Collections.singletonList(daoNormal));
        Mockito.when(mockEncoder.matches(Mockito.anyString(), Mockito.anyString())).thenReturn(false);
        try {
            testedService.loginUser(dtoNormal.getLoginData());
            Assert.fail("Successfully logged in user, which had wrong password");
        } catch (UserAuthenticationException ignored) {
        }
        Mockito.verify(mockRepository, Mockito.times(1)).findByLogin(Mockito.anyString());
        Mockito.verify(mockTokenService, Mockito.never()).getTokenFor(Mockito.any(User.class));
    }

    @Test
    public void loginUserNoSuchUser() {
        Mockito.when(mockRepository.findByLogin(daoNormal.getLogin()))
                .thenReturn(new ArrayList<>());
        Mockito.when(mockEncoder.matches(Mockito.anyString(), Mockito.anyString())).thenReturn(true);
        try {
            testedService.loginUser(dtoNormal.getLoginData());
            Assert.fail("Successfully logged in user, which was not present in repository");
        } catch (UserAuthenticationException ignored) {
        }
        Mockito.verify(mockRepository, Mockito.times(1)).findByLogin(Mockito.anyString());
        Mockito.verify(mockTokenService, Mockito.never()).getTokenFor(Mockito.any(User.class));
    }

    @Test
    public void registerNewUserPositive() {
        try {
            testedService.registerNewUser(dtoNormal);
        } catch (UserRegistrationException e) {
            Assert.fail("Failed to register valid user");
        }
        Mockito.verify(mockRepository, Mockito.times(1)).save(Mockito.any(UserDAO.class));
    }

    @Test
    public void registerNewUserPasswordMismatch() {
        try {
            testedService.registerNewUser(dtoPasswordMismatch);
            Assert.fail("Registered user with mismatched passwords");
        } catch (UserRegistrationException ignored) {
        }
        Mockito.verify(mockRepository, Mockito.never()).save(Mockito.any(UserDAO.class));
    }

    @Test
    public void registerNewUserLoginExists() {
        Mockito.when(mockRepository.findByLogin(daoNormal.getLogin()))
                .thenReturn(Collections.singletonList(daoNormal));
        try {
            testedService.registerNewUser(dtoNormal);
            Assert.fail("Registered user when repository refused to perform saving");
        } catch (UserRegistrationException ignored) {
        }
        Mockito.verify(mockRepository, Mockito.never()).save(Mockito.any(UserDAO.class));
    }

    @Test
    public void getCurrentUserPositive() {
        SecurityUserDetails securityUserDetails = new SecurityUserDetails(daoNormal);
        Authentication auth =
                new UsernamePasswordAuthenticationToken(securityUserDetails, null,
                                                        securityUserDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(auth);
        Mockito.when(mockRepository.findByLogin(daoNormal.getLogin()))
                .thenReturn(Collections.singletonList(daoNormal));
        Optional<User> user = testedService.getCurrentUser();
        Assert.assertTrue("Failed to return properly authorised user", user.isPresent());
        Assert.assertEquals("Returned different user, than currently authorised", daoNormal.getLogin(),
                            user.get().getLogin());
        SecurityContextHolder.clearContext();
    }

    @Test
    public void getCurrentUserNoContext() {
        SecurityContextHolder.clearContext();
        Optional<User> user = testedService.getCurrentUser();
        Mockito.verify(mockRepository, Mockito.never()).findByLogin(Mockito.anyString());
        Assert.assertFalse("Found user, when there were no one authorised", user.isPresent());
        SecurityContextHolder.clearContext();
    }

    @Test
    public void getCurrentUserAnonymous() {
        SecurityUserDetails securityUserDetails = new SecurityUserDetails(daoNormal);
        Authentication auth =
                new AnonymousAuthenticationToken("non_empty", daoNormal, securityUserDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(auth);
        Mockito.when(mockRepository.findByLogin(daoNormal.getLogin()))
                .thenReturn(Collections.singletonList(daoNormal));
        Optional<User> user = testedService.getCurrentUser();
        Mockito.verify(mockRepository, Mockito.never()).findByLogin(Mockito.anyString());
        Assert.assertFalse("Found user, when authorised one was anonymous", user.isPresent());
        SecurityContextHolder.clearContext();
    }

    @Test
    public void getCurrentUserNotInRepository() {
        SecurityUserDetails securityUserDetails = new SecurityUserDetails(daoNormal);
        Authentication auth =
                new UsernamePasswordAuthenticationToken(securityUserDetails, null,
                                                        securityUserDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(auth);
        Mockito.when(mockRepository.findByLogin(daoNormal.getLogin()))
                .thenReturn(new ArrayList<>());
        try {
            testedService.getCurrentUser();
            Assert.fail("Did not throw exception, when the authorised user wasn't in repository");
        } catch (Exception ignored) {

        }
        Mockito.verify(mockRepository, Mockito.times(1)).findByLogin(daoNormal.getLogin());
        SecurityContextHolder.clearContext();
    }
    
}