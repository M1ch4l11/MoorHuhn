package com.example.moorhuhn.MainProcess;

import com.example.moorhuhn.Pom.Labels;
import com.example.moorhuhn.Pom.Main;
import javafx.animation.Animation;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URISyntaxException;

public class HelloApplication extends Application {
    public static boolean stav = true;
    public static int pocetNabojov = 10;
    @Override
    public void start(Stage stage)  {
        Group root = new Group();
        root.getChildren().add(new ImageView(new Image("First.png")));
        Scene scene = new Scene(root, 600,400 );
        // labels
        Labels labels = new Labels(scene);
        Label lblSpace = labels.getSpace();
        root.getChildren().add(lblSpace);
        root.getChildren().add(labels.getLabelESC());
        //scene
        scene.setOnKeyPressed(event -> {
            if(event.getCode()== KeyCode.ESCAPE) Platform.exit();
                if (event.getCode() == KeyCode.SPACE) {
                    while (stav){
                        Main main = null;
                        try {
                            main = new Main(stage, labels, root);
                        } catch (MalformedURLException e) {
                            e.printStackTrace();
                        }
                        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
                        stage.setX((screenBounds.getWidth() - stage.getWidth()) / 2);
                        stage.setY((screenBounds.getHeight() - stage.getHeight()) / 2);
                        root.getChildren().remove(lblSpace);
                        root.getChildren().add(main);
                        main.addNaboje(pocetNabojov);
                        stav = false;
                    }
            }
        });
        scene.setFill(Color.GRAY);
        stage.getIcons().add(new Image("ML5-removebg-preview.png"));
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }
}