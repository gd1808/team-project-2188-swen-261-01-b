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
import com.webcheckers.model.Move;

/**
 * The POST /validateMove route handler.
 *
 */

public class PostValidateMove implements Route{
    private static final Logger LOG = Logger.getLogger(PostSignInRoute.class.getName());

    private final TemplateEngine templateEngine;

    PostValidateMove(TemplateEngine templateEngine) {
        LOG.config("PostValidateMove is initialized.");

        this.templateEngine = templateEngine;
    }

    @Override
    public Message handle(Request request, Response response) {
        LOG.finer("PostValidateMove is invoked.");

         //Move cannot be created from String
        String moveString = request.queryParams("actionData");
		//This line can be uncommented to print the move string that is later parsed by Move.parseMove()
		//System.out.println(moveString);
        Move move = Move.parseMove(moveString);
		/*
		These print lines can be used to make sure the proper row and cells are being given to the move object
		System.out.println("Move startRow: " + move.getStart().getRow() + " - startCell: " + move.getStart().getCell());
		System.out.println("Move endRow: " + move.getEnd().getRow() + " - endCell: " + move.getEnd().getCell());
		*/
		
        PlayerServices current = request.session().attribute("PlayerServices");
        boolean isValid = current.isValidMove(move);
        // send validateMove to model board for validity check
        // disable all other pieces
        if (isValid) {
            return Message.info("Valid Move!");
        } else {
            return Message.error("Invalid move!");
        }
        // if valid: send INFO
        // if invalid: send ERROR, move piece back, enable pieces
    }
}
