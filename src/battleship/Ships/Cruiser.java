package battleship.Ships;

public class Cruiser extends Ship {
    public Cruiser(int _x, int _y, boolean _isVertical)
    {
        super(_x, _y, _isVertical);
        length = 3;
        charRepresentation = 'C';
    }
}
