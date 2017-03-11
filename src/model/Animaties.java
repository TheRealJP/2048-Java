package model;

import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.ScaleTransition;
import javafx.animation.Transition;
import javafx.scene.Node;
import javafx.util.Duration;

/**
 * Created by Jonathan on 11/03/2017.
 */
public class Animaties {
    public void fadeTransition(Node node, boolean autoReverse, Duration duration) {
        //toont label animatie als er wordt gesaved
        FadeTransition fadeIn = new FadeTransition(duration, node);
        fadeIn.setFromValue(0.0); //opacity
        fadeIn.setInterpolator(Interpolator.EASE_BOTH); //verzacht de overgang
        fadeIn.setToValue(1.0); //opacity
        fadeIn.setCycleCount(2); // 1 cycle voor aan en 1 voor uit
        fadeIn.setAutoReverse(autoReverse); //gaat terug naar originele status
        node.setVisible(true);
        fadeIn.play(); //animatie spelen
    }
}
