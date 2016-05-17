package org.cyberpredators.nanites.view;

/*
 * GridViewContextMenu.java
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

import java.util.function.Consumer;

import javafx.scene.control.ContextMenu;
import javafx.scene.paint.Color;

import org.cyberpredators.nanites.model.Mod;
import org.cyberpredators.nanites.model.NanitesGrid;

public class GridViewContextMenu extends ContextMenu {

	private NanitesGrid grid;
	private int gridX;
	private int gridY;
	private Consumer<Void> callback;

	public void setGridPosition(int gridX, int gridY) {
		this.gridX = gridX;
		this.gridY = gridY;
	}

	public void setNanitesGrid(NanitesGrid grid) {
		this.grid = grid;
	}

	public void useMod(Mod mod) {
		this.getItems().clear();
		for (byte state : mod.getStates())
			addStateMenuItemFor(mod, state);
	}

	private void addStateMenuItemFor(Mod mod, byte state) {
		String name = mod.getNameOfState(state);
		Color color = mod.getColorMap().get(state);
		StateMenuItem stateMenuItem = new StateMenuItem(name, color);
		stateMenuItem.setOnAction(event -> {
			grid.setStateOf(gridX, gridY, state);
			callback.accept(null);
		});
		this.getItems().add(stateMenuItem);
	}

	public void setCallback(Consumer<Void> callback) {
		this.callback = callback;
	}
}
