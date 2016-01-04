package Frame;

import ResultPanel.Blocks;
import ResultPanel.ResultPanel;
import DBConnection.Connector;
import SQL.SQLSearch;
import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ScrollPaneConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Cursor;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.font.TextAttribute;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JCheckBox;
import javax.swing.JFormattedTextField;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JSlider;
import javax.swing.UIManager;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import static Frame.FirstFrame.password;
import static Frame.FirstFrame.username;

/**
 * This class is the frame for client which is the core program
 */

public class MainFrame {

    public JFrame frame;
    public static JInternalFrame internalFrame;
    public static String[] name, tel, address, weblink;
    public static JScrollPane scrollPane;
    private static int slStudentDis;
    Connector connect = new Connector();
    public JPasswordField PassLogin;
    public JFormattedTextField UserLogin;
    protected Login login = new Login();
    private JFormattedTextField budget2;

    /**
     * Create the application.
     * @throws java.sql.SQLException
     */
    public MainFrame() throws SQLException {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() throws SQLException {
        // created frame
        frame = new JFrame();
        frame.setBounds(100, 100, 450, 300);
        frame.setSize(1002, 600);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.X_AXIS));
        frame.setResizable(false);
        frame.setUndecorated(true);

        //Panel on the frame
        JPanel panel = new JPanel();
        frame.getContentPane().add(panel);
        panel.setLayout(null);
        panel.setBackground(java.awt.Color.DARK_GRAY);

