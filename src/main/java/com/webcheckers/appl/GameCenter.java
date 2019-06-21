package com.webcheckers.appl;

import com.webcheckers.appl.PlayerServices;

/**
 * The object to coordinate the state of the Web Application and keep site wide stats.
 *
 */
public class GameCenter {

	/**
   * Get a new {@Linkplain PlayerServices} object to provide client-specific services to
   * the client who just connected to this application.
   *
   * @return
   *   A new {@Link PlayerServices}
   */
  public PlayerServices newPlayerServices() {
    return new PlayerServices(this);
  }

}
