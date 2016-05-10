package org.cyberpredators.nanites.parser.test;

/*
 * YamlModFactoryTest.java
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
import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

import javafx.scene.paint.Color;

import org.cyberpredators.nanites.model.Mod;
import org.cyberpredators.nanites.model.rules.LowMaxCondition;
import org.cyberpredators.nanites.model.rules.LowMinCondition;
import org.cyberpredators.nanites.model.rules.NumberCondition;
import org.cyberpredators.nanites.parser.ModFactoryException;
import org.cyberpredators.nanites.parser.YamlModFactory;
import org.cyberpredators.nanites.parser.YamlParser;
import org.junit.Before;
import org.junit.Test;

import com.esotericsoftware.yamlbeans.YamlException;

public class YamlModFactoryTest {

	private Mod parsedMod;

	private final String yamlGameOfLifeMod =
		"rules: \n" +
		" - #overCrowding\n" +
		"   ifIs: living\n" +
		"   minimum: 4\n" +
		"   neighborState: living\n" +
		"   thenBecome: dead\n" +

		" - #underPopulation\n" +
		"   ifIs: living\n" +
		"   maximum: 1\n" +
		"   neighborState: living\n" +
		"   thenBecome: dead\n" +

		" - #birth\n" +
		"   ifIs: dead\n" +
		"   number: 3\n" +
		"   neighborState: living\n" +
		"   thenBecome: living\n" +
		
		"colors: \n" +
		"   dead: 0x101010\n" +
		"   living: aliceblue\n" +

		"defaultState: dead";

	@Before
	public void setUp() throws YamlException {
		parsedMod = null;
		try (Reader sourceReader = new StringReader(yamlGameOfLifeMod)) {
			parsedMod = YamlModFactory.createMod(YamlParser.parse(sourceReader));			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ModFactoryException e) {
			e.printStackTrace();
			fail("A mod factory exception occured");
		}
	}

	@Test
	public void testInit() {
		assertThat(parsedMod, notNullValue());
	}

	@Test
	public void testDefaultStateName() {
		assertThat(parsedMod.getNameOfState((byte) 1), is("dead"));
	}

	@Test
	public void testStatesNumber() {
		assertThat(parsedMod.getNumberOfStates(), is(2));
	}

	@Test
	public void testNumberOfRules() {
		assertThat(parsedMod.getRules().getRulesOfState((byte) 1).size(), is(1));
		assertThat(parsedMod.getRules().getRulesOfState((byte) 2).size(), is(2));
	}

	@Test
	public void testTypeOfRules() {
		assertThat(parsedMod.getRules().getRulesOfState((byte) 1).get(0).condition, instanceOf(NumberCondition.class));
		assertThat(parsedMod.getRules().getRulesOfState((byte) 2).get(0).condition, instanceOf(LowMinCondition.class));
		assertThat(parsedMod.getRules().getRulesOfState((byte) 2).get(1).condition, instanceOf(LowMaxCondition.class));
	}

	@Test
	public void testColorsOfStates() {
		assertThat(parsedMod.getColorOfState((byte) 1), is(Color.web("0x101010")));
		assertThat(parsedMod.getColorOfState((byte) 2), is(Color.ALICEBLUE));
	}
}

