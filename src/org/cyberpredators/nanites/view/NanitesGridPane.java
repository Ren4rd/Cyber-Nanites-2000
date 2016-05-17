package org.cyberpredators.nanites.view;

/*
 * NanitesGridPane.java
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

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;

import org.cyberpredators.nanites.model.Game;
import org.cyberpredators.nanites.model.Mod;

public class NanitesGridPane extends GridPane implements Observer, Initializable {

	@FXML private Button autoPlayButton;
	@FXML private Button stopAutoPlayButton;
	@FXML private Button nextButton;
	@FXML private GridView gridView;
	@FXML private Label turnCounter;
	@FXML private Slider zoomSlider;

	private final Timeline timeline;
	private final SimpleBooleanProperty isPlaying = new SimpleBooleanProperty(false);
	private final SimpleObjectProperty<Game> game = new SimpleObjectProperty<>();

	public NanitesGridPane() {
		this.timeline = new Timeline();
		final URL url = getClass().getResource("/org/cyberpredators/nanites/view/nanites_grid_pane.fxml");
		final FXMLLoader fxmlLoader = new FXMLLoader(url, null);
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);
		try {
			fxmlLoader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		gridView.zoomFactorProperty().bind(zoomSlider.valueProperty());
	}

	@Override
	public void initialize(URL location, ResourceBundle bundle) {
		BooleanBinding gameOff = game.isNull();
		autoPlayButton.disableProperty().bind(isPlaying.or(gameOff));
		stopAutoPlayButton.disableProperty().bind(isPlaying.not().or(gameOff));
		nextButton.disableProperty().bind(isPlaying.or(gameOff));
	}

	public void setGame(Game game) {
		this.game.set(game);
		game.addObserver(this);
		gridView.setNanitesGrid(game.getCurrentNanitesGrid());
		update();
	}

	public void useMod(Mod mod) {
		gridView.useMod(mod);
	}

	@Override
	public void update(Observable observable, Object arg) {
		update();
	}

	private void update() {
		gridView.setNanitesGrid(game.get().getCurrentNanitesGrid());
		turnCounter.setText("Turn " + game.get().getNumberOfTurns());
	}

	@FXML
	private void next() {
		game.get().nextState();
	}

	@FXML
	private void autoplay() {
		timeline.stop();
		timeline.getKeyFrames().clear();
		timeline.setCycleCount(Timeline.INDEFINITE);
		KeyFrame keyFrame = new KeyFrame(Duration.millis(100), e -> game.get().nextState());
		timeline.getKeyFrames().add(keyFrame);
		timeline.playFromStart();
		isPlaying.setValue(true);
	}

	@FXML
	public void stopAutoplay() {
		timeline.stop();
		isPlaying.setValue(false);
	}
}
