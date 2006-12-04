package com.supercheckers.ui;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;

/**
 * Supercheckers Game Board
 * 
 * @author Mike Goodspeed
 * @url http://www.mikegoodspeed.com/blog/projects/supercheckers/
 * @id $Id$
 * @headurl $HeadURL$
 */
public class GameBoardFrm extends JFrame {

	private static final long serialVersionUID = -4212234804742120343L;
	private JPanel content = null;
	private JMenuBar menu = null;
	private JMenu gameMnu = null;
	private JMenuItem newMnuItem = null;
	private JCheckBoxMenuItem easyMnuItem = null;
	private JCheckBoxMenuItem mediumMnuItem = null;
	private JCheckBoxMenuItem hardMnuItem = null;
	private JCheckBoxMenuItem multiplayerMnuItem = null;
	private JMenuItem exitMnuItem = null;
	private JMenu helpMnu = null;
	private JMenuItem aboutMnuItem = null;
	private JPanel p1Pnl = null;
	private JLabel p1ImgLbl = null;
	private JLabel p1NameLbl = null;
	private JLabel p1TypeLbl = null;
	private JLabel titleLbl = null;
	private JPanel p2Pnl = null;
	private JLabel p2ImgLbl = null;
	private JLabel p2NameLbl = null;
	private JLabel p2TypeLbl = null;
	/**
	 * This method initializes 
	 * 
	 */
	public GameBoardFrm() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 */
	private void initialize() {
		this.setSize(new Dimension(590, 475));
		this.setResizable(false);
		this.setJMenuBar(getMenu());
		this.setContentPane(getContent());
		this.setTitle("Supercheckers");

	}

	/**
	 * This method initializes content	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getContent() {
		if (content == null) {
			titleLbl = new JLabel();
			titleLbl.setBounds(new Rectangle(180, 4, 225, 49));
			titleLbl.setHorizontalAlignment(SwingConstants.CENTER);
			titleLbl.setFont(new Font("Tahoma", Font.BOLD, 24));
			titleLbl.setText("Supercheckers");
			content = new JPanel();
			content.setLayout(null);
			content.add(getP1Pnl(), null);
			content.add(titleLbl, null);
			content.add(getP2Pnl(), null);
		}
		return content;
	}

	/**
	 * This method initializes menu	
	 * 	
	 * @return javax.swing.JMenuBar	
	 */
	private JMenuBar getMenu() {
		if (menu == null) {
			menu = new JMenuBar();
			menu.add(getGameMnu());
			menu.add(getHelpMnu());
		}
		return menu;
	}

	/**
	 * This method initializes fileMnu	
	 * 	
	 * @return javax.swing.JMenu	
	 */
	private JMenu getGameMnu() {
		if (gameMnu == null) {
			gameMnu = new JMenu();
			gameMnu.setText("Game");
			gameMnu.setMnemonic(KeyEvent.VK_G);
			gameMnu.add(getNewMnuItem());
			gameMnu.addSeparator();
			gameMnu.add(getEasyMnuItem());
			gameMnu.add(getMediumMnuItem());
			gameMnu.add(getHardMnuItem());
			gameMnu.add(getMultiplayerMnuItem());
			gameMnu.addSeparator();
			gameMnu.add(getExitMnuItem());
		}
		return gameMnu;
	}

	/**
	 * This method initializes helpMnu	
	 * 	
	 * @return javax.swing.JMenu	
	 */
	private JMenu getHelpMnu() {
		if (helpMnu == null) {
			helpMnu = new JMenu();
			helpMnu.setText("Help");
			helpMnu.setMnemonic(KeyEvent.VK_H);
			helpMnu.add(getAboutMnuItem());
		}
		return helpMnu;
	}

