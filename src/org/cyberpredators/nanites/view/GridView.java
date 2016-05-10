package org.cyberpredators.nanites.view;

/*
 * GridView.java
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

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import org.cyberpredators.nanites.model.NanitesGrid;

public class GridView extends Canvas {

	private static final byte naniteWidth = 4;
	private static final byte naniteHeight = 4;
	private final GraphicsContext graphicsContext;
	private HashMap<Byte, Color> colorMap;

	public GridView() {
		graphicsContext = this.getGraphicsContext2D();
	}

	public void setNanitesGrid(NanitesGrid nanitesGrid) {
		this.setWidth(nanitesGrid.getWidth() * naniteWidth);
		this.setHeight(nanitesGrid.getHeight() * naniteHeight);
	}

	public void setColorMap(HashMap<Byte, Color> colorMap) {
		this.colorMap = colorMap;
	}

	public synchronized void printGrid(NanitesGrid nanitesGrid) {
		for (int i = 0; i < nanitesGrid.getWidth(); i++) {
			for (int j = 0; j < nanitesGrid.getHeight(); j++) {
				graphicsContext.setFill(colorMap.get(nanitesGrid.getStateOf(i, j)));
				graphicsContext.fillRect(i*naniteWidth, j*naniteHeight, naniteWidth, naniteHeight);
			}
		}
	}
}
