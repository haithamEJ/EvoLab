package com.haitham.evolab.ui;

import javafx.geometry.Insets;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.shape.Circle;

import java.util.Set;
import java.util.HashSet;
import java.util.Random;

public class WorldView {
    int rows,cols,cellSize;

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

    public void spawnRand(int numberElements,GridPane maze) {


        int cols = this.getCols(maze);
        int rows = this.getRows(maze);
        double w = this.getCellWidth(maze);

        Random random = new Random();
        Set<String> occupied = new HashSet<>();

        for (int i = 0 ; i < numberElements ; i++) {

            int row , col ;
            String position;

            do{
                row = random.nextInt(rows);
                col = random.nextInt(cols);
                position = row + "," + col;
            }while (occupied.contains(position));

            occupied.add(position);
            Circle circle = new Circle(w/3);
            maze.add(circle, col, row);
            GridPane.setMargin(circle, new Insets(10,10,10,10));
        }

    }

}
