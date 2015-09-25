package org.cyberpredators.nanites.model.test;

/*
 * NanitesGridTest.java
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

import static org.hamcrest.core.IsCollectionContaining.hasItem;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.cyberpredators.nanites.model.NanitesGrid;
import org.junit.Before;
import org.junit.Test;

public class NanitesGridTest {

	private final static int width = 3;
	private final static int height = 3;

	private NanitesGrid sut;

	@Before
	public void setUp() {
		sut = new NanitesGrid(width, height);
		sut.setStateOf(0, 0, (byte) 1);
		sut.setStateOf(1, 0, (byte) 2);
		sut.setStateOf(2, 0, (byte) 3);
		sut.setStateOf(0, 1, (byte) 4);
		sut.setStateOf(1, 1, (byte) 5);
		sut.setStateOf(2, 1, (byte) 6);
		sut.setStateOf(0, 2, (byte) 7);
		sut.setStateOf(1, 2, (byte) 8);
		sut.setStateOf(2, 2, (byte) 9);
	}

	@Test
	public void testInit() {
		sut.initialize();
		for (int x = 0; x < width; x++)
			for (int y = 0; y < height; y++)
			assertEquals(1, sut.getStateOf(x, y));
	}

	@Test
	public void testGetState() {
		assertEquals((byte) 1, sut.getStateOf(0, 0));
	}

	@Test
	public void testSetState() {
		sut.setStateOf(2, 2, (byte) 1);
		assertEquals((byte) 1, sut.getStateOf(2, 2));
	}

	@Test
	public void testGetNeighborhodd() {
		List<Byte> neighbors  = sut.getNeighborhoodOf(1, 1);
		assertThat(neighbors.size(), is(8));
		assertThat(neighbors, hasItem((byte) 1));
		assertThat(neighbors, hasItem((byte) 2));
		assertThat(neighbors, hasItem((byte) 3));
		assertThat(neighbors, hasItem((byte) 4));
		assertThat(neighbors, hasItem((byte) 6));
		assertThat(neighbors, hasItem((byte) 7));
		assertThat(neighbors, hasItem((byte) 8));
		assertThat(neighbors, hasItem((byte) 9));
	}
}
