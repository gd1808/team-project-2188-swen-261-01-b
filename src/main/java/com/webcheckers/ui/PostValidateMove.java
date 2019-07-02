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
import org.graalvm.compiler.lir.LIRInstruction;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Session;
import spark.TemplateEngine;
import static spark.Spark.halt;

import com.webcheckers.appl.GameCenter;

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

        /* Move cannot be created from String
        Move move = request.queryParams("validateMove");
        System.out.println("move " + move);
        PlayerServices current = request.session().attribute("PlayerServices");
        boolean isValid = current.isValidMove(move);
        // send validateMove to model board for validity check
        // disable all other pieces
        if (isValid) {
            return Message.info("true");
        } else {
            return Message.error("false");
        }
        // if valid: send INFO
        // if invalid: send ERROR, move piece back, enable pieces
        */
        return Message.info("true");


    }
}
