package restaurant;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextPane;
import java.awt.SystemColor;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JCheckBox;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;


public class AddWindow extends JFrame {
    SQLInsert sql = new SQLInsert();
    Connector connect = new Connector();
	private JPanel contentPane;
	private JTextField textname;
	private JTextField textaddress;
	private JTextField texttel;
        private JTextField textweb;

	private JLabel exitbutton;
	private JLabel addbutton;
        
        private String name,tel,address;
        
        JComboBox boxbudget;
        JCheckBox studentbox;
        JComboBox cuisinebox;
	
        JTextField pic;
        
        

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddWindow frame = new AddWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public AddWindow() {
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(240, 130, 250, 510);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
                contentPane.setBackground(Color.DARK_GRAY);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		addbutton = new JLabel("Add");
		addbutton.setForeground(SystemColor.window);
		addbutton.setFont(new Font("Arial", Font.BOLD, 14));
		addbutton.setIcon(new ImageIcon(AddWindow.class.getResource("/resources/Checked-32.png")));
		addbutton.setBounds(180, 75, 92, 44);
                addbutton.addMouseListener(new MouseAdapter() {
                    
                        @Override
                        public void mouseEntered(MouseEvent e){
                            Cursor cur1 = new Cursor(Cursor.HAND_CURSOR);
                            addbutton.setCursor(cur1);
                        }
                        
			@Override
			public void mouseClicked(MouseEvent e) {
                        //add Restaurant
                                sql.addRestaurant(textname.getText(), textaddress.getText(), texttel.getText(), 
                                        textweb.getText(), pic.getText(), boxbudget.getSelectedItem().toString(), 
                                        studentbox.isSelected(), cuisinebox.getSelectedItem().toString());
                                JOptionPane.showMessageDialog(null, "Restaurant is add!");
			}
		});
                
		contentPane.add(addbutton);
		
		exitbutton = new JLabel("");
		exitbutton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				exitbutton.setIcon(new ImageIcon(AddWindow.class.getResource("/resources/closeButtonHover.png")));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				exitbutton.setIcon(new ImageIcon(AddWindow.class.getResource("/resources/closeButton.png")));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				setVisible(false);
				dispose();
			}
		});
		exitbutton.setIcon(new ImageIcon(AddWindow.class.getResource("/resources/closeButton.png")));
		exitbutton.setBounds(220,0, 30, 30);
		contentPane.add(exitbutton);
		
		textname = new JTextField();
		textname.setBounds(10, 30, 209, 20);
		contentPane.add(textname);
		textname.setColumns(10);
		
		textaddress = new JTextField();
		textaddress.setBounds(10, 75, 209, 20);
		contentPane.add(textaddress);
		textaddress.setColumns(10);
		
		texttel = new JTextField();
		texttel.setBounds(10, 120, 209, 20);
		contentPane.add(texttel);
		texttel.setColumns(10);
                
                JButton btnNewButton_7 = new JButton("+");
		btnNewButton_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();
			    FileNameExtensionFilter filter = new FileNameExtensionFilter(
			        "JPG & GIF Images", "jpg", "gif" ,"png");
			    chooser.setFileFilter(filter);
			    int returnVal = chooser.showSaveDialog(null);
			    if(returnVal == JFileChooser.APPROVE_OPTION){
			    	pic.setText(chooser.getSelectedFile().getAbsolutePath());
			    }
			}
		});
		
		textweb = new JTextField();
		textweb.setBounds(10, 165, 209, 20);
		contentPane.add(textweb);
		textweb.setColumns(10);
		
		pic = new JTextField();
		pic.setBounds(10,210, 165, 22);
		contentPane.add(pic);
		pic.setColumns(10);
		btnNewButton_7.setBounds(180, 210, 30, 22);
		contentPane.add(btnNewButton_7);
		
                String[] budget = new String[3];
                budget[0] = "30";
                budget[1] = "60";
                budget[2] = "90";
                
		boxbudget = new JComboBox(budget);
		boxbudget.setBounds(10, 255, 103, 20);
		contentPane.add(boxbudget);
		
		studentbox = new JCheckBox();
		studentbox.setBounds(8,190, 128, 23);
                studentbox.setOpaque(false);
		contentPane.add(studentbox);
                
		//add all cuisine type
                String[] allcuisine = connect.makeList("select Cuisine from Cuisine_Types");

		cuisinebox = new JComboBox(allcuisine);
		cuisinebox.setBounds(10, 375, 118, 20);
		contentPane.add(cuisinebox);

                
		JTextPane txtpnNameAddress = new JTextPane();
		txtpnNameAddress.setForeground(SystemColor.inactiveCaptionBorder);
		txtpnNameAddress.setFont(new Font("Arial", Font.PLAIN, 12));
		txtpnNameAddress.setOpaque(false);
		txtpnNameAddress.setText("Name:\r\n\r\n\nAddress :\r\n\n\r\nTel:\r\n\n\r\nWebsite:"
                        + "\r\n\n\r\nPicture:\r\n\n\r\nBudget:\r\n\n\r\nStudent Discount:"
                        + "\r\n\r\nCuisine:\r\n\r\n\nFood Type:");
		txtpnNameAddress.setBackground(SystemColor.control);
		txtpnNameAddress.setEditable(false);
		txtpnNameAddress.setBounds(7, 11, 157, 500);
		contentPane.add(txtpnNameAddress);
		
	}
}

