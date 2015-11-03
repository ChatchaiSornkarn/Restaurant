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
import java.awt.Color;
import java.awt.ScrollPane;
import java.awt.Label;
import java.awt.Panel;
import javax.swing.JScrollBar;
import javax.swing.JLayeredPane;

import java.awt.Component;
import java.sql.SQLException;
import javax.swing.JList;
import javax.swing.JTabbedPane;
import static restaurant.SQLRestaurant.*;

public class MainFrame {

	public JFrame frame;
        
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
		frame.setSize(800, 433);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.X_AXIS));
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JTextPane txtpnPleaseSelectThe = new JTextPane();
		txtpnPleaseSelectThe.setEditable(false);
		txtpnPleaseSelectThe.setBackground(SystemColor.control);
		txtpnPleaseSelectThe.setFont(new Font("Lucida Handwriting", Font.PLAIN, 14));
		txtpnPleaseSelectThe.setText("Please select the cusinie");
		txtpnPleaseSelectThe.setBounds(0, 0, 318, 20);
		panel.add(txtpnPleaseSelectThe);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(0, 31, 197, 20);
		comboBox.addItem("Thai");
		comboBox.addItem("Swedish");
		comboBox.addItem("Indian");
		panel.add(comboBox);
                
		JTextPane txtpnSelectBudget = new JTextPane();
		txtpnSelectBudget.setBackground(SystemColor.control);
		txtpnSelectBudget.setEditable(false);
		txtpnSelectBudget.setFont(new Font("Lucida Handwriting", Font.PLAIN, 14));
		txtpnSelectBudget.setText("Select budget");
		txtpnSelectBudget.setBounds(0, 62, 197, 20);
		panel.add(txtpnSelectBudget);
		
		JRadioButton rdbtnSek = new JRadioButton("15-50 SEK");
		rdbtnSek.setBounds(6, 92, 109, 23);
		panel.add(rdbtnSek);
		
		JRadioButton rdbtnSek_1 = new JRadioButton("50-75 SEK");
		rdbtnSek_1.setBounds(6, 118, 109, 23);
		panel.add(rdbtnSek_1);
		
		JRadioButton rdbtnSek_2 = new JRadioButton("75-125 SEK");
		rdbtnSek_2.setBounds(6, 144, 109, 23);
		panel.add(rdbtnSek_2);
		
		ButtonGroup btnG=new ButtonGroup();
		btnG.add(rdbtnSek);
		rdbtnSek.setSelected(true);
		btnG.add(rdbtnSek_1);
		btnG.add(rdbtnSek_2);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//CODE ACTION HERE
			}
		});
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\Beroo94\\Desktop\\Arrow-right-icon.png"));
		lblNewLabel.setBounds(10, 186, 46, 51);
		panel.add(lblNewLabel);
		
                
		JTextPane txtpnGo = new JTextPane();
		txtpnGo.setBackground(SystemColor.control);
		txtpnGo.setEditable(false);
		txtpnGo.setFont(new Font("Stencil", Font.PLAIN, 14));
		txtpnGo.setText("GO");
		txtpnGo.setBounds(20, 226, 33, 20);
		panel.add(txtpnGo);
                
                JList jpanel = new JList(selectRestName());
                jpanel.setBounds(207, 31, 567, 352);
                JScrollPane jscroll = new JScrollPane(jpanel);
                panel.add(jscroll);
                
                JTabbedPane tabbedPane = getJTabbedPane();
                panel.add(tabbedPane);
                
                name = selectRestName();
                tel = selectRestTel();
                address = selectRestAddress();
                
		for(int i = 0; i < 3; i++){  
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
        
        
}
