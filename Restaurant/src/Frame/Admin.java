package Frame;

import ResultPanel.Blocks;
import ResultPanel.ResultPanel;
import SQL.SQLOwners;
import SQL.SQLSearch;
import SQL.SQLFilter;
import javax.swing.JFrame;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import java.awt.Font;
import java.awt.SystemColor;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ScrollPaneConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollBar;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.JInternalFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import static SQL.SQLRestaurant.*;
import java.awt.Color;
import static Frame.AdminFrame.internalFrame;
import static Frame.AdminFrame.scrollPane;
import static Frame.Login.adminLogin;

/**
 * Class: This is a frame including function for super admin
 * @author
 */
public class Admin {

    public JFrame frame;
    public static JInternalFrame internalFrame;
    private static String[] slBudget = new String[3];
    public static String[] name, tel, address, weblink;
    private static JPanel panel;
    public static JScrollPane scrollPane;
    private static String cuisine;
    public static ResultPanel res;
    public static boolean inUse = false;

    /**
     * Create the application.
     */
    public static void setCuisine(String string) {
        cuisine = string;
    }

    public Admin() throws SQLException {
        initialize();
        inUse = true;
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() throws SQLException {
        // created frame
        frame = new JFrame();
        frame.setBounds(100, 100, 450, 300);
        frame.setSize(990, 602);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.X_AXIS));
        frame.setUndecorated(true);

        //Panel on the frame
        JPanel panel = new JPanel();
        frame.getContentPane().add(panel);
        panel.setLayout(null);
        panel.setBackground(java.awt.Color.DARK_GRAY);

