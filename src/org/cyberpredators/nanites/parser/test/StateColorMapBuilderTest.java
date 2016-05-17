package org.cyberpredators.nanites.parser.test;

/*
 * StateColorMapBuilderTest.java
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

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;

import java.util.HashMap;

import javafx.scene.paint.Color;

import org.cyberpredators.nanites.model.StateNameMap;
import org.cyberpredators.nanites.parser.StateColorMapBuilder;
import org.junit.Before;
import org.junit.Test;

public class StateColorMapBuilderTest {

	private StateColorMapBuilder sut;

	@Before
	public void setUp() {
		sut = new StateColorMapBuilder();
	}

	@Test
	public void testPut() {
		sut.put((byte) 1, Color.ALICEBLUE);
		assertThat(sut.getStateColors().get((byte) 1), is(Color.ALICEBLUE));
	}

	@Test
	public void testComplete() {
		StateNameMap stateNames = new StateNameMap();
		stateNames.setDefaultStateName("state1");
		stateNames.addIfNotPresent("state2");
		stateNames.addIfNotPresent("state3");
		sut.put((byte) 1, Color.ALICEBLUE);
		sut.completeWithRandomColors(stateNames);
		HashMap<Byte, Color> stateColors = sut.getStateColors();
		assertThat(stateColors.get(stateNames.getStateOfName("state1")), is(Color.ALICEBLUE));
		assertThat(stateColors.get(stateNames.getStateOfName("state2")), notNullValue());
		assertThat(stateColors.get(stateNames.getStateOfName("state3")), notNullValue());
	}
}
