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

import java.awt.BorderLayout;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * This class displays demonstrates the use of the ImageLabel.
 *
 * @author Damien Vergnet
 */
public class TestImageLabel {
  public static void main(String[] args) throws IOException {
    JFrame f = new JFrame("ImageLabel Test");

    f.add(new ImageLabel(new ImageIcon(ImageIO.read(TestImageLabel.class.getResourceAsStream("lena.bmp"))), false), BorderLayout.NORTH);
    f.add(new ImageLabel(new ImageIcon(ImageIO.read(TestImageLabel.class.getResourceAsStream("lena.bmp"))), true), BorderLayout.CENTER);
    f.add(new ImageLabel(new ImageIcon(ImageIO.read(TestImageLabel.class.getResourceAsStream("lena.bmp"))), JLabel.LEFT),
        BorderLayout.WEST);
    f.add(new ImageLabel(new ImageIcon(ImageIO.read(TestImageLabel.class.getResourceAsStream("lena.bmp"))), JLabel.RIGHT),
        BorderLayout.EAST);

    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    f.pack();
    f.setLocationRelativeTo(null);
    f.setVisible(true);
  }
}
