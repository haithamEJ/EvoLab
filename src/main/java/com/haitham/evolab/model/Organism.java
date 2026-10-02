package com.haitham.evolab.model;

public abstract class Organism {
    private int x , y , energy;
    private int rad = 8;
//x are columns and y are rows
    protected Organism(int x , int y , int energy){
        this.x = x;
        this.y = y;
        this.energy = energy;
    }

    public abstract void move(int rows, int cols );

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getEnergy() {
        return energy;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }

    public int getRad() {
        return rad;
    }

    public void setRad(int rad) {
        this.rad = rad;
    }
}
