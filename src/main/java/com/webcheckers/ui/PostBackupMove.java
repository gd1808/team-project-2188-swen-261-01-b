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
 * The POST /backupMove route handler.
 *
 */

public class PostBackupMove implements Route{
    private static final Logger LOG = Logger.getLogger(PostSignInRoute.class.getName());

    private final TemplateEngine templateEngine;

    PostBackupMove(TemplateEngine templateEngine) {
        LOG.config("PostBackupMove is initialized.");

        this.templateEngine = templateEngine;
    }

    @Override
    public Message handle(Request request, Response response) {
        LOG.finer("PostBackupMove is invoked.");

        PlayerServices current = request.session().attribute("PlayerServices");
        // get move recent move from current Player
        // revert the current Players move in the Game state
        // return Message about success of revert
        return Message.info("true");

    }
}
