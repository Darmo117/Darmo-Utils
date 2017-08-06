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
package net.darmo_creations.utils.version;

/**
 * This exception is thrown when a version mismatch happens.
 *
 * @author Damien Vergnet
 */
public class VersionException extends Exception {
  private static final long serialVersionUID = 6117608149659458527L;

  private Version targetVersion, actualVersion;

  /**
   * Builds an exception.
   * 
   * @param targetVersion the target version
   * @param actualVersion the ectual version
   */
  public VersionException(Version targetVersion, Version actualVersion) {
    super("version mismatch: target '" + targetVersion + "', actual '" + actualVersion + "'");
    this.targetVersion = targetVersion;
    this.actualVersion = actualVersion;
  }

  public Version getTargetVersion() {
    return this.targetVersion;
  }

  public Version getActualVersion() {
    return this.actualVersion;
  }
}
