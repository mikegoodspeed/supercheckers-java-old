package com.supercheckers.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

import com.supercheckers.Supercheckers;
import com.supercheckers.constants.SCConstants;
import com.supercheckers.datastructures.Team;

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

	private Supercheckers manager = null;
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
	private JPanel boardPnl = null;
	private JLabel[][] buttons = null;
	private JButton submitBtn = null;
	private JButton resetBtn = null;
	private JPanel MovePnl = null;
	private JPanel turnPnl = null;
	private JLabel turnLbl = null;
	
	private MouseListener buttonMouseListener = new MouseListener() {
		public void mouseEntered(MouseEvent e) {}
		public void mouseExited(MouseEvent e) {}
		public void mousePressed(MouseEvent e) {}
		public void mouseReleased(MouseEvent e) {}
		public void mouseClicked(MouseEvent e) {
			String[] loc = ((JLabel) e.getSource()).getName().split(",");
			System.out.println("clicked on: " + loc[0] + "," + loc[1]);
		}
	};

	/**
	 * This method initializes
	 *
	 */
	public GameBoardFrm(Supercheckers manager) {
		super();
		this.manager = manager;
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {}
		initialize();
	}

	/**
	 * This method initializes this
	 *
	 */
	private void initialize() {
		this.setSize(new Dimension(342, 458));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setJMenuBar(getMenu());
		this.setContentPane(getContent());
		this.setTitle("Supercheckers");
		this.setLocationRelativeTo(null);
	}

	/**
	 * This method initializes content
	 *
	 * @return javax.swing.JPanel
	 */
	private JPanel getContent() {
		if (content == null) {
			content = new JPanel();
			content.setLayout(null);
			content.add(getBoardPnl(), null);
			content.add(getTurnPnl(), null);
			content.add(getMovePnl(), null);
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

	/**
	 * This method initializes boardPnl
	 *
	 * @return javax.swing.JPanel
	 */
	private JPanel getBoardPnl() {
		if (boardPnl == null) {
			GridLayout gridLayout = new GridLayout();
			gridLayout.setRows(8);
			gridLayout.setColumns(8);
			boardPnl = new JPanel();
			boardPnl.setBounds(new Rectangle(0, 0, 336, 336));
			boardPnl.setLayout(gridLayout);
			populateBoardPnl();
		}
		return boardPnl;
	}

	/**
	 * This method initializes submitBtn
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getSubmitBtn() {
		if (submitBtn == null) {
			submitBtn = new JButton();
			submitBtn.setMnemonic(KeyEvent.VK_E);
			submitBtn.setText("Submit");
		}
		return submitBtn;
	}

	/**
	 * This method initializes resetBtn
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getResetBtn() {
		if (resetBtn == null) {
			resetBtn = new JButton();
			resetBtn.setText("Reset");
			resetBtn.setEnabled(false);
		}
		return resetBtn;
	}

	/**
	 * This method initializes MovePnl
	 *
	 * @return javax.swing.JPanel
	 */
	private JPanel getMovePnl() {
		if (MovePnl == null) {
			MovePnl = new JPanel();
			MovePnl.setLayout(new BoxLayout(getMovePnl(), BoxLayout.X_AXIS));
			MovePnl.setBorder(BorderFactory.createTitledBorder(null, "Move", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Tahoma", Font.PLAIN, 11), new Color(51, 94, 168)));
			MovePnl.setBounds(new Rectangle(192, 336, 143, 73));
			MovePnl.add(getResetBtn(), null);
			MovePnl.add(getSubmitBtn(), null);
		}
		return MovePnl;
	}

	/**
	 * This method initializes turnPnl
	 *
	 * @return javax.swing.JPanel
	 */
	private JPanel getTurnPnl() {
		if (turnPnl == null) {
			GridBagConstraints gridBagConstraints = new GridBagConstraints();
			gridBagConstraints.gridx = 0;
			gridBagConstraints.gridy = 0;
			turnLbl = new JLabel();
			turnLbl.setIcon(SCConstants.OUTSIDE_TEAM1);
			turnPnl = new JPanel();
			turnPnl.setLayout(new GridBagLayout());
			turnPnl.setBounds(new Rectangle(0, 336, 73, 73));
			turnPnl.setBorder(BorderFactory.createTitledBorder(null, "Turn", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Tahoma", Font.PLAIN, 11), new Color(51, 94, 168)));
			turnPnl.add(turnLbl, gridBagConstraints);
		}
		return turnPnl;
	}

	private void populateBoardPnl() {
		buttons = new JLabel[8][8];
		for (int row = 0; row <= 8 - 1; row++) {
			for (int col = 0; col <= 8 - 1 ; col++) {
				buttons[row][col] = new JLabel();
				buttons[row][col].setName(row + "," + col);
				buttons[row][col].addMouseListener(buttonMouseListener);
				boardPnl.add(buttons[row][col]);
			}
		}
		updateBoardPnl();
	}

	public void updateBoardPnl() {
		for (int row = 0; row < 8; row++) {
			for (int col = 0; col < 8; col++) {
				Team t = manager.getBoard().get(row, col);
				if (row < 2 || row >= 6 || col < 2 || col >= 6) { // not center area
					if (SCConstants.TEAM1.equals(t)) {
						buttons[row][col].setIcon(SCConstants.OUTSIDE_TEAM1);
					} else if (SCConstants.TEAM2.equals(t)) {
						buttons[row][col].setIcon(SCConstants.OUTSIDE_TEAM2);
					} else {
						buttons[row][col].setIcon(SCConstants.OUTSIDE_EMPTY);
					}
				} else { // center area
					if (SCConstants.TEAM1.equals(t)) {
						buttons[row][col].setIcon(SCConstants.INSIDE_TEAM1);
					} else if (SCConstants.TEAM2.equals(t)) {
						buttons[row][col].setIcon(SCConstants.INSIDE_TEAM2);
					} else {
						buttons[row][col].setIcon(SCConstants.INSIDE_EMPTY);
					}
				}
			}
		}
	}
	
	public void setTurn(Team t) {
		if (SCConstants.TEAM1.equals(t)) {
			turnLbl.setIcon(SCConstants.OUTSIDE_TEAM1);
		} else {
			turnLbl.setIcon(SCConstants.OUTSIDE_TEAM2);
		}
	}
}  //  @jve:decl-index=0:visual-constraint="71,13"