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
		this.width = width;
		this.height = height;
		this.nanites = new byte[(width + 2) * (height + 2)];
	}

	public void initialize() {
		for (int x = 0; x < width; x++)
			for (int y = 0; y < height; y++)
				setStateOf(x, y, (byte) 1);
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public byte getStateOf(int x, int y) {
		int index = indexOfCoordinates(x, y);
		return nanites[index];
	}

	public void setStateOf(int x, int y, byte state) {
		int index = indexOfCoordinates(x, y);
		nanites[index] = state;
	}

	public List<Byte> getNeighborhoodOf(int x, int y) {
		int index = indexOfCoordinates(x, y);
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

	/**
	 * @return the index corresponding to x (from left to right) and y (from top to bottom)
	 */
	private int indexOfCoordinates(int x, int y) {
		return  1 + x + (width + 2) * (y + 1);
	}
}
