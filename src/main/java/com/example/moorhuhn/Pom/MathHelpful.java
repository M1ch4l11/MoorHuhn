package com.example.moorhuhn.Pom;

import javafx.stage.Stage;

public class MathHelpful {
    Stage stage;

    public MathHelpful(Stage stage){
        this.stage = stage;
    }



    public double getMath(int index){
        switch (index){
            case 1 -> {return Math.random() * stage.getScene().getHeight();}
            case 2 -> {return Math.random() * (stage.getScene().getWidth()/2);}
            case 3 -> {
                double matNum = ((Math.random() * (stage.getScene().getWidth()/2))*2);
                while (true){
                    if(matNum<stage.getScene().getWidth()/2){
                      matNum = ((Math.random() * (stage.getScene().getWidth()/2))*2);
                    } else break;
                }
                return matNum;
            }
            case 4 -> {return Math.random() * stage.getScene().getHeight();}
        }
        return 1.0;
    }

    public int getMathInt(){
        int out = 0;
        while (true){
            out =((int) (Math.random() * 4)) + 1;
            if(out==1||out==2||out==3||out==4) {
                return out;
            }
            else continue;
        }
    }

    public int getSpeed(){
        int out = 0;
        while (true){
            out =((int) (Math.random() * 30)) + 10;
            if(out==10||out==20||out==25||out==30) {
                return out;
            }
            else continue;
        }
    }
}
