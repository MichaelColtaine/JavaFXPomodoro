package com.javafx.pomodoro;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class ConfirmationBox {

	private boolean isTrue;
	private final Button yesBtn, noBtn;
	private final Label label;
	private final Stage stage;
	private final VBox root;
	private final Scene scene;
	private final HBox hBox;

	public ConfirmationBox() {
		this.stage = new Stage();
		this.stage.initStyle(StageStyle.UTILITY);
		this.stage.setMinWidth(250);
		this.stage.setMinHeight(150);
		this.stage.initModality(Modality.APPLICATION_MODAL);

		this.yesBtn = new Button();
		this.yesBtn.setPrefSize(90, 26);
		this.yesBtn.setOnAction(e -> {
			isTrue = true;
			stage.close();
		});
		this.noBtn = new Button();
		this.noBtn.setPrefSize(90, 26);
		this.noBtn.setOnAction(e -> {
			isTrue = false;
			stage.close();
		});

		this.label = new Label();

		this.hBox = new HBox(10);
		this.hBox.getChildren().addAll(yesBtn, noBtn);
		this.hBox.setAlignment(Pos.CENTER);

		this.root = new VBox(20);
		this.root.setAlignment(Pos.CENTER);
		this.root.getChildren().addAll(label, hBox);
		root.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		this.scene = new Scene(root, 100, 100);

	}

	public boolean show(String message, String title, String yes, String no) {
		this.label.setText(message);
		this.yesBtn.setText(yes);
		this.noBtn.setText(no);
		this.stage.setScene(scene);
		this.stage.setTitle(title);
		this.stage.showAndWait();
		return isTrue;
	}

}
