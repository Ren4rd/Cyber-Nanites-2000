package org.cyberpredators.nanites.model.rules;

/*
 * HighMinNumberRule.java
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

public class HighMinNumberRule extends AbstractRule {

	private final byte neighborsRequiredState;
	private final int requiredNumber;

	public HighMinNumberRule(byte newState, byte neighborsRequiredState, int requiredNumber) {
		super(newState);
		this.neighborsRequiredState = neighborsRequiredState;
		this.requiredNumber = requiredNumber;
	}

	@Override
	public boolean canBeApplied(List<Byte> neighborhood) {
		int n = 8;
		for (byte neighborState: neighborhood) {
			if (neighborState != neighborsRequiredState)
				n--;
			if (n < requiredNumber)
				return false;
		}
		return true;
	}
}
