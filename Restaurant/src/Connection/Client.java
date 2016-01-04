package Connection;

import java.io.*;
import java.net.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Class: Chat support for user
 *
 * @author Obada
 */
public class Client extends JFrame {

    private JTextField userText;
    private JTextArea chatWindow;
    private ObjectOutputStream output;
    private ObjectInputStream input;
    private String message = "";
    private String serverIP;//the person I'm talking to
    private Socket connection;
    private JPanel panel;
    private JButton end;

    /**
     * user chat support
     * @param host 
     */
    public Client(String host) {
        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        end = new JButton("End");
        end.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                try {
                    output.writeObject("Anonymous - End");
                    output.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        panel.add(end);
        setTitle("Tech Support");
        serverIP = host;
        userText = new JTextField();
        userText.setEditable(false);
        userText.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                sendMessage(event.getActionCommand());
                userText.setText("");
            }
        }
        );
        panel.add(userText);
        getContentPane().add(panel, BorderLayout.SOUTH);
        chatWindow = new JTextArea();
        chatWindow.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(chatWindow);
        getContentPane().add(scrollPane, BorderLayout.CENTER);

        JTextPane txtpnJustPressEnter = new JTextPane();
        txtpnJustPressEnter.setFont(new Font("Arial", Font.PLAIN, 12));
        txtpnJustPressEnter.setForeground(Color.WHITE);
        txtpnJustPressEnter.setBackground(Color.BLACK);
        txtpnJustPressEnter.setEnabled(false);
        txtpnJustPressEnter.setEditable(false);
        txtpnJustPressEnter.setText("Press enter to send a message!");
        scrollPane.setColumnHeaderView(txtpnJustPressEnter);
        setSize(460, 417);
        setVisible(true);
    }

    /**
     *  Start the connecting server
     */
    public void startRunning() {
        try {
            connectToServer();
            setStreams();
            whileChatting();
        } catch (EOFException eofException) {
            showMessage("\n Client closed the connection");
        } catch (IOException ioException) {
            ioException.printStackTrace();
        } finally {
            closeCrap();
        }
    }

    private void connectToServer() throws IOException {
        showMessage("Attempting connection....\n");
        end.setEnabled(false);
        connection = new Socket(InetAddress.getByName(serverIP), 6789);
        end.setEnabled(true);
        showMessage("Connected to: " + connection.getInetAddress().getHostName() + "\n");
    }

    private void setStreams() throws IOException {
        output = new ObjectOutputStream(connection.getOutputStream());
        output.flush();
        input = new ObjectInputStream(connection.getInputStream());

    }

    /**
     * while chatting with server
     */
    private void whileChatting() {
        buttonOn(true);
        ableToType(true);
        do {
            try {
                message = (String) input.readObject();
                showMessage("\n" + message);
            } catch (ClassNotFoundException | IOException classnotfoundException) {
                showMessage("\n I dont know what kinda object is that");
                break;
            }
        } while (!message.equals("SERVER _ End"));
    }

    /**
     * Close the streams and sockets
     */
    private void closeCrap() {
        showMessage("\n Quiting the chat");
        buttonOn(false);
        ableToType(false);
        try {
            output.close();
            input.close();
            connection.close();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    /**
     * send msgs to server
     *
     * @param message
     */
    private void sendMessage(String message) {
        try {
            output.writeObject("Anonymous - " + message);
            output.flush();
            showMessage("\nAnonymous - " + message);
        } catch (IOException ioException) {
            chatWindow.append("\n something messed up while sending msg");
        }
    }

    /**
     * Change and update the chatWindow
     *
     * @param m
     */
    private void showMessage(final String m) {
        SwingUtilities.invokeLater(
                new Runnable() {
                    public void run() {
                        chatWindow.append(m);
                    }
                }
        );
    }

    /**
     * gives user permission to type
     */
    private void ableToType(final boolean tof) {
        SwingUtilities.invokeLater(
                new Runnable() {
                    public void run() {
                        userText.setEditable(tof);
                    }
                }
        );
    }

    private void buttonOn(final boolean tof) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                end.setEnabled(tof);
            }
        });

    }

    public static void main(String[] args) {
        Client client = new Client("127.0.0.1");
        client.setDefaultCloseOperation(EXIT_ON_CLOSE);
        client.setVisible(true);
        client.startRunning();

    }

}
