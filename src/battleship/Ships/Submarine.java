package battleship.Ships;

public class Submarine extends Ship {
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
