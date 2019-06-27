package com.webcheckers.ui;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.webcheckers.ui.Space;

public class Row implements Iterable<Space> {
	private int index;
	private List<Space> spaces;
	
	Row(int index) {
		this.index = index;
		spaces = new ArrayList<Space>();
		for (int i = 0; i < 7; i++) {
			spaces.add(new Space(i, false));
		}
	}
	
	public int getIndex() {
		return index;
	}
	
	public Iterator<Space> iterator() {
		return spaces.iterator();
	}
}