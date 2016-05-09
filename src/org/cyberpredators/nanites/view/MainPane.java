package org.cyberpredators.nanites.view;

/*
 * MainPane.java
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
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.GridPane;

import org.cyberpredators.nanites.model.Game;

public class MainPane extends GridPane implements Observer, Initializable {

	@FXML private GridView gridView;
	@FXML private Label turnCounter;

	private final Game game;

	public MainPane(Game game) {
		this.game = game;
		final URL url = getClass().getResource("/org/cyberpredators/nanites/view/main_pane.fxml");
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
		game.addObserver(this);
		gridView.setOnMouseClicked(event -> {
			if (event.getButton() == MouseButton.PRIMARY)
				game.nextState();
		});
		gridView.setNanitesGrid(game.getCurrentNanitesGrid());
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
