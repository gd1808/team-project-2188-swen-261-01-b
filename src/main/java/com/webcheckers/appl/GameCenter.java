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
        PlayerServices ps = getPlayerById(username);
        // if the user does not exist, it is not available
		if (!this.players.contains(ps)) {
			return false;
		} else {
			return ps.isAvailable();
		}
	}

	/**
	 * Create a new checkers Game.
	 * Adds two players to the game, and stores it in this GameCenter.
	 *
	 * @param p1 Player 1
	 * @param p2 Player 2
	 */
	public void createGame(String p1, String p2) {
		PlayerServices player1 = getPlayerById(p1);
		PlayerServices player2 = getPlayerById(p2);
		Game game = new Game(player1, player2, this);

		player1.addGame(game);
		player2.addGame(game);
	}
}
