package com.webcheckers.ui;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Row implements Iterable<Space> {
    private int index;
    private List<Space> spaces;

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
			if (index < 3 && space.isValid()) {
				space.addPiece(Piece.Color.WHITE);
			}
			else if (index > 4 && space.isValid()) {
				space.addPiece(Piece.Color.RED);
			}
            spaces.add(space);
        }
    }

    public int getIndex() {
        return index;
    }

    public Iterator<Space> iterator() {
        return spaces.iterator();
    }
}
