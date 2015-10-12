package org.cyberpredators.nanites.model.rules.test;

/*
 * MinMaxConditionTest.java
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

import java.util.List;

import org.cyberpredators.nanites.model.rules.Condition;
import org.cyberpredators.nanites.model.rules.HighMaxCondition;
import org.cyberpredators.nanites.model.rules.HighMinCondition;
import org.cyberpredators.nanites.model.rules.LowMaxCondition;
import org.cyberpredators.nanites.model.rules.LowMinCondition;
import org.junit.Test;

public class MinMaxConditionTest {

	private final static List<Byte> manyLivingNeighbors = NeighborhoodFactory.create(1, 1, 1, 2, 2, 2, 2, 2);
	private final static List<Byte> fewLivingNeighbors = NeighborhoodFactory.create(1, 1, 1, 1, 1, 2, 2, 2);

	@Test
	public void testHighMaxNumberRule() {
		Condition condition = new HighMaxCondition((byte) 2, 4);
		assertThat(condition.verifiedIn(manyLivingNeighbors), is(false));
		assertThat(condition.verifiedIn(fewLivingNeighbors), is(true));
	}

	@Test
	public void testLowMaxNumberRule() {
		Condition condition = new LowMaxCondition((byte) 2, 4);
		assertThat(condition.verifiedIn(manyLivingNeighbors), is(false));
		assertThat(condition.verifiedIn(fewLivingNeighbors), is(true));
	}

	@Test
	public void testHighMinNumberRule() {
		Condition condition = new HighMinCondition((byte) 2, 4);
		assertThat(condition.verifiedIn(manyLivingNeighbors), is(true));
		assertThat(condition.verifiedIn(fewLivingNeighbors), is(false));
	}

	@Test
	public void testLowMinNumberRule() {
		Condition condition = new LowMinCondition((byte) 2, 4);
		assertThat(condition.verifiedIn(manyLivingNeighbors), is(true));
		assertThat(condition.verifiedIn(fewLivingNeighbors), is(false));
	}
}
