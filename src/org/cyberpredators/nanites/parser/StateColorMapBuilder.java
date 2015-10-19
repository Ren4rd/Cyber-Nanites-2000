package org.cyberpredators.nanites.parser;

/*
 * StateColorMapBuilder.java
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

import javafx.scene.paint.Color;

import org.cyberpredators.nanites.model.StateNameMap;

public class StateColorMapBuilder {

	private final HashMap<Byte, Color> stateColors = new HashMap<>();
	private final RandomColorGenerator colorGenerator = new RandomColorGenerator();

	public void put(byte state, Color color) {
		stateColors.put(state, color);
	}

	public void completeWithRandomColors(StateNameMap stateNames) {
		for (String stateName : stateNames.getNames())
			if (!stateColors.containsKey(stateNames.getStateOfName(stateName)))
				stateColors.put(stateNames.getStateOfName(stateName), colorGenerator.getNewColor());
	}

	public HashMap<Byte, Color> getStateColors() {
		return stateColors;
	}
}
