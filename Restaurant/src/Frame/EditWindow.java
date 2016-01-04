package Frame;

import DBConnection.Connector;
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
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import static SQL.SQLRestaurant.selectCuisine;
import static SQL.SQLUpdate.*;
import static DBConnection.Connector.*;
import ResultPanel.ResultPanel;

/**
 * Class: This is the Interface frame for deleting the restaurant
 * @author Obada
 */
public class EditWindow extends JFrame {
        
        Connector connect = new Connector();
	private JPanel contentPane;
	private JTextField textname;
	private JTextField textaddress;
	private JTextField texttel;
        private JTextField textweb;
        private String oldName;
        private int restId;
        private int cuisineId;
	private JLabel lblNewLabel_1;
	private JLabel closebutton;
	private JLabel editbutton;
        private String name,tel,address;
        private JCheckBox studentbox;
        private int studentDiscount;
        private JComboBox cuisinebox;
        private JComboBox boxbudget;
        private JComboBox foodtypebox;
        private JComboBox environmentbox;
        private JTextField pic;
        
	
	/**
	 * Create the frame.
	 */
    public EditWindow(ResultPanel res){
    	initialize();
    	textname.setText(res.name);
    	textaddress.setText(res.address);
    	texttel.setText(res.tel);
        textweb.setText(getWebadress(res.name));
        pic.setText("Cange to new picture");
        studentbox.setSelected(getStudentDiscount(res.name));
        boxbudget.setSelectedItem(getBudgetRange(res.name));
        cuisinebox.setSelectedItem(getCuisine(res.name));
        foodtypebox.setSelectedItem(getFoodType(res.name));
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
		setBounds(240, 130, 250, 510);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
                contentPane.setBackground(Color.DARK_GRAY);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		editbutton = new JLabel("Edit");
		editbutton.setForeground(SystemColor.window);
		editbutton.setFont(new Font("Arial", Font.PLAIN, 14));
		editbutton.setIcon(new ImageIcon(EditWindow.class.getResource("/resources/Edit-32.png")));
		editbutton.setBounds(180, 465, 92, 44);
                
                editbutton.addMouseListener(new MouseAdapter() {
                    
                        @Override
                        public void mouseEntered(MouseEvent e){
                            Cursor cur1 = new Cursor(Cursor.HAND_CURSOR);
                            editbutton.setCursor(cur1);
                        }
                        
			@Override
			public void mouseClicked(MouseEvent e) {
                        //edit Restaurant
                            if(studentbox.isSelected() == true) {studentDiscount = 1;}
                            if(studentbox.isSelected() == false) {studentDiscount = 0;}
                            if(pic.getText().charAt(0) == '/' ){
                                editRestaurant(textname.getText(), textaddress.getText(), texttel.getText(), 
                                        textweb.getText(), pic.getText(), studentDiscount,  oldName);
                                editRestaurant(cuisinebox.getSelectedIndex(),  restId);
                                editRestaurantFoodType(foodtypebox.getSelectedIndex(),  restId);
                                JOptionPane.showMessageDialog(null, "Edit Success!");
                            }
                            else {
                                editRestaurant(textname.getText(), textaddress.getText(), texttel.getText(), 
                                        textweb.getText(), studentDiscount, boxbudget.getSelectedIndex()+1, oldName);
                                editRestaurant(cuisinebox.getSelectedIndex(),  restId);
                                editRestaurantFoodType(foodtypebox.getSelectedIndex(),  restId);
                                JOptionPane.showMessageDialog(null, "Edit Success!");
                            }
			}
		});
		contentPane.add(editbutton);
		
		closebutton = new JLabel("");
		closebutton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				closebutton.setIcon(new ImageIcon(AddWindow.class.getResource("/resources/closeButtonHover.png")));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				closebutton.setIcon(new ImageIcon(AddWindow.class.getResource("/resources/closeButton.png")));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				setVisible(false);
				dispose();
			}
		});
		closebutton.setIcon(new ImageIcon(AddWindow.class.getResource("/resources/closeButton.png")));
		closebutton.setBounds(220, 0, 30, 30);
		contentPane.add(closebutton);
		
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
                
                textweb = new JTextField();
		textweb.setBounds(10, 165, 209, 20);
		contentPane.add(textweb);
		textweb.setColumns(10);
		
                                
                JButton addimage = new JButton("+");
		addimage.addActionListener(new ActionListener() {
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
		pic.setBounds(10, 210, 165, 22);
		contentPane.add(pic);
		pic.setColumns(10);
		addimage.setBounds(180, 210, 30, 22);
		contentPane.add(addimage);
                
                studentbox = new JCheckBox();
		studentbox.setBounds(8, 295, 128, 23);
                studentbox.setOpaque(false);
		contentPane.add(studentbox);
		
                String[] budget = new String[3];
                budget[0] = "30";
                budget[1] = "60";
                budget[2] = "90";
                
		boxbudget = new JComboBox(budget);
		boxbudget.setBounds(10, 255, 103, 20);
		contentPane.add(boxbudget);
		
                
////		add All to type
                String[] allcuisine = selectCuisine();
                String[] allcuisine1 = new String[allcuisine.length+1];
                allcuisine1[0] = "No chosen cuisine";
                for(int i = 1; i <= allcuisine.length; i++){
                    allcuisine1[i] = allcuisine[i-1];
                }
                
		cuisinebox = new JComboBox(allcuisine1);
		cuisinebox.setBounds(10, 330, 118, 20);
		contentPane.add(cuisinebox);
                
                String[] allFoodType = connect.makeList("select Food from Food_Type");
                String[] allFoodType1 = new String[allFoodType.length+1];
                allFoodType1[0] = "No chosen food type";
                for(int i = 1; i <= allFoodType.length; i++){
                    allFoodType1[i] = allFoodType[i-1];
                }
                
                foodtypebox = new JComboBox(allFoodType1);
		foodtypebox.setBounds(10, 375, 118, 20);
		contentPane.add(foodtypebox);
                
                String[] allenvironment = connect.makeList("select Setting from Setting");
                
                environmentbox = new JComboBox(allenvironment);
                environmentbox.setBounds(10, 420, 118, 20);
                contentPane.add(environmentbox);
                
		JTextPane txtpnNameAddress = new JTextPane();
		txtpnNameAddress.setForeground(SystemColor.inactiveCaptionBorder);
		txtpnNameAddress.setFont(new Font("Arial", Font.PLAIN, 12));
		txtpnNameAddress.setOpaque(false);
		txtpnNameAddress.setText("Name:\r\n\r\n\nAddress :\r\n\n\r\nTel:\r\n\n\r\nWebsite:"
                        + "\r\n\n\r\nPicture:\r\n\n\r\nBudget:\r\n\n\r\nStudent Discount:"
                        + "\r\n\r\nCuisine:\r\n\r\n\nFood Type:\r\n\r\n\nEnvironment");
		txtpnNameAddress.setBackground(SystemColor.control);
		txtpnNameAddress.setEditable(false);
		txtpnNameAddress.setBounds(7, 11, 157, 500);
		contentPane.add(txtpnNameAddress);
             

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

