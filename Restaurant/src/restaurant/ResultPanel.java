package restaurant;

import javax.swing.JPanel;
import javax.swing.JTextPane;
import java.awt.SystemColor;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

public class ResultPanel extends JPanel {
     
	String name;
	String address;
	String tel;
	/**
	 * Create the panel.
	 */
	public ResultPanel(String name, String address,String tel) {
		this.name=name;
		this.address=address;
		this.tel=tel;
		setLayout(null);
		
		JTextPane txtpnName = new JTextPane();
		txtpnName.setFont(new Font("Century", Font.PLAIN, 14));
		txtpnName.setBackground(SystemColor.control);
		txtpnName.setEditable(false);
		txtpnName.setText("Name:\r\nTelephone:\r\nAddress:");
		txtpnName.setBounds(0, 0, 72, 66);
		add(txtpnName);
		
		JTextPane txtpnFfdfd = new JTextPane();
                txtpnFfdfd.setBackground(SystemColor.control);
		txtpnFfdfd.setEditable(false);
		txtpnFfdfd.setFont(new Font("Century", Font.PLAIN, 14));
		txtpnFfdfd.setText("   "+name+"\r\n   "+address+"\r\n   "+tel);
		txtpnFfdfd.setBounds(72, 0, 378, 66);
		add(txtpnFfdfd);
		
		JLabel lblNewLabel = new JLabel("Leave Feedback");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\Beroo94\\Desktop\\misc_58.png"));
                lblNewLabel.setFont(new Font("Century", Font.PLAIN, 14));
                lblNewLabel.setHorizontalAlignment(lblNewLabel.TRAILING);
		lblNewLabel.setBounds(390, 78, 164, 34);
		add(lblNewLabel);

	}

}
