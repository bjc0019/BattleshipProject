package battleship.model.ships;

public class Cruiser extends Ship {
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
