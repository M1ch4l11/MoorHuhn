package com.example.moorhuhn.Pom;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Naboj extends Canvas {
    GraphicsContext gc;
    public Naboj() {
        super(30,80);
        gc = getGraphicsContext2D();
        vykresli();
    }

    public void vykresli(){
        gc.clearRect(0,0,getWidth(),getHeight());
        gc.drawImage(new Image("Naboj-removebg-preview.png"),0,0,getWidth(),getHeight());
    }
    public void clearGC(){
        gc.clearRect(0,0,getWidth(),getHeight());
    }
}
