// ID: 206775074

/**
 * @author hadas eshel
 */

package game;

// import
import primitivesgeometry.Point;
import primitivesgeometry.Rectangle;
import sprites.Block;
import sprites.Sprite;
import sprites.Velocity;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * This class hold the details of level 2.
 */
public class Level2 implements LevelInformation {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private static final int NUM_OF_BLOCKS = 15;
    private static final double SIZE_OF_BLOCKS = 50.4;
    private static final int FRAME_THICKNESS = 20;
    private static final int HEIGHT_OF_BLOCKS = 25;
    private static final int DISTANCE_OF_BALLS = 25;
    private static final int NUM_OF_BALLS = 10;
    @Override
    public int numberOfBalls() {
        return NUM_OF_BALLS;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> theList = new ArrayList<Velocity>();
        for (int i = 0; i < NUM_OF_BALLS / 2; i++) {
            theList.add(new Velocity(3, -3));
            theList.add(new Velocity(-3, -3));
        }
        return theList;
    }

    @Override
    public List<Point> initialBallPoint() {
        List<Point> theList = new ArrayList<Point>();
        int j = 2 * NUM_OF_BALLS * NUM_OF_BALLS / 3;
        int k = NUM_OF_BALLS * NUM_OF_BALLS / 3;
        for (int i = 0; i < NUM_OF_BALLS; i++) {
            theList.add(new Point(WIDTH / 4 +  i * 1.4 * DISTANCE_OF_BALLS + k,
                    HEIGHT * 5 / 8 - i * DISTANCE_OF_BALLS - j));
            theList.add(new Point(3 * WIDTH / 4 - i * 1.4 * DISTANCE_OF_BALLS - k,
                    HEIGHT * 5 / 8 - i * DISTANCE_OF_BALLS - j));
            j = j - 6 * i;
            k = k - 2 * i;
        }
        return theList;
    }

    @Override
    public int paddleSpeed() {
        return 3;
    }

    @Override
    public Point paddlePoint() {
        return new Point(WIDTH / 2 - 300, HEIGHT - HEIGHT_OF_BLOCKS + 5 - FRAME_THICKNESS - 2);
    }


    @Override
    public int paddleWidth() {
        return 600;
    }

    @Override
    public String levelName() {
        return new String("Wide Easy");
    }

    @Override
    public Sprite getBackground() {
        Block theBackgrounds = new Block(new Rectangle(new Point(0, 0), WIDTH, HEIGHT));
        theBackgrounds.setColor(Color.WHITE);
        return theBackgrounds;
    }

    @Override
    public List<Block> blocks() {
        List<Block> theList = new ArrayList<Block>();
        for (int i = 0; i < NUM_OF_BLOCKS; i++) {
            Block block = new Block(new Rectangle(new Point(WIDTH - FRAME_THICKNESS - 2
                    - NUM_OF_BLOCKS * SIZE_OF_BLOCKS + (i * SIZE_OF_BLOCKS),
                    HEIGHT / 3), SIZE_OF_BLOCKS, HEIGHT_OF_BLOCKS));
            if (i == 0 || i == 1) {
                block.setColor(Color.RED);
            } else if (i == 2 || i == 3) {
                block.setColor(Color.ORANGE);
            } else if (i == 4 || i == 5) {
                block.setColor(Color.YELLOW);
            } else if (i == 6 || (i == 7 || i == 8)) {
                block.setColor(Color.GREEN);
            } else if (i == 9 || i == 10) {
                block.setColor(Color.blue);
            } else if (i == 11 || i == 12) {
                block.setColor(Color.PINK);
            } else if (i == 13 || i == 14) {
                block.setColor(Color.cyan);
            }
            theList.add(block);
        }
        return theList;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 15;
    }
}

