package org.cyberpredators.nanites.model;

/*
 * StateNameMap.java
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

import java.util.HashMap;
import java.util.Map;

public class StateNameMap {

	private final Map<Byte, String> stateNames;
	private byte lastState;

	public StateNameMap() {
		stateNames = new HashMap<Byte, String>();
		lastState = 1;
	}

	public void setDefaultStateName(String name) {
		stateNames.put((byte) 1, name);
	}

	public String getNameOfState(byte state) {
		return stateNames.get(state);
	}

	public void addIfNotPresent(String stateName) {
		if (!stateNames.containsValue(stateName))
			add(stateName);
	}

	private void add(String stateName) {
		stateNames.put(++lastState, stateName);
	}

	public int getNumberOfStates() {
		return stateNames.size();
	}
}
