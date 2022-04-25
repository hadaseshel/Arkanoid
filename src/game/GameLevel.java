// ID: 206775074

/**
 * @author hadas eshel
 */

package game;

// imports
import animation.Animation;
import animation.AnimationRunner;
import animation.CountdownAnimation;
import animation.PauseScreen;
import animation.KeyPressStoppableAnimation;
import biuoop.DrawSurface;
import biuoop.GUI;
import java.awt.Color;
import java.util.ArrayList;
import biuoop.KeyboardSensor;
import collision.Collidable;
import collision.HitListener;
import collision.GameEnvironment;
import collision.BallRemover;
import collision.BlockRemover;
import collision.ScoreTrackingListener;
import primitivesgeometry.Point;
import primitivesgeometry.Rectangle;
import sprites.Sprite;
import sprites.SpriteCollection;
import sprites.Ball;
import sprites.Paddle;
import sprites.Block;
import sprites.LevelName;
import sprites.ScoreIndicator;
import auxiliary.Counter;

/**
 * This class hold the sprites and the collidables, and will be in charge of the animation.
 */
public class GameLevel implements Animation {
    // fields: sprites ,environment, gui , paddle, size of the screen.
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private LevelInformation levelInformation;
    private GUI gui;
    private Paddle paddle;
    private KeyboardSensor keyboard;
    private Counter counterOfBlock = new Counter();
    private Counter counterOfBalls = new Counter();
    private Counter score;
    private AnimationRunner runner;
    private boolean running;
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private static final int HEIGHT_OF_BLOCKS = 25;
    private static final int RADIOS = 5;
    private static final int FRAME_THICKNESS = 20;
    private static final int POINTS_FOR_AND_LEVEL = 100;

    /**
     * This constructor method creates the game.
     * @param levelInformation the level inforamtion.
     * @param ar the runner of animation.
     * @param ks the Input write permission.
     * @param gui the screen.
     * @param score the score of the game.
     */
    public GameLevel(LevelInformation levelInformation, AnimationRunner ar, KeyboardSensor ks, Counter score, GUI gui) {
        this.environment = new GameEnvironment();
        this.sprites = new SpriteCollection();
        this.levelInformation = levelInformation;
        this.score = score;
        this.runner = ar;
        this.keyboard = ks;
        this.gui = gui;
    }

    /**
     * This method add the given collide able to the environment.
     * @param c the given collide able.
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }
    /**
     * This method add the given sprite to the sprite collection.
     * @param s the given sprite.
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    /**
     * This method remove the given collide from the environment.
     * @param c the given sprite.
     */
    public void removeCollidable(Collidable c) {
        this.environment.removeCollidable(c);
    }

    /**
     * This method remove the given sprite from the sprite collection.
     * @param s the given sprite.
     */
    public void removeSprite(Sprite s) {
        this.sprites.removeSprite(s);
    }

    /**
     * This method return the counter of the blocks.
     * @return the counter of the balls.
     */
    public Counter getCounterOfBlock() {
        return this.counterOfBlock;
    }

    /**
     * This method return the counter of the blocks.
     * @return the counter of the balls.
     */
    public Counter getCounterOfBalls() {
        return this.counterOfBalls;
    }

