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
package net.darmo_creations.utils.swing;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 * An image label can display images and resizes them to fit inside its bounds.
 *
 * @author Damien Vergnet
 */
public class ImageLabel extends JLabel {
  private static final long serialVersionUID = 3710328037282006238L;

  private boolean keepRatio;

  /**
   * Creates a label with the specified image and horizontal alignment. The image is centered
   * vertically in the display area and the ratio is conserved.
   * 
   * @param image the image to display
   * @param horizontalAlignment one of the following constants defined in SwingConstants: LEFT,
   *          CENTER, RIGHT.
   */
  public ImageLabel(ImageIcon image, int horizontalAlignment) {
    this(image, horizontalAlignment, true);
  }

  /**
   * Creates a label with the specified image. The horizontal alignement is set to CENTER. The image
   * is centered vertically in its display area.
   * 
   * @param image the image to display
   * @param keepRatio if true the image's ratio will be conserved; otherwise the image will be
   *          streched to fill the whole label
   */
  public ImageLabel(ImageIcon image, boolean keepRatio) {
    this(image, JLabel.CENTER, keepRatio);
  }

  private ImageLabel(ImageIcon image, int horizontalAlignment, boolean keepRatio) {
    super(image, horizontalAlignment);
    if (horizontalAlignment != CENTER && horizontalAlignment != LEFT && horizontalAlignment != RIGHT)
      throw new IllegalArgumentException(Integer.toString(horizontalAlignment));
    this.keepRatio = keepRatio;
  }

  @Override
  protected final void paintComponent(Graphics g) {
    ImageIcon icon = (ImageIcon) getIcon();

    if (icon != null) {
      Graphics2D g2 = (Graphics2D) g;
      Dimension size = getSize();
      int newX = 0;
      int newY = 0;
      int newW = size.width;
      int newH = size.height;

      if (this.keepRatio) {
        float zoom = 1;
        int imgW = icon.getIconWidth();
        int imgH = icon.getIconHeight();

        if (imgH > size.height) {
          zoom = (float) size.height / imgH;
        }
        if (imgW * zoom > size.width) {
          zoom = (float) size.width / imgW;
        }
        newW = (int) (zoom * imgW);
        newH = (int) (zoom * imgH);

        switch (getHorizontalAlignment()) {
          case LEFT:
            newX = 0;
            break;
          case RIGHT:
            newX = size.width - newW;
            break;
          case CENTER:
            newX = (size.width - newW) / 2;
            break;
        }
        newY = (size.height - newH) / 2;
      }

      g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
      g2.drawImage(icon.getImage(), newX, newY, newW, newH, null);
      paintComponent(g2, newX, newY, newW, newH);
      g2.dispose();
    }

    super.paintComponent(g);
  }

  /**
   * This method is called right after the image has been drawn.
   * 
   * @param g the graphics context
   * @param x image x
   * @param y image y
   * @param w image width
   * @param h image height
   */
  protected void paintComponent(Graphics2D g, int x, int y, int w, int h) {}
}
