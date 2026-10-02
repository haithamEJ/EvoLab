package com.haitham.evolab.model;

import java.util.Random;

public class O1 extends Organism{

    public O1(int x , int y , int  energy ){
        super(x,y,energy);
    }

    public O1(int x , int y){
        super(x,y,100);
    }

    @Override
    public void move(int rowMax , int colMax){
        Random random = new Random();
        String [] directions = {"North","South","East","West"};
        String direction = directions[random.nextInt(4)];

        switch (direction){
            case "North":
                if( getY() > 0) { // if ila chft  cordonne fiha chi element dial food energy khssha tzad then go to the world is moving and change cords of circle of food
                    setY(getY() - 1);
                }
                break;
            case "South":
                if(getY() < rowMax - 1 ) {
                    setY(getY() + 1);
                }
                break;
            case "West":
                if(getX() > 0) {
                    setX(getX()-1);
                }
                break;
            case "East":
                if(getX() < colMax - 1) {
                    setX(getX()+1);
                }
                break;
            default:
                System.out.println("Well O1 cant move ");
                break;

        }
    }

}
