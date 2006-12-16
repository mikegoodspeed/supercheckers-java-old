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

import com.supercheckers.constants.SCConstants;
import com.supercheckers.datastructures.Board;
import com.supercheckers.datastructures.Move;
import com.supercheckers.datastructures.Team;
import com.supercheckers.utils.GUIInput;

/**
 * Supercheckers Game Board
 * <p>
 * project Supercheckers <br />
 * url http://www.mikegoodspeed.com/blog/projects/supercheckers/
 * 
 * @author Mike Goodspeed
 * @version $Id$
 */
public class GameBoardFrm extends JFrame {

	private static final long serialVersionUID = -4212234804742120343L;

	private Board board = null;
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
	private Team currTeam = SCConstants.TEAM1;
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
			String[] loc = ((JLabel) e.getSource()).getName().split(",");
			int row = new Integer(loc[0]).intValue();
			int col = new Integer(loc[1]).intValue();
			if (board.isAvailableSpot(currTeam, inputListener.getMove(), row, col)) {
				setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
		}

		public void mouseExited(MouseEvent e) {
			setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		}

		public void mousePressed(MouseEvent e) {}

		public void mouseReleased(MouseEvent e) {}

		public void mouseClicked(MouseEvent e) {
			JLabel source = ((JLabel) e.getSource());
			String name = source.getName();
			String[] loc = name.split(",");
			int row = new Integer(loc[0]).intValue();
			int col = new Integer(loc[1]).intValue();
			if (listenForInput && board.isAvailableSpot(currTeam, inputListener.getMove(), row, col)) {
				if (inputListener.getMove().size() == 0) {
					if (board.isInMiddle(row, col)) {
						source.setIcon(SCConstants.getImg(SCConstants.INSIDE_EMPTY));
					} else {
						source.setIcon(SCConstants.getImg(SCConstants.OUTSIDE_EMPTY));
					}
				} else {
					if (SCConstants.TEAM1.equals(currTeam)) {
						if (board.isInMiddle(row, col)) {
							source.setIcon(SCConstants.getImg(SCConstants.INSIDE_TEAM1));
						} else {
							source.setIcon(SCConstants.getImg(SCConstants.OUTSIDE_TEAM1));
						}
					} else {
						if (board.isInMiddle(row, col)) {
							source.setIcon(SCConstants.getImg(SCConstants.INSIDE_TEAM2));
						} else {
							source.setIcon(SCConstants.getImg(SCConstants.OUTSIDE_TEAM2));
						}
					}
				}
				setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				inputListener.addSpot(row, col);
				if (inputListener.getMove().size() >= 2) {
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
			inputListener.clearMove();
			updateBoard(board);
			getSubmitBtn().setEnabled(false);
			getResetBtn().setEnabled(false);
			getBoardPnl().requestFocus();
		}
	};

	/**
	 * This method initializes
	 * 
	 * @param board 
	 * @param manager
	 */
	public GameBoardFrm(Board board) {
		super();
		this.board = board;
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
	 * @return JPanel
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
	 * @return JMenuBar
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
	 * @return JMenu
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
	 * @return JMenu
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
	 * @return JMenuItem
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
	 * @return JMenuItem
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
	 * @return JMenuItem
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
	 * @return JPanel
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
	 * @return JButton
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
	 * @return JButton
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
	 * @return JPanel
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
	 * @return JPanel
	 */
	private JPanel getTurnPnl() {
		if (turnPnl == null) {
			turnLbl = new JLabel();
			turnLbl.setIcon(SCConstants.getImg(SCConstants.OUTSIDE_TEAM1));
			turnPnl = new JPanel();
			turnPnl.setLayout(new BorderLayout());
			turnPnl.setBorder(BorderFactory.createTitledBorder("Turn"));
			turnPnl.add(turnLbl, BorderLayout.WEST);
		}
		return turnPnl;
	}

	/**
	 * This method initializes uiPanel
	 * 
	 * @return JPanel
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

	private void populateBoardPnl() {
		buttons = new JLabel[8][8];
		for (int row = 0; row <= 8 - 1; row++) {
			for (int col = 0; col <= 8 - 1; col++) {
				buttons[row][col] = new JLabel();
				buttons[row][col].setName(row + "," + col);
				buttons[row][col].addMouseListener(buttonMouseListener);
				boardPnl.add(buttons[row][col]);
			}
		}
		updateBoard(board);
	}

	/**
	 * Update the GUI board to reflect the actual board.
	 * 
	 * @param board 
	 */
	public void updateBoard(Board board) {
		this.board = board;
		for (int row = 0; row < 8; row++) {
			for (int col = 0; col < 8; col++) {
				Team t = board.get(row, col);
				if (!board.isInMiddle(row, col)) {
					if (SCConstants.TEAM1.equals(t)) {
						buttons[row][col].setIcon(SCConstants.getImg(SCConstants.OUTSIDE_TEAM1));
					} else if (SCConstants.TEAM2.equals(t)) {
						buttons[row][col].setIcon(SCConstants.getImg(SCConstants.OUTSIDE_TEAM2));
					} else {
						buttons[row][col].setIcon(SCConstants.getImg(SCConstants.OUTSIDE_EMPTY));
					}
				} else { // center area
					if (SCConstants.TEAM1.equals(t)) {
						buttons[row][col].setIcon(SCConstants.getImg(SCConstants.INSIDE_TEAM1));
					} else if (SCConstants.TEAM2.equals(t)) {
						buttons[row][col].setIcon(SCConstants.getImg(SCConstants.INSIDE_TEAM2));
					} else {
						buttons[row][col].setIcon(SCConstants.getImg(SCConstants.INSIDE_EMPTY));
					}
				}
			}
		}
	}

	/**
	 * Set the turn so the game board reflects who'se turn it is.
	 * 
	 * @param team
	 */
	public void setTurn(Team team) {
		this.currTeam = team;
		if (SCConstants.TEAM1.equals(team)) {
			turnLbl.setIcon(SCConstants.getImg(SCConstants.OUTSIDE_TEAM1));
		} else {
			turnLbl.setIcon(SCConstants.getImg(SCConstants.OUTSIDE_TEAM2));
		}
	}

	/**
	 * This method blocks until the submit button is pressed.
	 */
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

	/**
	 * Returns user-chosen move after the submit button has been pressed.
	 * 
	 * @return the move as chosen by the user
	 */
	public Move getMove() {
		return inputListener.getMove();
	}
}
