package network;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.BoxLayout;
import java.awt.BorderLayout;

public class Server{

	ObjectOutputStream output;
	ObjectInputStream input;
	ServerSocket server;
	Socket socket;
	JTextArea field;
	JTextField pane;
	JFrame frame;
	JScrollPane scroll;
	
	public Server (){
		 scroll = new JScrollPane();
		 frame = new JFrame("Server window");
	     pane = new JTextField();
	     frame.getContentPane().setLayout(new BorderLayout(0, 0));
	     frame.getContentPane().add(pane,BorderLayout.SOUTH);
	     field = new JTextArea();
	     field.setEditable(false);
	     frame.getContentPane().add(scroll, BorderLayout.CENTER);
	     scroll.setViewportView(field);
	     pane.addActionListener(new ActionListener(){
	    	 public void actionPerformed(ActionEvent event){ 
	    		if(event.getActionCommand().equals("file")){ 
	    		File file = new File("C:\\Users\\Obada\\Desktop\\cyber.txt");	
	    		sendObject(file);
	    		}else {
	    		sendMsg(event.getActionCommand());
	    		pane.setText("");
	    		}
	    	 }
	     });
	     frame.setVisible(true);
	     frame.setSize(300,300);
	}
	public void connect(){
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
		
	}public void setUpStreams(){
		try {
			output = new ObjectOutputStream(socket.getOutputStream());
			input = new ObjectInputStream(socket.getInputStream());
			writeMsg("Streams are up");
		} catch (IOException e) {
			System.out.println("Couldn't set up the streams");
			e.printStackTrace();
		}
	}public void whileChatting(){
		String income;
		income ="";
		do{
			try {
				income= (String) input.readObject();
				writeMsg(income);
			} catch (ClassNotFoundException | IOException e) {
				System.out.println("Couldn't read the msg");
				e.printStackTrace();
			}
		}while(!income.equals("Anonymous - End"));//closing cases
		closeConn();
	}public void writeMsg(String msg){
	         field.append(msg+"\n\r");
	}public void sendMsg(String msg){
		try {
			output.writeObject("Server _ "+msg);
			writeMsg(msg);
			output.flush();
		} catch (IOException e) {
			System.out.println("Couldn't send the msg");
			e.printStackTrace();
		}
	}public void run(){
		connect();
		setUpStreams();
		whileChatting();
	}
	public void closeConn(){
		try {
			writeMsg("Connection Lost");
			output.close();
			input.close();
			socket.close();
			server.close();
			pane.setEnabled(false);
		} catch (IOException e) {
			System.out.println("Couldn't close the connection");
			e.printStackTrace();
		}
		
	}public void sendObject(File file){
		try {
			output.writeObject(file);
			output.flush();
		} catch (IOException e) {
			System.out.println("Couldn't send the object");
			e.printStackTrace();
		}
	}
	
	public static void main(String [] args){
		Server server = new Server();
		server.run();
	}
}
