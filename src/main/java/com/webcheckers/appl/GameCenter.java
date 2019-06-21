package com.webcheckers.appl;

import com.webcheckers.appl.PlayerServices;

import java.util.ArrayList;

/**
 * The object to coordinate the state of the Web Application and keep site wide stats.
 *
 */
public class GameCenter {

    private ArrayList<PlayerServices> players = new ArrayList<>();

    public void addPlayer(PlayerServices ps) {
        this.players.add(ps);
    }
}
