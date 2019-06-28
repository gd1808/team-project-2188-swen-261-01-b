package com.webcheckers.ui;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.logging.Logger;
import java.util.regex.Pattern;

import com.webcheckers.appl.PlayerServices;
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
    public String handle(Request request, Response response) {
        LOG.finer("PostValidateMove is invoked.");

        String move = request.queryParams("validateMove");
        System.out.println(move);
        PlayerServices current = request.session().attribute("PlayerServices");
        current.isValidMove(move);
        // send validateMove to model board for validity check
        // disable all other pieces

        // if valid: send INFO
        // if invalid: send ERROR, move piece back, enable pieces
        return " ";
    }
}
