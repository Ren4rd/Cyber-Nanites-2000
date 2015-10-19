package org.cyberpredators.nanites.parser;

/*
 * RandomColorGenerator.java
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

import javafx.scene.paint.Color;

public class RandomColorGenerator {

	// Used to get more evenly distributed values
	private static final double goldenRatioConjugate = 2 /( 1 + Math.sqrt(5));

	private static final double saturation = 0.7;
	private static final double brightness = 0.85;
	private double hue = Math.random();

	public Color getNewColor() {
		hue += goldenRatioConjugate;
		hue %= 1;
		return Color.hsb(hue * 256, saturation, brightness);
	}
}
