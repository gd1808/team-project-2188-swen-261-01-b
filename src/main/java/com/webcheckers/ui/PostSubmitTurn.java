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
 * The POST /submitTurn route handler.
 *
 */

public class PostSubmitTurn implements Route{
    private static final Logger LOG = Logger.getLogger(PostSignInRoute.class.getName());

    private final TemplateEngine templateEngine;

    PostSubmitTurn(TemplateEngine templateEngine) {
        LOG.config("PostSubmitTurn is initialized.");

        this.templateEngine = templateEngine;
    }

    @Override
    public Message handle(Request request, Response response) {
        LOG.finer("PostSubmitTurn is invoked.");

        PlayerServices current = request.session().attribute("PlayerServices");
		//To-Do
		//Validate the players move
		
		//This only should happen after the players move is determined to be valid.
		//This should also happen before the active-color is switched on the board.
		if (false) {
			if (current.getCurrentGame().setGameOver(""));
		}
        //get move recent move from current Player
        // submit it to Board for advanced validation
        // if valid: return Message and redirect to game.ftl
        // if invalid: return Message
        return Message.info("true");
    }
}
