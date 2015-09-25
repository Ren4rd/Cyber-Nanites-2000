package org.cyberpredators.nanites.model;

/*
 * Mod.java
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

import org.cyberpredators.nanites.model.rules.RulesSet;

public class Mod {

	private final RulesSet rules;
	private final StateNameMap stateNames;

	public Mod(RulesSet rules, StateNameMap stateNames) {
		this.rules = rules;
		this.stateNames = stateNames;
	}

	public RulesSet getRules() {
		return rules;
	}

	public String getNameOfState(Byte state) {
		return stateNames.getNameOfState(state);
	}

	public int getNumberOfStates() {
		return stateNames.getNumberOfStates();
	}
}