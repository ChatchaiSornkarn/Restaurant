package restaurant;

import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.SwingUtilities;

import java.awt.SystemColor;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.BorderLayout;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.JSeparator;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import static restaurant.SQLRestaurant.getIcon;

public class ResultPanel extends JPanel {
        public ResultPanel res = this;
	protected String name;
	protected String address;
	protected String tel;
        
	/**
	 * Create the panel.
         * @param name
         * @param address
         * @param tel
	 */
	/**
	 * @wbp.parser.constructor
	 */

public ResultPanel(String name, String address, String tel) {
		addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				AdminFrame.res=res;
			}
		});
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				
			}
			@Override
			public void mouseExited(MouseEvent e) {//the light up stuff
				
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				AdminFrame.res=res;
			}
		});
		this.name=name;
		this.address=address;
		this.tel=tel;
		setSize(430,130);
		setLayout(new BorderLayout(0, 0));
		
		JTextPane textPane = new JTextPane();
		textPane.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				AdminFrame.res=res;
			}
		});
		addMouseListener(new MouseAdapter() {
			
			
			@Override
			public void mouseClicked(MouseEvent e) {
				AdminFrame.res=res;
			}
		});
		textPane.setText("Name:\r\nAddress:\r\nTel:");
		textPane.setFont(new Font("Arial", Font.PLAIN, 14));
		textPane.setEditable(false);
		textPane.setBackground(SystemColor.menu);
		add(textPane, BorderLayout.WEST);
		addMouseListener(new MouseAdapter() {
			@Override
			
			public void mouseClicked(MouseEvent e) {
				AdminFrame.res=res;
			}
		});
		
		JTextPane textPane_1 = new JTextPane();
		textPane_1.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				AdminFrame.res=res;
			}
		});
		textPane_1.setText("   "+name+"\r\n   "+address+"\r\n   "+tel);
		textPane_1.setFont(new Font("Arial", Font.PLAIN, 14));
		textPane_1.setEditable(false);
		textPane_1.setBackground(SystemColor.menu);
		add(textPane_1, BorderLayout.CENTER);
		
		final JLabel lblFeedback = new JLabel("Feedback");

		lblFeedback.setIcon(new ImageIcon(ResultPanel.class.getResource("/resources/yellow-star-icon.png")));
		add(lblFeedback, BorderLayout.EAST);
                
                JLabel lbl = new JLabel("");
		add(lbl, BorderLayout.WEST);
                lbl.setIcon(getIcon("Select image from Restaurant where RestName = \""+name+"\""));
		lblFeedback.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				lblFeedback.setIcon(new ImageIcon(ResultPanel.class.getResource("/resources/red-star-icon.png")));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblFeedback.setIcon(new ImageIcon(ResultPanel.class.getResource("/resources/yellow-star-icon.png")));
			}
			public void mouseClicked(MouseEvent e){
	Runnable run = new Runnable(){
		public void run(){
			if(AdminFrame.inUse==true){
				AdminFrame.res=res;
				AdminFrame.internalFrame.getContentPane().removeAll();
				AdminFrame.internalFrame.getContentPane().add(new SResultPanel (res));
				}else {
					MainFrame.internalFrame.getContentPane().removeAll();
					MainFrame.internalFrame.getContentPane().add(new SResultPanel (res));
				}
		}
		
	};
	Thread thread = new Thread(run);
	thread.start();
			}
		});
		
		JSeparator separator = new JSeparator();
		add(separator, BorderLayout.SOUTH);
                
	}

        public ResultPanel(ResultPanel resultpanel) {
		this.name = resultpanel.name;
		this.address = resultpanel.address;
		this.tel = resultpanel.tel;
	}
        
}
