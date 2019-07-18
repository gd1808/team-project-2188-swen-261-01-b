package com.webcheckers.ui;

import com.webcheckers.appl.Game;
import com.webcheckers.appl.GameCenter;
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
    private GameCenter gameCenter;
    private Game game;
    private PlayerServices p1;
    private PlayerServices p2;

    /**
     * Setup before each test
     */
    @BeforeEach
    public void setup() {
        // mock objects
        p1 = mock(PlayerServices.class);
        p2 = mock(PlayerServices.class);
        gameCenter = mock(GameCenter.class);
        game = mock(Game.class);

        gameCenter.addPlayer(p1);
        gameCenter.addPlayer(p2);
        gameCenter.createGame(p1.Id(), p2.Id());

        game = p1.getCurrentGame();
        request = mock(Request.class);
        session = mock(Session.class);
        when(request.session()).thenReturn(session);
        templateEngine = mock(TemplateEngine.class);
        response = mock(Response.class);


        CuT = new PostResignGame(templateEngine);


    }

    @Test
    void handleTest() {
        assertNull(CuT.handle(request, response));
        //Message message = CuT.handle(request, response);
        //assertNotNull(message);
        //assertTrue(message.isSuccessful());
    }
}