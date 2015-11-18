package restaurant;


import java.awt.EventQueue;


import javax.swing.JFrame;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import java.awt.Font;
import java.awt.SystemColor;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ScrollPaneConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.border.LineBorder;
import java.awt.ScrollPane;
import java.awt.Label;
import java.awt.Panel;
import javax.swing.JScrollBar;
import javax.swing.JLayeredPane;

import java.awt.Component;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.font.TextAttribute;
import java.sql.SQLException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ButtonModel;
import javax.swing.JCheckBox;
import javax.swing.JFormattedTextField;
import javax.swing.JInternalFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import static restaurant.SQLFilter.*;
import java.awt.Color;

public class AdminFrame {

	public JFrame frame;
        public static JInternalFrame internalFrame;
        private static String[] slBudget = new String[3];
        public static String[] name,tel,address;
        private static JPanel panel;
        public static JScrollPane scrollPane;
        private static String cuisine;
        public static ResultPanel res;
        public static boolean inUse=false;
	
	/**
	 * Create the application.
	 */
        
    
    public static void setCuisine(String string){
    	cuisine=string;
    }
        
	public AdminFrame() throws SQLException {
		initialize();
		inUse=true;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() throws SQLException {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setSize(990, 602);
                frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.X_AXIS));
                frame.setUndecorated(true);
                
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel);
		panel.setLayout(null);
                panel.setBackground(java.awt.Color.DARK_GRAY);
		
		final JLabel lblE = new JLabel("Edit");
		lblE.setForeground(SystemColor.inactiveCaptionBorder);
		lblE.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				if(res==null){
					JOptionPane.showMessageDialog(null, "Select a value\nPlease click on a restaurant panel", "alert", JOptionPane.ERROR_MESSAGE);
				}else {
				EditWindow edit=new EditWindow(res);
				edit.setVisible(true);
			}
			}
		});
		
		final JLabel lblAdd = new JLabel("Add");
		lblAdd.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				AddWindow frame= new AddWindow();
				frame.setVisible(true);
			}
			@Override
			public void mousePressed(MouseEvent arg0) {
				lblAdd.setIcon(new ImageIcon(AdminFrame.class.getResource("/resources/Upload to the Cloud Filled-32.png")));
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				lblAdd.setIcon(new ImageIcon(AdminFrame.class.getResource("/resources/Upload to the Cloud-32.png")));
			}
		});
		lblAdd.setForeground(SystemColor.inactiveCaptionBorder);
		lblAdd.setFont(new Font("Franklin Gothic Heavy", Font.PLAIN, 14));
		lblAdd.setIcon(new ImageIcon(AdminFrame.class.getResource("/resources/Upload to the Cloud-32.png")));
		lblAdd.setBounds(910, 87, 66, 40);
		panel.add(lblAdd);
		
		JLabel lblDelete = new JLabel("Delete");
		lblDelete.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(res==null){
					JOptionPane.showMessageDialog(null, "Select a value\nPlease click on a restaurant panel", "alert", JOptionPane.ERROR_MESSAGE);
				}else {
				DeleteWindow delete=new DeleteWindow(res);
				delete.setVisible(true);
			}
			}
		});
		lblDelete.setForeground(SystemColor.inactiveCaptionBorder);
		lblDelete.setFont(new Font("Franklin Gothic Heavy", Font.PLAIN, 12));
		lblDelete.setIcon(new ImageIcon(AdminFrame.class.getResource("/resources/Trash-32.png")));
		lblDelete.setBounds(910, 135, 80, 40);
		panel.add(lblDelete);
		lblE.setFont(new Font("Franklin Gothic Heavy", Font.PLAIN, 13));
		lblE.setIcon(new ImageIcon(AdminFrame.class.getResource("/resources/Edit-32.png")));
		lblE.setBounds(910, 178, 80, 40);
		panel.add(lblE);
		
		final JLabel lblNewLabel = new JLabel("");
		lblNewLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				AdminFrame.internalFrame.getContentPane().removeAll();
				AdminFrame.scrollPane.setViewportView(AdminFrame.internalFrame);
				for(int i=0; i<AdminFrame.name.length; i++){
					ResultPanel res=new ResultPanel(AdminFrame.name[i],AdminFrame.address[i],AdminFrame.tel[i]);
					AdminFrame.internalFrame.getContentPane().add(res);
				}
			}
			@Override
			public void mousePressed(MouseEvent arg0) {
				lblNewLabel.setIcon(new ImageIcon(AdminFrame.class.getResource("/resources/Refresh-50.png")));
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				lblNewLabel.setIcon(new ImageIcon(AdminFrame.class.getResource("/resources/Refresh-32.png")));	
			}
		});
		
		JLabel lblInfo = new JLabel("info\r\n\r\n");
		lblInfo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JOptionPane.showMessageDialog(null, "Click on Add to add your restaurant to the DB\nSelect a resturant by clicking on its panel and click\nEdit or Delete", "Info", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		lblInfo.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblInfo.setForeground(Color.WHITE);
		lblInfo.setIcon(new ImageIcon(AdminFrame.class.getResource("/resources/Attention-32.png")));
		lblInfo.setBounds(910, 476, 66, 29);
		panel.add(lblInfo);
		
		final JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setVisible(false);
				frame.dispose();
			}
			@Override
			public void mousePressed(MouseEvent e) {
				lblNewLabel_2.setIcon(new ImageIcon(AdminFrame.class.getResource("/resources/Close Window-32(1).png")));
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				lblNewLabel_2.setIcon(new ImageIcon(AdminFrame.class.getResource("/resources/Close Window-32.png")));
			}
		});
		lblNewLabel_2.setIcon(new ImageIcon(AdminFrame.class.getResource("/resources/Close Window-32.png")));
		lblNewLabel_2.setBounds(851, 3, 46, 40);
		panel.add(lblNewLabel_2);
		lblNewLabel.setIcon(new ImageIcon(AdminFrame.class.getResource("/resources/Refresh-32.png")));
		lblNewLabel.setBounds(910, 516, 70, 61);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Admin");
		lblNewLabel_1.setFont(new Font("Stencil", Font.PLAIN, 14));
		lblNewLabel_1.setIcon(new ImageIcon(AdminFrame.class.getResource("/resources/Manager-32.png")));
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setBounds(907, 3, 80, 78);
		panel.add(lblNewLabel_1);
                
                final JFormattedTextField UserLogin = new JFormattedTextField();
		UserLogin.setText(" Username/Email");
                UserLogin.setBackground(java.awt.Color.DARK_GRAY);
                UserLogin.setForeground(java.awt.Color.WHITE);
		UserLogin.setBounds(10, 3, 122, 20);
                UserLogin.addMouseListener(new MouseAdapter(){
                    @Override
                    public void mouseClicked(MouseEvent e){
                        UserLogin.setText("");
                    }
                });
		panel.add(UserLogin);

		final JPasswordField PassLogin = new JPasswordField();
		PassLogin.setBounds(142, 3, 128, 20);
                PassLogin.setBackground(java.awt.Color.DARK_GRAY);
                PassLogin.setForeground(java.awt.Color.WHITE);
		PassLogin.setText("jjjjjjjjjj");
                PassLogin.addMouseListener(new MouseAdapter(){
                    @Override
                    public void mouseClicked(MouseEvent e){
                        PassLogin.setText("");
                    }
                });
		panel.add(PassLogin);
                

		final JButton EnterLogin = new JButton("Enter");
		EnterLogin.setSelectedIcon(new ImageIcon(AdminFrame.class.getResource("/resources/Checked-32.png")));
		EnterLogin.setBounds(280, 3, 89, 20);
		panel.add(EnterLogin);
                EnterLogin.addMouseListener(new MouseAdapter() {
                    
			@Override
			public void mouseClicked(MouseEvent e) {
                            JOptionPane.showMessageDialog(null, "This action is not yet applicable");
			}
                                     
                    @Override
                    public void mouseEntered(MouseEvent e){
                          Cursor cur1 = new Cursor(Cursor.HAND_CURSOR);
                            EnterLogin.setCursor(cur1);
                        }
		});
                
                /*REGISTER FUNCTIONALITY, JUST COPY AND PASTE TO MAINFRAME OF UPDATED PROGRAM*/
                final JLabel register = new JLabel();
                register.setText("Click here to register for free");
                register.setBounds(378, 5, 200, 20);
                register.setFont(new Font("Arial", Font.PLAIN, 13));
                register.setForeground(java.awt.Color.blue);
                Font font = register.getFont();
                Map attributes = font.getAttributes();
                attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
                register.setFont(font.deriveFont(attributes));
                register.addMouseListener(new MouseAdapter (){
                    @Override
                    public void mouseEntered(MouseEvent e){
                        Cursor cur1 = new Cursor(Cursor.HAND_CURSOR);
                        register.setCursor(cur1);
                        register.setForeground(java.awt.Color.cyan);
                    }
                    @Override
                    public void mouseExited(MouseEvent e){
                        register.setForeground(java.awt.Color.blue);
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
                
                // Johan Search
                final JTextField searchbar = new JTextField();
		searchbar.setBounds(33, 60, 174, 20);
                searchbar.setText("Enter search terms here");
                searchbar.setFont(new Font("Arial", Font.ITALIC, 13));
                searchbar.setBorder(null);
		panel.add(searchbar);
		searchbar.setColumns(10);
                searchbar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		internalFrame.removeAll();
                        getInternalFrame();
        		String userSearch = searchbar.getText();
        		String strName = SQLSearch.searchRestName(userSearch);
                        String strAddress = SQLSearch.searchRestPhone(userSearch);
        		String strTel = SQLSearch.searchRestAddress(userSearch);
        		if (strName == "wrong") {
        			getResultPanel("No restaurant with this name", strTel, strAddress);
        		}
        		else{
				getResultPanel(strName, strTel, strAddress);
                        }
        	}
        });
                
                
		JTextPane txtpnPleaseSelectThe = new JTextPane();
		txtpnPleaseSelectThe.setEditable(false);
		txtpnPleaseSelectThe.setOpaque(false);
		txtpnPleaseSelectThe.setFont(new Font("Arial", Font.BOLD, 14));
		txtpnPleaseSelectThe.setText("Please select the cuisine");
                txtpnPleaseSelectThe.setForeground(java.awt.Color.white);
		txtpnPleaseSelectThe.setBounds(50, 107, 318, 20);
		panel.add(txtpnPleaseSelectThe);
		
                //add All to type
                String[] allcuisine = selectCuisine();
                String[] allcuisine1 = new String[allcuisine.length+1];
                allcuisine1[0] = "All";
                for(int i = 1; i <= allcuisine.length; i++){
                    allcuisine1[i] = allcuisine[i-1];
                }
                
                final JComboBox cuisinecomboBox = new JComboBox(allcuisine1);
		cuisinecomboBox.setBounds(53, 135, 150, 20);
                cuisinecomboBox.setFont(new Font("Arial", Font.PLAIN, 13));
                cuisinecomboBox.addMouseListener(new MouseAdapter(){
                    @Override
                    public void mouseEntered(MouseEvent e){
                          Cursor cur1 = new Cursor(Cursor.HAND_CURSOR);
                            cuisinecomboBox.setCursor(cur1);
                        }
                });
		panel.add(cuisinecomboBox);
                
		JTextPane txtpnSelectBudget = new JTextPane();
		txtpnSelectBudget.setOpaque(false);
		txtpnSelectBudget.setEditable(false);
		txtpnSelectBudget.setFont(new Font("Arial", Font.BOLD, 15));
		txtpnSelectBudget.setText("Select budget");
                txtpnSelectBudget.setForeground(java.awt.Color.white);
		txtpnSelectBudget.setBounds(50, 200, 197, 25);
		panel.add(txtpnSelectBudget);
		
		final JCheckBox sek15_20 = new JCheckBox("15-60 SEK");
		sek15_20.setBounds(53, 225, 109, 23);
                sek15_20.setOpaque(false);
                sek15_20.setFont(new Font("Arial", Font.PLAIN, 13));
                sek15_20.setForeground(java.awt.Color.white);
                sek15_20.addMouseListener(new MouseAdapter(){
                    @Override
                    public void mouseEntered(MouseEvent e){
                          Cursor cur1 = new Cursor(Cursor.HAND_CURSOR);
                            sek15_20.setCursor(cur1);
                        }
                    // fix
                    @Override
			public void mouseClicked(MouseEvent e) {
                            if(sek15_20.isSelected()){
                                slBudget[0] = "15-60";
                            }
                            else{
                                slBudget[0] = null;
                            }
                        }
                });
		panel.add(sek15_20);
		
		final JCheckBox sek50_75 = new JCheckBox("60-90 SEK");
		sek50_75.setBounds(53, 255, 109, 23);
                sek50_75.setOpaque(false);
                sek50_75.setFont(new Font("Arial", Font.PLAIN, 13));
                sek50_75.setForeground(java.awt.Color.white);
                sek50_75.addMouseListener(new MouseAdapter(){
                    @Override
                    public void mouseEntered(MouseEvent e){
                          Cursor cur1 = new Cursor(Cursor.HAND_CURSOR);
                            sek50_75.setCursor(cur1);
                        }
                    // fix
                    @Override
			public void mouseClicked(MouseEvent e) {
                            if(sek50_75.isSelected()){
                                slBudget[1] = "60-90";
                            }
                            else{
                            slBudget[1] = null;
                            }
                        }
                    
                });
		panel.add(sek50_75);
		
		final JCheckBox sek75_125 = new JCheckBox("90+ SEK");
		sek75_125.setBounds(53, 285, 109, 23);
                sek75_125.setOpaque(false);
                sek75_125.setFont(new Font("Arial", Font.PLAIN, 13));
                sek75_125.setForeground(java.awt.Color.white);
                sek75_125.addMouseListener(new MouseAdapter(){
                    @Override
                    public void mouseEntered(MouseEvent e){
                          Cursor cur1 = new Cursor(Cursor.HAND_CURSOR);
                            sek75_125.setCursor(cur1);
                        }
                    // fix
                    @Override
			public void mouseClicked(MouseEvent e) {
                            if(sek75_125.isSelected()){
                                slBudget[2] = "90+";
                            }
                            else{
                            slBudget[2] = null;
                            }
                        }
                });
		panel.add(sek75_125);
		
		final JButton selectbutton = new JButton("Select");
                selectbutton.setBounds(50, 340, 253, 29);
                
		selectbutton.addMouseListener(new MouseAdapter() {
                    
                        @Override
                        public void mouseEntered(MouseEvent e){
                            Cursor cur1 = new Cursor(Cursor.HAND_CURSOR);
                            selectbutton.setCursor(cur1);
                        }
                        
			@Override
			public void mouseClicked(MouseEvent e) {
                        //Selected cuisine in combobox placed in variable cuisine
				                cuisine = cuisinecomboBox.getSelectedItem().toString();
                                internalFrame.removeAll();
                                getInternalFrame();
                                getFilter(cuisine);
                                
			}
		});
		
		panel.add(selectbutton);
                
                scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(310, 67, 560, 510);
		panel.add(scrollPane);
                
                getInternalFrame();
                
                JLabel backgroundlayout = new JLabel("");
                backgroundlayout.addMouseListener(new MouseAdapter() {
                	@Override
                	public void mouseClicked(MouseEvent e) {
                	}
                });
                backgroundlayout.setIcon(new ImageIcon(getClass().getResource("/resources/TestBackground.png")));
                backgroundlayout.setBounds(0, 1, 1032, 600);
                panel.add(backgroundlayout);
                
                JTextPane txtpnRefresh = new JTextPane();
                txtpnRefresh.setFont(new Font("Tahoma", Font.PLAIN, 14));
                txtpnRefresh.setForeground(Color.WHITE);
                txtpnRefresh.setEditable(false);
                txtpnRefresh.setText("Refresh");
                txtpnRefresh.setBounds(910, 581, 66, 20);
                txtpnRefresh.setOpaque(false);
                panel.add(txtpnRefresh);
                
                name = selectRestName();
                tel = selectRestTel();
                address = selectRestAddress();
                
		for(int i=0; i<name.length; i++){
			getResultPanel(name[i], tel[i], address[i]);
		}
                 
        }
        
                private static void getInternalFrame(){
            internalFrame = new JInternalFrame("Please login to leave a feedback");
		internalFrame.getContentPane().setLayout(new BoxLayout(internalFrame.getContentPane(), BoxLayout.Y_AXIS));
		internalFrame.setEnabled(false);
		internalFrame.setSize(200, 10000);
		scrollPane.setViewportView(internalFrame);
		internalFrame.setVisible(true);
                internalFrame.setBorder(UIManager.getBorder("ScrollPane.border"));
        }
        
                private static void getResultPanel(String name, String tel, String address){
                    final ResultPanel resultPanel = new ResultPanel(name, address, tel);
                    resultPanel.addMouseListener(new MouseAdapter() {
                            
                                @Override
                                public void mouseEntered(MouseEvent e){
                                    Cursor cur1 = new Cursor(Cursor.HAND_CURSOR);
                                    resultPanel.setCursor(cur1);
                                }
                                
        			
        			
                        });
                    
                    internalFrame.getContentPane().add(resultPanel);
                }
                    
                /**
                 * 
                 * @param cuisine the cuisine type that you want to filter
                 */
                public static void getFilter(String cuisine){
                    String[][] cuisineRest = null;
                    // If the 
                    if(cuisine.equalsIgnoreCase("All")){
                        name = selectRestName();
                        tel = selectRestTel();
                        address = selectRestAddress();
                        cuisineRest = new String[name.length][3];
                        for(int i = 0; i < name.length; i++){  
                            cuisineRest[i][0] = name[i];
                            cuisineRest[i][1] = address[i];
                            cuisineRest[i][2] = tel[i];
                        }
                    }
                    else{
                        //Gets Name address and phone of all restaurants that been 
                        //filterde out with SQL filtering query in the class SQLFilter. 
                        cuisineRest = selectFilterCuisine(cuisine);
                    }
                    
                    String[][] budgetsRest = null;
            String[][] budgetsRest1 = null;
            String[][] budgetsRest2 = null;
            
            if(slBudget[0] == "15-60"){
            budgetsRest = SelectFilterBudget(slBudget[0]);
            }
            if(slBudget[1] == "60-90"){
            budgetsRest1 = SelectFilterBudget(slBudget[1]);
            }
            if(slBudget[2] == "90+"){
            budgetsRest2 = SelectFilterBudget(slBudget[2]);
            }
            
            if(slBudget[0] == "15-60"){
            for(int i = 0; i < cuisineRest.length; i++){
                for(int j = 0; j < budgetsRest.length; j++){
                    if(cuisineRest[i][0].equalsIgnoreCase(budgetsRest[j][0])){ 
                        getResultPanel(cuisineRest[i][0], cuisineRest[i][2], cuisineRest[i][1]);
                    }
                }
            }
            }
            
            if(slBudget[1] == "60-90"){
            for(int i = 0; i < cuisineRest.length; i++){
                for(int j = 0; j < budgetsRest1.length; j++){
                    if(cuisineRest[i][0].equalsIgnoreCase(budgetsRest1[j][0])){ 
                        getResultPanel(cuisineRest[i][0], cuisineRest[i][2], cuisineRest[i][1]);
                    }
                }
            }
            }
            
            if(slBudget[2] == "90+"){
            for(int i = 0; i < cuisineRest.length; i++){
                for(int j = 0; j < budgetsRest2.length; j++){
                    if(cuisineRest[i][0].equalsIgnoreCase(budgetsRest2[j][0])){ 
                        getResultPanel(cuisineRest[i][0], cuisineRest[i][2], cuisineRest[i][1]);
                    }
                }
            }
            }
            
            if(slBudget[0] == null && slBudget[1] == null && slBudget[2] == null){
            for(int i = 0; i < cuisineRest.length; i++){
                getResultPanel(cuisineRest[i][0], cuisineRest[i][2], cuisineRest[i][1]);
            }
            }
                    
                }
        }
