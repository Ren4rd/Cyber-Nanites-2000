package org.cyberpredators.nanites.parser;

/*
 * YamlModBuilder.java
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

import java.util.HashMap;
import java.util.List;

import javafx.scene.paint.Color;

import org.cyberpredators.nanites.model.Mod;
import org.cyberpredators.nanites.model.StateNameMap;
import org.cyberpredators.nanites.model.rules.Condition;
import org.cyberpredators.nanites.model.rules.Rule;
import org.cyberpredators.nanites.model.rules.RulesSet;

public class YamlModBuilder {

	private final RulesSet rulesSet = new RulesSet();
	private final StateNameMap stateNames = new StateNameMap();
	private final HashMap<Byte, Color> stateColors;

	public YamlModBuilder(YamlAdapter yaml) throws ModFactoryException {
		String defaultStateName = yaml.getStringOrThrow("defaultState", "No default state found");
		stateNames.setDefaultStateName(defaultStateName);

		List<YamlAdapter> yamlRules = yaml.getListYamlOrThrow("rules", "No rules found");
		for (int i = 0; i < yamlRules.size(); i++)
			addRule(yamlRules.get(i));

		StateColorMapBuilder stateColorBuilder = new StateColorMapBuilder();
		setGivenColorsOf(yaml, stateColorBuilder);
		stateColorBuilder.completeWithRandomColors(stateNames);
		stateColors = stateColorBuilder.getStateColors();
	}

	private void setGivenColorsOf(YamlAdapter yaml, StateColorMapBuilder stateColorBuilder) {
		try {
			YamlAdapter yamlStateColors = yaml.getYamlOrThrow("colors", "No colors found.");
			for (String stateName : stateNames.getNames())
				setColorIfGiven(stateColorBuilder, yamlStateColors, stateName);
		} catch (ModFactoryException e) {
			System.out.println(e.getMessage());
		}
	}

	private void setColorIfGiven(StateColorMapBuilder stateColorBuilder, YamlAdapter yamlStateColors, String stateName) {
		byte state = stateNames.getStateOfName(stateName);
		try {
			String htmlColor = yamlStateColors.getStringOrThrow(stateName, "No color specified for state " + stateName);
			stateColorBuilder.put(state, Color.web(htmlColor));
		} catch (ModFactoryException e) {
			System.out.println(e.getMessage());
		}
	}

	private void addRule(YamlAdapter yamlRule) throws ModFactoryException {
		String cellStateName = yamlRule.getStringOrThrow("ifIs", "There was a rule without a cell state.");
		stateNames.addIfNotPresent(cellStateName);
		String newStateName = yamlRule.getStringOrThrow("thenBecome", "There was a rule without a new cell state.");
		stateNames.addIfNotPresent(newStateName);
		rulesSet.addRuleToState(stateNames.getStateOfName(cellStateName), createRule(yamlRule));
	}

	private Rule createRule(YamlAdapter yamlRule) throws ModFactoryException {
		Condition condition = YamlConditionFactory.createCondition(yamlRule, stateNames);
		byte newState = stateNames.getStateOfName(yamlRule.getStringOrThrow("thenBecome", "New cell state not found"));
		return new Rule(newState, condition);
	}

	public Mod getMod() {
		return new Mod(rulesSet, stateNames, stateColors);
	}
}
