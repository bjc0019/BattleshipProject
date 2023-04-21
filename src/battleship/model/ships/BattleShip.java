package battleship.model.ships;

/** \
 * Ship subclass to represent Aircraft Carrier. Has length of 4 and char 
 * representation 'B'. 
 * @author bcalv
 */
public class BattleShip extends Ship {
    
    /** 
     * Constructor with input parameters 
     * @param _x Horizontal location
     * @param _y Vertical location
     * @param _isVertical Determines whether the ship is placed up/down or sideways
     */
    public BattleShip(int _x, int _y, boolean _isVertical)
    {
        super(_x, _y, _isVertical);
        length = 4;
        charRepresentation = 'B';
    }
    
    /** 
     * When constructed with no parameters, the location (x,y) and isVertical
     * are randomized. 
     */
    public BattleShip()
    {
        super();
        length = 4;
        charRepresentation = 'B';
        randomizeLocation();
    }
}