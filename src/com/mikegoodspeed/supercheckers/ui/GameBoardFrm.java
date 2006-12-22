package com.mikegoodspeed.supercheckers.ui;

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

import com.mikegoodspeed.supercheckers.datastructures.Board;
import com.mikegoodspeed.supercheckers.datastructures.Move;
import com.mikegoodspeed.supercheckers.datastructures.Spot;
import com.mikegoodspeed.supercheckers.datastructures.Team;
import com.mikegoodspeed.supercheckers.utils.GUIInput;

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
	private Team currTeam = Team.X;
	private GUIInput input = new GUIInput();
	private boolean listenForInput = false;
	private Spot hoverSpot = null;

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
		public void mouseClicked(MouseEvent e) {}

		public void mouseEntered(MouseEvent e) {
			String[] loc = ((JLabel) e.getSource()).getName().split(",");
			int row = new Integer(loc[0]).intValue();
			int col = new Integer(loc[1]).intValue();
			if (board.isAvailableSpot(currTeam, input.getMove(), row, col)) {
				setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			hoverSpot = new Spot(row, col);
		}

		public void mouseExited(MouseEvent e) {
			setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			hoverSpot = null;
		}

		public void mousePressed(MouseEvent e) {}

		public void mouseReleased(MouseEvent e) {
			Move move = getMove();
			JLabel source = ((JLabel) e.getSource());
			String[] loc = source.getName().split(",");
			int row = new Integer(loc[0]).intValue();
			int col = new Integer(loc[1]).intValue();
			if (listenForInput && new Spot(row, col).equals(hoverSpot) 
					&& board.isAvailableSpot(currTeam, move, row, col)) {
				if (move.size() == 0) {
					source.setIcon(Team.NOBODY.getIcon(board.isInMiddle(row, col)));
				} else {
					source.setIcon(currTeam.getIcon(board.isInMiddle(row, col)));
					int oldRow = move.getRow(move.size() - 1);
					int oldCol = move.getCol(move.size() - 1);
					Board clone = board.clone();
					clone.doMove(currTeam, move);
					if (clone.isValidJump(currTeam, oldRow, oldCol, row, col)) {
						JLabel oldSpot = getButtonByName(oldRow, oldCol);
						oldSpot.setIcon(Team.NOBODY.getIcon(clone.isInMiddle(oldRow, oldCol)));
						int jumpedRow = (oldRow + row) / 2;
						int jumpedCol = (oldCol + col) / 2;
						JLabel jumpedSpot = getButtonByName(jumpedRow, jumpedCol);
						if (!currTeam.equals(clone.get(jumpedRow, jumpedCol))) {
							jumpedSpot.setIcon(Team.NOBODY.getIcon(
									clone.isInMiddle(jumpedRow, jumpedCol)));
						}
					}
				}
				setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				input.addSpot(row, col);
				if (getMove().size() >= 2) {
					getSubmitBtn().setEnabled(true);
				}
				getResetBtn().setEnabled(true);
			}
		}
	};
	private ActionListener submitBtnActionListener = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			input.setReady();
			getSubmitBtn().setEnabled(false);
			getResetBtn().setEnabled(false);
		}
	};
	private ActionListener resetBtnActionListener = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			input.clearMove();
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

	private JLabel getButtonByName(int rowName, int colName) {
		String name = String.valueOf(rowName) + "," + String.valueOf(colName);
		for (int row = 0; row < 8; row++) {
			for (int col = 0; col < 8; col++) {
				if (name.equals(buttons[row][col].getName())) {
					return buttons[row][col];
				}
			}
		}
		return null;
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
	 * Returns user-chosen move after the submit button has been pressed.
	 *
	 * @return the move as chosen by the user
	 */
	public Move getMove() {
		return input.getMove();
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
	 * This method initializes turnPnl
	 *
	 * @return JPanel
	 */
	private JPanel getTurnPnl() {
		if (turnPnl == null) {
			turnLbl = new JLabel();
			turnLbl.setIcon(Team.X.getIcon(false));
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

	private void populateBoardPnl() {
		buttons = new JLabel[8][8];
		for (int row = 0; row < 8; row++) {
			for (int col = 0; col < 8; col++) {
				buttons[row][col] = new JLabel();
				buttons[row][col].setName(row + "," + col);
				buttons[row][col].addMouseListener(buttonMouseListener);
				boardPnl.add(buttons[row][col]);
			}
		}
		updateBoard(board);
	}

	/**
	 * Set the turn so the game board reflects who'se turn it is.
	 *
	 * @param team
	 */
	public void setTurn(Team team) {
		this.currTeam = team;
		turnLbl.setIcon(team.getIcon(false));
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
				buttons[row][col].setIcon(board.get(row, col).getIcon(board.isInMiddle(row, col)));
			}
		}
	}

	/**
	 * This method blocks until the submit button is pressed.
	 */
	public void waitForInput() {
		listenForInput = true;
		input = new GUIInput();
		input.start();
		try {
			input.join();
		} catch (Exception e) {}
		listenForInput = false;
		getSubmitBtn().setEnabled(false);
		getResetBtn().setEnabled(false);
		getBoardPnl().requestFocus();
	}
}
