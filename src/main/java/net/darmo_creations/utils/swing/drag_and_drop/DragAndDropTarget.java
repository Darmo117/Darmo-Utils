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
package net.darmo_creations.utils.swing.drag_and_drop;

/**
 * A drag-and-drop target will notify its listeners when a drag-and-drop event occurs.
 *
 * @author Damien Vergnet
 */
public interface DragAndDropTarget {
  /**
   * Adds a {@link DragAndDropListener}.
   * 
   * @param l the listener
   */
  void addDragAndDropListener(DragAndDropListener l);
}
