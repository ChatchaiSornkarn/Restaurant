package restaurant;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFormattedTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JOptionPane;

public class Restaurants {

	public JFrame frame;
	public JPasswordField PassLogin;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Restaurants window = new Restaurants();
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
	public Restaurants() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setSize(800, 433);
		// frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setUndecorated(true);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.X_AXIS));

		JPanel panel = new JPanel();
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		JLabel ResButton = new JLabel("");
		ResButton.addMouseListener(new MouseAdapter() {// code later
			@Override
			public void mouseEntered(MouseEvent arg0) {
				Cursor cur1 = new Cursor(Cursor.HAND_CURSOR);
				ResButton.setCursor(cur1);
                                ImageIcon II = new ImageIcon(getClass().getResource("/restaurant/UtensilHover1.png"));
                                ResButton.setIcon(II); 
			       // code
			}
                        public void mouseExited(MouseEvent arg0){
                            ImageIcon III = new ImageIcon(getClass().getResource("/restaurant/UtensilDefault1.png"));
                            ResButton.setIcon(III);
                        }
			@Override
			public void mouseClicked(MouseEvent e) {
				JFrame test = new JFrame("test");
				test.setSize(500, 500);
				test.setVisible(true);
			}
                        
		});
                
                JLabel searchText = new JLabel("");
		searchText.setIcon(new ImageIcon(getClass().getResource("/restaurant/ResSearch.png")));
		searchText.setBounds(180, 29, 464, 400);
		panel.add(searchText);
                
		ResButton.setIcon(new ImageIcon(getClass().getResource("/restaurant/UtensilDefault1.png")));
		ResButton.setBounds(311, 135, 172, 182);
		panel.add(ResButton);
                
		ResButton.setIcon(new ImageIcon(getClass().getResource("/restaurant/UtensilDefault1.png")));
		ResButton.setBounds(311, 135, 172, 182);
		panel.add(ResButton);

		JFormattedTextField UserLogin = new JFormattedTextField();
		UserLogin.setText(" Username/Email");
                UserLogin.setBackground(Color.BLACK);
                UserLogin.setForeground(Color.WHITE);
		UserLogin.setBounds(10, 6, 122, 20);
		panel.add(UserLogin);

		PassLogin = new JPasswordField();
		PassLogin.setBounds(142, 6, 128, 20);
                PassLogin.setBackground(Color.BLACK);
                PassLogin.setForeground(Color.WHITE);
		PassLogin.setText("jjjjjjjjjjjj");
		panel.add(PassLogin);

		JButton EnterLogin = new JButton("Enter");
		EnterLogin.setBounds(280, 4, 89, 23);
		panel.add(EnterLogin);
                EnterLogin.addMouseListener(new MouseAdapter() {
                    
			@Override
			public void mouseClicked(MouseEvent e) {
                            JOptionPane.showMessageDialog(null, "This action is not yet applicable");
			}
		});
		JLabel ExitTool = new JLabel("");
		ExitTool.addMouseListener(new MouseAdapter() {
                    
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				Cursor cur1 = new Cursor(Cursor.HAND_CURSOR);
				ExitTool.setCursor(cur1);
			}
		});
		ExitTool.setIcon(new ImageIcon(getClass().getResource("/restaurant/closeButton.png")));
		ExitTool.setBounds(762, 11, 32, 23);
		panel.add(ExitTool);

		JLabel Background = new JLabel("New label");
		Background.setIcon(new ImageIcon(getClass().getResource("/restaurant/FinalBack.png")));
		// lblNewLabel.setBounds(31, 48, 653, 358);
		Background.setSize(800, 433);
		panel.add(Background);
	}
}
