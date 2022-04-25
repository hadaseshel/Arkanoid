// ID: 206775074

/**
 * @author hadas eshel
 */

package animation;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * This class is in charg of "waiting-for-key-press" behavior.
 */
public class KeyPressStoppableAnimation implements Animation {
    private KeyboardSensor sensor;
    private String key;
    private Animation animation;
    private Boolean stop;
    private boolean isAlreadyPressed;

    /**
     * This constructor method creates the object of the PauseScreen.
     * @param sensor the sensor of the game.
     * @param key the key to press to stop animation.
     * @param animation the animation.
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation animation) {
        this.animation = animation;
        this.key = key;
        this.sensor = sensor;
        this.stop = false;
        this.isAlreadyPressed = true;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        this.animation.doOneFrame(d);
        if (this.sensor.isPressed(this.key)) {
            if (this.isAlreadyPressed) {
                this.stop = false;
            } else {
                this.stop = true;
            }
        } else {
            this.isAlreadyPressed = false;
        }
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}