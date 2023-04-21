package battleship.model.ships;

/** \
 * Ship subclass to represent Aircraft Carrier. Has length of 3 and char 
 * representation 'S'. 
 * @author bcalv
 */
public class Submarine extends Ship {
    
    /** 
     * Constructor with input parameters 
     * @param _x Horizontal location
     * @param _y Vertical location
     * @param _isVertical Determines whether the ship is placed up/down or sideways
     */
    public Submarine(int _x, int _y, boolean _isVertical)
    {
        super(_x, _y, _isVertical);
        length = 3;
        charRepresentation = 'S';
    }
    
    /** 
     * When constructed with no parameters, the location (x,y) and isVertical
     * are randomized. 
     */
    public Submarine()
    {
        super();
        length = 3;
        charRepresentation = 'S';
        randomizeLocation();
    }
}
