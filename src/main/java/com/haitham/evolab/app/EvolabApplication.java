package com.haitham.evolab.app;

import com.haitham.evolab.ui.WorldView;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.animation.Timeline;
import javafx.animation.KeyFrame;
import javafx.animation.Animation;
import javafx.util.Duration;


public class EvolabApplication extends Application {

    private boolean moving = false;


    @Override
    public void start(Stage stage) {

        int rows = 12;
        int cols = 25;
        int cellSize = 40;
        int numberCreatures = 4;
        int numberFood = 50;
        WorldView world = new WorldView(rows,cols,cellSize);
        String buttonVal = "Move the Organisms" ;
        Button moveButton = new Button(buttonVal);
        moveButton.setOnAction(event -> {
            this.moving = !this.moving;

            if (moving) {
                moveButton.setText("Stop the movement");
            } else {
                moveButton.setText("Move the Organisms");
            }
        });



        GridPane maze = world.createMaze();
        try {
            world.spawnRandCreatures(numberCreatures, maze);
        } catch (IllegalArgumentException e){
            System.out.println("No creature can be spawned :"+ e.getMessage());
        }
        try {
            world.spawnRandFood(numberFood, maze);
        } catch (IllegalArgumentException e) {
            System.out.println("Food spawning cancelled: " + e.getMessage());
        }

        HBox controls = new HBox(moveButton);
        controls.setAlignment(Pos.CENTER);
        controls.setPadding(new Insets(12));

        VBox root = new VBox(controls, maze);

        Scene scene = new Scene(root);

        scene.getStylesheets().add(
                getClass().getResource("/design/ui.css").toExternalForm()
        );

        stage.setScene(scene);
        stage.setTitle("EvoLab");
        stage.setResizable(false);
        stage.show();
        stage.centerOnScreen();

        Timeline timer = new Timeline(
                new KeyFrame(Duration.millis(500), event -> {
                    if (moving) {
                        world.WorldIsMoving(maze);
                    }
                })
        );

        timer.setCycleCount(Animation.INDEFINITE);
        timer.play();

        stage.setOnHidden(event -> timer.stop());
    }

    public static void main(String[] args) {
        launch(args);

    }
}