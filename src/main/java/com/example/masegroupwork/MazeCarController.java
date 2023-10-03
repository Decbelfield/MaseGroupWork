package com.example.masegroupwork;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.geometry.Bounds;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.util.Duration;

/**
 * This is the code to check if there is a collision with the car object and the blue
 * field of the maze and force the car to stay within the bounds of the maze.
 *
 * This also tracks the data of the pixels around the car object and tests if they are the
 * value of BLUE and with restrict the cars movement and send it back a step
 *
 * This also ties the focus for the systems keys to be bound to wasd for the movement of
 * the car object for traversal of the maze
 *
 *
 *
 */
public class MazeCarController {

    private static boolean isPixelCollision(ImageView PlayerCar, ImageView CarMazeMap, Color collisionColor) {
        Image image1 = PlayerCar.getImage();
        Image image2 = CarMazeMap.getImage();

        PixelReader pixelReader1 = image1.getPixelReader();
        PixelReader pixelReader2 = image2.getPixelReader();

        // Get the bounds of the images
        Bounds bounds1 = PlayerCar.getBoundsInParent();
        Bounds bounds2 = CarMazeMap.getBoundsInParent();

        int minX = (int) Math.max(bounds1.getMinX(), bounds2.getMinX());
        int minY = (int) Math.max(bounds1.getMinY(), bounds2.getMinY());
        int maxX = (int) Math.min(bounds1.getMaxX(), bounds2.getMaxX());
        int maxY = (int) Math.min(bounds1.getMaxY(), bounds2.getMaxY());

        for (int x = minX; x < maxX; x++) {
            for (int y = minY; y < maxY; y++) {
                Color color1 = pixelReader1.getColor(x, y);
                Color color2 = pixelReader2.getColor(x, y);

                collisionColor = Color.BLUE;

                if (isPixelCollision(PlayerCar, CarMazeMap, collisionColor)) {
                    // Handle collision here
                    System.out.println("Collision detected!");
                }

                return true;
            }

        }

        return false;
    }



    public static class GameController {
        @FXML
        private ImageView carPlayer;
        private double carX = 0; // Initial X position
        private double carY = 0; // Initial Y position
        private double step = 5; // Adjust the step size as needed
        private Timeline moveAnimation;
        private ImageView CarMazeMap;

        public void initialize() {
            carPlayer.requestFocus();
            carPlayer.setLayoutX(carX);
            carPlayer.setLayoutY(carY);

            // Initialize the Timeline for continuous movement
            moveAnimation = new Timeline(
                    new KeyFrame(Duration.millis(16), event -> moveCar())
            );
            moveAnimation.setCycleCount(Animation.INDEFINITE);

            // Bind key press and release events
            carPlayer.getScene().setOnKeyPressed(this::handleKeyPress);
            carPlayer.getScene().setOnKeyReleased(this::handleKeyRelease);
        }

        //takes the input from wasd to make the car traverse through the maze
        private void handleKeyPress(KeyEvent event) {
            KeyCode code = event.getCode();

            // Start the animation when a key is pressed
            if (!moveAnimation.getStatus().equals(Animation.Status.RUNNING)) {
                moveAnimation.play();
            }

            // Handle the pressed key
            switch (code) {
                case W:
                    carY -= step; // Move up
                    break;
                case A:
                    carX -= step; // Move left
                    break;
                case S:
                    carY += step; // Move down
                    break;
                case D:
                    carX += step; // Move right
                    break;
                default:
                    // Handle other key presses if needed
                    break;
            }

            carPlayer.setLayoutX(carX);
            carPlayer.setLayoutY(carY);
        }

        private void handleKeyRelease(KeyEvent event) {
            // Stop the animation when a key is released
            if (moveAnimation.getStatus().equals(Animation.Status.RUNNING)) {
                moveAnimation.stop();
            }
        }

        private double prevCarX;
        private double prevCarY;

        //this doesn't work no clue why, brain hurt
        private void moveCar() {
            // Store the current position as the previous position
            prevCarX = carX;
            prevCarY = carY;

            // Check for collisions before moving the car
            boolean collision = isPixelCollision(CarMazeMap,carPlayer, Color.BLUE);

            // Move the car only if no collision is detected
            if (!collision) {
                carPlayer.setLayoutX(carX);
                carPlayer.setLayoutY(carY);
            } else {
                // Restore the previous position in case of collision
                carX = prevCarX;
                carY = prevCarY;
            }
        }
    }


}