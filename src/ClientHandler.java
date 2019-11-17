import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.JTextArea;

public class ClientHandler extends Thread {
	
	private Socket socket;
	private InetAddress address;
	private JTextArea serverTextArea;
	private DataInputStream inputFromClient;
	private DataOutputStream outputToClient;
	private JDBC jdbc;
	private ArrayList<Student> students;
	private StudentsReviewGUI window;
	public int clientNum = 0;
	
	public ClientHandler (Socket s, JTextArea jta, JDBC jdbc) throws IOException {	
		
		this.socket = s;
		this.jdbc = jdbc;
		this.students = jdbc.students;
		this.serverTextArea = jta;
		this.address = socket.getInetAddress();
        this.inputFromClient = new DataInputStream(socket.getInputStream());
        this.outputToClient = new DataOutputStream(socket.getOutputStream());   
	}
	
	public void run() {
		while (true) {
			int uid = 0;
			try {
				uid = inputFromClient.readInt();
				if (uid == -1) {
					serverTextArea.append("Logging Client-1 Out.");
					socket.close();
					break;
				}
				else if (jdbc.findUserById(uid) != "") {
			    	outputToClient.writeBoolean(true);
				  	outputToClient.writeUTF(jdbc.findUserById(uid));
				  	clientNum++;
				  	outputToClient.writeInt(clientNum);
				  	window = new StudentsReviewGUI(jdbc);
				  	window.frame.setBounds(500,120,400,450);
					window.frame.setVisible(true);
			    } else {
			    	outputToClient.writeBoolean(false);
				  	outputToClient.writeUTF(jdbc.findUserById(uid));
			    }
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}   
		}
		    
	}
}

