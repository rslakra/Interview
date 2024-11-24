package com.rslakra.interview.fico;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.*;

import static org.testng.AssertJUnit.assertEquals;

/**
 * @author Rohtash Lakra
 * @created 1/23/24 1:25 PM
 */
public class FacebookLoginTest {


    private static final Logger LOGGER = LoggerFactory.getLogger(FacebookLoginTest.class);

    // valid facebook login

    @DataProvider
    private Iterator<Object[]> loginData() {
        List<Object[]> input = new ArrayList<>();
        input.add(new Object[]{"test1", "password1", "OK"});
        input.add(new Object[]{"error", "password2", "ERROR"});
        input.add(new Object[]{"test3", "password3", "OK"});
        input.add(new Object[]{"test5", null, null});
        return input.iterator();
    }

    private String login(String username, String password) {
        LOGGER.debug("+login({}, {})", username, password);
        if (password == null) {
            throw new RuntimeException("error");
        }

        LOGGER.debug("-login({}, {})", username, password);
        return "OK";
    }

    static class Response {
        String id;
        String status;

        Response(String id, String status) {
            this.id = id;
            this.status = status;
        }
    }

    static Map<String, Response> responses = new HashMap<>();

    //facebook.login
    @Test(dataProvider = "loginData")
    public void testLoginSuccess(String username, String password, String expected) {
        try {
            String result = login(username, password);
            responses.put(username, new Response(username, result));
            assertEquals(expected, result);
        } catch (RuntimeException ex) {
            responses.put(username, new Response(username, ex.toString()));
        }
    }


    @Test
    // invalid
    public void testLoginError() {

    }

}
