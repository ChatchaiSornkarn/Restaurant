package Connection;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.awt.BorderLayout;

/**
 * Class: Chat support host server
 *
 * @author Obada
 */
public class Server extends JFrame {

    ObjectOutputStream output;
    ObjectInputStream input;
    ServerSocket server;
    Socket socket;
    JTextArea field;
    JTextField pane;
    JFrame frame;
    JScrollPane scroll;

    public Server() {
        scroll = new JScrollPane();
        pane = new JTextField();
        getContentPane().setLayout(new BorderLayout(0, 0));
        getContentPane().add(pane, BorderLayout.SOUTH);
        field = new JTextArea();
        field.setEditable(false);
        getContentPane().add(scroll, BorderLayout.CENTER);
        scroll.setViewportView(field);
        pane.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                sendMsg(event.getActionCommand());
                pane.setText("");

            }
        });
        setVisible(true);
        setSize(300, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    /**
     * while connecting
     */
    public void connect() {
        try {
            server = new ServerSocket(6789);
            pane.setEnabled(false);
            writeMsg("waiting for connection");
            socket = server.accept();//wait for connection
            writeMsg("Connected\n\r");
            pane.setEnabled(true);
        } catch (IOException e) {
            System.out.println("Couldn't connect");
            e.printStackTrace();
        }

    }

    public void setUpStreams() {
        try {
            output = new ObjectOutputStream(socket.getOutputStream());
            input = new ObjectInputStream(socket.getInputStream());
            writeMsg("Streams are up");
        } catch (IOException e) {
            System.out.println("Couldn't set up the streams");
            e.printStackTrace();
        }
    }

    /**
     * while chatting
     * @param msg 
     */
    public void whileChatting() {
        String income;
        income = "";
        do {
            try {
                income = (String) input.readObject();
                writeMsg(income);
            } catch (ClassNotFoundException | IOException e) {
                System.out.println("Couldn't read the msg");
                e.printStackTrace();
                break;
            }
        } while (!income.equals("Anonymous - End"));//closing cases
    }

    /**
     * while chat
     * @param msg 
     */
    public void writeMsg(String msg) {
        field.append(msg + "\n\r");
    }

    /**
     * send msgs to server
     * @param message
     */
    public void sendMsg(String msg) {
        try {
            output.writeObject("SERVER _ " + msg);
            writeMsg(msg);
            output.flush();
        } catch (IOException e) {
            System.out.println("Couldn't send the msg");
            e.printStackTrace();
        }
    }

    /**
     * Start the connecting server
     */
    public void run() {
        try {
            connect();
            setUpStreams();
            whileChatting();
        } finally {
            closeConn();
        }
    }

    /**
     * The streams and sockets are closed
     */
    public void closeConn() {
        try {
            writeMsg("Connection Closed");
            output.close();
            input.close();
            socket.close();
            server.close();
            pane.setEnabled(false);
        } catch (IOException e) {
            System.out.println("Couldn't close the connection");
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        Server server = new Server();
        server.run();
    }
}
