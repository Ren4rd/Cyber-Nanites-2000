package org.cyberpredators.nanites.model.rules.test;

/*
 * ConjonctionOfRulesTest.java
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
import org.cyberpredators.nanites.model.rules.ConjonctionOfRules;
import org.cyberpredators.nanites.model.rules.HighMaxNumberRule;
import org.cyberpredators.nanites.model.rules.LowMinNumberRule;
import org.junit.Before;
import org.junit.Test;

public class ConjonctionRuleTest {

	private final static List<Byte> enoughLivingNeighbors = NeighborhoodFactory.create(1, 1, 1, 1, 2, 2, 2, 2);
	private final static List<Byte> tooManyLivingNeighbors = NeighborhoodFactory.create(2, 2, 2, 2, 2, 2, 2, 2);
	private final static List<Byte> notEnoughLivingNeighbors = NeighborhoodFactory.create(1, 1, 1, 1, 1, 1, 1, 2);

	private ConjonctionOfRules sut;

	@Before
	public void setUp() {
		AbstractRule firstRule = new LowMinNumberRule((byte) 1, (byte) 2, 2);
		AbstractRule secondRule = new HighMaxNumberRule((byte) 1, (byte) 2, 6);
		List<AbstractRule> rules = new ArrayList<>();
		rules.add(firstRule);
		rules.add(secondRule);
		sut = new ConjonctionOfRules((byte) 1, rules);
	}

	@Test
	public void testAllSubRulesVerified() {
		assertThat(sut.canBeApplied(enoughLivingNeighbors), is(true));
	}

	@Test
	public void testOnlyFirstSubRuleVerified() {
		assertThat(sut.canBeApplied(tooManyLivingNeighbors), is(false));
	}

	@Test
	public void testOnlySecondSubRuleVerified() {
		assertThat(sut.canBeApplied(notEnoughLivingNeighbors), is(false));
	}
}
