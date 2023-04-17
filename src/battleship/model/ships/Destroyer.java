package battleship.model.ships;

public class Destroyer extends Ship {
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
