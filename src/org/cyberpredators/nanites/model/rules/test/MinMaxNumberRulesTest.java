package org.cyberpredators.nanites.model.rules.test;

/*
 * MinMaxNumberRulesTest.java
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
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.cyberpredators.nanites.model.rules.AbstractRule;
import org.cyberpredators.nanites.model.rules.HighMaxNumberRule;
import org.cyberpredators.nanites.model.rules.HighMinNumberRule;
import org.cyberpredators.nanites.model.rules.LowMaxNumberRule;
import org.cyberpredators.nanites.model.rules.LowMinNumberRule;
import org.junit.Test;

public class MinMaxNumberRulesTest {

	private final static List<Byte> manyLivingNeighbors = createManyLivingNeighbors();
	private final static List<Byte> fewLivingNeighbors = createFewLivingNeighbors();

	private static List<Byte> createManyLivingNeighbors() {
		List<Byte> neighbors = new ArrayList<>();
		neighbors.add((byte) 1);
		neighbors.add((byte) 1);
		neighbors.add((byte) 1);
		neighbors.add((byte) 2);
		neighbors.add((byte) 2);
		neighbors.add((byte) 2);
		neighbors.add((byte) 2);
		neighbors.add((byte) 2);
		return neighbors;
	}

	private static List<Byte> createFewLivingNeighbors() {
		List<Byte> neighbors = new ArrayList<>();
		neighbors.add((byte) 1);
		neighbors.add((byte) 1);
		neighbors.add((byte) 1);
		neighbors.add((byte) 1);
		neighbors.add((byte) 1);
		neighbors.add((byte) 2);
		neighbors.add((byte) 2);
		neighbors.add((byte) 2);
		return neighbors;
	}

	@Test
	public void testHighMaxNumberRule() {
		AbstractRule rule = new HighMaxNumberRule((byte) 1, (byte) 2, 4);
		assertThat(rule.canBeApplied(manyLivingNeighbors), is(false));
		assertThat(rule.canBeApplied(fewLivingNeighbors), is(true));
	}

	@Test
	public void testLowMaxNumberRule() {
		AbstractRule rule = new LowMaxNumberRule((byte) 1, (byte) 2, 4);
		assertThat(rule.canBeApplied(manyLivingNeighbors), is(false));
		assertThat(rule.canBeApplied(fewLivingNeighbors), is(true));
	}

	@Test
	public void testHighMinNumberRule() {
		AbstractRule rule = new HighMinNumberRule((byte) 1, (byte) 2, 4);
		assertThat(rule.canBeApplied(manyLivingNeighbors), is(true));
		assertThat(rule.canBeApplied(fewLivingNeighbors), is(false));
	}

	@Test
	public void testLowMinNumberRule() {
		AbstractRule rule = new LowMinNumberRule((byte) 1, (byte) 2, 4);
		assertThat(rule.canBeApplied(manyLivingNeighbors), is(true));
		assertThat(rule.canBeApplied(fewLivingNeighbors), is(false));
	}
}
