package ResultPanel;

import SQL.SQLReview;
import javax.swing.JTextPane;
import java.awt.SystemColor;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JSeparator;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import static SQL.SQLRestaurant.getIcon;
import javax.swing.JButton;
import javax.swing.text.StyledDocument;
import Frame.Admin;
import Frame.AdminFrame;
import Frame.FirstFrame;
import Frame.Login;
import Frame.MainFrame;
import static Frame.AdminFrame.internalFrame;
import static SQL.SQLRating.addRating;
import java.awt.Color;
import javax.swing.JOptionPane;

/**
 * Class: Display restaurant that has been click
 *
 * @author Obada 60%, Merchad 30%, Chatchai 10%
 */
public class SResultPanel extends ResultPanel {

    int selected;
    String comment;
    JTextField userComment;
    JLabel lblNewLabel;
    FirstFrame FF = new FirstFrame();
    Login login = new Login();

    /**
     * Create the panel.
     */
    public SResultPanel(ResultPanel resultpanel) {//to construct the result out of the original one
        super(resultpanel.name, resultpanel.address, resultpanel.tel, resultpanel.weblink);
        selected = 0;
        setLayout(null);
        create();
    }

    public void create() {
        final JLabel onestar = new JLabel("");
        onestar.setIcon(new ImageIcon(SResultPanel.class.getResource("/resources/1449436231_icon-23-star.png")));
        onestar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent arg0) {
                onestar.setIcon(new ImageIcon(SResultPanel.class.getResource("/resources/1449436226_icon-23-star.png")));//s
            }

            @Override
            public void mouseExited(MouseEvent arg0) {
                if (selected == 0) {
                    onestar.setIcon(new ImageIcon(SResultPanel.class.getResource("/resources/1449436231_icon-23-star.png")));
                }
            }

