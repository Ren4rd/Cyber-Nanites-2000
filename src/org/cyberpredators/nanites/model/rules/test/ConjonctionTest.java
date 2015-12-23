package org.cyberpredators.nanites.model.rules.test;

/*
 * ConjonctionTest.java
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

import org.cyberpredators.nanites.model.rules.Condition;
import org.cyberpredators.nanites.model.rules.Conjonction;
import org.cyberpredators.nanites.model.rules.HighMaxCondition;
import org.cyberpredators.nanites.model.rules.LowMinCondition;
import org.junit.Before;
import org.junit.Test;

public class ConjonctionTest {

	private final static List<Byte> enoughLivingNeighbors = NeighborhoodFactory.create(1, 1, 1, 1, 2, 2, 2, 2);
	private final static List<Byte> tooManyLivingNeighbors = NeighborhoodFactory.create(2, 2, 2, 2, 2, 2, 2, 2);
	private final static List<Byte> notEnoughLivingNeighbors = NeighborhoodFactory.create(1, 1, 1, 1, 1, 1, 1, 2);

	private Conjonction sut;

	@Before
	public void setUp() {
		Condition firstCondition = new LowMinCondition((byte) 2, 2);
		Condition secondCondition = new HighMaxCondition((byte) 2, 6);
		List<Condition> rules = new ArrayList<>();
		rules.add(firstCondition);
		rules.add(secondCondition);
		sut = new Conjonction(rules);
	}

	@Test
	public void testAllSubRulesVerified() {
		assertThat(sut.verifiedIn(enoughLivingNeighbors), is(true));
	}

	@Test
	public void testOnlyFirstSubRuleVerified() {
		assertThat(sut.verifiedIn(tooManyLivingNeighbors), is(false));
	}

	@Test
	public void testOnlySecondSubRuleVerified() {
		assertThat(sut.verifiedIn(notEnoughLivingNeighbors), is(false));
	}
}
