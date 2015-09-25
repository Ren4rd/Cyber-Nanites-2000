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
import java.util.Map;

import org.cyberpredators.nanites.model.Mod;
import org.cyberpredators.nanites.model.StateNameMap;
import org.cyberpredators.nanites.model.rules.AbstractRule;
import org.cyberpredators.nanites.model.rules.MaxNumberRule;
import org.cyberpredators.nanites.model.rules.MinNumberRule;
import org.cyberpredators.nanites.model.rules.NumberRule;
import org.cyberpredators.nanites.model.rules.RulesSet;

public class YamlModFactory {

	public static Mod createMod(Map<? extends String, ?> yaml) {
		List<?> yamlRules = (List<?>) yaml.get("rules");
		String defaultStateName = (String) yaml.get("defaultState");
		RulesSet rulesSet = new RulesSet();
		StateNameMap stateNames = new StateNameMap();
		stateNames.setDefaultStateName(defaultStateName);

		for (int i = 0; i < yamlRules.size(); i++) {
			@SuppressWarnings("unchecked")
			Map<? extends String, ?> yamlRule = (Map<? extends String, ?>) yamlRules.get(i);
			String cellStateName = (String) yamlRule.get("ifIs");
			stateNames.addIfNotPresent(cellStateName);
			String newStateName = (String) yamlRule.get("thenBecome");
			stateNames.addIfNotPresent(newStateName);
			rulesSet.addRuleToState(stateNames.getStateOfName(cellStateName), createRule(yamlRule, stateNames));
		}
		return new Mod(rulesSet, stateNames);
	}

	private static AbstractRule createRule(Map<? extends String, ?> yamlRule, StateNameMap stateNames) {
		if (yamlRule.containsKey("minimum"))
			return createMinNumberRule(yamlRule, stateNames);
		else if (yamlRule.containsKey("maximum"))
			return createMaxNumberRule(yamlRule, stateNames);
		else if (yamlRule.containsKey("number"))
			return createNumberRule(yamlRule, stateNames);
		return null; //TODO : throw custom exception
	}

	private static MinNumberRule createMinNumberRule(Map<? extends String, ?> yamlRule, StateNameMap stateNames) {
		int minimum = Integer.parseInt((String) yamlRule.get("minimum"));//TODO test without parsing (cast issue?)
		byte newState = stateNames.getStateOfName((String) yamlRule.get("thenBecome"));
		String neighborStateName = (String) yamlRule.get("neighborState");
		stateNames.addIfNotPresent(neighborStateName);
		byte neighborState = stateNames.getStateOfName(neighborStateName);
		return new MinNumberRule(newState, neighborState, minimum);
	}

	private static MaxNumberRule createMaxNumberRule(Map<? extends String, ?> yamlRule, StateNameMap stateNames) {
		int maximum = Integer.parseInt((String) yamlRule.get("maximum"));//TODO test without parsing (cast issue?)
		byte newState = stateNames.getStateOfName((String) yamlRule.get("thenBecome"));
		String neighborStateName = (String) yamlRule.get("neighborState");
		stateNames.addIfNotPresent(neighborStateName);
		byte neighborState = stateNames.getStateOfName(neighborStateName);
		return new MaxNumberRule(newState, neighborState, maximum);
	}

	private static NumberRule createNumberRule(Map<? extends String, ?> yamlRule, StateNameMap stateNames) {
		int number = Integer.parseInt((String) yamlRule.get("number"));//TODO test without parsing (cast issue?)
		byte newState = stateNames.getStateOfName((String) yamlRule.get("thenBecome"));
		String neighborStateName = (String) yamlRule.get("neighborState");
		stateNames.addIfNotPresent(neighborStateName);
		byte neighborState = stateNames.getStateOfName(neighborStateName);
		return new NumberRule(newState, neighborState, number);
	}
}
