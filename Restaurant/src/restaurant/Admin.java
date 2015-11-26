package restaurant;

import java.awt.Button;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.JList;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JScrollPane;

public class Admin {
	Connector connect = new Connector();

	 JFrame rame;
	private JTextField txtAdminSiteUse;

	
	ArrayList<String> text = new ArrayList<String>();
	private JTextField res;
	private JTextField address;
	private JTextField tele;
	private JTextField website;
	private JTextField pic;
	private JTextField budget;
	private JTextField disc;

	private static JList list_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Admin window = new Admin();
					window.rame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	} 

	/**
	 * Create the application.
	 */
	public Admin() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		rame = new JFrame();
		rame.setBackground(SystemColor.desktop);
		rame.getContentPane().setForeground(SystemColor.desktop);
		rame.getContentPane().setBackground(Color.WHITE);
		rame.setSize(1150 , 500);
		rame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		rame.getContentPane().setLayout(null);
		
		txtAdminSiteUse = new JTextField();
		txtAdminSiteUse.setBounds(0, 0, 2350, 50);
		txtAdminSiteUse.setFont(new Font("Times New Roman", Font.BOLD, 14));
		txtAdminSiteUse.setText(" Admin site use with caution");
		txtAdminSiteUse.setForeground(Color.PINK);
		txtAdminSiteUse.setBackground(Color.BLACK);
		rame.getContentPane().add(txtAdminSiteUse);
		txtAdminSiteUse.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("  Administrator: ");
		lblNewLabel.setBounds(0, 50, 1350, 28);
		lblNewLabel.setBackground(Color.WHITE);
		rame.getContentPane().add(lblNewLabel);
		
		
		JPanel madmin = new JPanel();
		madmin.setBorder(new LineBorder(new Color(0, 0, 0), 4, true));
		madmin.setBounds(201, 90, 900, 244);
		rame.getContentPane().add(madmin);
		madmin.setLayout(new CardLayout(0, 0));
		
		JPanel add = new JPanel();
		add.setBackground(SystemColor.info);
		madmin.add(add, "name_430299286202735");
		add.setLayout(null);
		
		JLabel lblAddNewRestaurant = new JLabel("Add new restaurant");
		lblAddNewRestaurant.setBounds(8, 7, 166, 24);
		lblAddNewRestaurant.setFont(new Font("Times New Roman", Font.BOLD, 20));
		add.add(lblAddNewRestaurant);
		
		JLabel lblName = new JLabel("Restaurant name");
		lblName.setBounds(12, 38, 113, 16);
		add.add(lblName);
		
		JLabel lblAddress = new JLabel("Address");
		lblAddress.setBounds(131, 38, 82, 16);
		add.add(lblAddress);
		
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
		btnNewButton_7.setBounds(540, 61, 41, 22);
		add.add(btnNewButton_7);
		
		JLabel lblPicture = new JLabel("Picture(file path)");
		lblPicture.setBounds(445, 38, 95, 16);
		add.add(lblPicture);
		
		JLabel lblNewLabel_1 = new JLabel("Website");
		lblNewLabel_1.setBounds(343, 38, 60, 16);
		add.add(lblNewLabel_1);
		
		JLabel lblTelephone = new JLabel("Telephone");
		lblTelephone.setBounds(236, 38, 60, 16);
		add.add(lblTelephone);
		
		JLabel lblStudentDiscount = new JLabel("Student discount");
		lblStudentDiscount.setBounds(644, 38, 100, 16);
		add.add(lblStudentDiscount);
		
		JLabel lblBudget = new JLabel("Budget");
		lblBudget.setBounds(591, 38, 50, 16);
		add.add(lblBudget);
		
		JLabel lblCuisine = new JLabel("cuisine");
		lblCuisine.setBounds(762, 38, 95, 16);
		add.add(lblCuisine);
		
		res = new JTextField();
		res.setBounds(8, 61, 113, 22);
		add.add(res);
		res.setColumns(10);
		
		address = new JTextField();
		address.setBounds(131, 61, 82, 22);
		add.add(address);
		address.setColumns(10);
		
		tele = new JTextField();
		tele.setBounds(236, 61, 95, 22);
		add.add(tele);
		tele.setColumns(10);
		
		website = new JTextField();
		website.setBounds(343, 61, 90, 22);
		add.add(website);
		website.setColumns(10);
		
		pic = new JTextField();
		pic.setBounds(445, 61, 95, 22);
		add.add(pic);
		pic.setColumns(10);
		
		budget = new JTextField();
		budget.setBounds(591, 60, 41, 22);
		add.add(budget);
		budget.setColumns(10);
		
		disc = new JTextField();
		disc.setBounds(644, 61, 95, 22);
		add.add(disc);
		disc.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("");
		lblNewLabel_5.setBounds(461, 195, 95, 0);
		add.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("");
		lblNewLabel_6.setBounds(461, 289, 95, 0);
		add.add(lblNewLabel_6);
		
		JButton btnClear = new JButton("Clear");
		btnClear.setBounds(564, 195, 78, 25);
		add.add(btnClear);
		
		JButton btnSaveRestaurant = new JButton("Save restaurant");
		btnSaveRestaurant.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				connect.makeRestaurant(res.getText(), address.getText(), tele.getText(),
						website.getText(), pic.getText(),
						budget.getText(), disc.getText(), list_1.getSelectedValuesList());
			}
		});
		btnSaveRestaurant.setBounds(668, 195, 189, 25);
		add.add(btnSaveRestaurant);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(762, 62, 95, 111);
		add.add(scrollPane);
		
		list_1 = new JList(connect.makeList("select Cuisine from Cuisine_Types"));
		scrollPane.setViewportView(list_1);
		
		JPanel user = new JPanel();
		user.setBackground(Color.GREEN);
		madmin.add(user, "name_430254776220175");
		
		JButton btnNewButton_6 = new JButton("New button");
		user.add(btnNewButton_6);
		
		JButton btnNewButton_5 = new JButton("New button");
		user.add(btnNewButton_5);
		
		JPanel review = new JPanel();
		review.setBackground(Color.ORANGE);
		madmin.add(review, "name_428658833133265");
		
		JPanel remo = new JPanel();
		madmin.add(remo, "name_430310930577694");
		remo.setLayout(null);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(148, 43, 85, 22);
		remo.add(comboBox_1);
		
		JPanel admin = new JPanel();
		admin.setForeground(SystemColor.desktop);
		admin.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, SystemColor.desktop, null, new Color(0, 0, 0), null));
		madmin.add(admin, "name_641553752768966");
		admin.setBackground(SystemColor.control);
		admin.setLayout(null);
		
		JTextPane txtpnEnterNewAdmin = new JTextPane();
		txtpnEnterNewAdmin.setBounds(3, 5, 152, 22);
		txtpnEnterNewAdmin.setText("enter new admin name:  ");
		admin.add(txtpnEnterNewAdmin);
		
		JTextField textField_1 = new JTextField();
		textField_1.setBounds(170, 5, 136, 22);
		
		JTextPane txtpnEnterNewPassword = new JTextPane();
		txtpnEnterNewPassword.setBounds(3, 42, 152, 22);
		txtpnEnterNewPassword.setBackground(Color.WHITE);
		txtpnEnterNewPassword.setText("enter new password:");
		admin.add(txtpnEnterNewPassword);
		textField_1.addActionListener(new ActionListener() {
		

		public void actionPerformed(ActionEvent e) {
			String te = textField_1.getText();
			
		}
		});
		admin.add(textField_1);
		
		
		JTextField textField_2 = new JTextField();
		textField_2.setBounds(170, 42, 136, 22);
		
		textField_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 String te1 = textField_2.getText();
				 
			}
		});
		admin.add(textField_2);
		
		
		JButton btnNewButton_4 = new JButton("Save ");
		btnNewButton_4.setBackground(SystemColor.controlHighlight);
		btnNewButton_4.setBounds(377, 200, 79, 24);
		btnNewButton_4.addActionListener(new ActionListener() {
			

			public void actionPerformed(ActionEvent e) {
				if(textField_1.getText().length()<4 ||textField_2.getText().length() <4){
					JOptionPane.showMessageDialog(rame,
						   "The password and username has to have more than 3 characters", "error",
						    JOptionPane.ERROR_MESSAGE);
				}else
					JOptionPane.showMessageDialog(rame,
						    "new admin created","successful!!",
						    JOptionPane.PLAIN_MESSAGE);
				connect.makeRevie("Review",textField_1.getText(), textField_2.getText());
			}
		});
		admin.add(btnNewButton_4);
		
		Button button = new Button("clear");
		button.setBackground(SystemColor.controlHighlight);
		button.setBounds(292, 200, 79, 24);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField_1.setText("");
				textField_2.setText("");
			}
			
				
			
		});
		admin.add(button);
		
		
		JButton btnNewButton_2 = new JButton("Manage Admin");
		btnNewButton_2.setBounds(0, 91, 200, 50);
		btnNewButton_2.setFont(new Font("Times New Roman", Font.BOLD, 15));
		rame.getContentPane().add(btnNewButton_2);
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				madmin.removeAll();
				madmin.add(admin);
				madmin.repaint();
				madmin.revalidate();
			
				
			}
		});
		
		JButton btnNewButton = new JButton("Add restaurang");
		btnNewButton.setBounds(0, 236, 200, 50);
		btnNewButton.setFont(new Font("Times New Roman", Font.BOLD, 15));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				madmin.removeAll();
				madmin.add(add);
				madmin.repaint();
				madmin.revalidate();
				
			}
		});
		rame.getContentPane().add(btnNewButton);
		

		JButton btnNewButton_1 = new JButton("Remove restaurang");
		btnNewButton_1.setFont(new Font("Times New Roman", Font.BOLD, 15));
		btnNewButton_1.setBounds(0, 284, 200, 50);
		
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				madmin.removeAll();
				madmin.add(remo);
				madmin.repaint();
				madmin.revalidate();
			}
		});
		rame.getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_3 = new JButton("Remove review");
		btnNewButton_3.setBounds(0, 187, 200, 50);
		btnNewButton_3.setFont(new Font("Times New Roman", Font.BOLD, 15));
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				madmin.removeAll();
				madmin.add(review);
				madmin.repaint();
				madmin.revalidate();
			}
		});
		rame.getContentPane().add(btnNewButton_3);
		
			
			JButton btnRemoveUser = new JButton("Remove user");
			btnRemoveUser.setBounds(0, 140, 200, 50);
			btnRemoveUser.setFont(new Font("Times New Roman", Font.BOLD, 15));
			btnRemoveUser.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					madmin.removeAll();
					madmin.add(user);
					madmin.repaint();
					madmin.revalidate();
				}
			});
			
			rame.getContentPane().add(btnRemoveUser);
		
}

	private void add(JScrollPane jScrollPane) {
		// TODO Auto-generated method stub
		
	}	
}
