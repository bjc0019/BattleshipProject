package battleship.Ships;

/** \
 * Ship subclass to represent Aircraft Carrier. Has length of 5 and char 
 * representation 'A'. 
 * @author bcalv
 */
public class Carrier extends Ship {
    
    /** 
     * Constructor with input parameters 
     * @param _x
     * @param _y
     * @param _isVertical Determines whether the ship is placed up/down or sideways
     */
    public Carrier(int _x, int _y, boolean _isVertical)
    {
        super(_x, _y, _isVertical);
        length = 5;
        charRepresentation = 'A';
    }
    
    /** 
     * When constructed with no parameters, the location (x,y) and isVertical
     * are randomized. 
     */
    public Carrier()
    {
        super();
        length = 5;
        charRepresentation = 'A';
        randomizeLocation();
    }
}
