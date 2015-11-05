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
import java.sql.SQLException;
import javafx.scene.paint.Color;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.border.EmptyBorder;
import static restaurant.SQLStringReturn.*;

public class MainFrame {

	public JFrame frame;
        public JTabbedPane tabbedPane;
        private String[] name,tel,address;

	
	/**
	 * Create the application.
	 */
	public MainFrame() throws SQLException {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() throws SQLException {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setSize(820, 425);
                frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.X_AXIS));
                frame.setResizable(false);
                
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel);
		panel.setLayout(null);
                
      
                
                JLabel toolbar = new JLabel();
                toolbar.setIcon(new ImageIcon("resources/Background2.png"));
                toolbar.setBounds(100,100,450,300);
                panel.add(toolbar);
                
                JTextField searchbar = new JTextField();
		searchbar.setBounds(15, 30, 205, 20);
                searchbar.setText("Search...");
                searchbar.setFont(new Font("Century", Font.PLAIN, 13));
		panel.add(searchbar);
		searchbar.setColumns(10);
                searchbar.addMouseListener(new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent e) {
                searchbar.setText("");
			}
		});
                
                
		JTextPane txtpnPleaseSelectThe = new JTextPane();
		txtpnPleaseSelectThe.setEditable(false);
		txtpnPleaseSelectThe.setOpaque(false);
		txtpnPleaseSelectThe.setFont(new Font("Century", Font.PLAIN, 15));
		txtpnPleaseSelectThe.setText("Please select the cuisine");
		txtpnPleaseSelectThe.setBounds(15, 53, 318, 20);
		panel.add(txtpnPleaseSelectThe);
		
		JComboBox comboBox = new JComboBox(selectCuisine());
		comboBox.setBounds(15, 78, 203, 20);
                comboBox.setFont(new Font("Century", Font.PLAIN, 13));
		panel.add(comboBox);
                
		JTextPane txtpnSelectBudget = new JTextPane();
		txtpnSelectBudget.setOpaque(false);
		txtpnSelectBudget.setEditable(false);
		txtpnSelectBudget.setFont(new Font("Century", Font.PLAIN, 15));
		txtpnSelectBudget.setText("Select budget");
		txtpnSelectBudget.setBounds(15, 100, 197, 25);
		panel.add(txtpnSelectBudget);
		
		JRadioButton rdbtnSek = new JRadioButton("15-50 SEK");
		rdbtnSek.setBounds(15, 120, 109, 23);
                rdbtnSek.setOpaque(false);
                rdbtnSek.setFont(new Font("Century", Font.PLAIN, 13));
		panel.add(rdbtnSek);
		
		JRadioButton rdbtnSek_1 = new JRadioButton("50-75 SEK");
		rdbtnSek_1.setBounds(15, 140, 109, 23);
                rdbtnSek_1.setOpaque(false);
                rdbtnSek_1.setFont(new Font("Century", Font.PLAIN, 13));
		panel.add(rdbtnSek_1);
		
		JRadioButton rdbtnSek_2 = new JRadioButton("75-125 SEK");
		rdbtnSek_2.setBounds(15, 160, 109, 23);
                rdbtnSek_2.setOpaque(false);
                rdbtnSek_2.setFont(new Font("Century", Font.PLAIN, 13));
		panel.add(rdbtnSek_2);
		
		ButtonGroup btnG=new ButtonGroup();
		btnG.add(rdbtnSek);
		rdbtnSek.setSelected(true);
		btnG.add(rdbtnSek_1);
		btnG.add(rdbtnSek_2);
		
		JLabel lblNewLabel = new JLabel("");
                lblNewLabel.setIcon(new ImageIcon(getClass().getResource("/resources/RedGo.png")));
                lblNewLabel.setBounds(10, 186, 100, 50);
		lblNewLabel.addMouseListener(new MouseAdapter() {
                        
                        @Override
			public void mouseEntered(MouseEvent e) {
                        lblNewLabel.setIcon(new ImageIcon(getClass().getResource("/resources/GreenGo.png")));
			}
                    
                         @Override
			public void mouseExited(MouseEvent e) {
                        lblNewLabel.setIcon(new ImageIcon(getClass().getResource("/resources/RedGo.png")));
			}
                    
			@Override
			public void mousePressed(MouseEvent e) {
                        //Selected cuisine in combobox placed in variable cuisine
                		String cuisine = comboBox.getSelectedItem().toString();
                		
                		//Gets Name address and phone of all restaurants that been 
                		//filterde out with SQL filtering query in the class SQLFilter.  
                		String[] restName = selectFilterCuisineName(cuisine);
                		String[] address = selectFilterCuisineAddress(cuisine);
                		String[] phone = selectFilterCuisinePhone(cuisine);
                		
                		//If the tabbedPane is empty puts restaurant names in to tabbs
                		//and puts restaurant information into ResultPanel.
                		if (tabbedPane.getSelectedIndex() == -1) {
                		for(int i = 0; i < restName.length; i++){  
                            getResultPanel(tabbedPane, restName[i], phone[i], address[i]);
                            }
                		}
                		//If the tabbedPane is not empty. Empties the tabbedPane
                		// first and than puts restaurant names in to tabbs
                		//and puts restaurant information into ResultPanel.
                		else {
                			tabbedPane.removeAll();
                			for(int i = 0; i < restName.length; i++){  
                                getResultPanel(tabbedPane, restName[i], phone[i], address[i]);
                                }
                		}
			}
		});
		
		panel.add(lblNewLabel);
		
                
	//	JTextPane txtpnGo = new JTextPane();
	//	txtpnGo.setBackground(SystemColor.control);
	//	txtpnGo.setEditable(false);
	//	txtpnGo.setFont(new Font("Stencil", Font.PLAIN, 14));
	//	txtpnGo.setText("GO");
	//	txtpnGo.setBounds(20, 226, 33, 20);
	//	panel.add(txtpnGo);
                
                JList jpanel = new JList(selectRestName());
                jpanel.setBounds(207, 31, 567, 357);
                JScrollPane jscroll = new JScrollPane(jpanel);
                panel.add(jscroll);
                
                tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
                tabbedPane.setFont(new Font("Century", Font.PLAIN, 12));
		tabbedPane.setToolTipText(":)\r\n");
		tabbedPane.setBounds(235, 27, 572, 363);
                tabbedPane.setBackground(java.awt.Color.white);
		panel.add(tabbedPane);
                
                name = selectRestName();
                tel = selectRestTel();
                address = selectRestAddress();
                
		for(int i = 0; i < 50; i++){  
                getResultPanel(tabbedPane, name[i], tel[i], address[i]);
                }
                 
        }
        
        private static void getResultPanel(JTabbedPane tabbedPane, String name, String tel, String address){
            tabbedPane.addTab(name, new ImageIcon("C:\\Users\\Beroo94\\Desktop\\restaurant_12_2x.png"), new ResultPanel(name, tel, address));
        }
        
        private static JTabbedPane getJTabbedPane(){
            JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
            tabbedPane.setToolTipText("Hello\r\n");
            tabbedPane.setBounds(207, 31, 567, 352);
            
            return tabbedPane;
        }

    private Object getclass() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
        
        
}
