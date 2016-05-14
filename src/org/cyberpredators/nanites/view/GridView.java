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

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseButton;
import javafx.scene.paint.Color;

import org.cyberpredators.nanites.model.Mod;
import org.cyberpredators.nanites.model.NanitesGrid;

public class GridView extends ScrollPane implements Initializable {

	private static final byte naniteWidth = 8;
	private static final byte naniteHeight = 8;

	@FXML private Canvas canvas;
	@FXML private GridViewContextMenu contextMenu;
	private HashMap<Byte, Color> colorMap;
	private NanitesGrid grid;

	public GridView() {
		final URL url = getClass().getResource("/org/cyberpredators/nanites/view/grid_view.fxml");
		final FXMLLoader fxmlLoader = new FXMLLoader(url, null);
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);
		try {
			fxmlLoader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle bundle) {
		contextMenu.setCallback(done -> printGrid());
		canvas.setOnMouseClicked(event -> {
			int x = (int) event.getX();
			int y = (int) event.getY();
			int gridX = (int) (x / naniteWidth);
			int gridY = (int) (y / naniteHeight);
			if (event.getButton() == MouseButton.SECONDARY) {
				contextMenu.setGridPosition(gridX, gridY);
				contextMenu.show(this, event.getScreenX(), event.getScreenY());
			} else {
				contextMenu.hide();
			}
		});
	}

	public void setNanitesGrid(NanitesGrid grid) {
		this.grid = grid;
		canvas.setWidth(grid.getWidth() * naniteWidth);
		canvas.setHeight(grid.getHeight() * naniteHeight);
		contextMenu.setNanitesGrid(grid);
		printGrid();
	}

	public void useMod(Mod mod) {
		contextMenu.useMod(mod);
		colorMap = mod.getColorMap();
	}

	private void printGrid() {
		final GraphicsContext graphicsContext = canvas.getGraphicsContext2D();
		for (int i = 0; i < grid.getWidth(); i++) {
			for (int j = 0; j < grid.getHeight(); j++) {
				graphicsContext.setFill(colorMap.get(grid.getStateOf(i, j)));
				graphicsContext.fillRect(i*naniteWidth, j*naniteHeight, naniteWidth, naniteHeight);
			}
		}
	}
}
