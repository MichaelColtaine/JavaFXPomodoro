package com.javafx.pomodoro;

import java.net.URL;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class Clock extends Service<Void> {

	private String ALARM_SOUND_PATH = "/sounds/sound.mp3";
	private int startingTime, timeLeft;
	private MediaPlayer player;
	private BooleanProperty booleanProperty = new SimpleBooleanProperty();

	public Clock() {

	}

	public BooleanProperty isMuteButtonEnabled() {
		return booleanProperty;
	}

	private void muteButtonEnabled(boolean bol) {
		booleanProperty.set(bol);
	}

	@Override
	protected Task<Void> createTask() {
		return new Task<Void>() {
			@Override
			protected Void call() throws InterruptedException {
				for (int i = startingTime; i >= 0; i--) {
					if (i == 0) {
						startAlarm();
						muteButtonEnabled(false);
					} else {
						muteButtonEnabled(true);
					}
					timeLeft = i;
					updateMessage(convert(i));
					Thread.sleep(1000);
				}
				return null;
			}
		};
	}

	private String convert(int n) {
		int min = n / 60, sec = n - (min * 60);
		return String.format("%s : %s", convertNumber(min), convertNumber(sec));
	}

	private String convertNumber(int n) {
		if (n < 10) {
			return String.format("0%d", n);
		} else {
			return String.format("%d", n);
		}
	}

	private void startAlarm() {
		URL sound = getClass().getResource(ALARM_SOUND_PATH);
		Media media = new Media(sound.toString());
		player = new MediaPlayer(media);
		player.play();
	}

	public void mute() {
		if (player != null) {
			player.stop();
			muteButtonEnabled(true);
		}
	}

	public int getTimeLeft() {
		return timeLeft;
	}

	public void setTime(int time) {
		this.startingTime = time;
	}

}
