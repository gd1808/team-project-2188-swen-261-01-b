package com.webcheckers.ui;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class BoardView implements Iterable<Row> {
    private List<Row> rows;

    public BoardView() {
        rows = new ArrayList<>();
        for (int i = 0; i < 8; ++i) {
            rows.add(new Row(i));
        }
    }

    @Override
    public Iterator<Row> iterator() {
        return rows.iterator();
    }
}
