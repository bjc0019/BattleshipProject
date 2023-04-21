package battleship.model.ships;

/** \
 * Ship subclass to represent Aircraft Carrier. Has length of 2 and char 
 * representation 'D'. 
 * @author bcalv
 */
public class Destroyer extends Ship {
    
    /** 
     * Constructor with input parameters 
     * @param _x Horizontal location
     * @param _y Vertical location
     * @param _isVertical Determines whether the ship is placed up/down or sideways
     */
    public Destroyer(int _x, int _y, boolean _isVertical)
    {
        super(_x, _y, _isVertical);
        length = 2;
        charRepresentation = 'D';
    }
    
    /** 
     * When constructed with no parameters, the location (x,y) and isVertical
     * are randomized. 
     */
    public Destroyer()
    {
        super();
        length = 2;
        charRepresentation = 'D';
        randomizeLocation();
    }
}
