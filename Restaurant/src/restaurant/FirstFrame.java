package restaurant;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.font.TextAttribute;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFormattedTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import static restaurant.DBConnection.conn;
import restaurant.MainFrame;

public class FirstFrame {
public JFrame frame;
	public JPasswordField PassLogin;
        public JFormattedTextField UserLogin;
        protected Login login = new Login();
        public static String username;
        public static String password;
        
        
        

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FirstFrame window = new FirstFrame();
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
	public FirstFrame() {
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

		final JLabel ResButton = new JLabel("");
                ResButton.setIcon(new ImageIcon(getClass().getResource("/resources/UtensilDefault1.png")));
		ResButton.setBounds(311, 135, 172, 182);
		panel.add(ResButton);
		ResButton.addMouseListener(new MouseAdapter() {// code later
			@Override
			public void mouseEntered(MouseEvent arg0) {
				Cursor cur7 = new Cursor(Cursor.HAND_CURSOR);
				ResButton.setCursor(cur7);
                                ImageIcon II = new ImageIcon(getClass().getResource("/resources/UtensilHover1.png"));
                                ResButton.setIcon(II); 
			       // code
			}
                        public void mouseExited(MouseEvent arg0){
                            ImageIcon III = new ImageIcon(getClass().getResource("/resources/UtensilDefault1.png"));
                            ResButton.setIcon(III);
                        }
			@Override
			public void mouseClicked(MouseEvent e) {
			Runnable run = new Runnable (){
				public void run(){
					if(login.adminLogin==false){
						MainFrame mainframe = null;
		                            try {
		                                mainframe = new MainFrame();
		                            } catch (SQLException ex) {
		                                Logger.getLogger(FirstFrame.class.getName()).log(Level.SEVERE, null, ex);
		                            }
		                                mainframe.frame.setVisible(true);
					}
		                                //is login
		                                else {
						AdminFrame mainframe = null;
		                try {
		                    mainframe = new AdminFrame();
		                } catch (SQLException ex) {
		                    Logger.getLogger(FirstFrame.class.getName()).log(Level.SEVERE, null, ex);
		                }
		                    mainframe.frame.setVisible(true);
					}
				}
				
				
			};
			Thread thread = new Thread(run);
			thread.start();
			}
                        
		});	

		UserLogin = new JFormattedTextField();
		UserLogin.setText(" Username/Email");
                UserLogin.setBackground(Color.BLACK);
                UserLogin.setForeground(Color.WHITE);
		UserLogin.setBounds(10, 6, 122, 20);
                UserLogin.addMouseListener(new MouseAdapter(){
                    @Override
                    public void mouseClicked(MouseEvent e){
                        UserLogin.setText("");
                    }
                });
		panel.add(UserLogin);

		PassLogin = new JPasswordField();
		PassLogin.setBounds(142, 6, 128, 20);
                PassLogin.setBackground(Color.BLACK);
                PassLogin.setForeground(Color.WHITE);
		PassLogin.setText("jjjjjjjjjjjj");
                PassLogin.addMouseListener(new MouseAdapter(){
                    @Override
                    public void mouseClicked(MouseEvent e){
                        PassLogin.setText(""); 
                    }
                });
		panel.add(PassLogin);

		final JButton EnterLogin = new JButton("Enter");
		EnterLogin.setBounds(280, 4, 89, 23);
		panel.add(EnterLogin);
                EnterLogin.addMouseListener(new MouseAdapter() {
                    
                        @Override
                        public void mouseEntered(MouseEvent e){
                            Cursor cur1 = new Cursor(Cursor.HAND_CURSOR);
                            EnterLogin.setCursor(cur1);
                        }
                    
			@Override
			public  void mouseClicked(MouseEvent e) {
                        username = UserLogin.getText().trim();
                        password = PassLogin.getText().trim();
                        login.AdminLogin(username, password,frame);
                        if(login.adminLogin == true){
                            Admin adminFrame = null;
                            try {
                                adminFrame = new Admin();
                            } catch (SQLException ex) {
                                Logger.getLogger(FirstFrame.class.getName()).log(Level.SEVERE, null, ex);
                            }
                                 adminFrame.frame.setVisible(true);
                                 
                        }
                        else if(login.ownAdmin == true){
                            AdminFrame adminFrame = null;
                            try {
                                adminFrame = new AdminFrame();
                            } catch (SQLException ex) {
                                Logger.getLogger(FirstFrame.class.getName()).log(Level.SEVERE, null, ex);
                            }
                                 adminFrame.frame.setVisible(true);
                        }
                        }
			
		});
                
                /*REGISTER FUNCTIONALITY, JUST COPY AND PASTE TO FIRSTFRAME OF UPDATED PROGRAM*/
                final JLabel register = new JLabel();
                register.setText("Click here to register for free");
                register.setBounds(378, 5, 200, 20);
                register.setFont(new Font("Arial", Font.PLAIN, 13));
                register.setForeground(java.awt.Color.WHITE);
                Font font = register.getFont();
                Map attributes = font.getAttributes();
                attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
                register.setFont(font.deriveFont(attributes));
                register.addMouseListener(new MouseAdapter (){
                    @Override
                    public void mouseEntered(MouseEvent e){
                        Cursor cur1 = new Cursor(Cursor.HAND_CURSOR);
                        register.setCursor(cur1);
                        register.setForeground(java.awt.Color.CYAN);
                    }
                    @Override
                    public void mouseExited(MouseEvent e){
                        register.setForeground(java.awt.Color.WHITE);
                    }
                   @Override
			public void mouseClicked(MouseEvent e) {
				Register registerFrame = null;
                            try {
                                registerFrame = new Register();
                            } catch (SQLException ex) {
                                Logger.getLogger(FirstFrame.class.getName()).log(Level.SEVERE, null, ex);
                            }
                                registerFrame.frame.setVisible(true);
			}
                    
                });
                panel.add(register);
                
		final JLabel ExitTool = new JLabel("");
                ExitTool.setBounds(762, 5, 40, 40);
		ExitTool.addMouseListener(new MouseAdapter() {
                    
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				Cursor cur1 = new Cursor(Cursor.HAND_CURSOR);
				ExitTool.setCursor(cur1);
                                ExitTool.setIcon(new ImageIcon(getClass().getResource("/resources/closeButtonHover.png")));
			}
                        @Override
                        public void mouseExited(MouseEvent e){
                                ExitTool.setIcon(new ImageIcon(getClass().getResource("/resources/closeButton.png")));
                        }
		});
		ExitTool.setIcon(new ImageIcon(getClass().getResource("/resources/closeButton.png")));
		panel.add(ExitTool);

		JLabel Background = new JLabel("New label");
		Background.setIcon(new ImageIcon(getClass().getResource("/resources/FinalBack.png")));
		// lblNewLabel.setBounds(31, 48, 653, 358);
		Background.setSize(800, 433);
		panel.add(Background);
        }
}