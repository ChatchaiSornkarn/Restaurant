/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restaurant;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JTextPane;
import javax.swing.JButton;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import javax.swing.Action;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JDesktopPane;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.Choice;
import javax.swing.JCheckBox;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Checkbox;
import java.awt.List;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JProgressBar;

public class ResJframe {

	public JFrame frame;
	private final Action action = new SwingAction();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ResJframe window = new ResJframe();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ResJframe() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JTextPane txtpnClickshowTo = new JTextPane();
		txtpnClickshowTo.setBounds(0, 0, 434, 32);
		txtpnClickshowTo.setEditable(false);
		txtpnClickshowTo.setText("Click \"Show\" to find resturants in Lindholmen \n Select from the avilable options");
		frame.getContentPane().add(txtpnClickshowTo);
		
		JButton btnShow = new JButton("Show");
		btnShow.setBounds(0, 211, 434, 32);
		frame.getContentPane().add(btnShow);
		
		Checkbox checkbox = new Checkbox("New check box");
		checkbox.setBounds(12, 75, 95, 22);
		frame.getContentPane().add(checkbox);
		
		Checkbox checkbox_1 = new Checkbox("New check box");
		checkbox_1.setBounds(12, 103, 95, 22);
		frame.getContentPane().add(checkbox_1);
		
		Checkbox checkbox_2 = new Checkbox("New check box");
		checkbox_2.setBounds(12, 131, 107, 22);
		frame.getContentPane().add(checkbox_2);
		
		Checkbox checkbox_3 = new Checkbox("New check box");
		checkbox_3.setBounds(12, 47, 107, 22);
		frame.getContentPane().add(checkbox_3);
		
		JRadioButtonMenuItem rdbtnmntmWhatever = new JRadioButtonMenuItem("Whatever");
		rdbtnmntmWhatever.setBounds(299, 47, 125, 22);
		frame.getContentPane().add(rdbtnmntmWhatever);
		
		JRadioButtonMenuItem rdbtnmntmWhatever_1 = new JRadioButtonMenuItem("Whatever2");
		rdbtnmntmWhatever_1.setBounds(299, 75, 125, 22);
		frame.getContentPane().add(rdbtnmntmWhatever_1);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmWhatever = new JMenuItem("Whatever");
		mnFile.add(mntmWhatever);
		
		JMenu mnSettings = new JMenu("Settings");
		menuBar.add(mnSettings);
		
		JMenuItem mntmChange = new JMenuItem("Change ");
		mnSettings.add(mntmChange);
		
		JMenu mnActions = new JMenu("Actions");
		menuBar.add(mnActions);
		
		JMenuItem mntmShow = new JMenuItem("Show");
		mnActions.add(mntmShow);
	}

	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "SwingAction");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
		}
	}
	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
}
