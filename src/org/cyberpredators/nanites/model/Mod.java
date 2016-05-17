package org.cyberpredators.nanites.model;

/*
 * Mod.java
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

import java.util.Collection;
import java.util.HashMap;

import javafx.scene.paint.Color;

import org.cyberpredators.nanites.model.rules.RulesSet;

public class Mod {

	private final RulesSet rules;
	private final StateNameMap stateNames;
	private final HashMap<Byte, Color> stateColors;

	public Mod(RulesSet rules, StateNameMap stateNames, HashMap<Byte, Color> stateColors) {
		this.rules = rules;
		this.stateNames = stateNames;
		this.stateColors = stateColors;
	}

	public RulesSet getRules() {
		return rules;
	}

	public String getNameOfState(Byte state) {
		return stateNames.getNameOfState(state);
	}

	public HashMap<Byte, Color> getColorMap() {
		return stateColors;
	}

	public Collection<Byte> getStates() {
		return stateNames.getStates();
	}
}
