package com.example.assignment06;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class HelloApplication extends Application {

    private Text statusText;
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Task Thread!");

        // UI elements
        statusText = new Text("Status: ");
        statusText.setWrappingWidth(280);
        statusText.setFont(Font.font("Arial", 14));
        Button startButton = new Button("Start Task");

        // button click
        startButton.setOnAction(e -> {
            startTask();
        });

        // layout
        VBox root = new VBox(10);
        root.setPadding(new Insets(20));
        root.getChildren().addAll(statusText, startButton);

        Scene scene = new Scene(root, 320, 240);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void startTask() {
        Runnable printA = new PrintChar('a', 3000);
        Runnable printB = new PrintChar('b', 3000);
        Runnable print100 = new PrintNum(5000);

        // Create threads
        Thread thread1 = new Thread(printA);
        Thread thread2 = new Thread(printB);
        Thread thread3 = new Thread(print100);

        // Start threads
        thread1.start();
        thread2.start();
        thread3.start();
    }

    class PrintChar implements Runnable {

        private char charToPrint;
        private int times;

        public PrintChar(char c, int t) {
            charToPrint = c;
            times = t;
        }

        @Override
        public void run() {
            for (int i = 0; i < times; i++) {
                Platform.runLater(() -> {
                    statusText.setText(statusText.getText() + charToPrint);
                });
            }
        }
    }

    class PrintNum implements Runnable {
        private int lastNum;

        public PrintNum(int n) {
            lastNum = n;
        }

        @Override
        public void run() {
            for (int i = 1; i <= lastNum; i++) {
                int currentNum = i;
                Platform.runLater(() -> {
                    statusText.setText(statusText.getText() + " " + currentNum);
                });
            }
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
