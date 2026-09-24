package com.haitham.evolab.app;

import com.haitham.evolab.ui.WorldView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class EvolabApplication extends Application {




    @Override
    public void start(Stage stage) {

        int rows = 12;
        int cols = 25;
        int cellSize = 40;

        WorldView world = new WorldView(rows,cols,cellSize);
        GridPane maze = world.createMaze();
        world.spawnRand(4,maze);
        Scene scene = new Scene(maze, 1000, 480);

        stage.setScene(scene);
        stage.setTitle("EvoLab");
        stage.setResizable(false);
        stage.show();
        stage.centerOnScreen();
    }

    public static void main(String[] args) {
        launch(args);
    }
}