        //Exit button
        final JLabel ExitTool = new JLabel("");
        ExitTool.setBounds(967, 1, 40, 40);
        ExitTool.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                frame.dispose();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                Cursor cur1 = new Cursor(Cursor.HAND_CURSOR);
                ExitTool.setCursor(cur1);
                ExitTool.setIcon(new ImageIcon(getClass().getResource("/resources/closeButtonHover.png")));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                ExitTool.setIcon(new ImageIcon(getClass().getResource("/resources/closeButton.png")));
            }
        });
        ExitTool.setIcon(new ImageIcon(getClass().getResource("/resources/closeButton.png")));
        panel.add(ExitTool);

        //Login textfield on top
        UserLogin = new JFormattedTextField();
        UserLogin.setText(" Username/Email");
        UserLogin.setBackground(Color.BLACK);
        UserLogin.setForeground(Color.WHITE);
        UserLogin.setBounds(10, 6, 122, 20);
        UserLogin.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                UserLogin.setText("");
            }
        });
        panel.add(UserLogin);

        //Login textfield on top
        PassLogin = new JPasswordField();
        PassLogin.setBounds(142, 6, 128, 20);
        PassLogin.setBackground(Color.BLACK);
        PassLogin.setForeground(Color.WHITE);
        PassLogin.setText("jjjjjjjjjjjj");
        PassLogin.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                PassLogin.setText("");
            }
        });
        panel.add(PassLogin);
        
        //Login enter button on top
        final JButton EnterLogin = new JButton("Enter");
        EnterLogin.setBounds(280, 4, 89, 23);
        panel.add(EnterLogin);
        EnterLogin.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseEntered(MouseEvent e) {
                Cursor cur1 = new Cursor(Cursor.HAND_CURSOR);
                EnterLogin.setCursor(cur1);
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                username = UserLogin.getText().trim();
                password = PassLogin.getText().trim();
                login.AdminLogin(username, password, frame);
                if (login.adminLogin == true) {
                    Admin adminFrame = null;
                    try {
                        adminFrame = new Admin();
                    } catch (SQLException ex) {
                        Logger.getLogger(FirstFrame.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    adminFrame.frame.setVisible(true);

                } else if (login.ownAdmin == true) {
                    AdminFrame adminFrame = null;
                    try {
                        adminFrame = new AdminFrame();
                    } catch (SQLException ex) {
                        Logger.getLogger(FirstFrame.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    adminFrame.frame.setVisible(true);
                }
            }

        });

        //REGISTER FUNCTIONALITY
        final JLabel register = new JLabel();
        register.setText("Click here to register for free");
        register.setBounds(378, 5, 200, 20);
        register.setFont(new Font("Arial", Font.PLAIN, 13));
        register.setForeground(java.awt.Color.WHITE);
        Font font = register.getFont();
        Map attributes = font.getAttributes();
        attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
        register.setFont(font.deriveFont(attributes));
        register.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                Cursor cur1 = new Cursor(Cursor.HAND_CURSOR);
                register.setCursor(cur1);
                register.setForeground(java.awt.Color.CYAN);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                register.setForeground(java.awt.Color.WHITE);
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
        
        //Search
        final JTextField searchbar = new JTextField();
        searchbar.setBounds(53, 70, 178, 20);
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
                ImageIcon[] strIma = SQLSearch.searchRestImage(userSearch);

                if (strName[0].equals("wrong")) {
                    JOptionPane.showMessageDialog(frame, "No restaurant with this name.");
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
        searchButton.setBounds(235, 70, 90, 20);
        searchButton.setBorderPainted(false);
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
                ImageIcon[] strIma = SQLSearch.searchRestImage(userSearch);

                if (strName[0] == "wrong") {
                    //getResultPanel("No restaurant with this name", strTel, strAddress);
                } else {

                    for (int i = 0; i < strName.length - 1; i++) {
                        getResultPanel(strName[i], strAddress[i], strTel[i], strWebsite[i]);
                    }

                }
            }
        });
        panel.add(searchButton);

        //Text label: SELECT THE CUISINE, FOOD & Environment!!!
        JTextPane txtpnPleaseSelectThe = new JTextPane();
        txtpnPleaseSelectThe.setEditable(false);
        txtpnPleaseSelectThe.setOpaque(false);
        txtpnPleaseSelectThe.setFont(new Font("Arial", Font.BOLD, 13));
        txtpnPleaseSelectThe.setText("Select the Cuisine:");
        txtpnPleaseSelectThe.setForeground(java.awt.Color.white);
        txtpnPleaseSelectThe.setBounds(50, 107, 318, 20);
        panel.add(txtpnPleaseSelectThe);

        //Looking for cuisine function
        String[] allcuisine = connect.makeList("select Cuisine from Cuisine_Types");
        String[] allcuisine1 = new String[allcuisine.length + 1];
        allcuisine1[0] = "All";
        for (int i = 1; i <= allcuisine.length; i++) {
            allcuisine1[i] = allcuisine[i - 1];
        }
        final JComboBox cuisinecomboBox = new JComboBox(allcuisine1);
        cuisinecomboBox.setBounds(53, 135, 178, 20);
        cuisinecomboBox.setFont(new Font("Arial", Font.PLAIN, 13));
        cuisinecomboBox.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                Cursor cur1 = new Cursor(Cursor.HAND_CURSOR);
                cuisinecomboBox.setCursor(cur1);
            }
        });
        panel.add(cuisinecomboBox);
        
        JTextPane selectenvironment = new JTextPane();
        selectenvironment.setEditable(false);
        selectenvironment.setOpaque(false);
        selectenvironment.setFont(new Font("Arial", Font.BOLD, 13));
        selectenvironment.setText("Select the Environment:");
        selectenvironment.setForeground(java.awt.Color.white);
        selectenvironment.setBounds(50, 220, 318, 20);
        panel.add(selectenvironment);
        
        String[] allsetting = connect.makeList("select Setting from Setting");
        String[] allsetting1 = new String[allsetting.length + 1];
        allsetting1[0] = "All";
        for (int i = 1; i <= allsetting.length; i++) {
            allsetting1[i] = allsetting[i - 1];
        }
        final JComboBox settingcomboBox = new JComboBox(allsetting1);
        settingcomboBox.setBounds(53,250, 178, 20);
        settingcomboBox.setFont(new Font("Arial", Font.PLAIN, 13));
        settingcomboBox.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                Cursor cur1 = new Cursor(Cursor.HAND_CURSOR);
                settingcomboBox.setCursor(cur1);
            }
        });
        panel.add(settingcomboBox);

        //Looking for type of food function
        String[] allfood = connect.makeList("select Food from Food_Type");
        String[] allfood1 = new String[allfood.length + 1];
        allfood1[0] = "All";
        for (int i = 1; i <= allfood.length; i++) {
            allfood1[i] = allfood[i - 1];
        }
        
        JTextPane selectdiettype = new JTextPane();
        selectdiettype.setEditable(false);
        selectdiettype.setOpaque(false);
        selectdiettype.setFont(new Font("Arial", Font.BOLD, 13));
        selectdiettype.setText("Select the Diet Type:");
        selectdiettype.setForeground(java.awt.Color.white);
        selectdiettype.setBounds(50, 165, 318, 20);
        panel.add(selectdiettype);

        final JComboBox foodcomboBox = new JComboBox(allfood1);
        foodcomboBox.setBounds(53, 190, 178, 20);
        foodcomboBox.setFont(new Font("Arial", Font.PLAIN, 13));
        foodcomboBox.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                Cursor cur1 = new Cursor(Cursor.HAND_CURSOR);
                foodcomboBox.setCursor(cur1);
            }
        });
        panel.add(foodcomboBox);

        //Looking for budget function
        JTextPane txtpnSelectBudget = new JTextPane();
        txtpnSelectBudget.setOpaque(false);
        txtpnSelectBudget.setEditable(false);
        txtpnSelectBudget.setFont(new Font("Arial", Font.BOLD, 13));
        txtpnSelectBudget.setText("Select budget");
        txtpnSelectBudget.setForeground(java.awt.Color.white);
        txtpnSelectBudget.setBounds(50, 290, 197, 25);
        panel.add(txtpnSelectBudget);
        final JSlider slider = new JSlider(30, 100);
        slider.setOpaque(false);
        slider.setBackground(Color.DARK_GRAY);
        budget2 = new JFormattedTextField(NumberFormat.getNumberInstance());
        budget2.setText("65");
        budget2.setFont(new Font("Arial", Font.BOLD,13));
        budget2.setBorder(null);
        budget2.setForeground(Color.WHITE);
        budget2.setOpaque(false);
        budget2.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                String asd = budget2.getText();
                slider.setValue(30);
                if (!asd.matches("\\d+")) {
                    return;
                }
                int v = Integer.parseInt(asd);
                slider.setValue(v);
            }
        });
        budget2.setBounds(208, 312, 30, 22);
        panel.add(budget2);
        budget2.setColumns(10);
        slider.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                budget2.setText(String.valueOf(slider.getValue()));
            }
        });
        slider.setBounds(47, 315, 155, 26);
        slider.setPaintTicks(true);
        panel.add(slider);

        //STUDENT DISCOUNT CHECKBOX Function
        final JCheckBox sudentDis = new JCheckBox("Student discount");
        sudentDis.setBounds(50, 340, 159, 23);
        sudentDis.setOpaque(false);
        sudentDis.setFont(new Font("Arial", Font.PLAIN, 13));
        sudentDis.setForeground(java.awt.Color.WHITE);
        sudentDis.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                Cursor cur1 = new Cursor(Cursor.HAND_CURSOR);
                sudentDis.setCursor(cur1);
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                if (sudentDis.isSelected()) {
                    slStudentDis = 1;
                } else {
                    slStudentDis = 0;
                }
            }
        });
        panel.add(sudentDis);
        
        // Select button to confirm the filtering
        final JButton selectbutton = new JButton("Select");
        selectbutton.setBounds(53, 390, 272, 29);
        selectbutton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                Cursor cur1 = new Cursor(Cursor.HAND_CURSOR);
                selectbutton.setCursor(cur1);
            }

            @Override
            public void mousePressed(MouseEvent e) {
                //Selected cuisine in combobox placed in variable cuisine
                String[] setting = connect.setting(settingcomboBox.getSelectedItem().toString());
                String[] cuisine = connect.cuisine(cuisinecomboBox.getSelectedItem().toString());
                String discount = connect.sd(slStudentDis);
                String[] food = connect.food(foodcomboBox.getSelectedItem().toString());
                int bu = Integer.parseInt(String.valueOf(slider.getValue()));
                internalFrame.removeAll();
                getInternalFrame();

                for (int i = 0; connect.restFilter(cuisine, bu, discount, food,setting).length > i; i++) {
                    getResultPanel(connect.restFilter(cuisine, bu, discount, food,setting)[i], connect.telFilter(cuisine, bu, discount, food,setting)[i],
                            connect.addFilter(cuisine, bu, discount, food,setting)[i], connect.webFilter(cuisine, bu, discount, food,setting)[i]);
                }
                if (connect.restFilter(cuisine, bu, discount, food, setting).length < 10) {
                    addBlocks();
                }
            }
        });
        panel.add(selectbutton);

        //Scroll bar for restaurant display
        scrollPane = new JScrollPane();
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setBounds(330, 67, 660, 510);
        panel.add(scrollPane);

        //Display for panel include restaurant
        getInternalFrame();

        //Background Picture
        JLabel backgroundlayout = new JLabel("");
        ImageIcon icona = new ImageIcon(getClass().getResource("/resources/BackgroundFinal.png"));
        Image image = icona.getImage();
        Image bild = image.getScaledInstance(1002, 608, java.awt.Image.SCALE_SMOOTH);
        icona = new ImageIcon(bild);
        backgroundlayout.setIcon(icona);
        backgroundlayout.setBounds(0, 0, 1002, 608);
        panel.add(backgroundlayout);
        
        //restaurant to be display on panel
        name = connect.makeList("select RestName from Restaurant ORDER BY RestName");
        tel = connect.makeList("select Telephone from Restaurant ORDER BY RestName");
        address = connect.makeList("select Address from Restaurant ORDER BY RestName");
        weblink = connect.makeList("Select Website from Restaurant ORDER BY RestName");
        for (int i = 0; i < name.length; i++) {
            getResultPanel(name[i], tel[i], address[i], weblink[i]);
        }
        
    }
    
    /**
     * Refleshing the whole panel
     */
    private static void getInternalFrame() {
        internalFrame = new JInternalFrame();
        internalFrame.getContentPane().setLayout(new BoxLayout(internalFrame.getContentPane(), BoxLayout.Y_AXIS));
        internalFrame.setEnabled(false);
        internalFrame.setSize(200, 10000);
        scrollPane.setViewportView(internalFrame);
        internalFrame.setVisible(true);
        internalFrame.setBorder(UIManager.getBorder("ScrollPane.border"));
    }

    /**
     * This Method get restaurant panel get the class ResultPanel
     * @param name
     * @param tel
     * @param address
     * @param weblink 
     */
    private static void getResultPanel(String name, String tel, String address, String weblink) {
        ResultPanel resultPanel = new ResultPanel(name, address, tel, weblink);

        resultPanel.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseEntered(MouseEvent e) {
                Cursor cur1 = new Cursor(Cursor.HAND_CURSOR);
                resultPanel.setCursor(cur1);
            }

        });
        internalFrame.getContentPane().add(resultPanel);
    }

    /**
     * Add the emty block. 
     * Fixing the bug.
     */
    public void addBlocks() {
        for (int i = 0; i < 8; i++) {
            Blocks blocks = new Blocks();
            internalFrame.getContentPane().add(blocks);
        }
    }
}
