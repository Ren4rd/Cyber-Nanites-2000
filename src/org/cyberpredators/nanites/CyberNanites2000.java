package org.cyberpredators.nanites;

/*
 * CyberNanites2000.java
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

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import org.cyberpredators.nanites.view.MainPane;

public class CyberNanites2000 extends Application {

	public static void main(String[] args) {
		System.out.println("Hello webseekers !");
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		MainPane mainPane = new MainPane();
		Scene scene = new Scene(mainPane, 800, 600);
		primaryStage.setOnCloseRequest(event -> mainPane.quitApplication());
		primaryStage.setScene(scene);
		primaryStage.setTitle("CyberNanites2000");
		primaryStage.show();
	}
}