        // Edit restaurant button
        final JLabel lblE = new JLabel("Edit");
        lblE.setForeground(SystemColor.inactiveCaptionBorder);
        lblE.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                if (res == null) {
                    JOptionPane.showMessageDialog(null, "Select a value\nPlease click on a restaurant panel", "alert", JOptionPane.ERROR_MESSAGE);
                } else {
                    EditWindow edit = new EditWindow(res);
                    edit.setVisible(true);
                }
            }
        });

        // Add Restaurant button
        final JLabel lblAdd = new JLabel("Add");
        lblAdd.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                AddWindow frame = new AddWindow();
                frame.setVisible(true);
            }

            @Override
            public void mousePressed(MouseEvent arg0) {
                lblAdd.setIcon(new ImageIcon(Admin.class.getResource("/resources/Upload to the Cloud Filled-32.png")));
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                lblAdd.setIcon(new ImageIcon(Admin.class.getResource("/resources/Upload to the Cloud-32.png")));
            }
        });
        lblAdd.setForeground(SystemColor.inactiveCaptionBorder);
        lblAdd.setFont(new Font("Arial", Font.PLAIN, 14));
        lblAdd.setIcon(new ImageIcon(Admin.class.getResource("/resources/Upload to the Cloud-32.png")));
        lblAdd.setBounds(910, 87, 66, 40);
        panel.add(lblAdd);

        // Delete restaurant button
        JLabel lblDelete = new JLabel("Delete");
        lblDelete.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (res == null) {
                    JOptionPane.showMessageDialog(null, "Select a value\nPlease click on a restaurant panel", "alert", JOptionPane.ERROR_MESSAGE);
                } else {
                    DeleteWindow delete = new DeleteWindow(res);
                    delete.setVisible(true);
                }
            }
        });
        lblDelete.setForeground(SystemColor.inactiveCaptionBorder);
        lblDelete.setFont(new Font("Arial", Font.PLAIN, 14));
        lblDelete.setIcon(new ImageIcon(Admin.class.getResource("/resources/Trash-32.png")));
        lblDelete.setBounds(910, 135, 80, 40);
        panel.add(lblDelete);
        lblE.setFont(new Font("Arial", Font.PLAIN, 14));
        lblE.setIcon(new ImageIcon(Admin.class.getResource("/resources/Edit-32.png")));
        lblE.setBounds(910, 178, 80, 40);
        panel.add(lblE);

        // refresh button
        final JLabel lblNewLabel = new JLabel("");
        lblNewLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                Admin.internalFrame.getContentPane().removeAll();
                Admin.scrollPane.setViewportView(Admin.internalFrame);
                for (int i = 0; i < Admin.name.length; i++) {
                    ResultPanel res = new ResultPanel(Admin.name[i], Admin.address[i], Admin.tel[i], Admin.weblink[i]);
                    Admin.internalFrame.getContentPane().add(res);
                }
            }

            @Override
            public void mousePressed(MouseEvent arg0) {
                lblNewLabel.setIcon(new ImageIcon(Admin.class.getResource("/resources/Refresh-50.png")));
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                lblNewLabel.setIcon(new ImageIcon(Admin.class.getResource("/resources/Refresh-32.png")));
            }
        });

        //help button
        JLabel lblInfo = new JLabel("Info\r\n\r\n");
        lblInfo.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JOptionPane.showMessageDialog(null, "Click on Add to add your restaurant to the DB\nSelect a resturant by clicking on its panel and click\nEdit or Delete", "Info", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        lblInfo.setFont(new Font("Arial", Font.PLAIN, 14));
        lblInfo.setForeground(Color.WHITE);
        lblInfo.setIcon(new ImageIcon(Admin.class.getResource("/resources/Attention-32.png")));
        lblInfo.setBounds(910, 476, 66, 29);
        panel.add(lblInfo);

        final JLabel lblNewLabel_2 = new JLabel("");
        lblNewLabel_2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                adminLogin = false;
                frame.setVisible(false);
                frame.dispose();
            }

            @Override
            public void mousePressed(MouseEvent e) {
                lblNewLabel_2.setIcon(new ImageIcon(Admin.class.getResource("/resources/Close Window-32(1).png")));
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                lblNewLabel_2.setIcon(new ImageIcon(Admin.class.getResource("/resources/Close Window-32.png")));
            }
        });
        lblNewLabel_2.setIcon(new ImageIcon(Admin.class.getResource("/resources/Close Window-32.png")));
        lblNewLabel_2.setBounds(867, 0, 46, 40);
        panel.add(lblNewLabel_2);
        lblNewLabel.setIcon(new ImageIcon(Admin.class.getResource("/resources/Refresh-32.png")));
        lblNewLabel.setBounds(920, 516, 70, 61);
        panel.add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("Admin");
        lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 14));
        lblNewLabel_1.setIcon(new ImageIcon(Admin.class.getResource("/resources/Manager-32.png")));
        lblNewLabel_1.setForeground(Color.WHITE);
        lblNewLabel_1.setBounds(903, 1, 80, 78);
        panel.add(lblNewLabel_1);

        //Search bar
        final JTextField searchbar = new JTextField();
        searchbar.setBounds(55, 70, 167, 20);
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

                String[] strName = SQLSearch.searchRestName(userSearch);
                String[] strAddress = SQLSearch.searchRestPhone(userSearch);
                String[] strTel = SQLSearch.searchRestAddress(userSearch);
                String[] strWebsite = SQLSearch.searchRestWebsite(userSearch);
                if (strName[0].equals("wrong")) {
                    JOptionPane.showMessageDialog(frame, "No rerstaurant with this name.");
                } else {
                    for (int i = 0; i < strName.length; i++) {
                        getResultPanel(strName[i], strAddress[i], strTel[i], strWebsite[i]);
                    }
                }
                if (strName.length < 10) {
                    addBlocks();
                }
            }
        });
        searchbar.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                searchbar.setText("");
            }
        });

        //SEARCHBUTTON
        final JButton searchButton = new JButton("Search");
        searchButton.setBounds(225, 70, 75, 20);
        searchButton.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseEntered(MouseEvent e) {
                Cursor cur1 = new Cursor(Cursor.HAND_CURSOR);
                searchButton.setCursor(cur1);
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                internalFrame.removeAll();
                getInternalFrame();
                String userSearch = searchbar.getText();
                String[] strName = SQLSearch.searchRestName(userSearch);
                String[] strAddress = SQLSearch.searchRestPhone(userSearch);
                String[] strTel = SQLSearch.searchRestAddress(userSearch);
                String[] strWebsite = SQLSearch.searchRestWebsite(userSearch);
                if (strName[0].equals("wrong")) {
                    JOptionPane.showMessageDialog(frame, "No rerstaurant with this name.");
                } else {
                    for (int i = 0; i < strName.length; i++) {
                        getResultPanel(strName[i], strAddress[i], strTel[i], strWebsite[i]);
                    }
                }

            }
        });
        panel.add(searchButton);

        //Get all User that are register 
        String[] userOwners = SQLOwners.displayUsers();

        //Scroll for user to display
        JScrollPane scrollPane_1 = new JScrollPane();
        scrollPane_1.setBounds(55, 110, 246, 115);
        panel.add(scrollPane_1);
        
        //Display restaurant that owner own when owner is click
        final JList list_1 = new JList(userOwners);
        list_1.addMouseListener(new MouseAdapter() {
        	public void mouseClicked(MouseEvent e) {
                    internalFrame.removeAll();
                    getInternalFrame();
                    String[][] rest = SQLFilter.SelectOwnerRestaurants(list_1.getSelectedValue().toString());
                     for (int i = 0; i < rest.length; i++) {
                        getResultPanel(rest[i][0], rest[i][1], rest[i][2], rest[i][3]);
                    }

                
                if (rest.length < 10) {
                    addBlocks();
                }
            }
        });
        scrollPane_1.setViewportView(list_1);

        JScrollBar scrollBar = new JScrollBar();
        scrollPane_1.setRowHeaderView(scrollBar);

        //Scroll bar for restaurant display
        scrollPane = new JScrollPane();
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setBounds(310, 67, 560, 490);
        panel.add(scrollPane);

        //Display for panel include restaurant
        getInternalFrame();

        //Background Picture
        JLabel backgroundlayout = new JLabel("");
        backgroundlayout.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }
        });
        backgroundlayout.setIcon(new ImageIcon(getClass().getResource("/resources/BackgroundFinal.png")));
        backgroundlayout.setBounds(0, 1, 1032, 600);
        panel.add(backgroundlayout);

        JTextPane txtpnRefresh = new JTextPane();
        txtpnRefresh.setFont(new Font("Arial", Font.PLAIN, 14));
        txtpnRefresh.setForeground(Color.WHITE);
        txtpnRefresh.setEditable(false);
        txtpnRefresh.setText("Refresh");
        txtpnRefresh.setBounds(918, 565, 66, 20);
        txtpnRefresh.setOpaque(false);
        panel.add(txtpnRefresh);

       name = selectRestName(FirstFrame.username, FirstFrame.password);
        tel = selectRestTel(FirstFrame.username, FirstFrame.password);
        address = selectRestAddress(FirstFrame.username, FirstFrame.password);
        weblink = selectRestWebsite(FirstFrame.username, FirstFrame.password);

        for (int i = 0; i < name.length; i++) {
            getResultPanel(name[i], tel[i], address[i], weblink[i]);
        }
        if (name.length < 10) {
            addBlocks();
        }

    }

    public static void getInternalFrame() {
        internalFrame = new JInternalFrame("Please login to leave a feedback");
        internalFrame.getContentPane().setLayout(new BoxLayout(internalFrame.getContentPane(), BoxLayout.Y_AXIS));
        internalFrame.setEnabled(false);
        internalFrame.setSize(200, 10000);
        scrollPane.setViewportView(internalFrame);
        internalFrame.setVisible(true);
        internalFrame.setBorder(UIManager.getBorder("ScrollPane.border"));
    }

    private static void getResultPanel(String name, String tel, String address, String weblink) {
        final ResultPanel resultPanel = new ResultPanel(name, address, tel, weblink);
        resultPanel.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseEntered(MouseEvent e) {
                Cursor cur1 = new Cursor(Cursor.HAND_CURSOR);
                resultPanel.setCursor(cur1);
            }

        });

        internalFrame.getContentPane().add(resultPanel);
    }

    public void addBlocks() {
        for (int i = 0; i < 8; i++) {
            Blocks blocks = new Blocks();
            internalFrame.getContentPane().add(blocks);
        }
    }

}
