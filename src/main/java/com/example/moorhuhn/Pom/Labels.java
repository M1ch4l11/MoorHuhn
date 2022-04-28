package com.example.moorhuhn.Pom;


import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;


public class Labels extends Canvas {
    Label esc;
    Label space;
    Label score;
    Label reload;
    String style = "-fx-font-family: 'Arial Black';-fx-font-size: 20;-fx-text-fill: rgba(0,0,0,0.71)";
    public static int scoreNumber = 0;
    Scene scene;

    public Labels(Scene scene){
        this.scene = scene;
        esc = new Label("ESC");
        space = new Label("press SPACE to start");
        score = new Label("SCORE: " + scoreNumber);
        reload = new Label("Reload press E");
    }

    public Label getScore(){
        score.setStyle(style);
        score.setLayoutX(scene.getWidth()+100);score.setLayoutY(10);
        score.toFront();
        return this.score;
    }

    public Label getReload(){
        reload.setStyle(style);
        reload.toFront();
        return reload;
    }

    public Label getLabelESC(){
        esc.setStyle(style);
        esc.setLayoutX(10);esc.setLayoutY(10);
        esc.toFront();
        return this.esc;
    }

    public Label getSpace(){
        space.setStyle(style);
        space.setLayoutX(scene.getWidth()/2-100);
        space.setLayoutY(50);
        return this.space;
    }
}
