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

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

import org.cyberpredators.nanites.model.Game;
import org.cyberpredators.nanites.model.Mod;
import org.cyberpredators.nanites.model.NanitesGrid;
import org.cyberpredators.nanites.parser.ModFactoryException;
import org.cyberpredators.nanites.parser.YamlModFactory;
import org.cyberpredators.nanites.parser.YamlParser;

public class MainPane extends BorderPane {

	@FXML private NanitesGridPane nanitesGridPane;

	public MainPane() {
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

	public void setGame(Game game) {
		//TODO
		nanitesGridPane.setGame(game);
	}

	@FXML
	private void loadMod() {
		FileChooser fileChooser = new FileChooser();
		fileChooser.getExtensionFilters().add(new ExtensionFilter("Yaml file", "*.yml", "*.yaml"));
		fileChooser.setTitle("Choose a mod to load");
		final File selectedFile= fileChooser.showOpenDialog(null);
		if (selectedFile != null)
			loadModFrom(selectedFile);
	}

	private void loadModFrom(File modFile) {
		try (FileReader fileReader = new FileReader(modFile)) {
			Mod mod = YamlModFactory.createMod(YamlParser.parse(fileReader));

			final byte living = 2;

			NanitesGrid grid = new NanitesGrid(300, 200);
			grid.initialize();
			grid.setStateOf(3, 0, living);
			grid.setStateOf(3, 1, living);
			grid.setStateOf(3, 2, living);
			grid.setStateOf(2, 2, living);
			grid.setStateOf(1, 1, living);

			Game game = new Game(grid, mod.getRules());

			nanitesGridPane.useMod(mod);
			nanitesGridPane.setGame(game);
		} catch (IOException | ModFactoryException e) {
			e.printStackTrace();
		}
	}

	@FXML
	public void quitApplication() {
		nanitesGridPane.stopAutoplay();
		Platform.exit();
	}
}