            @Override
            public void mouseClicked(MouseEvent arg0) {
                selected = 1;
                addRating(selected, name);
            }
        });

        onestar.setBounds(10, 88, 31, 26);
        add(onestar);

        final JLabel twostars = new JLabel("");
        twostars.setIcon(new ImageIcon(SResultPanel.class.getResource("/resources/1449436231_icon-23-star.png")));
        twostars.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent arg0) {
                twostars.setIcon(new ImageIcon(SResultPanel.class.getResource("/resources/1449436226_icon-23-star.png")));//s
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (selected < 2) {
                    twostars.setIcon(new ImageIcon(SResultPanel.class.getResource("/resources/1449436231_icon-23-star.png")));
                }
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                selected = 2;
                addRating(selected, name);
                onestar.setIcon(new ImageIcon(SResultPanel.class.getResource("/resources/1449436226_icon-23-star.png")));//s
            }
        });

        twostars.setBounds(41, 88, 31, 26);
        add(twostars);

        final JLabel threestars = new JLabel("");
        threestars.setIcon(new ImageIcon(SResultPanel.class.getResource("/resources/1449436231_icon-23-star.png")));
        threestars.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                threestars.setIcon(new ImageIcon(SResultPanel.class.getResource("/resources/1449436226_icon-23-star.png")));//s
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (selected < 3) {
                    threestars.setIcon(new ImageIcon(SResultPanel.class.getResource("/resources/1449436231_icon-23-star.png")));
                }
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                selected = 3;
                addRating(selected, name);
                onestar.setIcon(new ImageIcon(SResultPanel.class.getResource("/resources/1449436226_icon-23-star.png")));//s
                twostars.setIcon(new ImageIcon(SResultPanel.class.getResource("/resources/1449436226_icon-23-star.png")));//s
            }
        });

        threestars.setBounds(72, 88, 31, 26);
        add(threestars);

        final JLabel fourstars = new JLabel("");
        fourstars.setIcon(new ImageIcon(SResultPanel.class.getResource("/resources/1449436231_icon-23-star.png")));
        fourstars.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                fourstars.setIcon(new ImageIcon(SResultPanel.class.getResource("/resources/1449436226_icon-23-star.png")));//s
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (selected < 4) {
                    fourstars.setIcon(new ImageIcon(SResultPanel.class.getResource("/resources/1449436231_icon-23-star.png")));
                }
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                selected = 4;
                addRating(selected, name);
                onestar.setIcon(new ImageIcon(SResultPanel.class.getResource("/resources/1449436226_icon-23-star.png")));//s
                twostars.setIcon(new ImageIcon(SResultPanel.class.getResource("/resources/1449436226_icon-23-star.png")));//s
                threestars.setIcon(new ImageIcon(SResultPanel.class.getResource("/resources/1449436226_icon-23-star.png")));//s

            }
        });

        fourstars.setBounds(103, 88, 31, 26);
        add(fourstars);

        JButton commentButton = new JButton("Enter");
        commentButton.setBounds(300, 147, 69, 25);
        add(commentButton);
        commentButton.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                Review.insertReview(name, FirstFrame.username, userComment.getText());
                userComment.setText("");
                JOptionPane.showMessageDialog(null, "Comment Successful!");
                if (AdminFrame.inUse == true) {
                    AdminFrame.internalFrame.getContentPane().removeAll();
                    AdminFrame.scrollPane.setViewportView(AdminFrame.internalFrame);
                    for (int i = 0; i < AdminFrame.name.length; i++) {
                        ResultPanel res = new ResultPanel(AdminFrame.name[i], AdminFrame.address[i], AdminFrame.tel[i], AdminFrame.weblink[i]);
                        AdminFrame.internalFrame.getContentPane().add(res);
                    }
                    // Add invinsible block
                    if (AdminFrame.name.length < 10) {
                        for (int i = 0; i < 8; i++) {
                            Blocks blocks = new Blocks();
                            AdminFrame.internalFrame.getContentPane().add(blocks);
                        }
                    }
                } else if (Admin.inUse == true) {
                    Admin.internalFrame.getContentPane().removeAll();
                    Admin.scrollPane.setViewportView(Admin.internalFrame);
                    for (int i = 0; i < Admin.name.length; i++) {
                        ResultPanel res = new ResultPanel(Admin.name[i], Admin.address[i], Admin.tel[i], Admin.weblink[i]);
                        Admin.internalFrame.getContentPane().add(res);
                    }
                    //Add invinsible block
                    if (Admin.name.length < 10) {
                        for (int i = 0; i < 8; i++) {
                            Blocks blocks = new Blocks();
                            Admin.internalFrame.getContentPane().add(blocks);
                        }
                    }
                } else {
                    MainFrame.internalFrame.getContentPane().removeAll();
                    MainFrame.scrollPane.setViewportView(MainFrame.internalFrame);
                    for (int i = 0; i < MainFrame.name.length; i++) {
                        ResultPanel res = new ResultPanel(MainFrame.name[i], MainFrame.address[i], MainFrame.tel[i], MainFrame.weblink[i]);
                        MainFrame.internalFrame.getContentPane().add(res);
                    }
                    // Add invinsible block
                    if (MainFrame.name.length < 10) {
                        for (int i = 0; i < 8; i++) {
                            Blocks blocks = new Blocks();
                            MainFrame.internalFrame.getContentPane().add(blocks);
                        }
                    }
                }

            }
        });

        JTextPane textPane = new JTextPane();
        textPane.setBounds(10, 0, 62, 128);
        textPane.setText("Name:\r\nAddress:\r\nTel:\r\nWebsite:"); // 
        textPane.setFont(new Font("Arial", Font.PLAIN, 14));
        textPane.setEditable(false);
        textPane.setBackground(SystemColor.menu);
        add(textPane);

        JTextPane textPane_1 = new JTextPane();
        textPane_1.setBounds(62, 0, 286, 128);
        textPane_1.setText("     " + name + "\r\n     " + address + "\r\n     " + tel + "\n     " + weblink); // added 2 extra spaces to each line so ":" could fit
        textPane_1.setFont(new Font("Arial", Font.PLAIN, 14));
        textPane_1.setEditable(false);
        textPane_1.setBackground(SystemColor.menu);
        add(textPane_1);

        JSeparator separator = new JSeparator();
        separator.setBounds(0, 128, 642, 2);
        add(separator);

        JTextPane txtpnLeaveAComment = new JTextPane();
        txtpnLeaveAComment.setBackground(SystemColor.control);
        txtpnLeaveAComment.setEditable(false);
        txtpnLeaveAComment.setText("Leave a comment");
        txtpnLeaveAComment.setBounds(0, 139, 62, 34);
        add(txtpnLeaveAComment);

        //the comment will be here
        userComment = new JTextField(); 
        userComment.setBounds(62, 147, 240, 26);
        add(userComment);
        userComment.setColumns(10);
        String userCommentInput = userComment.getText();

        //small rest pic
        JLabel lblewLabel = new JLabel("");
        lblewLabel.setIcon(getIcon("Select image from Restaurant where RestName = \"" + name + "\""));
        lblewLabel.setBounds(375, 10, 600, 100);
        add(lblewLabel);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 182, 620, 300);
        add(scrollPane);

        JTextPane commentSection = new JTextPane();
        String displayComments = "";
        StyledDocument commentD = commentSection.getStyledDocument();
        commentSection.setEditable(false);
        String[] comment = SQLReview.displayReview(name);
        for (int i = 0; i < comment.length; i++) {
            displayComments += comment[i] + "\n";
            commentSection.setText(displayComments);
        }
        scrollPane.setViewportView(commentSection);

        lblNewLabel = new JLabel("");
        lblNewLabel.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                if (AdminFrame.inUse == true) {
                    AdminFrame.internalFrame.getContentPane().removeAll();
                    AdminFrame.scrollPane.setViewportView(AdminFrame.internalFrame);
                    for (int i = 0; i < AdminFrame.name.length; i++) {
                        ResultPanel res = new ResultPanel(AdminFrame.name[i], AdminFrame.address[i], AdminFrame.tel[i], AdminFrame.weblink[i]);
                        AdminFrame.internalFrame.getContentPane().add(res);
                    }
                    // Add invinsible block
                    if (AdminFrame.name.length < 10) {
                        for (int i = 0; i < 8; i++) {
                            Blocks blocks = new Blocks();
                            AdminFrame.internalFrame.getContentPane().add(blocks);
                        }
                    }
                } else if (Admin.inUse == true) {
                    Admin.internalFrame.getContentPane().removeAll();
                    Admin.scrollPane.setViewportView(Admin.internalFrame);
                    for (int i = 0; i < Admin.name.length; i++) {
                        ResultPanel res = new ResultPanel(Admin.name[i], Admin.address[i], Admin.tel[i], Admin.weblink[i]);
                        Admin.internalFrame.getContentPane().add(res);
                    }
                    //Add invinsible block
                    if (Admin.name.length < 10) {
                        for (int i = 0; i < 8; i++) {
                            Blocks blocks = new Blocks();
                            Admin.internalFrame.getContentPane().add(blocks);
                        }
                    }
                } else {
                    MainFrame.internalFrame.getContentPane().removeAll();
                    MainFrame.scrollPane.setViewportView(MainFrame.internalFrame);
                    for (int i = 0; i < MainFrame.name.length; i++) {
                        ResultPanel res = new ResultPanel(MainFrame.name[i], MainFrame.address[i], MainFrame.tel[i], MainFrame.weblink[i]);
                        MainFrame.internalFrame.getContentPane().add(res);
                    }
                    // Add invinsible block
                    if (MainFrame.name.length < 10) {
                        for (int i = 0; i < 8; i++) {
                            Blocks blocks = new Blocks();
                            MainFrame.internalFrame.getContentPane().add(blocks);
                        }
                    }
                }
            }
        });

        JLabel homelabel = new JLabel("Home");
        homelabel.setFont(new Font("Arial", Font.BOLD, 12));
        homelabel.setForeground(Color.BLACK);
        homelabel.setBounds(600, 35, 60, 20);
        add(homelabel);

        lblNewLabel.setIcon(new ImageIcon(SResultPanel.class.getResource("/resources/Home-32.png")));
        lblNewLabel.setBounds(600, 0, 45, 45);
        add(lblNewLabel);

    }

    /**
     * Add the emty block. Fixing the bug.
     */
    public void addBlocks() {
        for (int i = 0; i < 8; i++) {
            Blocks blocks = new Blocks();
            internalFrame.getContentPane().add(blocks);
        }
    }
}
