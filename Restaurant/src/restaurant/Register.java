/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restaurant;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.font.TextAttribute;
import java.sql.SQLException;
import java.util.Map;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import static restaurant.SQLRegisterStore.*;
/**
 *
 * @author user
 */
public class Register {
    public JFrame frame;
    private static String passwordInput;
    private static String usernameInput;
    
    public Register() throws SQLException {
		initialize();
	}
    	private void initialize() throws SQLException {
		frame = new JFrame();
                frame.setBounds(400, 400, 500, 500);
                frame.setSize(500, 500);
                frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//		frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.X_AXIS));
                frame.setResizable(false);
                frame.setUndecorated(true);
                frame.setBackground(new Color(0,0,0,0));
                
                JTextField username = new JTextField("Desired Username");
                username.setBounds(165, 192, 200, 20);
                username.setFont(new Font("Arial", Font.PLAIN, 16));
                username.setForeground(Color.lightGray);
                username.setOpaque(false);
                username.setBorder(null);
                username.addMouseListener(new MouseAdapter(){
                @Override
                public void mouseClicked(MouseEvent e){
                    username.setText("");
                      
                }
                String usernameInput = username.getText();
                });
              
                frame.add(username);
                
                JTextField password = new JTextField("Password");
                password.setBounds(165, 243, 200, 20);
                password.setOpaque(false);
                password.setBorder(null);
                password.setFont(new Font("Arial", Font.PLAIN, 16));
                password.setForeground(Color.lightGray);
                password.addMouseListener(new MouseAdapter (){
                @Override
                public void mouseClicked(MouseEvent e){
                    password.setText("");
                    
                }
               String passwordInput = password.getText();
                });
              
                frame.add(password);
                
                   
                JButton login = new JButton("Login");
                login.setSize(30,5);
                login.setBounds(143,340,164, 25);
                login.addMouseListener(new MouseAdapter(){
                @Override
                public void mouseClicked(MouseEvent e ){
                   InsertUserRegister("hi","bye");
                }
                    
                });
                frame.add(login);
                
//                JTextField retypepassword = new JTextField("Re-type Password");
//                retypepassword.setBounds(165, 293, 200, 20);
//                retypepassword.setOpaque(false);
//                retypepassword.setBorder(null);
//                retypepassword.setFont(new Font("Arial", Font.PLAIN, 16));
//                retypepassword.setForeground(Color.lightGray);
//                retypepassword.addMouseListener(new MouseAdapter (){
//                @Override
//                public void mouseClicked(MouseEvent e){
//                    retypepassword.setText("");
//                }
//  
//                });
//               
//                frame.add(retypepassword);
                
                JLabel ExitTool = new JLabel("");
                ExitTool.setBounds(386, 228, 5, 2);
                ExitTool.setSize(40,40);
		ExitTool.addMouseListener(new MouseAdapter() {
                    
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.dispose();
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
		frame.add(ExitTool);
             
                
                JLabel registerbackground = new JLabel("");
                registerbackground.setIcon(new ImageIcon(getClass().getResource("/resources/Register.png")));
                registerbackground.setBounds(0,0,315,315);
                registerbackground.setSize(400,400);
                frame.add(registerbackground);
                
              
}
}
