package Frame;

import DBConnection.Connector;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import static DBConnection.DBConnection.conn;

/**
 * Class: Frame to register your self as a owner admin
 * @author Chiara
 */
public class Register extends Connector {

    public JFrame frame;
    private static String passwordInput;
    private static String usernameInput;

    public Register() throws SQLException {
        initialize();
    }

    private void initialize() throws SQLException {
        //create frame
        frame = new JFrame();
        frame.setBounds(400, 400, 500, 500);
        frame.setSize(500, 500);
        frame.setBackground(Color.YELLOW);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setResizable(false);
        frame.setUndecorated(false);
      //  frame.setBackground(new Color(0, 0, 0, 0));

        //Login textfield on top
        final JTextField username = new JTextField("Desired Username");
        username.setBounds(165, 192, 200, 20);
        username.setFont(new Font("Arial", Font.PLAIN, 16));
        username.setForeground(Color.lightGray);
        username.setOpaque(false);
        username.setBorder(null);
        username.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                username.setText("");

            }

        });
        frame.add(username);

        //Login textfield on top
        final JPasswordField password = new JPasswordField("Password");
        password.setBounds(165, 243, 200, 20);
        password.setOpaque(false);
        password.setBorder(null);
        password.setFont(new Font("Arial", Font.PLAIN, 16));
        password.setForeground(Color.lightGray);
        password.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                password.setText("");

            }
        });
        frame.add(password);

        //REGISTER FUNCTIONALITY
        JButton register = new JButton("Register");
        register.setSize(30, 5);
        register.setBounds(143, 340, 164, 25);
        register.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                boolean taken = false;
                usernameInput = username.getText().trim();
                passwordInput = password.getText().trim();
                String[] name = null;
                String sqlLoginVerify = "Select User from Login";
                try {
                    Statement stmt = conn.createStatement();
                    ResultSet rs = stmt.executeQuery(sqlLoginVerify);

                    int rowcount = 0;

                    if (rs.last()) {
                        rowcount = rs.getRow();
                        rs.beforeFirst();
                    }

                    name = new String[rowcount];
                    int i = 0;

                    while (rs.next()) {
                        name[i] = rs.getString("User");
                        i++;
                    }

                } catch (SQLException ex) {
                    Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
                }
                for (int i = 0; i < name.length; i++) {
                    if (usernameInput.equals(name[i])) {
                        JOptionPane.showMessageDialog(null, "Username already taken, please choose another username.");
                        taken = true;
                    }
                }
                if (usernameInput.equals(null) || passwordInput.equals(null) || usernameInput.equals("Desired Username") || passwordInput.equals("Password")) {
                    JOptionPane.showMessageDialog(null, "Please input something.");
                    taken = true;
                } else if (taken == false) {
                    System.out.println(usernameInput + passwordInput);
                    makeRevie("Login", usernameInput, passwordInput);
                    frame.dispose();
                    JOptionPane.showMessageDialog(null, "Register Successful");
                }

            }
        });
        frame.add(register);

        //Exit button
        final JLabel ExitTool = new JLabel("");
        ExitTool.setBounds(386, 228, 5, 2);
        ExitTool.setSize(40, 40);
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
        frame.add(ExitTool);

        JLabel registerbackground = new JLabel("");
        registerbackground.setBounds(0, 0, 315, 315);
        registerbackground.setSize(400, 400);
        registerbackground.setBackground(Color.DARK_GRAY);
        frame.add(registerbackground);

    }
}
