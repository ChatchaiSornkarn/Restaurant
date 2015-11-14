package restaurant;

import javax.swing.JPanel;
import javax.swing.JTextPane;
import java.awt.SystemColor;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.BorderLayout;
import javax.swing.JSeparator;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ResultPanel extends JPanel {
     
	protected String name;
	protected String address;
	protected String tel;
        
	/**
	 * Create the panel.
         * @param name
         * @param address
         * @param tel
	 */
	public ResultPanel(String name, String address, String tel) {
		this.name=name;
		this.address=address;
		this.tel=tel;
		setSize(430,130);
		setLayout(new BorderLayout(0, 0));
		
		JTextPane textPane = new JTextPane();
		textPane.setText("Name:\r\nAddress:\r\nTel:");
		textPane.setFont(new Font("Lucida Handwriting", Font.PLAIN, 14));
		textPane.setEditable(false);
		textPane.setBackground(SystemColor.menu);
		add(textPane, BorderLayout.WEST);
		
		JTextPane textPane_1 = new JTextPane();
		textPane_1.setText("   "+name+"\r\n   "+address+"\r\n   "+tel);
		textPane_1.setFont(new Font("Lucida Handwriting", Font.PLAIN, 14));
		textPane_1.setEditable(false);
		textPane_1.setBackground(SystemColor.menu);
		add(textPane_1, BorderLayout.CENTER);
		
		final JLabel lblFeedback = new JLabel("Feedback");
		
		lblFeedback.setIcon(new ImageIcon(ResultPanel.class.getResource("/resources/yellow-star-icon.png")));
		add(lblFeedback, BorderLayout.EAST);
		lblFeedback.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				lblFeedback.setIcon(new ImageIcon(ResultPanel.class.getResource("/resources/red-star-icon.png")));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblFeedback.setIcon(new ImageIcon(ResultPanel.class.getResource("/resources/yellow-star-icon.png")));
			}
		});
		
		JSeparator separator = new JSeparator();
		add(separator, BorderLayout.SOUTH);
                
	}

        public ResultPanel(ResultPanel resultpanel) {
		this.name= resultpanel.name;
		this.address= resultpanel.address;
		this.tel= resultpanel.tel;
	}
        
}