	/**
	 * This method initializes exitMnuItem	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getExitMnuItem() {
		if (exitMnuItem == null) {
			exitMnuItem = new JMenuItem();
			exitMnuItem.setText("Exit");
			exitMnuItem.setMnemonic(KeyEvent.VK_X);
		}
		return exitMnuItem;
	}

	/**
	 * This method initializes newMnuItem	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getNewMnuItem() {
		if (newMnuItem == null) {
			newMnuItem = new JMenuItem();
			newMnuItem.setText("New");
			newMnuItem.setMnemonic(KeyEvent.VK_N);
		}
		return newMnuItem;
	}

	/**
	 * This method initializes aboutMnuItem	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getAboutMnuItem() {
		if (aboutMnuItem == null) {
			aboutMnuItem = new JMenuItem();
			aboutMnuItem.setText("About Supercheckers");
			aboutMnuItem.setMnemonic(KeyEvent.VK_A);
		}
		return aboutMnuItem;
	}

	/**
	 * This method initializes p1Pnl	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getP1Pnl() {
		if (p1Pnl == null) {
			p1TypeLbl = new JLabel();
			p1TypeLbl.setText("Type: Human");
			p1TypeLbl.setBounds(new Rectangle(52, 28, 117, 17));
			p1NameLbl = new JLabel();
			p1NameLbl.setFont(new Font("Tahoma", Font.BOLD, 14));
			p1NameLbl.setBounds(new Rectangle(52, 4, 117, 21));
			p1NameLbl.setText("Player 1");
			p1ImgLbl = new JLabel();
			p1ImgLbl.setIcon(new ImageIcon("images/outside_green.jpg"));
			p1ImgLbl.setBounds(new Rectangle(4, 4, 41, 41));
			p1Pnl = new JPanel();
			p1Pnl.setLayout(null);
			p1Pnl.setBounds(new Rectangle(4, 4, 173, 49));
			p1Pnl.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
			p1Pnl.add(p1ImgLbl, null);
			p1Pnl.add(p1NameLbl, null);
			p1Pnl.add(p1TypeLbl, null);
		}
		return p1Pnl;
	}

	/**
	 * This method initializes p2Pnl	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getP2Pnl() {
		if (p2Pnl == null) {
			p2TypeLbl = new JLabel();
			p2TypeLbl.setBounds(new Rectangle(48, 28, 121, 17));
			p2TypeLbl.setText("Type: Medium Computer");
			p2NameLbl = new JLabel();
			p2NameLbl.setBounds(new Rectangle(48, 4, 121, 21));
			p2NameLbl.setFont(new Font("Tahoma", Font.BOLD, 14));
			p2NameLbl.setText("Player 2");
			p2ImgLbl = new JLabel();
			p2ImgLbl.setBounds(new Rectangle(4, 4, 41, 41));
			p2ImgLbl.setIcon(new ImageIcon("images/outside_orange.jpg"));
			p2Pnl = new JPanel();
			p2Pnl.setLayout(null);
			p2Pnl.setBounds(new Rectangle(408, 4, 173, 49));
			p2Pnl.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
			p2Pnl.add(p2ImgLbl, null);
			p2Pnl.add(p2NameLbl, null);
			p2Pnl.add(p2TypeLbl, null);
		}
		return p2Pnl;
	}

	/**
	 * This method initializes easyMnuItem	
	 * 	
	 * @return javax.swing.JCheckBoxMenuItem	
	 */
	private JCheckBoxMenuItem getEasyMnuItem() {
		if (easyMnuItem == null) {
			easyMnuItem = new JCheckBoxMenuItem();
			easyMnuItem.setText("Easy");
		}
		return easyMnuItem;
	}

	/**
	 * This method initializes mediumMnuItem	
	 * 	
	 * @return javax.swing.JCheckBoxMenuItem	
	 */
	private JCheckBoxMenuItem getMediumMnuItem() {
		if (mediumMnuItem == null) {
			mediumMnuItem = new JCheckBoxMenuItem();
			mediumMnuItem.setText("Medium");
			mediumMnuItem.setSelected(true);
		}
		return mediumMnuItem;
	}

	/**
	 * This method initializes hardMnuItem	
	 * 	
	 * @return javax.swing.JCheckBoxMenuItem	
	 */
	private JCheckBoxMenuItem getHardMnuItem() {
		if (hardMnuItem == null) {
			hardMnuItem = new JCheckBoxMenuItem();
			hardMnuItem.setText("Hard");
		}
		return hardMnuItem;
	}

	/**
	 * This method initializes multiplayerMnuItem	
	 * 	
	 * @return javax.swing.JCheckBoxMenuItem	
	 */
	private JCheckBoxMenuItem getMultiplayerMnuItem() {
		if (multiplayerMnuItem == null) {
			multiplayerMnuItem = new JCheckBoxMenuItem();
			multiplayerMnuItem.setText("Multiplayer");
		}
		return multiplayerMnuItem;
	}

}  //  @jve:decl-index=0:visual-constraint="4,12"
