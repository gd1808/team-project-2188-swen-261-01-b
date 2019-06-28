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

public class PostCheckTurn implements Route{
    private static final Logger LOG = Logger.getLogger(PostSignInRoute.class.getName());

    private final TemplateEngine templateEngine;

    PostCheckTurn(TemplateEngine templateEngine) {
        LOG.config("PostValidateMove is initialized.");

        this.templateEngine = templateEngine;
    }

    @Override
    public String handle(Request request, Response response) {
        LOG.finer("PostValidateMove is invoked.");

        //start building the view model
        Map<String, Object> vm = new HashMap<>();
        vm.put("title", "Game.");


        return templateEngine.render(new ModelAndView(vm, "game.ftl"));
    }
}
