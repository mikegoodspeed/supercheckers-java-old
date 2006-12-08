package com.supercheckers.ui;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.UIManager;

import com.supercheckers.Supercheckers;
import com.supercheckers.constants.SCConstants;
import com.supercheckers.datastructures.Board;
import com.supercheckers.datastructures.Team;
import com.supercheckers.utils.GUIInput;

/**
 * Supercheckers Game Board
 *
 * @author Mike Goodspeed
 * @url http://www.mikegoodspeed.com/blog/projects/supercheckers/
 * @version $Id$
 * @headurl $HeadURL$
 */
public class GameBoardFrm extends JFrame {

	private static final long serialVersionUID = -4212234804742120343L;

	private Supercheckers manager = null;
	private JPanel content = null;
	private JMenuBar menu = null;
	private JMenu gameMnu = null;
	private JMenuItem newMnuItem = null;
	private JMenuItem exitMnuItem = null;
	private JMenu helpMnu = null;
	private JMenuItem aboutMnuItem = null;
	private JPanel boardPnl = null;
	private JPanel uiPanel = null;
	private JLabel[][] buttons = null;
	private JButton submitBtn = null;
	private JButton resetBtn = null;
	private JPanel MovePnl = null;
	private JPanel turnPnl = null;
	private JLabel turnLbl = null;
	private GUIInput inputListener = new GUIInput();
	private boolean listenForInput = false;

	private ActionListener newMnuItemActionListener = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			System.out.println("show new game dialog");
		}
	};
	private ActionListener exitMnuItemActionListener = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
		}
	};
	private MouseListener buttonMouseListener = new MouseListener() {
		public void mouseEntered(MouseEvent e) {
			JLabel source = ((JLabel) e.getSource());
			if (getCoordinates().size() == 0) {
				if (SCConstants.OUTSIDE_TEAM1.equals(turnLbl.getIcon())) {
					if (SCConstants.OUTSIDE_TEAM1.equals(source.getIcon()) ||
							SCConstants.INSIDE_TEAM1.equals(source.getIcon())) {
						setCursor(new Cursor(Cursor.HAND_CURSOR));
					}
				} else {
					if (SCConstants.OUTSIDE_TEAM2.equals(source.getIcon()) ||
							SCConstants.INSIDE_TEAM2.equals(source.getIcon())) {
						setCursor(new Cursor(Cursor.HAND_CURSOR));
					}
				}
			} else {
				if (SCConstants.OUTSIDE_EMPTY.equals(source.getIcon()) ||
						SCConstants.INSIDE_EMPTY.equals(source.getIcon())) {
					setCursor(new Cursor(Cursor.HAND_CURSOR));
				}
			}
		}
		public void mouseExited(MouseEvent e) {
			setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		}
		public void mousePressed(MouseEvent e) {}
		public void mouseReleased(MouseEvent e) {}
		public void mouseClicked(MouseEvent e) {
			if (listenForInput) {
				JLabel source = ((JLabel) e.getSource());
				String name = source.getName();
				String[] loc = name.split(",");
				int row = new Integer(loc[0]).intValue();
				int col = new Integer(loc[1]).intValue();
				if (inputListener.getCoordinates().size() == 0) {
					if (Board.isInCenter(row, col)) {
						source.setIcon(SCConstants.INSIDE_EMPTY);
					} else {
						source.setIcon(SCConstants.OUTSIDE_EMPTY);
					}
				} else {
					if (SCConstants.OUTSIDE_TEAM1.equals(turnLbl.getIcon())) {
						if (Board.isInCenter(row, col)) {
							source.setIcon(SCConstants.INSIDE_TEAM1);
						} else {
							source.setIcon(SCConstants.OUTSIDE_TEAM1);
						}
					} else {
						if (Board.isInCenter(row, col)) {
							source.setIcon(SCConstants.INSIDE_TEAM2);
						} else {
							source.setIcon(SCConstants.OUTSIDE_TEAM2);
						}
					}
				}
				setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				inputListener.addCoordinate(name);
				if (inputListener.getCoordinates().size() >= 2) {
					getSubmitBtn().setEnabled(true);
				}
				getResetBtn().setEnabled(true);
			}
		}
	};
	private ActionListener submitBtnActionListener = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			inputListener.setReady();
			getSubmitBtn().setEnabled(false);
			getResetBtn().setEnabled(false);
		}
	};
	private ActionListener resetBtnActionListener = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			inputListener.clearCoordinates();
			updateBoardPnl();
			getSubmitBtn().setEnabled(false);
			getResetBtn().setEnabled(false);
			getBoardPnl().requestFocus();
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
		pack();
	}

	/**
	 * This method initializes this
	 *
	 */
	private void initialize() {
		this.setSize(new Dimension(400, 400));
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
			content.setLayout(new BorderLayout());
			content.add(getBoardPnl(), BorderLayout.WEST);
			content.add(getUiPanel(), BorderLayout.EAST);
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
			exitMnuItem.addActionListener(exitMnuItemActionListener);
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
			newMnuItem.setText("New...");
			newMnuItem.setMnemonic(KeyEvent.VK_N);
			newMnuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F2, 0));
			newMnuItem.addActionListener(newMnuItemActionListener);
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
			submitBtn.setMnemonic(KeyEvent.VK_S);
			submitBtn.setText("Submit");
			submitBtn.setEnabled(false);
			submitBtn.addActionListener(submitBtnActionListener);
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
			resetBtn.setMnemonic(KeyEvent.VK_R);
			resetBtn.setEnabled(false);
			resetBtn.addActionListener(resetBtnActionListener);
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
			MovePnl.setLayout(new BorderLayout());
			MovePnl.setBorder(BorderFactory.createTitledBorder("Move"));
			MovePnl.add(getSubmitBtn(), BorderLayout.CENTER);
			MovePnl.add(getResetBtn(), BorderLayout.WEST);
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
			turnLbl = new JLabel();
			turnLbl.setIcon(SCConstants.OUTSIDE_TEAM1);
			turnPnl = new JPanel();
			turnPnl.setLayout(new BorderLayout());
			turnPnl.setBorder(BorderFactory.createTitledBorder("Turn"));
			turnPnl.add(turnLbl, BorderLayout.WEST);
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
				if (!Board.isInCenter(row, col)) {
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

	public void waitForInput() {
		listenForInput = true;
		inputListener = new GUIInput();
		inputListener.start();
		try {
			inputListener.join();
		} catch (Exception e) {}
		listenForInput = false;
		getSubmitBtn().setEnabled(false);
		getResetBtn().setEnabled(false);
		getBoardPnl().requestFocus();
	}
	
	public List<String> getCoordinates() {
		return inputListener.getCoordinates();
	}

	/**
	 * This method initializes uiPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getUiPanel() {
		if (uiPanel == null) {
			uiPanel = new JPanel();
			uiPanel.setLayout(new BorderLayout());
			uiPanel.add(getTurnPnl(), BorderLayout.NORTH);
			uiPanel.add(getMovePnl(), BorderLayout.SOUTH);
		}
		return uiPanel;
	}
}  //  @jve:decl-index=0:visual-constraint="71,13"
