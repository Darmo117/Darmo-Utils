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
package net.darmo_creations.utils.events;

/**
 * Base class for all events.
 *
 * @author Damien Vergnet
 */
public abstract class AbstractEvent {
  private final boolean cancellable;
  private boolean cancelled;

  /**
   * Creates an event. It will be set to not canceled.
   */
  protected AbstractEvent(boolean cancellable) {
    this.cancellable = cancellable;
    this.cancelled = false;
  }

  /**
   * @return true if this event can be cancelled
   */
  public final boolean isCancellable() {
    return this.cancellable;
  }

  /**
   * @return true if this event has been cancelled
   */
  public final boolean isCancelled() {
    return this.cancelled;
  }

  /**
   * Cancels this event. If this event is not cancellable, an {@link IllegalStateException} will be
   * thrown.
   * 
   * @throws IllegalStateException if this event is not cancellable
   */
  public final void setCancelled() throws IllegalStateException {
    if (!isCancellable())
      throw new IllegalStateException("cannot cancel non-cancellable event " + getClass().getSimpleName());
    this.cancelled = true;
  }
}
