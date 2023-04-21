package battleship.model.ships;

/** \
 * Ship subclass to represent Aircraft Carrier. Has length of 3 and char 
 * representation 'C'. 
 * @author bcalv
 */
public class Cruiser extends Ship {
    
    /** 
     * Constructor with input parameters 
     * @param _x Horizontal location
     * @param _y Vertical location
     * @param _isVertical Determines whether the ship is placed up/down or sideways
     */
    public Cruiser(int _x, int _y, boolean _isVertical)
    {
        super(_x, _y, _isVertical);
        length = 3;
        charRepresentation = 'C';
    }
    
    /** 
     * When constructed with no parameters, the location (x,y) and isVertical
     * are randomized. 
     */
    public Cruiser()
    {
        super();
        length = 3;
        charRepresentation = 'C';
        randomizeLocation();
    }
}
