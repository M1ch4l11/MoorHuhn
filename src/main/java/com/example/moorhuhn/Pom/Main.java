package com.example.moorhuhn.Pom;


import com.example.moorhuhn.MainProcess.HelloApplication;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.ImageCursor;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.net.MalformedURLException;
import java.util.LinkedList;

public class Main extends Canvas {
    Stage stage;
    Labels labels;
    Group root;
    Timeline tm;
    int index = 1;
    public static int poc = 1;
    public static boolean mozeNemoze = true;
    public static boolean stavGun = true;
    int cyclePoc = 1;
    MathHelpful matClass;
    Image kur;
    public static Label score;
    //Naboje
    HBox hbo;
    LinkedList<Naboj> naboje;
    LinkedList<Duck> kacice;
    MediaPlayer media;
    MediaPlayer reload;
    MediaPlayer playerGun;
    public Main(Stage stage,Labels labels,Group root) throws MalformedURLException {
        File mediaFile2 = new File("src/main/resources/music/Reload.mp3");
        Media mdRel = new Media(mediaFile2.toURI().toURL().toString());
        reload = new MediaPlayer(mdRel);
        File mediaFile = new File("src/main/resources/music/BackSound.mp3");
        Media md = new Media(mediaFile.toURI().toURL().toString());
        media = new MediaPlayer(md);
        media.setCycleCount(MediaPlayer.INDEFINITE);
        media.play();
        File mediaFileGun = new File("src/main/resources/music/Gun.wav");
        Media mdG = new Media(mediaFileGun.toURI().toURL().toString());
        playerGun = new MediaPlayer(mdG);
        //---------------
        this.root = root;
        this.labels = labels;
        this.stage = stage;
        matClass = new MathHelpful(stage);
        kacice = new LinkedList<>();
        kur = new Image("kurzor.png");
        //-------------------
        stage.getScene().setCursor(new ImageCursor(kur));
        stage.getScene().setOnMouseClicked(event -> {

                if(HelloApplication.pocetNabojov==1){
                    addReload();
                    this.setOnMouseClicked(null);
                    Main.mozeNemoze=false;
                    reload();
                }else {
                    playerGun.stop();
                    playerGun.play();
                    HelloApplication.pocetNabojov--;
                    addNaboje(HelloApplication.pocetNabojov);
                }
        });
        stage.setWidth(900);
        stage.setHeight(600);
        root.getChildren().add(new ImageView(new Image("Background.jpg")));
        score = labels.getScore();
        root.getChildren().add(score);
        tm = new Timeline(new KeyFrame(Duration.seconds(2), event -> {
            try {
                addNewSle();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }));
        tm.setCycleCount(Animation.INDEFINITE);
        tm.play();
    }

    public void reload(){
        setFocusTraversable(true);
        setOnKeyPressed(event -> {
            if(event.getCode()== KeyCode.E){
                reload.play();
                HelloApplication.pocetNabojov=10;
                mozeNemoze=true;
                addNaboje(HelloApplication.pocetNabojov);
                for (int i = 0; i < root.getChildren().size(); i++) {
                    Node obj = root.getChildren().get(i);
                    if(obj instanceof Duck)((Duck) obj).setMouseClik();
                }
            }
        });
    }



    public void addNaboje(int pocet){
        for (int i = 0; i < root.getChildren().size(); i++) {
            Node obj = root.getChildren().get(i);
            if(obj instanceof HBox)root.getChildren().remove(obj);
        }
        hbo = new HBox(5);
        hbo.setLayoutX(10);
        hbo.setLayoutY(stage.getHeight()-90);
        naboje = new LinkedList<Naboj>();
        for (int i = 0; i < pocet; i++) {
            naboje.add(new Naboj());
        }
        for (Naboj nab: naboje) {
            hbo.getChildren().add(nab);
        }
        hbo.toFront();
        root.getChildren().add(hbo);
    }

    public void addReload(){
        for (int i = 0; i < root.getChildren().size(); i++) {
            Node obj = root.getChildren().get(i);
            if(obj instanceof HBox)root.getChildren().remove(obj);
        }
        hbo = new HBox();
        hbo.setLayoutX(10);
        hbo.setLayoutY(stage.getHeight()-90);
        hbo.getChildren().add(labels.getReload());
        root.getChildren().add(hbo);
    }


    public void addNewSle() throws MalformedURLException {
        if(cyclePoc==60){
            System.gc();
            stage.getScene().setOnMouseClicked(null);
            tm.stop();
            root.getChildren().clear();
            root.getChildren().add(new ImageView(new Image("End.png")));
            Label label = labels.getScore();
            stage.getScene().setOnKeyPressed(key -> {
                if(key.getCode()==KeyCode.ESCAPE)System.exit(2);
            });
            label.setLayoutX(stage.getScene().getWidth()/2-(label.getWidth()/2));
            label.setLayoutY(stage.getScene().getHeight()/2-(label.getHeight()+30));
            Button restart = new Button("Restart");
            restart.setOnAction(eventA -> {
                root.getChildren().clear();
                root.getChildren().remove(restart);
                cyclePoc=1;
                Labels.scoreNumber=0;
                root.getChildren().add(new ImageView(new Image("Background.jpg")));
                label.setText("SCORE: " + labels.scoreNumber);
                label.setLayoutX(stage.getScene().getWidth()-label.getWidth()-40);label.setLayoutY(10);
                root.getChildren().addAll(labels.getLabelESC(),label);
                addNaboje(10);
                stage.getScene().setOnKeyPressed(key -> {
                    if(key.getCode()==KeyCode.ESCAPE)System.exit(2);
                });
                stage.getScene().setOnMouseClicked(eventM -> {
                    if(HelloApplication.pocetNabojov==1){
                        addReload();
                        this.setOnMouseClicked(null);
                        Main.mozeNemoze=false;
                        reload();
                        stage.getScene().setOnKeyPressed(event -> {
                            {
                                if(event.getCode()== KeyCode.E){
                                    reload.play();
                                    HelloApplication.pocetNabojov=10;
                                    mozeNemoze=true;
                                    addNaboje(HelloApplication.pocetNabojov);
                                    for (int i = 0; i < root.getChildren().size(); i++) {
                                        Node obj = root.getChildren().get(i);
                                        if(obj instanceof Duck)((Duck) obj).setMouseClik();
                                    }
                                }
                            }
                        });
                    }else {
                        playerGun.stop();
                        playerGun.play();
                        HelloApplication.pocetNabojov--;
                        addNaboje(HelloApplication.pocetNabojov);
                    }
                });

                tm.play();
                System.gc();
                return;
            });
            restart.setStyle(label.getStyle());
            restart.setLayoutX(stage.getScene().getWidth()/2-55);
            restart.setLayoutY(stage.getScene().getHeight()/2);
            root.getChildren().addAll(label,restart);
            stage.show();
            return;
        } else cyclePoc++;
        System.gc();
        boolean stav = true;
        for (int i = 0; i < root.getChildren().size(); i++) {
            Node obj = root.getChildren().get(i);
            if(obj instanceof Duck)stav=false;
        }
        if(stav){
            kacice.clear();
            switch (index){
                case 1 -> {
                    for (int i = 0; i < 3; i++) {
                        Duck hn = new Duck(this.stage.getScene(),this.stage,this.index,this.root,this,mozeNemoze);
                        hn.setLayoutX(0-hn.getWidth());hn.setLayoutY(matClass.getMath(this.index));
                        kacice.add(hn);
                    }
                    for (int i = 0; i < 3; i++) {
                        Duck hn2 = new Duck(this.stage.getScene(),this.stage, 2,this.root,this,mozeNemoze);
                        hn2.setLayoutX(matClass.getMath(2));hn2.setLayoutY(stage.getHeight()+hn2.getHeight());
                        kacice.add(hn2);
                    }
                    index = 2;
                }
                case 2 -> {
                    for (int i = 0; i < 3; i++) {
                        Duck hn3 = new Duck(this.stage.getScene(), this.stage, 3,this.root,this,mozeNemoze);
                        hn3.setLayoutX(matClass.getMath(3));hn3.setLayoutY(stage.getHeight()+hn3.getHeight());
                        kacice.add(hn3);
                    }
                    for (int i = 0; i < 3; i++) {
                        Duck hn4 = new Duck(this.stage.getScene(), this.stage, 4,this.root,this,mozeNemoze);
                        hn4.setLayoutX(stage.getWidth());hn4.setLayoutY(matClass.getMath(this.index));
                        kacice.add(hn4);
                    }
                    index = 1;
                }
            }
            root.getChildren().addAll(kacice);
            for (int i = 0; i < root.getChildren().size(); i++) {
                Node obj = root.getChildren().get(i);
                if(obj instanceof Duck)((Duck) obj).time.play();
            }
            media.play();
        }
    }
}
