package com.jakubku.concurrency.concurrency;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.concurrent.ScheduledService;
import javafx.concurrent.Task;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Main extends Application {
    Button startBtn = new Button("Start");
    Button resetBtn = new Button("Reset");
    Button cancelBtn = new Button("Cancel");
    Button exitBtn = new Button("Exit");
    boolean onceStarted = false;
    ScheduledService<ObservableList<Integer>> service = new ScheduledService<>(){
        @Override
        protected Task<ObservableList<Integer>> createTask() {
            return new EvenNumTask(1, 20, 100);
        }
    };
    @Override
    public void start(Stage stage) {
        service.setPeriod(Duration.seconds(5));
        startBtn.setOnAction(
                e ->{
                    if(onceStarted){
                        service.restart();
                    } else {
                        service.start();
                        onceStarted = true;
                        startBtn.setText("Restart");
                    }
                });
        cancelBtn.setOnAction(e -> service.cancel());
        resetBtn.setOnAction(e -> service.reset());
        exitBtn.setOnAction(e -> stage.close());

        GridPane pane = new WorkerUI(service);
        HBox box = new HBox(5, startBtn, resetBtn, cancelBtn, exitBtn);

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
