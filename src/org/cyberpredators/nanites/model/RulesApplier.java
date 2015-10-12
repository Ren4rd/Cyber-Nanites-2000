package org.cyberpredators.nanites.model;

/*
 * RulesApplier.java
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

import org.cyberpredators.nanites.model.rules.Rule;
import org.cyberpredators.nanites.model.rules.RulesSet;

public class RulesApplier {

	private final RulesSet rules;

	public RulesApplier(RulesSet rules) {
		this.rules = rules;
	}

	public void applyRulesTo(NanitesGrid currentGrid, NanitesGrid bufferGrid) {
		int width = currentGrid.getWidth();
		int height = currentGrid.getHeight();
		for (int x = 0; x < width; x++)
			for (int y = 0; y < height; y++)
				applyRulesToNanite(currentGrid, bufferGrid, x, y);
	}

	private void applyRulesToNanite(NanitesGrid currentGrid, NanitesGrid bufferGrid, int x, int y) {
		List<Rule> rulesForState = rules.getRulesOfState(currentGrid.getStateOf(x, y));
		bufferGrid.setStateOf(x, y, currentGrid.getStateOf(x, y));
		if (rulesForState == null)
			return;
		for (Rule rule: rulesForState)
			if (rule.canBeApplied(currentGrid.getNeighborhoodOf(x, y))) {
				bufferGrid.setStateOf(x, y, rule.newState);
				return;
			}
	}

}
