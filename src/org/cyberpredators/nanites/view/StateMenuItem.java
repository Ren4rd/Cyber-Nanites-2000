package org.cyberpredators.nanites.view;

/*
 * StateMenuItem.java
 * Copyright (C) Remi Even 2016
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

import javafx.scene.control.MenuItem;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class StateMenuItem extends MenuItem {

	private final int RECTANGLE_SIZE = 20;
	private final int RECTANGLE_ARC_SIZE = 6;

	public StateMenuItem(String name, Color color) {
		super(name);
		Rectangle rectangle = new Rectangle(RECTANGLE_SIZE, RECTANGLE_SIZE, color);
		rectangle.setArcHeight(RECTANGLE_ARC_SIZE);
		rectangle.setArcWidth(RECTANGLE_ARC_SIZE);
		this.setGraphic(rectangle);
	}
}
