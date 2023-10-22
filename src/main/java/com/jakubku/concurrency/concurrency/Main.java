package com.jakubku.concurrency.concurrency;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Main extends Application {
    Button startBtn = new Button("Start");
    Button cancelBtn = new Button("Cancel");
    Button exitBtn = new Button("Exit");

    EvenNumTask task = new EvenNumTask(1, 20, 1000);
    @Override
    public void start(Stage stage) {
        startBtn.setOnAction(
                e ->{
                    Thread bgThread = new Thread(task);
                    bgThread.setDaemon(true);
                    bgThread.start();
                });
        cancelBtn.setOnAction(e -> task.cancel());
        exitBtn.setOnAction(e -> stage.close());

        GridPane pane = new WorkerUI(task);
        HBox box = new HBox(5, startBtn, cancelBtn, exitBtn);

        BorderPane root = new BorderPane();
        root.setCenter(pane);
        root.setBottom(box);
        Scene scene = new Scene(root, 500, 450);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
