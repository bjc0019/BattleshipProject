package myExceptions;

import java.lang.Exception;


/** 
 * Exception thrown if an access method is called with index parameters that
 * are out of bounds. 
 * @author bcalv
 */
public class IndexOutOfBounds extends Exception {
    public IndexOutOfBounds(String s)
    {
        super(s);
    }
}
