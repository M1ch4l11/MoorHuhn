package com.example.moorhuhn.Pom;


import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Duck extends Canvas {
    GraphicsContext gc;
    Timeline time;
    Scene scene;
    MathHelpful mat;
    int index,speed;
    Group root;
    Main main;
    ImageView view;
    ImageView view2;
//    MediaPlayer player;

    public Duck(Scene scene, Stage stage, int index, Group root, Main main, boolean stav)  {
        super(100,100);
        this.main = main;
        this.index = index;
        this.root = root;
        this.scene = scene;
        view = new ImageView("kacica.gif");
        view2 = new ImageView("kaciaPravo.gif");
        mat = new MathHelpful(stage);
        gc = getGraphicsContext2D();
        this.speed = mat.getSpeed();
        vykresli();
        if (stav) setOnMouseClicked(mouseEvent -> clickMouse());
        time  = new Timeline(new KeyFrame(Duration.millis(this.speed),event -> posun()));
        time.setCycleCount(Animation.INDEFINITE);
    }
    public void setMouseClik(){
        setOnMouseClicked(mouseEvent -> clickMouse());
    }

    public void vykresli(){
        gc.clearRect(0,0,getWidth(),getHeight());
        switch (index){
            case 1 -> {gc.drawImage(view.getImage(),0,0,100,100);}
            case 2 -> {gc.drawImage(view.getImage(),0,0,100,100);}
            case 3 -> {gc.drawImage(view2.getImage(),0,0,100,100);}
            case 4 -> {gc.drawImage(view2.getImage(),0,0,100,100);}
            case 5 -> {gc.drawImage(new Image("2.2-removebg-preview.png"),0,0,80,80);}
        }


    }
    public void clickMouse(){
        this.index=5;
        Main.score.setText("SCORE: " + (Labels.scoreNumber+=5));
    }

    public void posun(){
        switch (this.index) {
            case 1 -> {
                if (getLayoutX() <= scene.getWidth()) {
                    setLayoutX(getLayoutX() + 3);
                    vykresli();
                } else if (getLayoutX() > scene.getWidth()) {
                    time.stop();
                    Main.poc++;
                    root.getChildren().remove(this);
                }
            }
            case 2 -> {
                if (getLayoutY() > (this.getHeight()*-1)) {
                    setLayoutY(getLayoutY() - 3);
                    vykresli();
                } else {
                    time.stop();
                    Main.poc++;
                    this.root.getChildren().remove(this);
                }
                if (getLayoutX() <= scene.getWidth()) {
                    setLayoutX(getLayoutX() + 3);
                    vykresli();
                }
                else if (getLayoutX()>scene.getWidth()+this.getWidth()){
                    time.stop();
                    Main.poc++;
                    this.root.getChildren().remove(this);
                }
            }
            case 3 -> {
                if (getLayoutY() > (this.getHeight()*-1)) {
                    setLayoutY(getLayoutY() - 3);
                    vykresli();
                } else {
                    time.stop();
                    Main.poc++;
                    this.root.getChildren().remove(this);
                }
                if (getLayoutX() >= 0 - this.getWidth()) {
                    setLayoutX(getLayoutX() - 3);
                    vykresli();
                }
                else if (getLayoutX() < (this.getWidth()*-1)){
                    time.stop();
                    Main.poc++;
                    this.root.getChildren().remove(this);
                }
            }
            case 4 -> {
                if (getLayoutX() > (this.getWidth()*-1)) {
                    setLayoutX(getLayoutX() - 3);
                    vykresli();
                } else if (getLayoutX() < (scene.getWidth()-this.getWidth())) {
                    time.stop();
                    Main.poc++;
                    root.getChildren().remove(this);
                }
            }
            case 5 -> {
                if(getLayoutY() < scene.getHeight()+this.getHeight()){
                    setLayoutY(getLayoutY()+3);
                    vykresli();
                }
                else {
                    time.stop();
                    root.getChildren().remove(this);
                }
            }
        }
        if(Main.poc==5) {
            Main.poc=0;
            time.stop();
        }
    }


}
