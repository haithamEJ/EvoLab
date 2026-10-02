package com.haitham.evolab.ui;

import com.haitham.evolab.model.Food;
import com.haitham.evolab.model.O1;
import com.haitham.evolab.model.Organism;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.*;

public class WorldView {
    int rows,cols,cellSize;
    private final Map<Organism, Circle> organismShapes = new HashMap<>();
    private final Map<Food, Circle> foodShapes = new HashMap<>();
    private final Set<String> occupied = new HashSet<>();


    public WorldView(int rows , int cols , int cellSize){
        this.rows = rows;
        this.cols = cols;
        this.cellSize = cellSize;
    }

    public GridPane createMaze(){
        GridPane grid = new GridPane();

        grid.setGridLinesVisible(true);



        for (int i = 0; i < cols; i++) {
            ColumnConstraints column = new ColumnConstraints(cellSize);
            grid.getColumnConstraints().add(column);
        }

        for (int i = 0; i < rows; i++) {
            RowConstraints row = new RowConstraints(cellSize);
            grid.getRowConstraints().add(row);
        }

        return grid;
    }


    public int getRows(GridPane maze) {
        return maze.getRowConstraints().size();
    }

    public int getCols(GridPane maze) {
        return maze.getColumnConstraints().size();
    }

    public double getCellWidth(GridPane maze) {
        if (maze.getColumnConstraints().isEmpty()) {
            return 0;
        }

        return maze.getColumnConstraints().get(0).getPrefWidth();
    }

    public double getCellHeight(GridPane maze) {
        if (maze.getRowConstraints().isEmpty()) {
            return 0;
        }

        return maze.getRowConstraints().get(0).getPrefHeight();
    }

    public void spawnRandFood(int numberElements,GridPane maze) {


        int cols = this.getCols(maze);
        int rows = this.getRows(maze);
        double w = this.getCellWidth(maze);

        Random random = new Random();

        int available = rows * cols - occupied.size();

        if (numberElements < 0 || numberElements > available) {
            throw new IllegalArgumentException("Not enough empty cells");
        }

        for (int i = 0 ; i < numberElements ; i++) {

            int row , col ;
            String position;

            do{
                row = random.nextInt(rows);
                col = random.nextInt(cols);
                position = row + "," + col;
            }while (occupied.contains(position));


            occupied.add(position);
            Food food = new Food(col,row);
            Circle circle = new Circle(w/10);
            circle.setFill(Color.RED);
            maze.add(circle, col, row);
            GridPane.setMargin(circle, new Insets(10,10,10,10));
            foodShapes.put(food,circle);
        }

    }


    public void spawnRandCreatures(int numberElements, GridPane maze) {


        int cols = this.getCols(maze);
        int rows = this.getRows(maze);
        double w = this.getCellWidth(maze);

        Random random = new Random();

        int available = rows * cols - occupied.size();

        if (numberElements < 0 || numberElements > available) {
            throw new IllegalArgumentException("Not enough empty cells");
        }

        for (int i = 0 ; i < numberElements ; i++) {

            int row , col ;
            String position;

            do{
                row = random.nextInt(rows);
                col = random.nextInt(cols);
                position = row + "," + col;
            }while (occupied.contains(position));


            occupied.add(position);
            O1 creature = new O1(col,row);
            Circle circle = new Circle(w/creature.getRad()); // max /2
            circle.setFill(Color.GREEN);
            maze.add(circle, col, row);
            GridPane.setHalignment(circle, HPos.CENTER);
            GridPane.setValignment(circle, VPos.CENTER);
            organismShapes.put(creature,circle);
        }

    }

    public void WorldIsMoving(GridPane maze){
        double w = this.getCellWidth(maze);
        for (Map.Entry<Organism,Circle> entry : organismShapes.entrySet()){
            Organism o1 = entry.getKey() ;
            Circle cercle = entry.getValue();
            o1.move(rows,cols);
            GridPane.setColumnIndex(cercle,o1.getX());
            GridPane.setRowIndex(cercle,o1.getY());

            for(Food food :foodShapes.keySet()){
                if(food.getX() == o1.getX() && food.getY() == o1.getY()){
                    o1.setEnergy(o1.getEnergy()+food.getEnergy_effect());
                    Circle c = organismShapes.get(o1);
                    if(c.getRadius()< w/2) {
                        c.setRadius(w/(o1.getRad()-2));
                        o1.setRad(o1.getRad() -2);
                    }
                    maze.getChildren().remove(foodShapes.get(food));
                    foodShapes.remove(food);
                    break;
                }
            }
        }
    }
}
