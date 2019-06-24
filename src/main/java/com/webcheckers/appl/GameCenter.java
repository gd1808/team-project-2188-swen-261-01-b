package com.webcheckers.appl;

import com.webcheckers.appl.PlayerServices;

import java.util.ArrayList;

/**
 * The object to coordinate the state of the Web Application and keep site wide stats.
 *
 */
public class GameCenter {

    private ArrayList<PlayerServices> players = new ArrayList<>();
	private int total_players = 0;

	/**
     * Add the given PlayerServices to the players list.
     *
     * @param ps PlayerServices to add
     */
    public void addPlayer(PlayerServices ps) {
        this.players.add(ps);
		total_players++;
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
	
	/**
     * Get the total amount of logged in players.
     *
     * @return the number of currently logged in players.
     */
	public int getTotalPlayers() {
		return total_players;
	}
	
	public ArrayList<PlayerServices> getPlayers() {
		return players;
	}

	public PlayerServices getPlayerById(String username) {
		for (PlayerServices p : this.players) {
			if (p.Id().equals(username)) {
				return p;
			}
		}
		return null;
	}

	/**
	 * Check to see if a player is available for a game.
	 *
	 * @return true if available, false otherwise.
	 */
	public boolean isPlayerAvailable(String username) {
		// if the user does not exist, it is not available
		if (!this.players.contains(username)) {
			return false;
		} else {
			PlayerServices ps = getPlayerById(username);
			return ps.isAvailable();
		}
	}
}
