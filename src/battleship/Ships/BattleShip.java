package battleship.Ships;

public class BattleShip extends Ship {
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