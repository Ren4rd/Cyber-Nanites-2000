package org.cyberpredators.nanites;

/*
 * CyberNanites2000.java
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

import java.io.FileReader;
import java.io.IOException;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.util.Duration;

import org.cyberpredators.nanites.model.Game;
import org.cyberpredators.nanites.model.Mod;
import org.cyberpredators.nanites.model.NanitesGrid;
import org.cyberpredators.nanites.parser.ModFactoryException;
import org.cyberpredators.nanites.parser.YamlModFactory;
import org.cyberpredators.nanites.parser.YamlParser;
import org.cyberpredators.nanites.view.MainPane;

public class CyberNanites2000 extends Application {

	public static Timeline timeline = new Timeline();

	public static void main(String[] args) {
		System.out.println("Hello webseekers !");
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		try (FileReader fileReader = new FileReader("mods/gameOfLife.yaml")) {
			Mod mod = YamlModFactory.createMod(YamlParser.parse(fileReader));

			final byte living = 2;
			assert("living" == mod.getNameOfState((byte) 2));

			NanitesGrid grid = new NanitesGrid(300, 200);
			grid.initialize();
			grid.setStateOf(1, 1, living);
			grid.setStateOf(1, 2, living);
			grid.setStateOf(1, 0, living);

			grid.setStateOf(4, 1, living);
			grid.setStateOf(4, 2, living);
			grid.setStateOf(4, 0, living);
			grid.setStateOf(6, 0, living);
			grid.setStateOf(6, 1, living);
			grid.setStateOf(6, 2, living);
			grid.setStateOf(6, 3, living);

			Game game = new Game(grid, mod.getRules());

			MainPane mainPane = new MainPane(game);
			Scene scene = new Scene(mainPane, 800, 600);
			scene.addEventHandler(KeyEvent.KEY_RELEASED, event-> {
				switch (event.getCode()) {
				case RIGHT:
					game.nextState();
					break;
				case A:
					autoplay(game);
					break;
				case S:
					timeline.stop();
					break;
				default:
					break;
				}
			});
			primaryStage.setOnCloseRequest(event -> {
				timeline.stop();
			});
			primaryStage.setScene(scene);
			primaryStage.setTitle("CyberNanites2000");
			primaryStage.show();
		} catch (IOException | ModFactoryException e) {
			e.printStackTrace();
		}
	}

	private void autoplay(Game game) {
		timeline.stop();
		timeline = new Timeline();
		timeline.setCycleCount(Timeline.INDEFINITE);
		KeyFrame keyFrame = new KeyFrame(Duration.millis(50),
				e -> {
					game.nextState();
				});
		timeline.getKeyFrames().add(keyFrame);
		timeline.playFromStart();
	}
}
