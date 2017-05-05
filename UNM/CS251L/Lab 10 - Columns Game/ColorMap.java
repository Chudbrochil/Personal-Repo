/**
 * Anthony Galczak - agalczak@unm.edu - WGalczak@gmail.com
 * CS 251 - Lab 10 - Columns Game
 * 
 * Map class that holds mappings between 8 possible characters in the
 * internal BlockManager and maps them to the appropriate colors in order
 * to be painted on a Swing GUI.
 * 
 * This class is a fancy static data type for a map that is used throughout
 * the program.
 * 
 * ColorMap.java
 */

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;

public class ColorMap {
    public static Map<Character, Color> ColorMap;
    static
    {
        ColorMap = new HashMap<Character, Color>();
        ColorMap.put('.', Color.BLACK);
        ColorMap.put('a', Color.BLUE);
        ColorMap.put('b', Color.GRAY);
        ColorMap.put('c', Color.RED);
        ColorMap.put('d', Color.GREEN);
        ColorMap.put('e', Color.CYAN);
        ColorMap.put('f', Color.YELLOW);
        ColorMap.put('g', Color.WHITE);
        ColorMap.put('h', Color.ORANGE);
    }
}
