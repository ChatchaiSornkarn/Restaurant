package restaurant;

import javax.swing.JPanel;
import javax.swing.JTextPane;
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
import javax.swing.BoxLayout;
import static restaurant.SQLRating.getRating;

/**
 * Class: Display panel of the restaurant
 * @author obada
 */
public class ResultPanel extends JPanel {

    public ResultPanel res = this;
    protected String name;
    protected String address;
    protected String tel;
    public int rating;
    protected String weblink;

    /**
     * Create the panel
     *
     * @param name
     * @param address
     * @param tel
     */
    public ResultPanel(String name, String address, String tel, String weblink) {
        addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent arg0) {
                AdminFrame.res = res;
                Admin.res = res;
            }
        });
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent arg0) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }

            @Override
            public void mouseClicked(MouseEvent e) {
                AdminFrame.res = res;
                Admin.res = res;
            }
        });
        
        this.name = name;
        this.address = address;
        this.tel = tel;
        this.rating = getRating(name);
        this.weblink = weblink;
        setSize(430, 130);
        setLayout(new BorderLayout(0, 0));

        JTextPane textPane = new JTextPane();
        textPane.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent arg0) {
                AdminFrame.res = res;
                Admin.res = res;
            }
        });
        addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                AdminFrame.res = res;
                Admin.res = res;
            }
        });
        textPane.setText("Name:\r\nAddress:\r\nTel\r\nWebsite:");
        textPane.setFont(new Font("Arial", Font.PLAIN, 14));
        textPane.setEditable(false);
        textPane.setBackground(SystemColor.menu);
        add(textPane, BorderLayout.WEST);
        addMouseListener(new MouseAdapter() {
            @Override

            public void mouseClicked(MouseEvent e) {
                AdminFrame.res = res;
                Admin.res = res;
            }
        });

        JTextPane textPane_1 = new JTextPane();
        textPane_1.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent arg0) {
                AdminFrame.res = res;
                Admin.res = res;
            }
        });
        textPane_1.setText("   " + name + "\r\n   " + address + "\r\n   " + tel + "\r\n   "  +weblink);
        textPane_1.setFont(new Font("Arial", Font.PLAIN, 14));
        textPane_1.setEditable(false);
        textPane_1.setBackground(SystemColor.menu);
        add(textPane_1, BorderLayout.CENTER);

        final JLabel lblFeedback = new JLabel("Feedback");

        lblFeedback.setIcon(new ImageIcon(ResultPanel.class.getResource("/resources/1449436231_icon-23-star.png")));
        add(lblFeedback, BorderLayout.EAST);

        JLabel lbl = new JLabel("");
        add(lbl, BorderLayout.WEST);
        lbl.setIcon(getIcon("Select image from Restaurant where RestName = \"" + name + "\""));
        lblFeedback.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent arg0) {
                lblFeedback.setIcon(new ImageIcon(ResultPanel.class.getResource("/resources/1449436226_icon-23-star.png")));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                lblFeedback.setIcon(new ImageIcon(ResultPanel.class.getResource("/resources/1449436231_icon-23-star.png")));
            }

            //which restaurant are being press 
            public void mouseClicked(MouseEvent e) {
                        if (AdminFrame.inUse == true) {
                            AdminFrame.res = res;
                            AdminFrame.internalFrame.getContentPane().removeAll();
                            AdminFrame.internalFrame.getContentPane().add(new SResultPanel(res));
                        } else if (Admin.inUse == true) {
                            Admin.res = res;
                            Admin.internalFrame.getContentPane().removeAll();
                            Admin.internalFrame.getContentPane().add(new SResultPanel(res));
                        } else {
                            MainFrame.internalFrame.getContentPane().removeAll();
                            MainFrame.internalFrame.getContentPane().add(new SResultPanel(res));
                        }
                    }
        });

        JSeparator separator = new JSeparator();
        add(separator, BorderLayout.SOUTH);
        
        
        JPanel panel = new JPanel();
        add(panel, BorderLayout.NORTH);
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        
        // star
        if(rating==0){
        	for(int i=0; i<4; i++){
        		JLabel label = new JLabel();
        		label.setIcon(new ImageIcon(ResultPanel.class.getResource("/resources/1449436231_icon-23-star.png")));
        		panel.add(label);
        	}
        }else if(rating==4){
        	for(int i=0; i<4; i++){
        		JLabel label = new JLabel();
        		label.setIcon(new ImageIcon(ResultPanel.class.getResource("/resources/1449436226_icon-23-star.png")));
        		panel.add(label);
        	}
        }else{
        
        for(int i=0; i<rating; i++){
        	JLabel label = new JLabel();
    		label.setIcon(new ImageIcon(ResultPanel.class.getResource("/resources/1449436226_icon-23-star.png")));
    		panel.add(label);
        }
        for(int i=0; i<4-rating; i++){
        	JLabel label = new JLabel();
    		label.setIcon(new ImageIcon(ResultPanel.class.getResource("/resources/1449436231_icon-23-star.png")));
    		panel.add(label);
        }
        
        }     
    }

    /**
     * when reuse panel 
     */
    public ResultPanel(ResultPanel resultpanel) {
        this.name = resultpanel.name;
        this.address = resultpanel.address;
        this.tel = resultpanel.tel;
        this.rating = getRating(this.name);
        this.weblink = resultpanel.weblink;
    }

}
