package com.javafx.pomodoro;

import javafx.application.Platform;
import javafx.beans.binding.BooleanExpression;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;

public class Controller {

	private Clock clock;
	private int currentTime = 1500;

	@FXML
	private Label time;
	@FXML
	private ToggleButton startBtn;
	@FXML
	private Button restartBtn, muteBtn;
	@FXML
	private MenuItem exitMenuItem, settingsMenuItem, restartMenuItem, startMenuItem;
	@FXML
	private ToggleGroup radioButtonsGroup;
	@FXML
	private RadioButton longRadioButton, shortRadioButton;

	@FXML
	private void onStart() {
		if (startBtn.getText().equals("_Start") || startBtn.getText().equals("Start")) {
			start();
		} else {
			if (clock != null) {
				stop();
			}
		}
	}

	@FXML
	private void onRestart() {
		if (clock != null) {
			restart();
		}
	}

	@FXML
	private void onExit() {
		boolean isTrue = new ConfirmationBox().show("Are you sure you want to leave?", "Confirm Leaving", "Yes", "No");
		if (isTrue) {
			Platform.exit();
		}
	}

	@FXML
	private void onMute() {
		if (clock != null) {
			clock.mute();
			restart();
		}
	}

	private void start() {
		startBtn.setText("Stop");
		clock = new Clock();
		clock.setTime(currentTime);
		clock.start();
		muteBtn.disableProperty().bind(BooleanExpression.booleanExpression(clock.isMuteButtonEnabled()));
		time.textProperty().bind(clock.messageProperty());

	}

	private void stop() {
		if (clock != null) {
			time.textProperty().unbind();
			currentTime = clock.getTimeLeft();
			startBtn.setText("Start");
			clock.cancel();
		}
	}

	private void restart() {
		clock.mute();
		clock.cancel();
		time.textProperty().unbind();
		radioButtonSelected();
		startBtn.setSelected(false);
		startBtn.setText("Start");
	}

	@FXML
	private void onSelectedRadioButotn() {
		stop();
		if (clock != null) {
			clock.mute();
		}
		radioButtonSelected();

	}

	private void radioButtonSelected() {
		if (longRadioButton.isSelected()) {
			time.setText("25 : 00");
			currentTime = 1500;
		} else {
			time.setText("05 : 00");
			currentTime = 300;
		}
	}

}
