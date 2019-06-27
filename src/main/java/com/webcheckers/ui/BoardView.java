package com.webcheckers.ui;

import java.util.ArrayList;
import java.util.Iterator;

public class BoardView implements Iterable<Row> {
    private ArrayList<Row> rows;

    public BoardView() {
        rows = new ArrayList<>();
        for (int i = 0; i < 7; ++i) {
            rows.add(new Row(i));
        }
    }

    @Override
    public Iterator<Row> iterator() {
        return rows.iterator();
    }
}
