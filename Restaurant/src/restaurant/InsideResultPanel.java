/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restaurant;

import java.awt.Font;
import java.awt.SystemColor;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTextPane;

/**
 *
 * @author chatchai
 */
public class InsideResultPanel extends ResultPanel{
    
    public InsideResultPanel(ResultPanel resultPanel) {
        super(resultPanel);
        setLayout(null);
		
        JTextPane txtpnName = new JTextPane();
	txtpnName.setFont(new Font("Arial", Font.PLAIN, 14));
	txtpnName.setBackground(SystemColor.control);
	txtpnName.setEditable(false);
	txtpnName.setText("Name:\r\nTelephone:\r\nAddress:");
	txtpnName.setBounds(0, 0, 72, 66);
	add(txtpnName);
		
	JTextPane txtpnFfdfd = new JTextPane();
        txtpnFfdfd.setBackground(SystemColor.control);
	txtpnFfdfd.setEditable(false);
	txtpnFfdfd.setFont(new Font("Arial", Font.PLAIN, 14));
	txtpnFfdfd.setText("   "+name+"\r\n   "+address+"\r\n   "+tel);
	txtpnFfdfd.setBounds(72, 0, 378, 66);
	add(txtpnFfdfd);
		
	JLabel lblNewLabel = new JLabel("Leave Feedback");
	lblNewLabel.setIcon(new ImageIcon("C:\\Users\\Beroo94\\Desktop\\misc_58.png"));
        lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        lblNewLabel.setHorizontalAlignment(lblNewLabel.TRAILING);
	lblNewLabel.setBounds(385, 78, 164, 34);
	add(lblNewLabel);

	}

}
