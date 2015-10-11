package org.cyberpredators.nanites.parser;

/*
 * YamlModFactory.java
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

import java.util.List;

import org.cyberpredators.nanites.model.Mod;
import org.cyberpredators.nanites.model.StateNameMap;
import org.cyberpredators.nanites.model.rules.AbstractRule;
import org.cyberpredators.nanites.model.rules.HighMaxNumberRule;
import org.cyberpredators.nanites.model.rules.HighMinNumberRule;
import org.cyberpredators.nanites.model.rules.LowMaxNumberRule;
import org.cyberpredators.nanites.model.rules.LowMinNumberRule;
import org.cyberpredators.nanites.model.rules.NumberRule;
import org.cyberpredators.nanites.model.rules.RulesSet;

public class YamlModFactory {

	public static Mod createMod(YamlAdapter yaml) throws ModFactoryException {
		List<YamlAdapter> yamlRules = yaml.getListYamlOrThrow("rules", "No rules found");
		String defaultStateName = yaml.getStringOrThrow("defaultState", "No default state found");
		RulesSet rulesSet = new RulesSet();
		StateNameMap stateNames = new StateNameMap();
		stateNames.setDefaultStateName(defaultStateName);

		for (int i = 0; i < yamlRules.size(); i++) {
			YamlAdapter yamlRule = yamlRules.get(i);
			String cellStateName = yamlRule.getStringOrThrow("ifIs", "Cell state not found in rule " + i);
			stateNames.addIfNotPresent(cellStateName);
			String newStateName = yamlRule.getStringOrThrow("thenBecome", "New cell state not found in rule " + i);
			stateNames.addIfNotPresent(newStateName);
			rulesSet.addRuleToState(stateNames.getStateOfName(cellStateName), createRule(yamlRule, stateNames));
		}
		return new Mod(rulesSet, stateNames);
	}

	private static AbstractRule createRule(YamlAdapter yamlRule, StateNameMap stateNames) throws ModFactoryException {
		if (yamlRule.containsKey("minimum"))
			return createMinNumberRule(yamlRule, stateNames);
		else if (yamlRule.containsKey("maximum"))
			return createMaxNumberRule(yamlRule, stateNames);
		else if (yamlRule.containsKey("number"))
			return createNumberRule(yamlRule, stateNames);
		throw new ModFactoryException("Unknown type of rule, or incomplete rule");
	}

	private static AbstractRule createMinNumberRule(YamlAdapter yamlRule, StateNameMap stateNames) throws ModFactoryException {
		int minimum = yamlRule.getIntOrThrow("minimum", "No minimum value found");
		byte newState = stateNames.getStateOfName(yamlRule.getStringOrThrow("thenBecome", "New cell state not found"));
		String neighborStateName = yamlRule.getStringOrThrow("neighborState", "Neighborhood cell state not found");
		stateNames.addIfNotPresent(neighborStateName);
		byte neighborState = stateNames.getStateOfName(neighborStateName);
		if (minimum <= 4)
			return new LowMinNumberRule(newState, neighborState, minimum);
		else
			return new HighMinNumberRule(newState, neighborState, minimum);
	}

	private static AbstractRule createMaxNumberRule(YamlAdapter yamlRule, StateNameMap stateNames) throws ModFactoryException {
		int maximum = yamlRule.getIntOrThrow("maximum", "No maximum value found");
		byte newState = stateNames.getStateOfName(yamlRule.getStringOrThrow("thenBecome", "New cell state not found"));
		String neighborStateName = yamlRule.getStringOrThrow("neighborState", "Neighborhood cell state not found");
		stateNames.addIfNotPresent(neighborStateName);
		byte neighborState = stateNames.getStateOfName(neighborStateName);
		if (maximum <= 4)
			return new LowMaxNumberRule(newState, neighborState, maximum);
		else
			return new HighMaxNumberRule(newState, neighborState, maximum);
	}

	private static NumberRule createNumberRule(YamlAdapter yamlRule, StateNameMap stateNames) throws ModFactoryException {
		int number = yamlRule.getIntOrThrow("number", "No number value found");
		byte newState = stateNames.getStateOfName(yamlRule.getStringOrThrow("thenBecome", "New cell state not found"));
		String neighborStateName = yamlRule.getStringOrThrow("neighborState", "Neighborhood cell state not found");
		stateNames.addIfNotPresent(neighborStateName);
		byte neighborState = stateNames.getStateOfName(neighborStateName);
		return new NumberRule(newState, neighborState, number);
	}
}
