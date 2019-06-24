package com.webcheckers.ui;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Logger;

import com.webcheckers.appl.PlayerServices;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.TemplateEngine;

import com.webcheckers.appl.GameCenter;
import com.webcheckers.util.Message;

/**
 * The UI Controller to GET the Game In  page.
 *
 * @author <a href='mailto:bdbvse@rit.edu'>Bryan Basham</a>
 */
public class PostGameRoute implements Route {
    private static final Logger LOG = Logger.getLogger(PostGameRoute.class.getName());

    private static final Message WELCOME_MSG = Message.info("Game.");

    private final GameCenter gameCenter;
    private final TemplateEngine templateEngine;

    /**
     * Create the Spark Route (UI controller) to handle all {@code GET /game} HTTP requests.
     *
     * @param gameCenter
     *    The {@link GameCenter} for the application.
     * @param templateEngine
     *   the HTML template rendering engine
     */
    public PostGameRoute(final GameCenter gameCenter, final TemplateEngine templateEngine) {
        LOG.config("PostGameRoute is initialized.");
        //validation
        Objects.requireNonNull(gameCenter, "gameCenter must not be null");
        Objects.requireNonNull(templateEngine, "templateEngine must not be null");
        // state
        this.gameCenter = Objects.requireNonNull(gameCenter, "gameCenter is required");
        this.templateEngine = Objects.requireNonNull(templateEngine, "templateEngine is required");
    }

    /**
     * Render the WebCheckers Sign In page.
     *
     * @param request
     *   the HTTP request
     * @param response
     *   the HTTP response
     *
     * @return
     *   the rendered HTML for the Sign In page
     */
    @Override
    public Object handle(Request request, Response response) {
        LOG.finer("PostGameRoute is invoked.");

        //start building the view model
        Map<String, Object> vm = new HashMap<>();
        vm.put("title", "Game");

        // display a user message in the Sign In page
        vm.put("message", "Game");

        //retrieve the opponent
        String opponent = request.queryParams("opponent");
        System.out.println(opponent);

        //check if opponent is available
        //TODO check opponent
        // if unavailable, show error and return to home
        if (!gameCenter.isPlayerAvailable(opponent)) {
            vm.put("Error", "Player is busy.");
            return templateEngine.render(new ModelAndView(vm, "home.ftl"));
        } else {

        }

        /*
        PlayerServices ps = request.session().attribute("PlayerServices");
        vm.put("PlayerServices", ps);
        vm.put("UserName", ps.Id());
        */

        // render the View
        return templateEngine.render(new ModelAndView(vm , "game.ftl"));
    }
}
