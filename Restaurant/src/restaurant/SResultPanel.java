package restaurant;
import javax.swing.JTextPane;
import java.awt.SystemColor;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JSeparator;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.Icon;
import static restaurant.SQLRestaurant.getIcon;

public class SResultPanel extends ResultPanel {
     
	
	int selected;
	String comment;
	private JTextField textField;
	/**
	 * Create the panel.
	 */
//	public SResultPanel(String name, String address, String tel) {//I don't think we need this one but just in case
//		super(name,address,tel);
//		selected=0;
//		setSize(511,358);
//		setLayout(null);
//		 create();
//		
//	}
	public SResultPanel(ResultPanel resultpanel){//to construct the result out of the original one
		super(resultpanel.name,resultpanel.address,resultpanel.tel);
		selected=0;
		setSize(542,352);
		setLayout(null);
		create();
	}
	public void create(){
		final JLabel onestar = new JLabel("New label");
		onestar.setIcon(new ImageIcon(SResultPanel.class.getResource("/resources/red-star-icon.png")));
		onestar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				onestar.setIcon(new ImageIcon(SResultPanel.class.getResource("/resources/yellow-star-icon.png")));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				if(selected==0)
				onestar.setIcon(new ImageIcon(SResultPanel.class.getResource("/resources/red-star-icon.png")));
			}
			@Override
			public void mouseClicked(MouseEvent arg0) {
			selected=1;
			}
		});
		
		onestar.setBounds(0, 66, 31, 26);
		add(onestar);
		
		final JLabel twostars = new JLabel("New label");
		twostars.setIcon(new ImageIcon(SResultPanel.class.getResource("/resources/red-star-icon.png")));
		twostars.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				twostars.setIcon(new ImageIcon(SResultPanel.class.getResource("/resources/yellow-star-icon.png")));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				if(selected<2)
					twostars.setIcon(new ImageIcon(SResultPanel.class.getResource("/resources/red-star-icon.png")));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				selected=2;
				onestar.setIcon(new ImageIcon(SResultPanel.class.getResource("/resources/yellow-star-icon.png")));
			}
		});
		
		twostars.setBounds(31, 66, 31, 26);
		add(twostars);
		
		final JLabel threestars = new JLabel("New label");
		threestars.setIcon(new ImageIcon(SResultPanel.class.getResource("/resources/red-star-icon.png")));
		threestars.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				threestars.setIcon(new ImageIcon(SResultPanel.class.getResource("/resources/yellow-star-icon.png")));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				if(selected<3)
					threestars.setIcon(new ImageIcon(SResultPanel.class.getResource("/resources/red-star-icon.png")));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				selected=3;
				onestar.setIcon(new ImageIcon(SResultPanel.class.getResource("/resources/yellow-star-icon.png")));
				twostars.setIcon(new ImageIcon(SResultPanel.class.getResource("/resources/yellow-star-icon.png")));
			}
		});
		
		threestars.setBounds(62, 66, 31, 26);
		add(threestars);
		
		final JLabel fourstars = new JLabel("New label");
		fourstars.setIcon(new ImageIcon(SResultPanel.class.getResource("/resources/red-star-icon.png")));
		fourstars.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				fourstars.setIcon(new ImageIcon(SResultPanel.class.getResource("/resources/yellow-star-icon.png")));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				if(selected<4)
					fourstars.setIcon(new ImageIcon(SResultPanel.class.getResource("/resources/red-star-icon.png")));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				selected=4;
				onestar.setIcon(new ImageIcon(SResultPanel.class.getResource("/resources/yellow-star-icon.png")));
				twostars.setIcon(new ImageIcon(SResultPanel.class.getResource("/resources/yellow-star-icon.png")));
				threestars.setIcon(new ImageIcon(SResultPanel.class.getResource("/resources/yellow-star-icon.png")));
				
			}
		});
		
		fourstars.setBounds(93, 66, 31, 26);
		add(fourstars);
		
		JTextPane textPane = new JTextPane();
		textPane.setBounds(10, 0, 62, 128);
		textPane.setText("Name:\r\nAddress:\r\nTel:");
		textPane.setFont(new Font("Lucida Handwriting", Font.PLAIN, 14));
		textPane.setEditable(false);
		textPane.setBackground(SystemColor.menu);
		add(textPane);
		
		JTextPane textPane_1 = new JTextPane();
		textPane_1.setBounds(62, 0, 286, 128);
		textPane_1.setText("   "+name+"\r\n   "+address+"\r\n   "+tel);
		textPane_1.setFont(new Font("Lucida Handwriting", Font.PLAIN, 14));
		textPane_1.setEditable(false);
		textPane_1.setBackground(SystemColor.menu);
		add(textPane_1);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 128, 542, 2);
		add(separator);
		
		JTextPane txtpnLeaveAComment = new JTextPane();
		txtpnLeaveAComment.setBackground(SystemColor.control);
		txtpnLeaveAComment.setEditable(false);
		txtpnLeaveAComment.setText("Leave a comment");
		txtpnLeaveAComment.setBounds(0, 139, 62, 34);
		add(txtpnLeaveAComment);
		
		textField = new JTextField();//the comment will be here
		textField.setBounds(62, 147, 240, 26);
		add(textField);
		textField.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 182, 498, 170);
		add(scrollPane);
		
		JTextPane txtpnPutTheComment = new JTextPane();
		txtpnPutTheComment.setEditable(false);
		txtpnPutTheComment.setText("Put the comment next to it's username and print each one in a new line and the  scroll is automatically provided\r\nUser : Comment\r\nUser : Comment\r\nUser : Comment\r\nUser : Comment\r\nUser : Comment\r\nUser : Comment\r\nUser : Comment\r\nUser : Comment\r\nUser : Comment\r\nUser : Comment\r\nUser : Comment\r\nUser : Comment\r\nUser : Comment\r\nUser : Comment\r\nUser : Comment\r\nUser : Comment\r\nUser : Comment");
		scrollPane.setViewportView(txtpnPutTheComment);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				if(AdminFrame.inUse==true){
				AdminFrame.internalFrame.getContentPane().removeAll();
				AdminFrame.scrollPane.setViewportView(AdminFrame.internalFrame);
				for(int i=0; i<AdminFrame.name.length; i++){
					ResultPanel res=new ResultPanel(AdminFrame.name[i],AdminFrame.address[i],AdminFrame.tel[i]);
					AdminFrame.internalFrame.getContentPane().add(res);
				}
				}else {
					MainFrame.internalFrame.getContentPane().removeAll();
					MainFrame.scrollPane.setViewportView(MainFrame.internalFrame);
					for(int i=0; i<MainFrame.name.length; i++){
						ResultPanel res=new ResultPanel(MainFrame.name[i],MainFrame.address[i],MainFrame.tel[i]);
						MainFrame.internalFrame.getContentPane().add(res);
					}
				}
				
			}
		});
		lblNewLabel.setIcon(new ImageIcon(SResultPanel.class.getResource("/resources/Home-32.png")));
		lblNewLabel.setBounds(501, 0, 31, 45);
		add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("BACK");
		lblNewLabel_1.setFont(new Font("Arial Black", Font.PLAIN, 12));
		lblNewLabel_1.setBounds(500, 41, 42, 28);
		add(lblNewLabel_1);
                
                JLabel label = new JLabel("");
		label.setBounds(0, 0, 542, 509);
		label.setIcon(getIcon("Select image from Restaurant where RestName = \""+name+"\""));
		add(label);
	}
}
