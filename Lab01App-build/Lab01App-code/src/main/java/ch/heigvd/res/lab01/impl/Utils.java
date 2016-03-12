package ch.heigvd.res.lab01.impl;

import java.util.logging.Logger;

/**
 *
 * @author Olivier Liechti
 */
public class Utils {

  private static final Logger LOG = Logger.getLogger(Utils.class.getName());

  /**
   * This method looks for the next new line separators (\r, \n, \r\n) to extract
   * the next line in the string passed in arguments. 
   * 
   * @param lines a string that may contain 0, 1 or more lines
   * @return an array with 2 elements; the first element is the next line with
   * the line separator, the second element is the remaining text. If the argument does not
   * contain any line separator, then the first element is an empty string.
   */
  public static String[] getNextLine(String lines) {

    for (int i = 0; i < lines.length(); ++i){
      char c = lines.charAt(i);

      if (c == '\r') {
        if (i+1 < lines.length() && lines.charAt(i+1) == '\n') {
          return splitAt(lines, i+2);
        }
        return splitAt(lines, i+1);
      }
      else if (c == '\n') {
        return splitAt(lines, i+1);
      }
    }
    return new String[] {"", lines};
  }

  /**
   * Splits a string at a given position and returns the two parts in an array.
   * @param s The string to split
   * @param index The position where to split
   * @return The array with the two parts
   */
  private static String[] splitAt(String s, int index) {
    return new String[] {s.substring(0, index), s.substring(index)};
  }
}
