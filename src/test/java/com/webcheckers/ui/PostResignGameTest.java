package com.webcheckers.ui;

import com.webcheckers.util.Message;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import com.webcheckers.appl.PlayerServices;
import spark.*;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import static org.mockito.ArgumentMatchers.any;

/**
 * unit tests for the PostResignGame class
 */
@Tag("UI-tier")
class PostResignGameTest {

    private PostResignGame CuT;

    private Request request;
    private Session session;
    private Response response;
    private TemplateEngine templateEngine;
    //private PlayerServices playerServices;

    /**
     * Setup before each test
     */
    @BeforeEach
    public void setup() {
        // mock objects
        request = mock(Request.class);
        session = mock(Session.class);
        when(request.session()).thenReturn(session);
        templateEngine = mock(TemplateEngine.class);
        response = mock(Response.class);

        CuT = new PostResignGame(templateEngine);

        // friendly objects
        //playerServices = request.session().attribute("PlayerServices");
    }

    @Test
    void handleTest() {
        Message message = CuT.handle(request, response);
        assertNotNull(message);

        assertTrue(message.isSuccessful());
    }
}