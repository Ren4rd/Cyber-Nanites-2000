package org.cyberpredators.nanites.parser;

/*
 * YamlConditionFactory.java
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

import java.util.ArrayList;
import java.util.List;

import org.cyberpredators.nanites.model.StateNameMap;
import org.cyberpredators.nanites.model.rules.Condition;
import org.cyberpredators.nanites.model.rules.Conjonction;
import org.cyberpredators.nanites.model.rules.Disjonction;
import org.cyberpredators.nanites.model.rules.HighMaxCondition;
import org.cyberpredators.nanites.model.rules.HighMinCondition;
import org.cyberpredators.nanites.model.rules.LowMaxCondition;
import org.cyberpredators.nanites.model.rules.LowMinCondition;
import org.cyberpredators.nanites.model.rules.NumberCondition;

public class YamlConditionFactory {

	public static Condition createCondition(YamlAdapter yamlCondition, StateNameMap stateNames) throws ModFactoryException {
		if (yamlCondition.containsKey("minimum"))
			return createMinCondition(yamlCondition, stateNames);
		if (yamlCondition.containsKey("maximum"))
			return createMaxCondition(yamlCondition, stateNames);
		if (yamlCondition.containsKey("number"))
			return createNumberCondition(yamlCondition, stateNames);
		if (yamlCondition.containsKey("verifiesAll"))
			return createConjonction(yamlCondition, stateNames);
		if (yamlCondition.containsKey("verifiesOne"))
			return createDisjonction(yamlCondition, stateNames);
		else
			return createTrue();
	}

	private static Condition createMinCondition(YamlAdapter yamlCondition, StateNameMap stateNames) throws ModFactoryException {
		int minimum = yamlCondition.getIntOrThrow("minimum", "No minimum value found");
		String neighborStateName = yamlCondition.getStringOrThrow("neighborState", "Neighborhood cell state not found");
		stateNames.addIfNotPresent(neighborStateName);
		byte neighborState = stateNames.getStateOfName(neighborStateName);
		if (minimum <= 4)
			return new LowMinCondition(neighborState, minimum);
		else
			return new HighMinCondition(neighborState, minimum);
	}

	private static Condition createMaxCondition(YamlAdapter yamlCondition, StateNameMap stateNames) throws ModFactoryException {
		int maximum = yamlCondition.getIntOrThrow("maximum", "No maximum value found");
		String neighborStateName = yamlCondition.getStringOrThrow("neighborState", "Neighborhood cell state not found");
		stateNames.addIfNotPresent(neighborStateName);
		byte neighborState = stateNames.getStateOfName(neighborStateName);
		if (maximum <= 4)
			return new LowMaxCondition(neighborState, maximum);
		else
			return new HighMaxCondition(neighborState, maximum);
	}

	private static NumberCondition createNumberCondition(YamlAdapter yamlCondition, StateNameMap stateNames) throws ModFactoryException {
		int number = yamlCondition.getIntOrThrow("number", "No number value found");
		String neighborStateName = yamlCondition.getStringOrThrow("neighborState", "Neighborhood cell state not found");
		stateNames.addIfNotPresent(neighborStateName);
		byte neighborState = stateNames.getStateOfName(neighborStateName);
		return new NumberCondition(neighborState, number);
	}

	private static Condition createConjonction(YamlAdapter yamlCondition, StateNameMap stateNames) throws ModFactoryException {
		List<Condition> conditions = new ArrayList<>();
		for (YamlAdapter yamlSubCondition : yamlCondition.getListYamlOrThrow("verifiesAll", "No subcondition found in conjonction"))
			conditions.add(createCondition(yamlSubCondition, stateNames));
		return new Conjonction(conditions);
	}

	private static Condition createDisjonction(YamlAdapter yamlCondition, StateNameMap stateNames) throws ModFactoryException {
		List<Condition> conditions = new ArrayList<>();
		for (YamlAdapter yamlSubCondition : yamlCondition.getListYamlOrThrow("verifiesOne", "No subcondition found in conjonction"))
			conditions.add(createCondition(yamlSubCondition, stateNames));
		return new Disjonction(conditions);
	}

	private static Condition createTrue() {
		return (neighborhood -> true);
	}
}