    /**
     * this method Initialize a new game: create the Blocks and Ball (and Paddle) and add them to the game.
     */
    public void initialize() {
        this.sprites.addSprite(levelInformation.getBackground());
        HitListener listenerBlock = new BlockRemover(this, this.counterOfBlock);
        HitListener listnerBalls = new BallRemover(this, this.counterOfBalls);
        HitListener lisnterScore = new ScoreTrackingListener(this.score);
        ScoreIndicator scoreIndicator = new ScoreIndicator(this.score);
        // create the frames block.
        ArrayList<Block> theFrameBlock = new ArrayList<Block>();
        theFrameBlock.add(new Block(new Rectangle(new Point(1, 1),  WIDTH - 2, FRAME_THICKNESS)));
        theFrameBlock.add(new Block(new Rectangle(new Point(WIDTH - FRAME_THICKNESS - 1, FRAME_THICKNESS + 2),
                FRAME_THICKNESS, HEIGHT - FRAME_THICKNESS - 3)));
        theFrameBlock.add(new Block(new Rectangle(new Point(FRAME_THICKNESS + 1, HEIGHT),
                WIDTH - 2 * FRAME_THICKNESS - 3, FRAME_THICKNESS)));
        theFrameBlock.add(new Block(new Rectangle(new Point(1, FRAME_THICKNESS + 2),
                FRAME_THICKNESS, HEIGHT - FRAME_THICKNESS - 3)));
        // set color to the block of frames and add them to the game.
        for (int i = 0; i < theFrameBlock.size(); i++) {
            Block theBlock = (Block) theFrameBlock.get(i);
            theBlock.setColor(Color.DARK_GRAY);
            theBlock.addToGame(this);
            if (i == 2) {
                theBlock.addHitListener(listnerBalls);
            }
        }
        LevelName levelName = new LevelName(levelInformation.levelName());
        this.sprites.addSprite(levelName);

        // create the paddle.
        this.paddle = new Paddle(new Rectangle(levelInformation.paddlePoint(), levelInformation.paddleWidth(),
                HEIGHT_OF_BLOCKS - 5), Color.yellow, levelInformation.paddleSpeed());
        this.paddle.setSizeOfScreen(FRAME_THICKNESS + 2, WIDTH - FRAME_THICKNESS - 2);
        this.paddle.addToGame(this);

        for (int i = 0; i < levelInformation.numberOfBalls(); i++) {
            Ball ball = new Ball(levelInformation.initialBallPoint().get(i), RADIOS, Color.white);
            ball.setVelocity(levelInformation.initialBallVelocities().get(i));
            ball.setSizeOfScreen(FRAME_THICKNESS + 2, HEIGHT - 1 - FRAME_THICKNESS,
                    FRAME_THICKNESS + 2, WIDTH - 1 - FRAME_THICKNESS);
            ball.setGameEnvironment(this.environment);
            ball.addToGame(this);
            ball.setPaddle(this.paddle);
        }
        this.counterOfBalls.increase(levelInformation.numberOfBalls());

        for (int i = 0; i < levelInformation.numberOfBlocksToRemove(); i++) {
            Block block = levelInformation.blocks().get(i);
            block.addToGame(this);
            block.addHitListener(listenerBlock);
            block.addHitListener(lisnterScore);
        }
        this.counterOfBlock.increase(levelInformation.numberOfBlocksToRemove());

        this.addSprite(scoreIndicator);
    }
    @Override
    public boolean shouldStop() {
        return !this.running;
    }
    @Override
    public void doOneFrame(DrawSurface d) {
        this.paddle.setKeyboardSensor(this.keyboard);
        // pause the game.
        if (this.keyboard.isPressed("p")) {
            this.runner.run(new KeyPressStoppableAnimation(this.keyboard, KeyboardSensor.SPACE_KEY,
                    new PauseScreen()));
        }
        // draw all the objects of sprites and show them.
        this.sprites.drawAllOn(d);
        // notify all the sprites that time passed.
        this.sprites.notifyAllTimePassed();
        if (this.counterOfBlock.getValue() == 0 || this.counterOfBalls.getValue() == 0) {
            if (this.counterOfBlock.getValue() == 0) {
                this.score.increase(POINTS_FOR_AND_LEVEL);
            }
            this.running = false;
        }
    }

    /**
     * this method Run the game -- start the animation loop.
     */
    public void run() {
        // countdown before turn starts.
        this.runner.setMillisecondsPerFrame(660);
        this.runner.run(new CountdownAnimation(2, 3, this.sprites));
        this.runner.setMillisecondsPerFrame();
        this.running = true;
        // use our runner to run the current animation -- which is one turn of the game.
        this.runner.run(this);
        //this.gui.close();
    }
}