package restaurant;

import java.awt.BorderLayout;
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
import static restaurant.SQLInsert.*;
import static restaurant.MainFrame.internalFrame;
import javax.swing.JCheckBox;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import static restaurant.SQLRestaurant.selectCuisine;

public class AddWindow extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
        
        JComboBox comboBox_1;
        JCheckBox chckbxNewCheckBox;
        JComboBox comboBox;
	private String name,tel,address;
        JTextField pic;
        private JTextField textField_3;

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
		setBounds(100, 100, 469, 380);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblNewLabel_3 = new JLabel("Add");
		lblNewLabel_3.setForeground(SystemColor.window);
		lblNewLabel_3.setFont(new Font("Impact", Font.PLAIN, 14));
		lblNewLabel_3.setIcon(new ImageIcon(AddWindow.class.getResource("/resources/Checked-32.png")));
		lblNewLabel_3.setBounds(371, 330, 92, 44);
                lblNewLabel_3.addMouseListener(new MouseAdapter() {
                    
                        @Override
                        public void mouseEntered(MouseEvent e){
                            Cursor cur1 = new Cursor(Cursor.HAND_CURSOR);
                            lblNewLabel_3.setCursor(cur1);
                        }
                        
			@Override
			public void mouseClicked(MouseEvent e) {
                        //add Restaurant
                                addRestaurant(textField.getText(), textField_1.getText(), textField_2.getText(), 
                                        textField_3.getText(), pic.getText(), comboBox_1.getSelectedItem().toString(), 
                                        chckbxNewCheckBox.isSelected(), comboBox.getSelectedItem().toString());
                                JOptionPane.showMessageDialog(null, "Restaurant is add!");
			}
		});
                
		contentPane.add(lblNewLabel_3);
		
		lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				lblNewLabel_2.setIcon(new ImageIcon(AddWindow.class.getResource("/resources/closeButtonHover.png")));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblNewLabel_2.setIcon(new ImageIcon(AddWindow.class.getResource("/resources/closeButton.png")));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				setVisible(false);
				dispose();
			}
		});
		lblNewLabel_2.setIcon(new ImageIcon(AddWindow.class.getResource("/resources/closeButton.png")));
		lblNewLabel_2.setBounds(437, 6, 26, 30);
		contentPane.add(lblNewLabel_2);
		
		textField = new JTextField();
		textField.setBounds(68, 11, 209, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(103, 55, 209, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(68, 98, 209, 20);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
                
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
		
		textField_3 = new JTextField();
		textField_3.setBounds(78, 140, 130, 26);
		contentPane.add(textField_3);
		textField_3.setColumns(10);
		
		pic = new JTextField();
		pic.setBounds(176, 187, 209, 22);
		contentPane.add(pic);
		pic.setColumns(10);
		btnNewButton_7.setBounds(397, 187, 41, 22);
		contentPane.add(btnNewButton_7);
		
                String[] budget = new String[3];
                budget[0] = "30";
                budget[1] = "60";
                budget[2] = "90";
                
		comboBox_1 = new JComboBox(budget);
		comboBox_1.setBounds(78, 233, 103, 27);
		contentPane.add(comboBox_1);
		
		chckbxNewCheckBox = new JCheckBox();
		chckbxNewCheckBox.setBounds(149, 274, 128, 23);
		contentPane.add(chckbxNewCheckBox);
                
		//add All to type
                String[] allcuisine = selectCuisine();
                String[] allcuisine1 = new String[allcuisine.length+1];
                allcuisine1[0] = "All";
                for(int i = 1; i <= allcuisine.length; i++){
                    allcuisine1[i] = allcuisine[i-1];
                }
                
		comboBox = new JComboBox(allcuisine1);
		comboBox.setBounds(78, 319, 118, 27);
		contentPane.add(comboBox);
		
		
		JTextPane txtpnNameAddress = new JTextPane();
		txtpnNameAddress.setForeground(SystemColor.inactiveCaptionBorder);
		txtpnNameAddress.setFont(new Font("Stencil", Font.PLAIN, 14));
		txtpnNameAddress.setOpaque(false);
		txtpnNameAddress.setText("Name:\r\n\r\n\r\nAddress :\r\n\r\n\r\nTel:\r\n\r\n\r\nWebsite:\r\n\r\n\r\nPicture(file path):\r\n\r\n\r\nBudget:\r\n\r\n\r\nStudentDiscount:\r\n\r\n\r\nCuisine:");
		txtpnNameAddress.setBackground(SystemColor.control);
		txtpnNameAddress.setEditable(false);
		txtpnNameAddress.setBounds(7, 11, 157, 363);
		contentPane.add(txtpnNameAddress);
		
		JLabel lblNewLabel = new JLabel();
		lblNewLabel.setIcon(new ImageIcon(AddWindow.class.getResource("/resources/TestBackground.png")));
		lblNewLabel.setBounds(-330, 0, 999, 432);
		contentPane.add(lblNewLabel);
	}
}
