package com.webcheckers.ui;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.logging.Logger;
import java.util.regex.Pattern;

import com.webcheckers.appl.PlayerServices;
import com.webcheckers.model.Move;
import com.webcheckers.util.Message;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Session;
import spark.TemplateEngine;
import static spark.Spark.halt;

import com.webcheckers.appl.GameCenter;

/**
 * The POST /resignGame route handler.
 *
 */

public class PostResignGame implements Route{
    private static final Logger LOG = Logger.getLogger(PostSignInRoute.class.getName());

    private final TemplateEngine templateEngine;

    PostResignGame(TemplateEngine templateEngine) {
        LOG.config("PostResignGame is initialized.");

        this.templateEngine = templateEngine;
    }

    @Override
    public Message handle(Request request, Response response) {
        LOG.finer("PostResignGame is invoked.");

        PlayerServices current = request.session().attribute("PlayerServices");

        String gameOverMessage = current.Id() + " has resigned.";

        if(current.getCurrentGame().setGameOver(gameOverMessage)) {
            //current.getCurrentGame().switchTurn(current);
            current.endCurrentGame();

            return Message.info("true");
        } else {
            return Message.error("Other player resigned. Refresh to get to the end game screen.");
        }

    }
}
