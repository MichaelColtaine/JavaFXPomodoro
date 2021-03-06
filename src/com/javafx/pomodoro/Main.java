package com.javafx.pomodoro;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("View.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

			primaryStage.setOnCloseRequest(e -> {
				boolean isTrue = new ConfirmationBox().show("Are you sure you want to leave?", "Confirm Leaving", "Yes",
						"No");
				if (isTrue) {
					Platform.exit();
				} else {
					e.consume();
				}
			});
			primaryStage.getIcons().add(new Image("/images/icon.png"));
			primaryStage.setTitle("Pomodoro clock");
			primaryStage.setScene(scene);
			primaryStage.setResizable(false);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
