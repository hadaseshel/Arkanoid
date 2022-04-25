// ID: 206775074

/**
 * @author hadas eshel
 */

package auxiliary;

/**
 * This class is a simple class that is used for counting things.
 */
public class Counter {
    // field
    private int val;

    /**
     * This method create the object counter.
     */
    public Counter() {
        this.val = 0;
    }

    /**
     * This method add number to current count.
     * @param number the number.
     */
    public void increase(int number) {
        this.val = this.val + number;
    }

    /**
     * This method subtract number from current count.
     * @param number the number.
     */
    public void decrease(int number) {
        this.val = this.val - number;
    }

    /**
     * This method get current count.
     * @return current count.
     */
    public int getValue() {
        return this.val;
    }
}