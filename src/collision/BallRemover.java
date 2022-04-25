// ID: 206775074

/**
 * @author hadas eshel
 */

package collision;

// imports
import game.GameLevel;
import sprites.Block;
import sprites.Ball;
import auxiliary.Counter;

/**
 * This class is in charge of removing balls from the game, as well as keeping count
 * of the number of balls that remain.
 */
public class BallRemover implements HitListener {
    private GameLevel game;
    private Counter remainingBalls;

    /**
     * This constructor method creates the BallRemover object.
     * @param game the game we play.
     * @param removedBalls the number of balls in game.
     */
    public BallRemover(GameLevel game, Counter removedBalls) {
        this.game = game;
        this.remainingBalls = removedBalls;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(this.game);
        this.remainingBalls.decrease(1);
    }
}

