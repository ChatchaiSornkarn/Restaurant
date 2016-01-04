package Frame;

import ResultPanel.ResultPanel;
import java.awt.Color;
import java.awt.Cursor;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextPane;
import java.awt.SystemColor;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JOptionPane;
import static SQL.SQLDelete.*;

/**
 * Class: This is the Interface frame for deleting the restaurant
 * @author Obada 70%, Chiara 30%
 */
public class DeleteWindow extends JFrame {

    private JPanel contentPane;
    private JTextField textField;
    private JTextField textField_1;
    private JTextField textField_2;
    private JLabel lblNewLabel_1;
    private JLabel exitbutton;
    private JLabel deletebutton;
    private String name, tel, address;

    /**
     * Create the frame.
     */
    public DeleteWindow(ResultPanel res) {
        initialize();
        textField.setText(res.name);
        textField_1.setText(res.address);
        textField_2.setText(res.tel);
    }

    public DeleteWindow() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    public void initialize() {
        setUndecorated(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(240, 130, 250, 190);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setBackground(Color.DARK_GRAY);
        setContentPane(contentPane);
        contentPane.setLayout(null);

        //button delete restaurant on click
        deletebutton = new JLabel("Delete");
        deletebutton.setForeground(SystemColor.window);
        deletebutton.setFont(new Font("Arial", Font.PLAIN, 14));
        deletebutton.setIcon(new ImageIcon(DeleteWindow.class.getResource("/resources/Cancel-32.png")));
        deletebutton.setBounds(170, 150, 92, 44);
        deletebutton.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseEntered(MouseEvent e) {
                Cursor cur1 = new Cursor(Cursor.HAND_CURSOR);
                deletebutton.setCursor(cur1);
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                //add Restaurant
                deleteRestaurant(textField.getText(), textField_1.getText(), textField_2.getText());
                JOptionPane.showMessageDialog(null, "Delete Success!");
            }
        });
        contentPane.add(deletebutton);

        //exit window
        exitbutton = new JLabel("");
        exitbutton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent arg0) {
                exitbutton.setIcon(new ImageIcon(AddWindow.class.getResource("/resources/closeButtonHover.png")));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                exitbutton.setIcon(new ImageIcon(AddWindow.class.getResource("/resources/closeButton.png")));
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                setVisible(false);
                dispose();
            }
        });
        exitbutton.setIcon(new ImageIcon(AddWindow.class.getResource("/resources/closeButton.png")));
        exitbutton.setBounds(220, 0, 30, 30);
        contentPane.add(exitbutton);

        textField = new JTextField();
        textField.setBounds(10, 33, 209, 20);
        contentPane.add(textField);
        textField.setColumns(10);

        textField_1 = new JTextField();
        textField_1.setBounds(10, 75, 209, 20);
        contentPane.add(textField_1);
        textField_1.setColumns(10);

        textField_2 = new JTextField();
        textField_2.setBounds(10, 120, 209, 20);
        contentPane.add(textField_2);
        textField_2.setColumns(10);

        JTextPane txtpnNameAddress = new JTextPane();
        txtpnNameAddress.setForeground(SystemColor.inactiveCaptionBorder);
        txtpnNameAddress.setFont(new Font("Arial", Font.PLAIN, 12));
        txtpnNameAddress.setOpaque(false);
        txtpnNameAddress.setText("Name:\r\n\r\n\nAddress :\r\n\n\r\nTel:");
        txtpnNameAddress.setBackground(SystemColor.control);
        txtpnNameAddress.setEditable(false);
        txtpnNameAddress.setBounds(10, 11, 102, 239);
        contentPane.add(txtpnNameAddress);
    }

}
