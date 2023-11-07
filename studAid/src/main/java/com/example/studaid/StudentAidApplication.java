package com.example.studaid;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.Objects;

public class StudentAidApplication extends Application {
    private double x;
    private double y;

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("fxmls/Login.fxml")));

        Scene scene = new Scene(root);
        root.setOnMousePressed((MouseEvent event) ->{
            x= event.getSceneX();
            y=event.getSceneY();
        });

        root.setOnMouseDragged((MouseEvent event)->{
            stage.setX(event.getScreenX()-x);
            stage.setY(event.getScreenY()-y);
            stage.setOpacity(.8);
        });

        root.setOnMouseReleased((MouseEvent event)->{
            stage.setOpacity(1);
        });
        stage.initStyle(StageStyle.TRANSPARENT);

        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}