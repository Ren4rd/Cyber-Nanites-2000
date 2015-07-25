package org.cyberpredators.nanites.model;

import java.util.ArrayList;
import java.util.List;

/*
 * NanitesGrid.java
 * Copyright (C) Remi Even 2015
 * 
 * This file is part of CyberNanites2000.
 * 
 * CyberNanites2000 is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * CyberNanites2000 is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with CyberNanites2000. If not, see <http://www.gnu.org/licenses/>.
 */

public class NanitesGrid {

	private final int width;
	private final int height;
	private final byte[] nanites;

	public NanitesGrid(int width, int height) {
		super();
		this.width = width;
		this.height = height;
		this.nanites = new byte[(width + 2) * (height + 2)];
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public byte getStateOf(int index) {
		return nanites[index];
	}

	public void setStateOf(int index, byte state) {
		nanites[index] = state;
	}

	/**
	 * Calling this with an index of a cell on the border will throw a IndexOutOfBoundsException
	 */
	public List<Byte> getNeighborhoodOf(int index) {
		ArrayList<Byte> neighborhood = new ArrayList<Byte>(8);
		neighborhood.add(nanites[index + 1]);
		neighborhood.add(nanites[index + width + 3]);
		neighborhood.add(nanites[index + width + 2]);
		neighborhood.add(nanites[index + width + 1]);
		neighborhood.add(nanites[index - 1]);
		neighborhood.add(nanites[index - width - 3]);
		neighborhood.add(nanites[index - width - 2]);
		neighborhood.add(nanites[index - width - 1]);
		return neighborhood;
	}
}
