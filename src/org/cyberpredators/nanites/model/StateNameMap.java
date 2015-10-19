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
import java.util.Map.Entry;
import java.util.Set;

public class StateNameMap {

	private final Map<String, Byte> stateNames;
	private byte lastState;

	public StateNameMap() {
		stateNames = new HashMap<String, Byte>();
		lastState = 1;
	}

	public void setDefaultStateName(String name) {
		stateNames.put(name, (byte) 1);
	}

	public String getNameOfState(byte state) {
		for (Entry<String, Byte> entry : stateNames.entrySet())
			if (entry.getValue() == state)
				return entry.getKey();
		return null;
	}

	public void addIfNotPresent(String stateName) {
		if (!stateNames.containsKey(stateName))
			add(stateName);
	}

	private void add(String stateName) {
		stateNames.put(stateName, ++lastState);
	}

	public byte getStateOfName(String name) {
		return stateNames.get(name);
	}

	public int getNumberOfStates() {
		return stateNames.size();
	}

	public Set<String> getNames() {
		return stateNames.keySet();
	}
}
