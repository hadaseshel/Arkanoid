// ID: 206775074

/**
 * @author hadas eshel
 */

package collision;

/**
 * This interface create in order to Objects that want to be notified of hit events,.
 */
public interface HitNotifier {
    /**
     * This method Add hl as a listener to hit events.
     * @param hl the hit listener.
     */
    void addHitListener(HitListener hl);
    /**
     * This method Remove hl from the list of listeners to hit events.
     * @param hl the hit listener.
     */
    void removeHitListener(HitListener hl);
}