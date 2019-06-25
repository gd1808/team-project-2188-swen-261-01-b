package com.webcheckers.ui;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Logger;

import com.webcheckers.appl.Game;
import com.webcheckers.appl.PlayerServices;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.TemplateEngine;

import com.webcheckers.appl.GameCenter;
import com.webcheckers.util.Message;

/**
 * The UI Controller to GET the Game  page.
 *
 * @author <a href='mailto:bdbvse@rit.edu'>Bryan Basham</a>
 */
public class GetGameRoute implements Route {
    private static final Logger LOG = Logger.getLogger(GetGameRoute.class.getName());

    private static final Message WELCOME_MSG = Message.info("Sign in.");

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
    public GetGameRoute(final GameCenter gameCenter, final TemplateEngine templateEngine) {
        this.gameCenter = Objects.requireNonNull(gameCenter, "gameCenter is required");
        this.templateEngine = Objects.requireNonNull(templateEngine, "templateEngine is required");
        //
        LOG.config("GetGameRoute is initialized.");
    }

    /**
     * Render the WebCheckers Game In page.
     *
     * @param request
     *   the HTTP request
     * @param response
     *   the HTTP response
     *
     * @return
     *   the rendered HTML for the Game In page
     */
    @Override
    public Object handle(Request request, Response response) {
        LOG.finer("GetGameRoute is invoked.");
        //
        Map<String, Object> vm = new HashMap<>();
        vm.put("title", "Game");

        PlayerServices current = request.session().attribute("PlayerServices");
        Game game = current.getCurrentGame();
        PlayerServices player1 = game.getPlayer1();
        PlayerServices player2 = game.getPlayer2();

        vm.putAll(PostGameRoute.JSAttributes(player1, player2, current));

        // render the View
        return templateEngine.render(new ModelAndView(vm , "game.ftl"));
    }
}
