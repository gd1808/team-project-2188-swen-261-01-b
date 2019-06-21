package com.webcheckers.ui;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.logging.Logger;

import com.webcheckers.appl.PlayerServices;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Session;
import spark.TemplateEngine;
import static spark.Spark.halt;

import com.webcheckers.appl.GameCenter;

/**
 * The {@code POST /signin} route handler.
 *
 */
public class PostSignInRoute implements Route {
    private static final Logger LOG = Logger.getLogger(PostSignInRoute.class.getName());

  //
  // Constants
  //

  // Values used in the view-model map for rendering the page after the user submits their name.
  static final String INVALID_NAME = "Invalid name, please try another...";
  static final String VIEW_NAME = "signin.ftl";

  //
  // Static methods
  //


  //
  // Attributes
  //

  private final GameCenter gameCenter;
  private final TemplateEngine templateEngine;

  //
  // Constructor
  //

  /**
   * The constructor for the {@code POST /signin} route handler.
   *
   * @param gameCenter
   *    The {@link GameCenter} for the application.
   * @param templateEngine
   *    template engine to use for rendering HTML page
   *
   * @throws NullPointerException
   *    when the {@code templateEngine} parameter is null
   */
  PostSignInRoute(final GameCenter gameCenter, TemplateEngine templateEngine) {
    LOG.config("PostSignInRoute is initialized.");
    // validation
	Objects.requireNonNull(gameCenter, "gameCenter must not be null");
    Objects.requireNonNull(templateEngine, "templateEngine must not be null");
    //
	this.gameCenter = gameCenter;
    this.templateEngine = templateEngine;
  }

  private boolean usernameIsValid(String usernmae) {
      //TODO add condition for valid name
      return true;
  }

  //
  // TemplateViewRoute method
  //

  /**
   * {@inheritDoc}
   *
   */
  @Override
  public String handle(Request request, Response response) {
    LOG.finer("PostSignInRoute is invoked.");

    //start building the view model
    Map<String, Object> vm = new HashMap<>();
    vm.put("title", "Welcome!");

    //retrive the username
      String username = request.queryParams("username");

      // check if name is valid
      if (usernameIsValid(username)) {
          // create new player and add to session
          PlayerServices ps = new PlayerServices(username, this.gameCenter);
          request.session().attribute("PlayerServices", ps);
          //redirect back to home page
          response.redirect(WebServer.HOME_URL);
          return templateEngine.render(new ModelAndView(vm, "home.ftl"));
      } else {
          // error and redirect back to sign in
          vm.put("error", "Invalid name");
          response.redirect(WebServer.SIGN_IN_URL);
          return null;
      }



//    // start building the View-Model
//    final Map<String, Object> vm = new HashMap<>();
//
//    /* A null playerServices indicates a timed out session or an illegal request on this URL.
//     * In either case, we will redirect back to home.
//     */
//    // retrieve request parameter
//    final String userName = request.queryParams("username");
//
//	/*TODO
//	 * Add logic to see if username is valid.
//	 * Store the username somewhere so it can be added to the player lobby.
//	 */
//	/*
//	 * This doesn't do anything, but a line like this needs to be added to GetHomeRoute
//	 * once PlayerServices is implemented
//	 */
//	//vm.put("currentPlayer", new PlayerServices(userName, gamecenter));
//
//	response.redirect(WebServer.HOME_URL);
//	return null;
//	*/
  }
}
