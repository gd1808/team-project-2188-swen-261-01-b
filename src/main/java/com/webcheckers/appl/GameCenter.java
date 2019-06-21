package com.webcheckers.appl;

import com.webcheckers.appl.PlayerServices;

import java.util.ArrayList;

/**
 * The object to coordinate the state of the Web Application and keep site wide stats.
 *
 */
public class GameCenter {

    private ArrayList<PlayerServices> players = new ArrayList<>();

	/**
     * Add the given PlayerServices to the players list.
     *
     * @param ps PlayerServices to add
     */
    public void addPlayer(PlayerServices ps) {
        this.players.add(ps);
    }
	
	/**
     * Check if a provided username is taken.
     *
     * @param name String to test
     * @return true if username is taken, false otherwise.
     */
	public boolean nameIsTaken(String name) {
		for (PlayerServices player : players) {
			if (name.equals(player.Id())) {
				return true;
			}
		}
		return false;
	}
}
