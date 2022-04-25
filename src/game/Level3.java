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
 * This class hold the details of level 3.
 */
public class Level3 implements LevelInformation {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private static final int NUM_OF_BLOCKS = 40;
    private static final double SIZE_OF_BLOCKS = 50.4;
    private static final int FRAME_THICKNESS = 20;
    private static final int HEIGHT_OF_BLOCKS = 25;
    private static final int DISTANCE_OF_BALLS = 25;
    private static final int NUM_OF_BALLS = 2;
    private static final int NUM_OF_LINES_BLOCKS = 5;
    private static final int NUM_OF_BLOCK_IN_LINE = 6;
    private static final String LEVEL = "Green 3";
    @Override
     public int numberOfBalls() {
      return NUM_OF_BALLS;
  }

  @Override
  public List<Velocity> initialBallVelocities() {
      List<Velocity> theList = new ArrayList<Velocity>();
      theList.add(new Velocity(-2, -3));
      theList.add(new Velocity(2, -3));
      return theList;
  }

  @Override
  public List<Point> initialBallPoint() {
      List<Point> theList = new ArrayList<Point>();
      theList.add(new Point(WIDTH / 2 - 90, 5 * HEIGHT / 7));
      theList.add(new Point(WIDTH / 2 + 90, 5 * HEIGHT / 7));
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
      return LEVEL;
  }

  @Override
  public Sprite getBackground() {
      Block theBackgrounds = new Block(new Rectangle(new Point(0, 0), WIDTH, HEIGHT));
      theBackgrounds.setColor(Color.GREEN.darker());
      return theBackgrounds;
  }

  @Override
  public List<Block> blocks() {
      List<Block> theList = new ArrayList<Block>();
      for (int j = 0; j < NUM_OF_LINES_BLOCKS; j++) {
          for (int i = 0; i < NUM_OF_BLOCK_IN_LINE + j; i++) {
              Block block = new Block(new Rectangle(new Point(WIDTH - FRAME_THICKNESS - 2
                      - NUM_OF_BLOCK_IN_LINE * SIZE_OF_BLOCKS  + 5 * SIZE_OF_BLOCKS - (i * SIZE_OF_BLOCKS),
                      2 * HEIGHT / 5 - j * HEIGHT_OF_BLOCKS - 1 - j), SIZE_OF_BLOCKS, HEIGHT_OF_BLOCKS));
              if (j == 0) {
                  block.setColor(Color.WHITE);
              } else if (j == 1) {
                  block.setColor(Color.BLUE);
              } else if (j == 2) {
                  block.setColor(Color.YELLOW);
              } else if (j == 3) {
                  block.setColor(Color.RED);
              } else if (j == 4) {
                  block.setColor(Color.GRAY);
              }
              theList.add(block);
          }
      }
      return theList;
  }

  @Override
  public int numberOfBlocksToRemove() {
      return NUM_OF_BLOCKS;
  }
}