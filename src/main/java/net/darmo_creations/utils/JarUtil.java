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

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

/**
 * This class provides useful methods to handle the jar, restart the application, etc.
 *
 * @author Damien Vergnet
 */
public final class JarUtil {
  private static String dir;

  /**
   * @return the jar's directory
   */
  public static String getJarDir() {
    if (dir == null) {
      String path = ClassLoader.getSystemClassLoader().getResource(".").getPath();

      if (File.separatorChar == '\\')
        path = path.substring(1); // Removes the first '/' on Windows.
      dir = path;
    }

    return dir.replace('/', File.separatorChar);
  }

  /**
   * @return the full path of this jar
   */
  public static URI getJar() throws URISyntaxException {
    return FilesUtil.class.getProtectionDomain().getCodeSource().getLocation().toURI();
  }

  /**
   * Restarts the application. The method fetches the installed Java binaries and then the jar. Once
   * all has been found, a new process is created for the new application and the current one is
   * closed by calling {@code System.exit(0)}.
   * 
   * @param executableExtension the extension of the executable file to run. If null, the ".jar"
   *          extension is used
   * @throws IOException if the executable or Java could not be found
   * @throws URISyntaxException if the jar path is ill-formed
   * @note The method does not work when running in an IDE like Eclipse as the classes are not
   *       packed in a jar.
   */
  public static void restartApplication(String executableExtension) throws IOException, URISyntaxException {
    String javaBin = System.getProperty("java.home") + File.separator + "bin" + File.separator + "java";
    File currentJar = new File(JarUtil.getJar());

    // Build command: java -jar <application>.jar
    List<String> command = new ArrayList<>();
    command.add(javaBin);
    command.add("-jar");
    command.add(currentJar.getPath());

    if (!currentJar.getName().endsWith(executableExtension != null ? executableExtension : ".jar"))
      throw new IOException("unable to find executable");

    ProcessBuilder builder = new ProcessBuilder(command);
    builder.start();
    System.exit(0);
  }

  private JarUtil() {}
}
