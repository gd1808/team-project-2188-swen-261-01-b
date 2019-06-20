package com.webcheckers.ui;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Objects;

import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Session;
import spark.TemplateEngine;
import static spark.Spark.halt;

/**
 * The {@code POST /signin} route handler.
 *
 */
public class PostSignInRoute implements Route {

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

  private final TemplateEngine templateEngine;

  //
  // Constructor
  //

  /**
   * The constructor for the {@code POST /signin} route handler.
   *
   * @param templateEngine
   *    template engine to use for rendering HTML page
   *
   * @throws NullPointerException
   *    when the {@code templateEngine} parameter is null
   */
  PostSignInRoute(TemplateEngine templateEngine) {
    // validation
    Objects.requireNonNull(templateEngine, "templateEngine must not be null");
    //
    this.templateEngine = templateEngine;
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
    // start building the View-Model
    final Map<String, Object> vm = new HashMap<>();

    /* A null playerServices indicates a timed out session or an illegal request on this URL.
     * In either case, we will redirect back to home.
     */
    // retrieve request parameter
    final String userName = request.queryParams("username");
	/*FIXME
	 *vm.put("currentUser", true);
	 */
	vm.put("title", "Welcome " + userName + "!");

    // render the View
    return templateEngine.render(new ModelAndView(vm , "home.ftl"));
  }
}
