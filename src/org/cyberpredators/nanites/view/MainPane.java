package org.cyberpredators.nanites.view;

/*
 * MainPane.java
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

import java.util.Observable;
import java.util.Observer;

import javafx.geometry.Insets;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

import org.cyberpredators.nanites.model.Game;

public class MainPane extends GridPane implements Observer {

	private final GridView gridView;
	private final Text turnCounter;

	public MainPane(Game game) {
		this.setPadding(new Insets(20, 20, 20, 20));
		game.addObserver(this);
		ScrollPane scrollPane = new ScrollPane();
		gridView = new GridView(game.getCurrentNanitesGrid());
		gridView.setOnMouseClicked(event -> {
			if (event.getButton() == MouseButton.PRIMARY)
				game.nextState();
		});
		scrollPane.setContent(gridView);
		this.add(scrollPane, 0, 0);
		turnCounter = new Text();
		this.add(turnCounter, 0, 1);
		update(game);
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		update((Game) arg0);
	}

	private void update(Game game) {
		gridView.printGrid(game.getCurrentNanitesGrid());
		turnCounter.setText("Turn " + game.getNumberOfTurns());
	}
}
