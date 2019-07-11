package com.webcheckers.ui;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * A class for the Rows in the Checkers game
 */
public class Row implements Iterable<Space> {
    private int index;
    private List<Space> spaces;

    /**
     * The constructor for the Rows in the Checkers game that determines if a space is playable or not
     * @param index the amount of rows in the game
     */
    Row(int index) {
        this.index = index;
        spaces = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            Space space;
            if (index % 2 == 0) {
                if (i % 2 == 0) {
                    space = new Space(i, false);
                }
                else {
                    space = new Space(i, true);
                }
            }
            else {
                if (i % 2 == 0) {
                    space = new Space(i, true);
                }
                else {
                    space = new Space(i, false);
                }
			}
            spaces.add(space);
        }
    }

    public void setPieceAt(int column, Piece piece) {
        spaces.get(column).addPiece(piece.getColor());
    }

    /**
     * Generates a piece with the color input
     * @param //color the color of the piece

	public void generatePieces(Piece.Color color) {
		for (Space space : spaces) {
			if (space.isValid()) {
				space.addPiece(color);
			}
		}
	}*/
	
	public List<Space> getSpaces() {
		return spaces;
	}

    /**
     * Gets the index of the row
     * @return the index
     */
    public int getIndex() {
        return index;
    }

    /**
     * The iterator that goes through the spaces in the row
     * @return the collection of spaces
     */
    public Iterator<Space> iterator() {
        return spaces.iterator();
    }
}
