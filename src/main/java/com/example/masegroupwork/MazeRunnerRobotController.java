package com.example.masegroupwork;

import javafx.animation.PauseTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * MazeRunnerRobotController:
 *
 * Uses TranslateTransition, PauseTranslation, and SequentialTransition to make an
 * animation of the robot going through the maze.
 *
 * @author guzma
 */
public class MazeRunnerRobotController implements Initializable {

    @FXML
    private ImageView robo;
    @FXML
    private Button car;

    /**
     * The controller will make use of a combination of three
     * classes of the Animation package to fully animate
     * the robot traversing the maze:
     *
     *      TranslateTransition - makes designated node translate horizontally
     *                            and/or vertically by using setToX() / setToY()
     *                            methods.
     *
     *      PauseTranslation - used to pause between the multiple translate transitions
     *                         applied on the designated node in the sequential order.
     *
     *      SequentialTransition - plays a list of animations in a sequential order
     *
     * Steps 1-13 are different translations depending on how the robot has to traverse the maze
     * to reach the end.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //1st Step (Horizontal Translation going right coming from off-screen)
        TranslateTransition step1 = new TranslateTransition(Duration.millis(500), robo);
        step1.setFromX(-24);
        step1.setToX(22);

        //2nd Step (Vertical Translation going up by 105 pixels)
        TranslateTransition step2 = new TranslateTransition(Duration.millis(500), robo);
        step2.setToY(-104);

        //3rd Step (Horizontal Translation going right by 208 pixels)
        TranslateTransition step3 = new TranslateTransition(Duration.millis(500), robo);
        step3.setToX(230);

        //4th Step (Vertical Translation going up by 51 pixels)
        TranslateTransition step4 = new TranslateTransition(Duration.millis(500), robo);
        step4.setToY(-155);

        //5th Step (Horizontal Translation going right by 53 pixels)
        TranslateTransition step5 = new TranslateTransition(Duration.millis(500), robo);
        step5.setToX(283);

        //6th Step (Vertical Translation going down by 102 pixels)
        TranslateTransition step6 = new TranslateTransition(Duration.millis(500), robo);
        step6.setToY(53);

        //7th Step (Horizontal Translation going right by 53 pixels)
        TranslateTransition step7 = new TranslateTransition(Duration.millis(500), robo);
        step7.setToX(336);

        //8th Step (Vertical Translation going up by 101 pixels)
        TranslateTransition step8 = new TranslateTransition(Duration.millis(500), robo);
        step8.setToY(-48);

        //9th Step (Horizontal Translation going right by 105 pixels)
        TranslateTransition step9 = new TranslateTransition(Duration.millis(500), robo);
        step9.setToX(441);

        //10th Step (Vertical Translation going up by 105 pixels)
        TranslateTransition step10 = new TranslateTransition(Duration.millis(500), robo);
        step10.setToY(-153);

        //11th Step (Horizontal Translation going right by 34 pixels)
        TranslateTransition step11 = new TranslateTransition(Duration.millis(500), robo);
        step11.setToX(495);

        //12th Step (Vertical Translation going down by 139 pixels)
        TranslateTransition step12 = new TranslateTransition(Duration.millis(500), robo);
        step12.setToY(-14);

        //13th Step; Maze's Exit Point (Horizontal Translation going right off-screen)
        TranslateTransition step13 = new TranslateTransition(Duration.millis(500), robo);
        step13.setToX(569);

        //Plays steps 1-13 in a sequence with a pause in between
        SequentialTransition sequentialTransition = new SequentialTransition(
                step1, new PauseTransition(),
                step2, new PauseTransition(),
                step3, new PauseTransition(),
                step4, new PauseTransition(),
                step5, new PauseTransition(),
                step6, new PauseTransition(),
                step7, new PauseTransition(),
                step8, new PauseTransition(),
                step9, new PauseTransition(),
                step10, new PauseTransition(),
                step11, new PauseTransition(),
                step12, new PauseTransition(),
                step13, new PauseTransition()

        );

        //plays the animation indefinitely
        sequentialTransition.setCycleCount(Timeline.INDEFINITE);
        sequentialTransition.play();
    }
    public void VroomVroom(KeyEvent event) {
        System.out.println(event.getCode());

        double x = car.getLayoutX();
        double y = car.getLayoutY();

        switch (event.getCode()) {
            case W:
                y -= 2.0;
                car.setLayoutY(y);
                car.setRotate(-90);
                break;

            case A:
                x -= 2.0;
                car.setLayoutX(x);
                car.setRotate(0);
                break;

            case S:
                y += 2.0;
                car.setLayoutY(y);
                car.setRotate(90);
                break;

            case D:
                x += 2.0;
                car.setLayoutX(x);
                car.setRotate(360);
                break;

            default:
                break;
        }

    }
}