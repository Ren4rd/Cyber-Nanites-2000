package org.cyberpredators.nanites.model.test;

/*
 * StateNameMapTest.java
 * Copyright (C) Remi Even 2015-2016
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

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.hasItems;

import org.cyberpredators.nanites.model.StateNameMap;
import org.junit.Before;
import org.junit.Test;

public class StateNameMapTest {

	private StateNameMap sut;

	@Before
	public void setUp() {
		sut = new StateNameMap();
		sut.setDefaultStateName("dead");
		sut.addIfNotPresent("living");
	}

	@Test
	public void testDefaultStateName() {
		assertThat(sut.getNameOfState((byte) 1), is("dead"));
	}

	@Test
	public void testFirstStateName() {
		assertThat(sut.getNameOfState((byte) 2), is ("living"));
	}

	@Test
	public void testAddNewStateName() {
		sut.addIfNotPresent("sick");
		assertThat(sut.getNameOfState((byte) 3), is("sick"));
	}

	@Test
	public void testAddStateNameAlreadyKnown() {
		sut.addIfNotPresent("living");
		assertThat(sut.getNumberOfStates(), is(2));
	}

	@Test
	public void testGetStateOfName() {
		assertThat(sut.getStateOfName("dead"), is((byte) 1));
		assertThat(sut.getStateOfName("living"), is((byte) 2));
	}

	@Test
	public void testGetNames() {
		assertThat(sut.getNames().size(), is(2));
		assertThat(sut.getNames(), hasItems("dead", "living"));
	}

	@Test
	public void testGetStates() {
		assertThat(sut.getStates().size(), is(2));
		assertThat(sut.getStates(), hasItems((byte) 1, (byte) 2));
	}
}
