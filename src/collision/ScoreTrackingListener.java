// ID: 206775074

/**
 * @author hadas eshel
 */

package collision;

// imports
import sprites.Block;
import sprites.Ball;
import auxiliary.Counter;

/**
 * This class is in charge of update the score counter.
 */
public class ScoreTrackingListener implements HitListener {
    private Counter currentScore;
    private static final int POINTS_FOR_HIT = 5;
    private static final int POINTS_FOR_AND_LEVEL = 100;

    /**
     * This constructor method creates the ScoreTrackingListener object.
     * @param scoreCounter the score counter of the game we play.
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
       this.currentScore.increase(POINTS_FOR_HIT);
    }
}

