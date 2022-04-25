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
 * This class is in charge of removing blocks from the game, as well as keeping count
 * of the number of blocks that remain.
 */
public class BlockRemover implements HitListener {
    private GameLevel game;
    private Counter remainingBlocks;

    /**
     * This constructor method creates the BlockRemover object.
     * @param game the game we play.
     * @param removedBlocks the number of block in game.
     */
    public BlockRemover(GameLevel game, Counter removedBlocks) {
        this.game = game;
        this.remainingBlocks = removedBlocks;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        beingHit.removeHitListener(this);
        beingHit.removeFromGame(this.game);
        this.remainingBlocks.decrease(1);
    }
}