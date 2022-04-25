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
 * This class hold the details of level 1.
 */

public class Level1 implements LevelInformation {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private static final int SIZE_OF_BLOCKS = 40;
    private static final int FRAME_THICKNESS = 20;
    private static final int HEIGHT_OF_BLOCKS = 25;
    @Override
    public int numberOfBalls() {
        return 1;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> theList = new ArrayList<Velocity>();
        theList.add(new Velocity(0, -4));
        return theList;
    }

    @Override
    public List<Point> initialBallPoint() {
        List<Point> theList = new ArrayList<Point>();
        theList.add(new Point(WIDTH / 2, HEIGHT * 5 / 7));
        return theList;
    }

    @Override
    public int paddleSpeed() {
        return 5;
    }

    @Override
    public Point paddlePoint() {
        return new Point(WIDTH / 2 - 40, HEIGHT - HEIGHT_OF_BLOCKS + 5 - FRAME_THICKNESS - 2);
    }


    @Override
    public int paddleWidth() {
        return 80;
    }

    @Override
    public String levelName() {
        return new String("Direct Hit");
    }

    @Override
    public Sprite getBackground() {
        Block theBackgrounds = new Block(new Rectangle(new Point(0, 0), WIDTH, HEIGHT));
        theBackgrounds.setColor(Color.BLACK);
        return theBackgrounds;
    }

    @Override
    public List<Block> blocks() {
        List<Block> theList = new ArrayList<Block>();
        Block block = new Block(new Rectangle(new Point(WIDTH / 2 - SIZE_OF_BLOCKS / 2, HEIGHT / 5),
                SIZE_OF_BLOCKS, SIZE_OF_BLOCKS));
        block.setColor(Color.RED);
        theList.add(block);
        return theList;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 1;
    }
}


