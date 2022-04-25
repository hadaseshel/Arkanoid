// ID: 206775074

/**
 * @author hadas eshel
 */

package game;

// import
import primitivesgeometry.Point;
import sprites.Block;
import sprites.Sprite;
import sprites.Velocity;
import java.util.List;

/**
 * This interface specifies the information required to fully describe a level.
 */
public interface LevelInformation {
    /**
     * This method return the number of balls in the game.
     * @return the number of balls in the game.
     */
    int numberOfBalls();
    /**
     * This method return the initial velocity of each ball.
     * @return The initial velocity of each ball
     */
    List<Velocity> initialBallVelocities();

    /**
     * This method return the initial Point of each ball.
     * @return The initial Point of each ball.
     */
    List<Point> initialBallPoint();

    /**
     * This method return the paddle speed.
     * @return the paddle speed.
     */
    int paddleSpeed();

    /**
     * This method return the paddle Point.
     * @return the paddle Point.
     */
    Point paddlePoint();

    /**
     * This method return the paddle width.
     * @return the paddle width.
     */
    int paddleWidth();
    /**
     * This method return the level name that will be displayed at the top of the screen.
     * @return the level name that will be displayed at the top of the screen.
     */
    String levelName();
    /**
     * This method return a sprite with the background of the level.
     * @return a sprite with the background of the level
     */
    Sprite getBackground();

    /**
     * This method return The Blocks that make up this level, each block contains its size, color and location.
     * @return The Blocks that make up this level, each block contains its size, color and location.
     */
    List<Block> blocks();

    /**
     * This method return the Number of blocks that should be removed before the level is considered to be "cleared".
     * @return the Number of blocks that should be removed before the level is considered to be "cleared".
     */
    int numberOfBlocksToRemove();
}