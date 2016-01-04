package Frame;

import ResultPanel.ResultPanel;
import DBConnection.Connector;
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
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import static SQL.SQLRestaurant.selectCuisine;
import static SQL.SQLUpdate.*;
import static DBConnection.Connector.*;

public class EditWindow extends JFrame {
        
        Connector connect = new Connector();
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
        private JTextField textField_3;
        private String oldName;
        private int restId;
        private int cuisineId;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
        private String name,tel,address;
        private JCheckBox chckbxNewCheckBox;
        private int studentDiscount;
        private JComboBox comboBox;
        private JComboBox comboBox_1;
        private JComboBox comboBox_2;
        private JTextField pic;
        
	
	/**
	 * Create the frame.
	 */
    public EditWindow(ResultPanel res){
    	initialize();
    	textField.setText(res.name);
    	textField_1.setText(res.address);
    	textField_2.setText(res.tel);
        textField_3.setText(getWebadress(res.name));
        pic.setText("Cange if you want new picture");
        chckbxNewCheckBox.setSelected(getStudentDiscount(res.name));
        comboBox_1.setSelectedItem(getBudgetRange(res.name));
        comboBox.setSelectedItem(getCuisine(res.name));
        comboBox_2.setSelectedItem(getFoodType(res.name));
        oldName = res.name; 
        restId = getRestId(oldName);
        cuisineId = getCuisineId(res.name);
    }
    
    
    
	public EditWindow() {
		initialize();
	}
	public void initialize(){
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 360, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblNewLabel_3 = new JLabel("Edit");
		lblNewLabel_3.setForeground(SystemColor.window);
		lblNewLabel_3.setFont(new Font("Impact", Font.PLAIN, 14));
		lblNewLabel_3.setIcon(new ImageIcon(EditWindow.class.getResource("/resources/Edit-32.png")));
		lblNewLabel_3.setBounds(240, 350, 111, 60);
                
                lblNewLabel_3.addMouseListener(new MouseAdapter() {
                    
                        @Override
                        public void mouseEntered(MouseEvent e){
                            Cursor cur1 = new Cursor(Cursor.HAND_CURSOR);
                            lblNewLabel_3.setCursor(cur1);
                        }
                        
			@Override
			public void mouseClicked(MouseEvent e) {
                        //edit Restaurant
                            if(chckbxNewCheckBox.isSelected() == true) {studentDiscount = 1;}
                            if(chckbxNewCheckBox.isSelected() == false) {studentDiscount = 0;}
                            if(pic.getText().charAt(0) == '/' ){
                                editRestaurant(textField.getText(), textField_1.getText(), textField_2.getText(), 
                                        textField_3.getText(), pic.getText(), studentDiscount,  oldName);
                                editRestaurant(comboBox.getSelectedIndex(),  restId);
                                editRestaurantFoodType(comboBox_2.getSelectedIndex(),  restId);
                                JOptionPane.showMessageDialog(null, "Edit Success!");
                            }
                            else {
                                editRestaurant(textField.getText(), textField_1.getText(), textField_2.getText(), 
                                        textField_3.getText(), studentDiscount, comboBox_1.getSelectedIndex()+1, oldName);
                                editRestaurant(comboBox.getSelectedIndex(),  restId);
                                editRestaurantFoodType(comboBox_2.getSelectedIndex(),  restId);
                                JOptionPane.showMessageDialog(null, "Edit Success!");
                            }
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
		lblNewLabel_2.setBounds(328, 0, 26, 30);
		contentPane.add(lblNewLabel_2);
		
		textField = new JTextField();
		textField.setBounds(85, 11, 209, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(85, 45, 209, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(85, 79, 209, 20);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
                
                textField_3 = new JTextField();
		textField_3.setBounds(85, 113, 209, 20);
		contentPane.add(textField_3);
		textField_3.setColumns(10);
		
                                
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
		
		pic = new JTextField();
		pic.setBounds(85, 147, 209, 20);
		contentPane.add(pic);
		pic.setColumns(10);
		btnNewButton_7.setBounds(300, 147, 41, 22);
		contentPane.add(btnNewButton_7);
                
                chckbxNewCheckBox = new JCheckBox();
		chckbxNewCheckBox.setBounds(85, 182, 128, 23);
		contentPane.add(chckbxNewCheckBox);
		
                String[] budget = new String[3];
                budget[0] = "30";
                budget[1] = "60";
                budget[2] = "90";
                
		comboBox_1 = new JComboBox(budget);
		comboBox_1.setBounds(85, 232, 209, 20);
		contentPane.add(comboBox_1);
		
                
////		add All to type
                String[] allcuisine = selectCuisine();
                String[] allcuisine1 = new String[allcuisine.length+1];
                allcuisine1[0] = "No chosen cuisine";
                for(int i = 1; i <= allcuisine.length; i++){
                    allcuisine1[i] = allcuisine[i-1];
                }
                
		comboBox = new JComboBox(allcuisine1);
		comboBox.setBounds(85, 267, 209, 20);
		contentPane.add(comboBox);
                
                String[] allFoodType = connect.makeList("select Food from Food_Type");
                String[] allFoodType1 = new String[allFoodType.length+1];
                allFoodType1[0] = "No chosen food type";
                for(int i = 1; i <= allFoodType.length; i++){
                    allFoodType1[i] = allFoodType[i-1];
                }
                
                comboBox_2 = new JComboBox(allFoodType1);
		comboBox_2.setBounds(85, 302, 209, 20);
		contentPane.add(comboBox_2);
                
		JTextPane txtpnNameAddress = new JTextPane();
		txtpnNameAddress.setForeground(SystemColor.inactiveCaptionBorder);
		txtpnNameAddress.setFont(new Font("Stencil", Font.PLAIN, 14));
		txtpnNameAddress.setOpaque(false);
		txtpnNameAddress.setText("Name:\r\n\r\nAddress:\r\n\r\nTel:\r\n\r\n"+
                        "Website:\r\n\r\nImage:\r\n\r\nStudent\nDiscount:\r\n\r\nBudget:"
                        + "\r\n\r\nCuisine:\r\n\r\nFood Type:");
		txtpnNameAddress.setBackground(SystemColor.control);
		txtpnNameAddress.setEditable(false);
		txtpnNameAddress.setBounds(10, 11, 102, 350);
		contentPane.add(txtpnNameAddress);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon(AddWindow.class.getResource("/resources/TestBackground.png")));
		lblNewLabel.setBounds(-476, -25, 845, 425);
		contentPane.add(lblNewLabel);
		
//		lblNewLabel_1 = new JLabel("New label");
//		lblNewLabel_1.setBounds(282, 153, 46, 14);
//		contentPane.add(lblNewLabel_1);

	}
        
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ResultPanel res=new ResultPanel("gf","gf","gf","gf");
					EditWindow frame = new EditWindow(res);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
}

