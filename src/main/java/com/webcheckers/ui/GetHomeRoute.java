package com.webcheckers.ui;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Logger;

import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Session;
import spark.TemplateEngine;

import com.webcheckers.appl.GameCenter;
import com.webcheckers.appl.PlayerServices;
import com.webcheckers.util.Message;

/**
 * The UI Controller to GET the Home page.
 *
 * @author <a href='mailto:bdbvse@rit.edu'>Bryan Basham</a>
 */
public class GetHomeRoute implements Route {
  private static final Logger LOG = Logger.getLogger(GetHomeRoute.class.getName());

  private static final Message WELCOME_MSG = Message.info("Welcome to the world of online Checkers.");

  private final GameCenter gameCenter;
  private final TemplateEngine templateEngine;
  
  // Key in the session attribute map for the player who started the session
  static final String PLAYERSERVICES_KEY = "playerServices";

  /**
   * Create the Spark Route (UI controller) to handle all {@code GET /} HTTP requests.
   *
   * @param gameCenter
   *    The {@link GameCenter} for the application.
   * @param templateEngine
   *   the HTML template rendering engine
   */
  public GetHomeRoute(final GameCenter gameCenter, final TemplateEngine templateEngine) {
	this.gameCenter = Objects.requireNonNull(gameCenter, "gameCenter is required");
    this.templateEngine = Objects.requireNonNull(templateEngine, "templateEngine is required");
    //
    LOG.config("GetHomeRoute is initialized.");
  }

  /**
   * Render the WebCheckers Home page.
   *
   * @param request
   *   the HTTP request
   * @param response
   *   the HTTP response
   *
   * @return
   *   the rendered HTML for the Home page
   */
  @Override
  public Object handle(Request request, Response response) {
    LOG.finer("GetHomeRoute is invoked.");

    //start building the view model
    Map<String, Object> vm = new HashMap<>();
    vm.put("title", "Welcome!");

    System.out.println(request.session().attributes());
    if (request.session().attributes().contains("PlayerServices")) {
      vm.put("PlayerServices", request.session().attribute("PlayerServices"));
    }
    return templateEngine.render(new ModelAndView(vm, "home.ftl"));
  }
    /*
    LOG.finer("GetHomeRoute is invoked.");
	// retrieve the HTTP session
    final Session httpSession = request.session();

    // start building the View-Model
    final Map<String, Object> vm = new HashMap<>();
    vm.put("title", "Welcome!");
	
	// if this is a brand new browser session
    if(httpSession.attribute(PLAYERSERVICES_KEY) == null) {
      // get the object that will provide client-specific services for this player
      System.out.println("New player connected");
    // if
	} else if (vm.get("PlayerServices") == null) {
      final PlayerServices playerService = gameCenter.newPlayerServices();
      httpSession.attribute(PLAYERSERVICES_KEY, playerService);
      vm.put("PlayerServices", playerService);
    }


    // display a user message in the Home page
    vm.put("message", WELCOME_MSG);

    // render the View
    return templateEngine.render(new ModelAndView(vm , "home.ftl"));
  }
  */
}
