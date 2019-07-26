package com.webcheckers.ui;

import com.webcheckers.appl.GameCenter;
import com.webcheckers.appl.PlayerServices;
import spark.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Logger;

/**
 * the UI Controller to GET the Replay-Game page.
 * This Route will be accessed when someone chooses to replay one of their previous games.
 *
 */

public class GetReplayGameRoute implements Route {
    private static final Logger LOG = Logger.getLogger(GetReplayGameRoute.class.getName());

    // FreeMarker
    private final TemplateEngine templateEngine;

    /**
     * Create the Sprk Route (UI controller) to handle all {@code GET /replay/game} HTTP requests
     *
     * @param templateEngine the HTML template rendering engine
     */
    public GetReplayGameRoute(TemplateEngine templateEngine) {
        this.templateEngine = Objects.requireNonNull(templateEngine, "templateEngine is required");
        LOG.config("GetReplayGameRoute is initialized.");
    }

    /**
     * Render the WebCheckers Game in page.
     *
     * @param request the HTTP request
     * @param response the HTTP response
     * @return the rendered HTML for the Game in page.
     */
    @Override
    public Object handle(Request request, Response response) {
        LOG.finer("GetReplayGameRoute is invoked.");
        // start building the view-model
        Map<String, Object> vm = new HashMap<>();
        vm.put("title", "Game");

        // retrieve the PlayerServices from the session
        PlayerServices current = request.session().attribute("PlayerServices");
        if (current == null) {
            vm.put("title", "Welcome!");
            // redirect back to home page
            response.redirect(WebServer.HOME_URL);
            return templateEngine.render(new ModelAndView(vm, "home.ftl"));
        }
        // TODO retrieve the game via the query params
        // lots of backend stuff needs to be implemented to do this
        return null;
    }
}
