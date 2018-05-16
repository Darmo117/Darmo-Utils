/*
 * Copyright © 2017 Damien Vergnet
 * 
 * This file is part of Darmo Utils.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package net.darmo_creations.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.MissingResourceException;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

/**
 * This class handles internationalization. Language files should be named 'LOCALE.lang' with LOCALE
 * being the locale's code (e.g.: en_US.lang).
 * 
 * @author Damien Vergnet
 */
public final class I18n {
  private static ResourceBundle resource;

  /**
   * Loads the preferred locale.<br/>
   * <b>This method must be called before any other from this class.</b>
   * 
   * @param stream the input stream
   * @throws IOException if the lang file cannot be openned
   */
  public static void init(InputStream stream) throws IOException {
    resource = new PropertyResourceBundle(new InputStreamReader(stream, StandardCharsets.UTF_8));
  }

  /**
   * Returns a formatted dated with the localized date pattern. Formatting tags are the following:
   * <ul>
   * <li>Y - the year on 4 digits; leading zeroes are added if necessary</li>
   * <li>M - the month on 2 digits; leading zeroes are added if necessary</li>
   * <li>D - the day of the month on 2 digits; leading zeroes are added if necessary</li>
   * <li>y - the year without leading zeroes</li>
   * <li>m - the month without leading zeroes</li>
   * <li>d - the day of the month without leading zeroes</li>
   * </ul>
   * 
   * @param year the year
   * @param month the month
   * @param day the day of the month
   * @return the formatted date
   */
  public static String getFormattedDate(String year, String month, String day) {
    String format = getLocalizedString("date.format");

    String res = format.replace("Y", String.format("%4s", year).replace(' ', '0'));
    res = res.replace("M", String.format("%2s", month).replace(' ', '0'));
    res = res.replace("D", String.format("%2s", day).replace(' ', '0'));
    res = res.replace("y", String.format("%2s", year.substring(year.length() - 2)).replace(' ', '0'));
    res = res.replace("m", month);
    res = res.replace("d", day);

    return res;
  }

  /**
   * Returns the localized string corresponding to the given key. If no key was found, the key is
   * returned.
   * 
   * @param unlocalizedString the unlocalized string
   * @return the localized string
   */
  public static String getLocalizedString(String unlocalizedString) {
    try {
      return resource.getString(unlocalizedString);
    }
    catch (MissingResourceException ex) {
      return unlocalizedString;
    }
  }

  /**
   * Returns the localized word corresponding to the given key. If no key was found, the key is
   * returned. No need to specify "word." at the beginning.
   * 
   * @param unlocalizedWord the unlocalized word
   * @param feminine if true, the feminine will be returned
   * @param plural if true, the plural will be returned
   * @return the localized word
   */
  public static String getLocalizedWord(String unlocalizedWord, boolean feminine, boolean plural) {
    return getLocalizedString("word." + unlocalizedWord + (feminine ? ".feminine" : "") + (plural ? ".plural" : ""));
  }

  /**
   * Returns the localized mnemonic for the given key. If no key was found, '\0' (null character)
   * will be returned. No need to specify ".mnemonic" in the key.
   * 
   * @param unlocalizedString the key
   * @return the localized mnemonic
   */
  public static char getLocalizedMnemonic(String unlocalizedString) {
    String s = getLocalizedString(unlocalizedString + ".mnemonic");
    if (s.length() == 1)
      return s.charAt(0);
    return '\0';
  }

  /**
   * Converts the given string to title case.
   * 
   * @param input the string
   * @return the string in title case
   */
  public static String toTitleCase(String input) {
    StringBuilder titleCase = new StringBuilder();
    boolean nextTitleCase = true;

    for (char c : input.toCharArray()) {
      if (Character.isSpaceChar(c)) {
        nextTitleCase = true;
      }
      else if (nextTitleCase) {
        c = Character.toTitleCase(c);
        nextTitleCase = false;
      }

      titleCase.append(c);
    }

    return titleCase.toString();
  }

  private I18n() {}
}
