package com.haitham.evolab.model;

public class Food {
    int x , y ;
    private final int  energy_effect = 3;

    public Food(int x , int y){
        this.x = x;
        this.y = y;
    }


    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getEnergy_effect() {
        return energy_effect;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
}
