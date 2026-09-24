package com.haitham.evolab.model;

public abstract class Organism {
    private int x , y , energy;

    protected Organism(int x , int y , int energy){
        this.x = x;
        this.y = y;
        this.energy = energy;
    }

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
}
