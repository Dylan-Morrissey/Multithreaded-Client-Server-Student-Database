import java.io.*;
import java.net.*;
import java.util.*;
import java.awt.*;
import javax.swing.*;

public class Server extends JFrame {
  // Text area for displaying contents
  private JTextArea jta = new JTextArea();
  private static JDBC jdbc = new JDBC();
  
  public static void main(String[] args) {
	jdbc.run();
    new Server();
  }

  public Server() {
    // Place text area on the frame
    setLayout(new BorderLayout());
    add(new JScrollPane(jta), BorderLayout.CENTER);

    setTitle("Server");
    setSize(500, 300);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setVisible(true); // It is necessary to show the frame here!

    try {
      // Create a server socket
      ServerSocket serverSocket = new ServerSocket(8000);
      jta.append("Server started at " + new Date() + '\n');

      // Listen for a connection request
      Socket socket = serverSocket.accept();

      // Create data input and output streams
      DataInputStream inputFromClient = new DataInputStream(
        socket.getInputStream());
      DataOutputStream outputToClient = new DataOutputStream(
        socket.getOutputStream());
      
      while (true) {
    
          String username= "";
          int uid = inputFromClient.readInt();

          if (jdbc.findUserById(uid) != "") {
        	  outputToClient.writeBoolean(true);
        	  outputToClient.writeUTF(jdbc.findUserById(uid));
          }
        	  
          
          jta.append("UID received from client: " + uid + '\n');
      
        }

     
    } catch(IOException ex) {
      System.err.println(ex);
    }
  }
}