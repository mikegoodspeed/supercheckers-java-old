/*
 * Supercheckers - the game of Kings Court
 * Copyright (C) 2002-2007 Mike Goodspeed
 * 
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */
package com.mikegoodspeed.supercheckers.ui;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

/**
 * Status Bar
 * <p>
 * project Supercheckers <br />
 * url http://www.mikegoodspeed.com/blog/projects/supercheckers/
 *
 * @author Mike Goodspeed
 * @version $Id$
 */
public class JStatusBar extends JPanel {

	private static final long serialVersionUID = 2305619542836254904L;
	
	private JLabel message = new JLabel();

	protected JStatusBar() {
		super();
		setBorder(new BevelBorder(BevelBorder.LOWERED));
		add(message);
		clear();
	}
	
	/**
	 * Clear the status bar text
	 */
	public void clear() {
		setText(" ");
	}
	
	/**
	 * Set the status bar text
	 * 
	 * @param text message to be displayed
	 */
	public void setText(String text) {
		message.setText(" " + text);
	}
}
